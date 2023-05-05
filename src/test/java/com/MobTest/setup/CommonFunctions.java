package com.MobTest.setup;

import java.util.Set;

import com.MobTest.core.MobActions;
import com.MobTest.core.MobDriver;
import com.MobTest.core.TestReporter;

import io.appium.java_client.AppiumDriver;

public class CommonFunctions extends MobActions{

	public CommonFunctions(){
		super(MobDriver.driver.get());
	}


	public void ActivateApp() {
		TestReporter.subStep("Activate App");
		activateApp("org.nativescript.examples");
	}

	public void PushAppInBackGround() {
		TestReporter.step("Put application in the Background ");
		sendAppInBackGround(2);
	}

	public void closeBroswer(){
		TestReporter.subStep("Close broswer");
		AppiumDriver<?> driver = getDriver();
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(contextName);
			if (contextName.contains("WEBVIEW")) {
				driver.context(contextName);
				while (driver.getWindowHandles().size() > 1) {
                    driver.close();
                }
				driver.close();
				waitForSec(1);
				driver.context("NATIVE_APP");
			}
		}
	}



}
