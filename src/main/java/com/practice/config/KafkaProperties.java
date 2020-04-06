package com.practice.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.practice.model.Topics;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

	private String bootstrapServers;
	private String group;
	private List<Topics> topics;
	private String startingOffsets;
	private String checkpointLocation;
	
	public String getBootstrapServers() {
		return bootstrapServers;
	}
	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public List<Topics> getTopics() {
		return topics;
	}
	public void setTopics(List<Topics> topics) {
		this.topics = topics;
	}
	public String getStartingOffsets() {
		return startingOffsets;
	}
	public void setStartingOffsets(String startingOffsets) {
		this.startingOffsets = startingOffsets;
	}
	public String getCheckpointLocation() {
		return checkpointLocation;
	}
	public void setCheckpointLocation(String checkpointLocation) {
		this.checkpointLocation = checkpointLocation;
	}
	
}
