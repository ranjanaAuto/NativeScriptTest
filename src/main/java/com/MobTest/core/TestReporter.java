package com.MobTest.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.MobTest.utilities.UtilDate;
import io.appium.java_client.AppiumDriver;


public class TestReporter {
	
	public static void log(String message) {
		Reporter.log(message);
	}

	public static void description(String message) {
		Reporter.log("<mark><b>" + message + "</b></mark> </br>");
	}

	public static void step(String message) {
		//    	Reporter.log("</br>");
		Reporter.log("<strong>" + message + "</strong>");
	}

	public static void subStep(String message) {
		Reporter.log("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &#9830; <i>" + message + "</i>");
	}
	
	public static void taskescreenshot() {
		String imageName = UtilDate.dateNow("yyyyMMddhhmmsss");
		taskescreenshot(imageName);
				
	}
		
	protected static void taskescreenshot(String methodName) {
		AppiumDriver<?> driver = MobDriver.driver.get();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String reportDirectory="";
		try {
				reportDirectory = new File(System.getProperty("user.dir"))+ "/target/surefire-reports/screenshots";
				File tempFile = new File(reportDirectory);
				if(!tempFile.exists())
					new File(reportDirectory).mkdirs();
				
			String filePath =  reportDirectory  +"/" + methodName + ".png";
			File destFile = new File(filePath);
			FileUtils.copyFile(scrFile, destFile);
			
			String relPath = "./screenshots/" + methodName + ".png";
			Reporter.log("</br><a href='"+ relPath+ "'> <img src='"+ relPath + "' height='300' width='180' /> </a></br>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}