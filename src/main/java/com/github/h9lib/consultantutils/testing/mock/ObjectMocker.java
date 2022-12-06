package com.github.h9lib.consultantutils.testing.mock;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class ObjectMocker {
	
	/**
	 * This method can be used to create an object parameterized on the generic object T. The generated object
	 * is composed by randomic values for each properties.
	 * 
	 * @param <T>
	 * @param className the class of the object you need to mock
	 * @return a mocked object
	 */
	public static <T> T createSingleObject(Class<T> className) {
		PodamFactory podam = new PodamFactoryImpl();
		return podam.manufacturePojo(className);
	}
	
	/**
	 * This method can be used to create a list of objects parameterized on the generic type T. The generated objects
	 * in the list are composed by randomic values for each properties.
	 * 
	 * @param <T>
	 * @param className the class of the object you need to mock
	 * @param size the number of mocked elements in the returned list
	 * @return a list of mocked objects
	 */
	public static <T> List<T> createMultiObject(Class<T> className, int size) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < size; i++) {

			PodamFactory podam = new PodamFactoryImpl();
			result.add(podam.manufacturePojo(className));
		}
		return result;
	}

	/**
	 * To use this method you have to create a folder named "<b>mocks</b>" under the <b>src/main/resources</b>
	 * or <b>src/test/resources</b> folder of your project, depending on the application context. In this folder
	 * you have to add all the json files you need to mock the objects.
	 * To select a mock, you only have to pass to the method the name of the file, without path or extension.
	 * 
	 * @param <T>
	 * @param mockName a String that represents the name of the file in which you are mocking the object
	 * @param className the class of the object you need to mock
	 * @return the mocked object
	 * @throws IOException
	 */
	public static <T> T getMock(String mockName, Class<T> className) throws IOException {
		ClassLoader classLoader = className.getClassLoader();
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	
		
		File mockFile = new File(Objects.requireNonNull(classLoader.getResource("mocks/" + mockName + ".json")).getFile());
		String jsonMockedObject = FileUtils.readFileToString(mockFile, StandardCharsets.UTF_8);
		T mappedObject = objectMapper.readValue(jsonMockedObject, className);
		
		return mappedObject;
	}
	
	/**
	 * To use this method you have to create a folder named "<b>mocks</b>" under the <b>src/main/resources</b>
	 * or <b>src/test/resources</b> folder of your project, depending on the application context. In this folder
	 * you have to add all the json files you need to mock the objects.
	 * To select a mock, you only have to pass to the method the name of the file, without path or extension.
	 * This method allow you to specify a different path for the subfolders you want to insert under the root
	 * mocks/.
	 * 
	 * @param <T>
	 * @param mockName a String that represents the name of the file in which you are mocking the object
	 * @param subfolderPath a string that represents the subfolder path of root mock-files (mocks/)
	 * @param className the class of the object you need to mock
	 * @return the mocked object
	 * @throws IOException
	 */
	public static <T> T getMock(String mockName, String subfolderPath, Class<T> className) throws IOException {
		ClassLoader classLoader = className.getClassLoader();
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	

		File mockFile = new File(Objects.requireNonNull(classLoader.getResource("mocks/" + subfolderPath + "/" + mockName + ".json")).getFile());
		String jsonMockedObject = FileUtils.readFileToString(mockFile, StandardCharsets.UTF_8);
		T mappedObject = objectMapper.readValue(jsonMockedObject, className);
		
		return mappedObject;
	}
	
}

