package com.tradeling.mobile.pageObject.mobileBuyerApp;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import com.tradeling.mobile.driver.MobileActions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutScreen {
	
	String city = "Dubai";
	String number = "0999999999";
	String text = "test";

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='collapsed'])[2]/android.view.ViewGroup/android.view.ViewGroup")
	MobileElement btnBankTransfer;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PROCEED TO REVIEW']")
	MobileElement btnProceedToReview;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PURCHASE']")
	MobileElement btnPurchase;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Thank you, your order has been placed']")
	MobileElement txtOrderPlaced;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Ship to ']")
	MobileElement btnShipToCollapsed;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD ADDRESS']")
	MobileElement btnAddAddress;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='City']")
	MobileElement drpdwnCity;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	MobileElement inputTextField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Dubai']")
	MobileElement selectCity;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Business Address']")
	MobileElement txtBusinessAddress;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Business Address']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.EditText")
	MobileElement inputBusinessAddress;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='State']")
	MobileElement txtState;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='State']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.EditText")
	MobileElement inputState;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='P.O. box']")
	MobileElement txtPoBox;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='P.O. box']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.EditText")
	MobileElement inputPoBox;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Address label']")
	MobileElement txtAddressLabel;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Address label']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.EditText")
	MobileElement inputAddressLabel;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone number']")
	MobileElement txtPhoneNumber;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone number']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.EditText")
	MobileElement inputPhoneNumber;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Use this address as my billing address']")
	MobileElement checkboxBilling;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='DONE']")
	MobileElement btnDone;


	
	MobileActions actions;

	public CheckoutScreen(MobileActions action) {
		this.actions = action;
		PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
	}

	public void btnBankTransferCheckout() {
		actions.click(btnBankTransfer);
	}

	public void btnProceedToReviewCheckout() {
		actions.click(btnProceedToReview);
	}

	public void btnPurchaseCheckout() {
		actions.click(btnPurchase);
	}

	public String txtOrderPlaced() {
		return actions.getText(txtOrderPlaced);

	}
	
	public void createAddress() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actions.click(btnShipToCollapsed);
		actions.click(btnAddAddress);
		
		actions.click(drpdwnCity);
		
		Thread.sleep(2000);
		actions.enterText(inputTextField, city);
		actions.click(selectCity);
		
		actions.click(txtBusinessAddress);
		actions.enterText(inputBusinessAddress, text);

		actions.sendKeys(Keys.ENTER);
		actions.enterText(inputState, text);
		
		actions.sendKeys(Keys.ENTER);
		actions.enterText(inputPoBox, text);
		
		actions.sendKeys(Keys.ENTER);
		actions.enterText(inputAddressLabel, text);
		
		actions.sendKeys(Keys.ENTER);
		actions.enterText(inputPhoneNumber, number);
		
		actions.sendKeys(Keys.ENTER);

		System.out.println(actions.getPageSource());

		actions.click(checkboxBilling);
		actions.click(btnDone);
	}

}
