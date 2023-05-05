package com.MobTest.core;

import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.testng.Assert;

import com.MobTest.utilities.FileOperation;

public class TestData {

	public static ThreadLocal<HashMap<String,String>> dataMap = new ThreadLocal<HashMap<String,String>>();

	/**
	 * Reading testdata from Test Data file .
	 * @param fileName
	 * @param nodeName
	 */
	@SuppressWarnings("unchecked")
	public static void setValue(String fileName, String nodeName) {
		HashMap<String, String> map = dataMap.get();


		String testDataFile = "src/test/resources/testData/"+fileName+".json";
		JSONObject dataObject = FileOperation.readJsonFile(testDataFile);
		JSONObject JsonObject = (JSONObject) dataObject.get(nodeName);		
		if(JsonObject == null) {
			TestReporter.description("********  TEST DATA FOR: "+ nodeName +" , NOT FOUND. PLEASE CHECK YOUR INPUT  *******");
			Assert.fail();
		}else {
			if(map == null) {			
				map = (HashMap<String, String>) JsonObject.clone();
				dataMap.set(map);
			}else {

				Iterator<String> keys = JsonObject.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					String value = (String) JsonObject.get(key);
					dataMap.get().put(key, value);
				}
			}  
		}  
	}

	/**
	 * Get the value based on variable name
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return dataMap.get().get(key);
	}

	/**
	 * Clear all the testdata for the test case
	 */
	public static void clear() {
		dataMap.get().clear();
	}

	/**
	 * Put one value into the Test data
	 * @param key
	 * @param value
	 */
	public static void insertValue(String key, String value) {
		dataMap.get().put(key, value);
	}
}