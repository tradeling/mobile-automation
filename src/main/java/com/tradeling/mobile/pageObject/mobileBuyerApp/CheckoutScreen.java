package com.tradeling.mobile.pageObject.mobileBuyerApp;

import org.openqa.selenium.support.PageFactory;

import com.tradeling.mobile.driver.MobileActions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutScreen {
	
	 @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='collapsed'])[2]/android.view.ViewGroup/android.view.ViewGroup")
	 MobileElement btnBankTransfer;


	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='PROCEED TO REVIEW']")
	 MobileElement btnProceedToReview;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='PURCHASE']")
	 MobileElement btnPurchase;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Thank you, your order has been placed']")
	 MobileElement txtOrderPlaced;
	 
	 

	 

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
}
