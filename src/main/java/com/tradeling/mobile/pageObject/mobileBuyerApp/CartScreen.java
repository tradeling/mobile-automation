package com.tradeling.mobile.pageObject.mobileBuyerApp;

import org.openqa.selenium.support.PageFactory;

import com.tradeling.mobile.driver.MobileActions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartScreen {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT 1 PRODUCTS FROM THIS SELLER']")
	MobileElement btnCheckoutCartScreen;

	MobileActions actions;

	public CartScreen(MobileActions action) {
		this.actions = action;

		try {
			PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnCheckoutOnCart() {
		actions.click(btnCheckoutCartScreen);

	}
}
