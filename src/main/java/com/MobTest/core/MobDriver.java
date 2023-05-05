package com.MobTest.core;

import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.MobTest.utilities.FileOperation;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MobDriver {

	public String devicePlatformName;
	public static ThreadLocal<AppiumDriver<?>> driver = new ThreadLocal<AppiumDriver<?>>();

	public void setDriver(){

		devicePlatformName = ConfigData.PLATFORM;
		switch (devicePlatformName.toUpperCase()){
		case "ANDROID":
			androidDriver();
			break;
		case "IOS":
			iosDriver();
			break;				
		default:			
			Assert.fail(devicePlatformName +"   --> No Such Driver available");	
			break;
		}
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	/**
	 * Initiate Android driver
	 */
	
	private void androidDriver() {

		MutableCapabilities capabilities = new DesiredCapabilities();
		loadCapabilities(capabilities);
		String HUB_URL ="";
		if(ConfigData.EXECUTION_ON.equalsIgnoreCase("local")) {
			System.out.println("  ******* Execution in Local Machine - ANDROID ******* ");
			loadCapabilities_LOCAL(capabilities);
			HUB_URL = loadAppiumConfig_LOCAL();	
		}else {
			Assert.fail("Configdata - > EXECUTION_ON : need to be Local");
		}

		try {
			AndroidDriver<?> androidDriver = new AndroidDriver<MobileElement>(new URL(HUB_URL), capabilities);
			driver.set(androidDriver);			
		} catch (SessionNotCreatedException e) {
			e.printStackTrace();
		}catch( Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Initiate iOS driver
	 */
	private void iosDriver() {

		Assert.fail("iOS Driver not configure yet" );

	}	

	@SuppressWarnings("unchecked")
	/**
	 * Load capabilities
	 * @param capabilities
	 */
	private void loadCapabilities(MutableCapabilities capabilities) {
		String testDataFile = "src/test/resources/config/"+ ConfigData.PLATFORM + ".config.json";
		JSONObject dataObject = FileOperation.readJsonFile(testDataFile);
		JSONObject JsonObject = (JSONObject) dataObject.get(ConfigData.CAPABITILITIES);


		Iterator<String> keys = JsonObject.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			String value = (String) JsonObject.get(key);
			capabilities.setCapability(key, value);;
		}
	}

	/**
	 * Get Driver
	 * @return
	 */
	public AppiumDriver<?> getDriver() {
		return driver.get();
	}

	/**
	 * Close app
	 */
	public void closeDriver() {
		AppiumDriver<?> driver = getDriver();
		try {	

			if(getDriver() != null ) {
				driver.closeApp();
				Thread.sleep(2000);
				driver.quit();
			}

		}catch ( Exception e ) {
			driver.quit();		
		}
		driver = null;
	}

	// ############## Local config ################

	@SuppressWarnings("unchecked")
	/**
	 * LOad Local Capabilities
	 * @param capabilities
	 */
	private void loadCapabilities_LOCAL(MutableCapabilities capabilities) {
		String testDataFile = "src/test/resources/config/local.config.json";
		JSONObject dataObject = FileOperation.readJsonFile(testDataFile);
		JSONObject JsonObject = (JSONObject) dataObject.get(ConfigData.PLATFORM.toLowerCase()+"_capabilities");


		Iterator<String> keys = JsonObject.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			String value = (String) JsonObject.get(key);
			capabilities.setCapability(key, value);;
		}
	}

	/**
	 * Load appium config
	 * @return
	 */
	private String loadAppiumConfig_LOCAL() {
		String testDataFile = "src/test/resources/config/local.config.json";
		JSONObject dataObject = FileOperation.readJsonFile(testDataFile);
		JSONObject JsonObject = (JSONObject) dataObject.get("appium_server");

		String HubIP = (String) JsonObject.get("HubIP");
		String Port = (String) JsonObject.get("Port");
		return "http://"+ HubIP + ":" + Port + "/wd/hub";
	}

}
