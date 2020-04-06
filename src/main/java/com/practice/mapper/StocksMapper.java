package com.practice.mapper;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;

import com.practice.model.Stock;

public class StocksMapper implements MapFunction<Row, Stock>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Stock call(Row value) throws Exception {
		Stock s = new Stock();
		s.setDate(value.getAs("date"));
		s.setCompany(value.getAs("company"));
		s.setOpen(value.getAs("open"));
		s.setClose(value.getAs("close"));
		s.setHigh(value.getAs("high"));
		s.setLow(value.getAs("low"));
		s.setVolume(value.getAs("volume"));
		return s;
	}
	
	
}
