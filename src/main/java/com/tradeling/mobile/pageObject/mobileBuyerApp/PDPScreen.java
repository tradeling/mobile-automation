package com.tradeling.mobile.pageObject.mobileBuyerApp;

import org.openqa.selenium.support.PageFactory;

import com.tradeling.mobile.driver.MobileActions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PDPScreen {

	MobileActions actions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	MobileElement btnAddToCart;

	@AndroidFindBy(xpath = "//android.widget.Button")
	MobileElement btnAddToCartPopup;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT']")
	MobileElement btnCheckout;
	



	public PDPScreen(MobileActions action) {
		this.actions = action;
		PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
	}

	public void btnAddToCartPDP() {
		actions.click(btnAddToCart);
	}

	public void addToCartBtnPopup() {
		actions.click(btnAddToCartPopup);
	}
	
	public void btnCheckoutPopup() {
		actions.click(btnCheckout);
	}
	
}
