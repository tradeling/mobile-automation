package com.tradeling.mobile.buyerApp.tests;

import org.testng.annotations.Test;

import com.tradeling.data.buyerApp.Constants;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.mobileBuyerApp.HomeScreen;
import com.tradeling.mobile.pageObject.mobileBuyerApp.LoginScreen;

public class BuyerAppE2E extends EnvironmentSetup {

	@Test
	public void buyerApp_e2e() {
		LoginScreen loginScreen = new LoginScreen(actions.get());
		loginScreen.login_buyer(platform);
		
		HomeScreen homeScreen = new HomeScreen(actions.get());
		homeScreen.searchBox(Constants.Search_Keyword);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
