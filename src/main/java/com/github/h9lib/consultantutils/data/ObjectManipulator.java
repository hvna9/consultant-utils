package com.github.h9lib.consultantutils.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectManipulator {
	/**
	 * To create an Object from a Json String. Based on Jackson features.
	 * 
	 * @param <T>
	 * @param json
	 * @param className
	 * @return the Object mapped from the Json String.
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static <T> T convertToObject(String json, Class<T> className) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper.readValue(json, className);
	}
	
	/**
	 *  To create a Json String from an Object. Based on Jackson features.
	 *  
	 * @param <T>
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	public static <T> String convertToJson(T obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
