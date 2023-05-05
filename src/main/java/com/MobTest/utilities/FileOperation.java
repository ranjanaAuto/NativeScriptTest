package com.MobTest.utilities;

import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

public class FileOperation{

	
	/**
	 * Read Json file
	 * @param fileName
	 * @return
	 */
	public static JSONObject readJsonFile(String fileName) {
		
		JSONObject JsonObject = null;
		
		try {
			JSONParser jsonParser = new JSONParser();
			JsonObject = (JSONObject) jsonParser.parse(new FileReader(fileName));
		} 
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed parsing test data file : " + fileName);
		}
		return JsonObject;
	}
	
	public static Boolean fileExits(String fileName) {
		File file = new File(fileName);
		
		if(file.exists())
			return true;
		
		return false;
	}
	
}
