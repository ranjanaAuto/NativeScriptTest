package com.MobTest.testcases;

import org.testng.annotations.Test;

import com.MobTest.core.TestData;
import com.MobTest.core.TestReporter;
import com.MobTest.screens.DashBoard;
import com.MobTest.screens.Reservations;
import com.MobTest.setup.BaseTest;
import com.MobTest.setup.CommonFunctions;

public class MyTest extends BaseTest{

	@Test(groups = {"android_regression","android_smoke"})
	public void Assignment() {
		TestReporter.description(" Assignment Task: Online Table Reservation  ");
		TestData.setValue("scenario1", "User_Tinted");

		new DashBoard().Get_Started();
		new DashBoard().Verify_DashBoard_LayOut();

		new CommonFunctions().PushAppInBackGround();
		new CommonFunctions().ActivateApp();

		new DashBoard().NavigateToReservations();
		new Reservations().clickOnNewReserVations();
		new Reservations().verifyNewReservationWindow();
		new Reservations().enterName();
		new Reservations().entePhoneNumber();
		new Reservations().selectDate();
		new Reservations().selectTime();
		new Reservations().addGuest();
		new Reservations().selectFloor();
		new Reservations().selectTable();
		new Reservations().selectOrigin();
		new Reservations().cliOnDone();

		new Reservations().selectOldrequest();
		new Reservations().clickOnBackButton();

		new DashBoard().clickonMenu();
		new DashBoard().clickonCodeSample();
		
		new CommonFunctions().closeBroswer();
		new CommonFunctions().ActivateApp();
	}

}
