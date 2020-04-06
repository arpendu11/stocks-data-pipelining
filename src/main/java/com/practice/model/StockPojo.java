package com.practice.model;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class StockPojo {
	
	private String date;
	private String company;
	private Float open;
	private Float close;
	private Float high;
	private Float low;
	private Integer volume;
	private static StructType structType = DataTypes.createStructType(new StructField[] {
					DataTypes.createStructField("date", DataTypes.StringType, false),
					DataTypes.createStructField("company", DataTypes.StringType, false),
					DataTypes.createStructField("open", DataTypes.FloatType, false),
					DataTypes.createStructField("close", DataTypes.FloatType, false),
					DataTypes.createStructField("high", DataTypes.FloatType, false),
					DataTypes.createStructField("low", DataTypes.FloatType, false),
					DataTypes.createStructField("volume", DataTypes.IntegerType, false), });
	
	public StockPojo(String date, String company, Float open, Float close, Float high, Float low, Integer volume) {
		this.date = date;
		this.company = company;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.volume = volume;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Float getOpen() {
		return open;
	}
	public void setOpen(Float open) {
		this.open = open;
	}
	public Float getClose() {
		return close;
	}
	public void setClose(Float close) {
		this.close = close;
	}
	public Float getHigh() {
		return high;
	}
	public void setHigh(Float high) {
		this.high = high;
	}
	public Float getLow() {
		return low;
	}
	public void setLow(Float low) {
		this.low = low;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer integer) {
		this.volume = integer;
	}
	public static StructType getStructType() {
	    return structType;
	}

	@Override
	public String toString() {
		return "Stock [date=" + date + ", company=" + company + ", open=" + open + ", close=" + close
				+ ", high=" + high + ", low=" + low + ", volume=" + volume + "]";
	}
	
}
