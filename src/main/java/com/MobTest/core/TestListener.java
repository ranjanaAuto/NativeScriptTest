package com.MobTest.core;

import org.testng.*;


public class TestListener extends TestListenerAdapter {

	@Override
	/**
	 * Take screen shot on fail
	 */
	public void onTestFailure(ITestResult result) {
		String methodName = result.getName();
		if(result.getStatus() != 0){
			TestReporter.taskescreenshot(methodName);
		}
	}

	@Override
	/**
	 * Take screen shot on Pass
	 */
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getName();
		if(result.getStatus() != 0){
			TestReporter.taskescreenshot(methodName);
		}
	}


	
}
