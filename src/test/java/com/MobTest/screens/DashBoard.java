package com.MobTest.screens;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.MobTest.core.ConfigData;
import com.MobTest.core.MobActions;
import com.MobTest.core.MobDriver;
import com.MobTest.core.TestReporter;

public class DashBoard extends MobActions{


	public DashBoard(){
		super(MobDriver.driver.get());
	}

	public void Get_Started() {
		TestReporter.subStep("Click On Get Started");
		tap(getDriver().findElementByXPath("//android.widget.Button[@text='GET STARTED']"));

	}
	public void Verify_DashBoard_LayOut() {
		WebDriverWait wait = new WebDriverWait(getDriver(), ConfigData.MAX_WAIT_TIME);
		WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//android.view.ViewGroup[@content-desc=\"Layouts\"]/android.widget.TextView")))); 
		String found = e.getText();
		if(found.contains("Layouts")) {
			TestReporter.subStep(" Found Layouts");
		}else
			Assert.fail("Expected: <Layouts> but Found <" + found+">");	
	}

	public void NavigateToReservations() {
		TestReporter.step("Navigate To Reservations");
		By by = By.xpath("//android.widget.TextView[@text='Reservations']");
		scrollTillElementVisible(by);
		tap(getDriver().findElement(by));
	}

	public void clickonMenu() {
		TestReporter.step("Click on Menu");
		tap(getDriver().findElementByXPath("//android.view.ViewGroup[@content-desc=\"ActionBar\"]/android.widget.ImageButton"));
	}

	public void clickonCodeSample() {
		TestReporter.step("Click on Code Sample");
		tap(getDriver().findElementByXPath("//android.widget.TextView[@text='Code samples']"));
		waitForSec(2);
		String str = getDriver().findElementById("com.android.chrome:id/url_bar").getText();
		if(str.contains("github.com/NativeScript/nativescript-sdk-examples-js"))
			TestReporter.subStep("github.com/NativeScript/nativescript-sdk-examples-js");
		else
			Assert.fail("Expected: <github.com/NativeScript/nativescript-sdk-examples-js>  but found:<"+str+">");
	}
	
}
