package com.practice.services;

import javax.transaction.Transactional;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.config.KafkaProperties;
import com.practice.config.SparkProperties;
import com.practice.model.StockPojo;
import com.practice.model.StockProfilePojo;

@Service
public class SampleSparkJob {
	
	@Autowired
	SparkProperties spark;
	
	@Autowired
	KafkaProperties kafka;
	
	@Transactional
	public void startConsuming() throws InterruptedException, StreamingQueryException {
		System.setProperty("hadoop.home.dir", "D:\\winutils");
		SparkSession kafkaSession = SparkSession.builder().appName("Spark Sample Job").master("local[3]").getOrCreate();
		
		Dataset<Row> stockMessage = kafkaSession.readStream()
				.format("kafka")
				.option("kafka.bootstrap.servers", "localhost:9092")
				.option("subscribe", "stocks")
				.option("startingOffsets", "latest")
				.option("failOnDataLoss", "false")
				.load();

		Dataset<StockPojo> stocks = stockMessage.selectExpr("CAST(value AS STRING) as message")
				.select(functions.from_json(functions.col("message"), StockPojo.getStructType()).as("json")).select("json.*")
				.as(Encoders.bean(StockPojo.class));

		Dataset<Row> stockProfileMessage = kafkaSession.readStream()
				.format("kafka")
				.option("kafka.bootstrap.servers", "localhost:9092")
				.option("subscribe", "stocks_profiles")
				.option("startingOffsets", "latest")
				.option("failOnDataLoss", "false")
				.load();

		Dataset<StockProfilePojo> stockProfiles = stockProfileMessage.selectExpr("CAST(value AS STRING) as message")
				.select(functions.from_json(functions.col("message"), StockProfilePojo.getStructType()).as("json")).select("json.*")
				.as(Encoders.bean(StockProfilePojo.class));

		Dataset<Row> joinedDf = stocks.join(stockProfiles, stocks.col("company").equalTo(stockProfiles.col("company")))
				.drop(stockProfiles.col("company"))
				.toDF();

		StreamingQuery query = joinedDf
				.selectExpr("CAST(date AS STRING) AS key", "to_json(struct(*)) AS value")
				.writeStream()
				.outputMode("append")
				.format("kafka")
				.option("topic", "stocks_profiles_joined")
				.option("kafka.bootstrap.servers", "localhost:9092")
				.option("checkpointLocation", "./etl-from-json")
				.start();
		query.awaitTermination();

	}
}
