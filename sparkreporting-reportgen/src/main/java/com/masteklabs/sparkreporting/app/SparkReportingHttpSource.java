package com.masteklabs.sparkreporting.app;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.ProcessingTime;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import com.masteklabs.sparkreporting.bean.Tweet;
import com.masteklabs.sparkreporting.server.JettyServer;
import com.masteklabs.sparkreporting.util.ConvertorUtil;

import scala.Tuple2;

public final class SparkReportingHttpSource {
	public static final  Logger log = Logger.getLogger(SparkReportingHttpSource.class.getName());
	public static final String CURR_DAY_TABLE_NAME = "tweets";
	public static final String PREV_DAY_TABLE_NAME = "tweets_prev1";
	public static final String DAY_BEFORE_YEST_TABLE_NAME = "tweets_prev2";
	public static String streamingSourceHost;
	public static int streamingSourcePort;
	static Function<String, Tweet> tweetExtractor = new Function<String, Tweet>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Tweet call(String line) throws Exception {
			return ConvertorUtil.convertToTweet(line);

		}
	};

	public static void main(String[] args) throws Exception {
		if (args.length < 5) {
			System.err.println("Usage: SparkReportingHttpSource <hostname> <port>"
					+ " <query execution time frequency in seconds> <prev day file path>");
			System.exit(1);
		}

		streamingSourceHost = args[0];
		streamingSourcePort = Integer.parseInt(args[1]);
		String queryTimeString = args[2];
		String prev1DayFilePath = args[3];
		String prev2DayFilePath = args[4];

		int queryTime = Integer.parseInt(queryTimeString);

		SparkSession spark = SparkSession.builder().appName("SparkReportingTool").getOrCreate();

		createAndCacheOldDayFilesAsTable(spark, prev1DayFilePath, PREV_DAY_TABLE_NAME);

		createAndCacheOldDayFilesAsTable(spark, prev2DayFilePath, DAY_BEFORE_YEST_TABLE_NAME);

		Dataset<Row> peoples = startCurrDayFileAsStreamingTable(spark);

		Thread currDayQuery = new Thread() {
			public void run() {
				StreamingQuery query = peoples.writeStream().outputMode("append")
						.trigger((ProcessingTime.create(queryTime, TimeUnit.SECONDS))).queryName(CURR_DAY_TABLE_NAME)
						.format("memory").option("truncate", "false").start();

				try {
					log.debug("starting thread 2");
					new Thread(new JettyServer(spark)).start();
					query.awaitTermination();

				} catch (StreamingQueryException e) {
					e.printStackTrace();
				} finally {

				}
			}
		};
		currDayQuery.start();

		log.debug("All threads started");

	}

	private static void createAndCacheOldDayFilesAsTable(SparkSession spark, String filePath, String tableName) {
		// create and cache table of day start
		JavaRDD<Tweet> tweetPrev1Rdd = spark.read().textFile(filePath).javaRDD().map(tweetExtractor);
		Dataset<Row> tweetPrev1DF = spark.createDataFrame(tweetPrev1Rdd, Tweet.class);
		tweetPrev1DF.createOrReplaceTempView(tableName);
		spark.sqlContext().cacheTable(tableName);
		spark.sql("select * from " + tableName).show();
		// create and cache table of day end

	}

	private static Dataset<Row> startCurrDayFileAsStreamingTable(SparkSession spark) {

		Dataset<Row> lines = spark.readStream().format("socket").option("host", streamingSourceHost)
				.option("port", streamingSourcePort).option("includeTimestamp", true).load();

		Dataset<Row> tweets = lines.as(Encoders.tuple(Encoders.STRING(), Encoders.TIMESTAMP()))
				.map((MapFunction<Tuple2<String, Timestamp>, Tweet>) t -> {
					return ConvertorUtil.convertToTweet(t._1);
				}, Encoders.bean(Tweet.class))

				.toDF();
		return tweets;

	}

}
