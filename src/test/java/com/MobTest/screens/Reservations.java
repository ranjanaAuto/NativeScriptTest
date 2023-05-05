package com.MobTest.screens;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.MobTest.core.MobActions;
import com.MobTest.core.MobDriver;
import com.MobTest.core.TestData;
import com.MobTest.core.TestReporter;
import com.MobTest.utilities.UtilDate;

public class Reservations extends MobActions{


	public Reservations(){
		super(MobDriver.driver.get());
	}

	public void verifyNewReservationWindow() {
		String found = getDriver().findElementByXPath("//android.view.ViewGroup[@content-desc=\"ActionBar\"]/android.widget.TextView").getText();
		if(found.contains("New reservation")) {
			TestReporter.step("New Reservation");
		}else {
			Assert.fail("Expected <New reservation> but Found: <"+found+">");
		}
	}
	public void clickOnNewReserVations() {
		TestReporter.subStep("Click On + (New reservation) icon");
		String str ="//android.view.ViewGroup[@content-desc='ActionBar']/following-sibling::android.widget.FrameLayout//android.view.ViewGroup/following-sibling::android.widget.ImageView";
		tap(getDriver().findElementByXPath(str));

	}

	public void enterName() {
		String currntTime = UtilDate.dateNow("hhmmss");
		TestData.insertValue("currntTime", currntTime);
		WebElement e = getDriver().findElementByXPath("//android.widget.EditText[@text='Name']");
		e.sendKeys("Test"+currntTime );
		TestReporter.subStep("Name: Test"+currntTime);
	}
	
	public void entePhoneNumber() {
		WebElement e = getDriver().findElementByXPath("//android.widget.EditText[@text='Phone']");
		String number= UtilDate.dateNow("ddMMhhmmss");
		e.sendKeys(number);
		TestReporter.subStep("Phone: "+number);
	}
	
	public void selectDate() {
		WebElement e = getDriver().findElementByXPath("//android.widget.TextView[@text='Date']/parent::android.widget.FrameLayout/following-sibling::android.widget.RelativeLayout//android.widget.EditText");
		String toDay= UtilDate.dateNow("EEE dd.MM");
		e.clear();
		e.sendKeys(toDay);
		TestReporter.subStep("Date: "+ toDay);
	}
	
	public void selectTime() {
		WebElement e = getDriver().findElementByXPath("//android.widget.TextView[@text='Time']/parent::android.widget.FrameLayout/following-sibling::android.widget.RelativeLayout//android.widget.EditText");
		String time= UtilDate.dateNow("hh:mm a");
		e.clear();
		e.sendKeys(time);
		TestReporter.subStep("Time: "+ time);
	}
	
	public void addGuest() {  //2
		WebElement e = getDriver().findElementById("org.nativescript.examples:id/number_picker_plus");
		e.click();
		String txt = getDriver().findElementById("org.nativescript.examples:id/number_picker_view").getText();
		TestReporter.subStep("Guest: "+ txt);
	}
	
	public void selectFloor() {  //3
		getDriver().findElementByXPath("//android.widget.TextView[@text='Section']/parent::android.widget.FrameLayout/following-sibling::android.widget.RelativeLayout//android.widget.TextView").click();
		waitForSec(1);
		String floor= TestData.getValue("Floor");;
		tap(getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='"+floor+"']"));
		TestReporter.subStep(floor);
	}
	
	public void selectTable() { // 4
		getDriver().findElementByXPath("//android.widget.TextView[@text='Table']/parent::android.widget.FrameLayout/following-sibling::android.widget.RelativeLayout//android.widget.TextView").click();
		waitForSec(1);
		String table= TestData.getValue("Table");
		tap(getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='"+table+"']"));
		TestReporter.subStep("Table: "+table);
	}
	
	public void selectOrigin() {  //2
		getDriver().findElementByXPath("//android.widget.TextView[@text='Origin']/parent::android.widget.FrameLayout/following-sibling::android.widget.RelativeLayout//android.widget.TextView").click();
		waitForSec(1);
		String origin= TestData.getValue("Origin");
		tap(getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='"+origin+"']"));
		TestReporter.subStep("Origin: "+origin);
	}
	
	public void cliOnDone() {
		TestReporter.subStep("Clik on Done");
		tap(getDriver().findElementByXPath("//android.widget.TextView[@text='DONE']"));
	}
	
	public void selectOldrequest() {
		TestReporter.step("Select old request and close");
		tap(getDriver().findElementByXPath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]"));
		waitForSec(1);
		if(getDriver().findElementByXPath("//android.widget.TextView[@text='Edit reservation']").equals(null))
			Assert.fail("Old Reservation not open");
		else
			TestReporter.subStep("Edit resevation");
		tap(getDriver().findElementByXPath("//android.view.ViewGroup[@content-desc=\"ActionBar\"]/android.widget.ImageButton"));
		
	}
	
	public void clickOnBackButton() {
	TestReporter.subStep("Click on Back Button");
	tap(getDriver().findElementByXPath("//android.view.ViewGroup[@content-desc=\"ActionBar\"]/android.widget.ImageButton"));
	
	}
}
