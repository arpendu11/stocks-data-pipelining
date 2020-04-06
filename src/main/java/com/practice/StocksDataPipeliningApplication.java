package com.practice;

import org.apache.spark.sql.streaming.StreamingQueryException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.practice.services.SampleSparkJob;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
@ConfigurationPropertiesScan("com.practice.config")
public class StocksDataPipeliningApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocksDataPipeliningApplication.class, args);
		SampleSparkJob sJob = new SampleSparkJob();
		try {
			sJob.startConsuming();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (StreamingQueryException e) {
			e.printStackTrace();
		}
	}

}
