package com.practice.controller;

import org.apache.spark.sql.streaming.StreamingQueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.services.SampleSparkJob;

@RestController
@RequestMapping("/spark")
public class SparkJobController {
	
	@Autowired
	SampleSparkJob sampleSparkJob;

	@PostMapping("/start")
	public void startSparkJob() throws InterruptedException, StreamingQueryException {
		sampleSparkJob.startConsuming();
	}
}
