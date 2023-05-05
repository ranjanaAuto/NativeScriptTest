package com.MobTest.setup;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.MobTest.core.ConfigData;
import com.MobTest.core.MobActions;
import com.MobTest.core.MobDriver;
import com.MobTest.core.TestData;

import io.appium.java_client.AppiumDriver;

public class BaseTest {
	
	public static ThreadLocal<MobActions> actions = new ThreadLocal<MobActions>();

	
	public MobActions getAction() {
		return actions.get();
	}
	
	public AppiumDriver<?> getDriver() {
		return MobDriver.driver.get();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupDevice(Method method) {
		
		ConfigData.CAPABITILITIES = "Capabilities_Default";   

		for(int count=1; count<= 3 ; count++) {
			new MobDriver().setDriver();
			if(getDriver() != null)
				break;
			else {
				System.out.println("Driver null  ---------------   "+ count );
				getAction().waitForSec(3);
			}
		}
	}

	
	@AfterMethod(alwaysRun = true)	
	public void releaseDevice(ITestResult result) {	
		TestData.clear();
		new MobDriver().closeDriver();
	}
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		new ConfigData().loadTestConfig();
	}


	@AfterSuite(alwaysRun = true)
	public void afterSuite(ITestContext context) {

	}

}
