package com.practice.config;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDeserializer implements Deserializer<JsonNode> {
	@Override
	public void close() {
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
	}

	@Override
	public JsonNode deserialize(String arg0, byte[] arg1) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = mapper.readValue(arg1, JsonNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonNode;
	}
}
