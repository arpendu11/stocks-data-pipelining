package com.practice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.first.repositories.StocksMySQLRepository;
import com.practice.model.Stock;
import com.practice.model.StockProfile;
import com.practice.second.repositories.StocksPostgresRepository;

@Service
public class Consumer {
	
	@Autowired
	StocksMySQLRepository mysqlRepo;
	
	@Autowired
	StocksPostgresRepository postgresRepo;
	
	@KafkaListener(topics = "stocks_profiles_joined",
			groupId = "group_json",
			containerFactory = "kafkaListenerContainerFactory")
	public void consumeStockJson(String message) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(message);
		Stock stock = new Stock();
		stock.setDate(json.findValue("date").textValue());
		stock.setCompany(json.findValue("company").textValue());
		stock.setOpen(json.findValue("open").floatValue());
		stock.setClose(json.findValue("close").floatValue());
		stock.setHigh(json.findValue("high").floatValue());
		stock.setLow(json.findValue("low").floatValue());
		stock.setVolume(json.findValue("volume").intValue());
		StockProfile stockProfile = new StockProfile();
		stockProfile.setCompany(json.findValue("company").textValue());
		stockProfile.setProfession(json.findValue("profession").textValue());
		stockProfile.setSector(json.findValue("sector").textValue());
		stockProfile.setAddress(json.findValue("address").textValue());
		stockProfile.setRegistration(json.findValue("registration").textValue());
		stock.setStockProfile(stockProfile);
		postgresRepo.save(stock);
		mysqlRepo.save(stock);
		System.out.println("Consumed Stock Joined message: " + stock.toString());
	}
	
}
