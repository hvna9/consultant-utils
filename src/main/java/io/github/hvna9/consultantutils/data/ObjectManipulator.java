package io.github.hvna9.consultantutils.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectManipulator {
	/**
	 * To create an Object from a JSON String. Based on Jackson features.
	 * 
	 * @param <T>
	 * @param json the JSON as a String.
	 * @param className the *.class Class in which the JSON should be mapped.
	 * @return the Object mapped from the JSON String.
	 * @throws JsonMappingException 
	 * @throws JsonProcessingException 
	 */
	public static final <T> T convertToObject(String json, Class<T> className) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper.readValue(json, className);
	}
	
	/**
	 *  To create a Json String from an Object. Based on Jackson features.
	 *  
	 * @param <T>
	 * @param obj
	 * @return the string as Json format which maps the Object.
	 * @throws JsonProcessingException
	 */
	public static final <T> String convertToJson(T obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
