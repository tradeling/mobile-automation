package com.tradeling.mobile.pageObject.mobileBuyerApp;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import com.tradeling.data.buyerApp.Constants;

import com.tradeling.mobile.driver.MobileActions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutScreen {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bank Transfer to Tradeling']")
	MobileElement btnBankTransfer;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PROCEED TO REVIEW']")
	MobileElement btnProceedToReview;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PURCHASE']")
	MobileElement btnPurchase;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Thank you, your order has been placed.']")
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
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='State']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.EditText")
	MobileElement inputState;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='P.O. box']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.EditText")
	MobileElement inputPoBox;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Address label']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.EditText")
	MobileElement inputAddressLabel;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone number']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.EditText")
	MobileElement inputPhoneNumber;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Use this address as my billing address']")
	MobileElement checkboxBilling;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='DONE']")
	MobileElement btnDone;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bank Transfer to Tradeling']")
	MobileElement bankTransferBtn;
	
	MobileActions actions;

	public CheckoutScreen(MobileActions action) {
		this.actions = action;
		PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
	}

	public void btnBankTransferCheckout() {
		actions.waitFor(8000);
		try {
			actions.scrollDown(0.8, 0.2);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		actions.click(bankTransferBtn);
	}

	public void btnProceedToReviewCheckout() {
		actions.click(btnProceedToReview);
	}

	public void btnPurchaseCheckout() {
		actions.click(btnPurchase);
	}

	public String txtOrderPlaced() {
		actions.waitFor(3000);
		return actions.getText(txtOrderPlaced);
	}
	
	public void createAddress() {
		actions.waitFor(3000);
		
		actions.click(btnShipToCollapsed);
		actions.click(btnAddAddress);
		
		actions.click(drpdwnCity);
		

		actions.waitFor(2000);
		actions.enterText(inputTextField, Constants.city);
		actions.click(selectCity);
		
		actions.click(txtBusinessAddress);
		actions.enterText(inputBusinessAddress, Constants.text);

		actions.sendKeys(Keys.ENTER);
		actions.enterText(inputState, Constants.text);
		
		actions.sendKeys(Keys.ENTER);
		actions.enterText(inputPoBox, Constants.text);
		
		actions.sendKeys(Keys.ENTER);
		actions.enterText(inputAddressLabel, Constants.text);
		
		actions.sendKeys(Keys.ENTER);
		actions.enterText(inputPhoneNumber, Constants.number);
		
		actions.sendKeys(Keys.ENTER);

		actions.click(checkboxBilling);
		actions.click(btnDone);
	}

}
