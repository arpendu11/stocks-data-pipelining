package com.practice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spark")
public class SparkProperties {
	
	private String appName;
	private String master;
	private String hadoopHomeDir;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getHadoopHomeDir() {
		return hadoopHomeDir;
	}
	public void setHadoopHomeDir(String hadoopHomeDir) {
		this.hadoopHomeDir = hadoopHomeDir;
	}

}
