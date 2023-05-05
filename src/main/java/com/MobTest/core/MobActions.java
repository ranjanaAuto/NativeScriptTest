package com.MobTest.core;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class MobActions {

	AppiumDriver<?> driver;

	public MobActions(AppiumDriver<?> driver) {
		this.driver = MobDriver.driver.get();
	}

	public AppiumDriver<?> getDriver() {
		return driver;
	}

	@SuppressWarnings("unchecked")
	public AndroidDriver<?> getAndriodDriver() {
		return (AndroidDriver<WebElement>)driver;
	}

	public void tap(WebElement element) {
		new TouchAction<>(driver)
		.tap(tapOptions().withElement(element(element)))
		.waitAction(waitOptions(ofMillis(250))).perform();
	}
	
	/**
	 * Wait second
	 * @param timeInSeconds
	 */
	public void waitForSec(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 	

	/**
	 * Scroll to the element until visible
	 * @param by
	 */
	public void scrollTillElementVisible(By by) {
		boolean elementFound = false;
		while (!elementFound) {
			try {
				WebElement element = driver.findElement(by);
				elementFound = element.isDisplayed();
			} catch (Exception e) {
				swipeUp();
			}
		}
	}
	
	/**
	 * Swip the scren up
	 */
	public void swipeUp() {
		Dimension size = driver.manage().window().getSize();

		int startX = size.width / 2;
		int startY = (int) (size.height * 0.8);
		int endX = startX;
		int endY = (int) (size.height * 0.2);

		TouchAction<?> touchAction = new TouchAction<>(driver);
		touchAction.press(point(startX, startY)).waitAction().moveTo(point(endX, endY)).release().perform();
	}

	/**
	 * Put App in background
	 * @param forSecond
	 */
	public void sendAppInBackGround(int forSecond) {
		driver.runAppInBackground(Duration.ofSeconds(forSecond));
	}
	
	/**
	 * Activate App
	 * @param packageName
	 */
	public void activateApp(String packageName) {
		driver.activateApp(packageName);
	}
}
