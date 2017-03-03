#!/bin/bash
# spark reporting app
/usr/local/spark2.1/bin/spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.1.0 --class com.masteklabs.sparkreporting.app.SparkReportingKafkaSource --master local[4] /data/masteklabs/FraudDetectionCode/sparkreporting-reportgen/target/sparkreporting-reportgen-0.0.1-SNAPSHOT-jar-with-dependencies.jar /data/masteklabs/temp/appconfig.properties