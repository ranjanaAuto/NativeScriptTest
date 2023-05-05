package com.MobTest.core;

import org.json.simple.JSONObject;

import com.MobTest.utilities.FileOperation;

public class ConfigData {
	
	public static Integer MAX_WAIT_TIME;
	public static String SCREENSHOT_LEVEL ;
	public static String PLATFORM ;
	public static String EXECUTION_ON ;
	public static String BUILDTYPE;
	public static String CAPABITILITIES;
	
	/**
	 * Load Config data
	 */
	public void loadTestConfig() {
		String testDataFile = "src/test/resources/config/test.config.json";
		JSONObject myDataObject = FileOperation.readJsonFile(testDataFile);
		JSONObject dataObject = (JSONObject) myDataObject.get("Default");

		MAX_WAIT_TIME = Integer.valueOf(String.valueOf(dataObject.get("MAX_WAIT_TIME"))) ;
		SCREENSHOT_LEVEL  = (String) dataObject.get("SCREENSHOT_LEVEL") ;
		EXECUTION_ON  = (String) dataObject.get("EXECUTION_ON") ;
		PLATFORM  = (String) dataObject.get("PLATFORM") ;
		BUILDTYPE =  (String) dataObject.get("BUILD_TYPE");
	}
}
