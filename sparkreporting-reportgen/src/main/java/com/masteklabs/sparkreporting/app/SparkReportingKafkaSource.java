package com.masteklabs.sparkreporting.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
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

public final class SparkReportingKafkaSource {

	public static final String CURR_DAY_TABLE_NAME = "tweets";
	public static final String PREV_DAY_TABLE_NAME = "tweets_prev1";
	public static final String DAY_BEFORE_YEST_TABLE_NAME = "tweets_prev2";
	public static final String BOOTSTRAP_SERVERS = "localhost:9092";	
	public static final long MILLISECONDS_IN_DAY=60*60*24*1000;
	public static String topics;
	public static   Logger log = Logger.getLogger(SparkReportingKafkaSource.class.getName());
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
	static Timer timer = new Timer();
	static TimerTask exitTask = new TimerTask() {
		@Override
		public void run() {
			log.info("*********now exiting log****************");
			System.out.println("*********now exiting****************");
			
			System.exit(0);
		}
	};
	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			System.err.println("Usage: SparkReportingTool <topics> <queryTimeString seconds> <baseDirectoryName>"
					+ " <exit time after whihc batch gets killed in minutes>");
			System.exit(1);
		}
		log.info("*********starting log****************");
		topics = args[0];
		String queryTimeString = args[1];
		
		String baseDirectoryName=args[2];
		int minutesAfterBatchGetsKilled=Integer.parseInt(args[3]);
		if(minutesAfterBatchGetsKilled<0 && minutesAfterBatchGetsKilled > 1380){
			System.err.println("minutes can be between 0 and 1438 only");
			System.exit(1);
		}
		SimpleDateFormat sdf=new SimpleDateFormat("YY-MM-dd");
		String currDate=sdf.format(new Date());
		timer.schedule(exitTask, new Date(System.currentTimeMillis() + minutesAfterBatchGetsKilled*60 * 1000));// Exits
		System.out.println("current date is"+currDate);
		String prevDate=sdf.format(new Date(System.currentTimeMillis()-MILLISECONDS_IN_DAY));
		String dayBeforeYesDate=sdf.format(new Date(System.currentTimeMillis()-(MILLISECONDS_IN_DAY*2)));
		//String currDayFileName=baseDirectoryName+"/"+currDate+"/FlumeData-"+currDate+".txt";
		String prevDayFileName=baseDirectoryName+"/"+prevDate+"/FlumeData-"+prevDate+".txt";
		String dayBeforeYesFileName=baseDirectoryName+"/"+dayBeforeYesDate+"/FlumeData-"+dayBeforeYesDate+".txt";

		int queryTime = Integer.parseInt(queryTimeString);

		SparkSession spark = SparkSession.builder().appName("SparkReportingTool").getOrCreate();

		createAndCacheOldDayFilesAsTable(spark, prevDayFileName, PREV_DAY_TABLE_NAME);

		createAndCacheOldDayFilesAsTable(spark, dayBeforeYesFileName, DAY_BEFORE_YEST_TABLE_NAME);

		Dataset<Row> peoples = startCurrDayFileAsStreamingTable(spark);

		Thread currDayQuery = new Thread() {
			public void run() {
				StreamingQuery query = peoples.writeStream().outputMode("append")
						.trigger((ProcessingTime.create(queryTime, TimeUnit.SECONDS))).queryName(CURR_DAY_TABLE_NAME)
						.format("memory").option("truncate", "false").start();

				try {
					System.out.println("starting thread 2");
					new Thread(new JettyServer(spark)).start();
					query.awaitTermination();

				} catch (StreamingQueryException e) {
					e.printStackTrace();
				} finally {

				}
			}
		};
		currDayQuery.start();

		System.out.println("All threads started");

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

		Dataset<String> lines = spark.readStream().format("kafka").option("kafka.bootstrap.servers", BOOTSTRAP_SERVERS)
				.option("subscribe", topics).load().selectExpr("CAST(value AS STRING)").as(Encoders.STRING());

		Dataset<Row> tweets = lines.map((String s) -> ConvertorUtil.convertToTweet(s), Encoders.bean(Tweet.class))
				.toDF();

		return tweets;

	}

}
