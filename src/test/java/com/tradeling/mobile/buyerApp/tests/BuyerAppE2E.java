package com.tradeling.mobile.buyerApp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tradeling.data.buyerApp.Constants;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.mobileBuyerApp.CartScreen;
import com.tradeling.mobile.pageObject.mobileBuyerApp.CheckoutScreen;
import com.tradeling.mobile.pageObject.mobileBuyerApp.HomeScreen;
import com.tradeling.mobile.pageObject.mobileBuyerApp.LoginScreen;
import com.tradeling.mobile.pageObject.mobileBuyerApp.PDPScreen;
import com.tradeling.mobile.pageObject.mobileBuyerApp.PLPScreen;

public class BuyerAppE2E extends EnvironmentSetup {

	LoginScreen loginScreen;
	HomeScreen homeScreen;
	PLPScreen plpScreen;
	PDPScreen pdpScreen;
	CartScreen cartScreen;
	CheckoutScreen checkoutScreen;
	

	@Test
	public void buyerApp_e2e() {
		loginScreen = new LoginScreen(actions.get());
		loginScreen.login_buyer(platform);
		
		
		homeScreen = new HomeScreen(actions.get());
		homeScreen.searchBox(Constants.Search_Keyword);
		
		
		plpScreen = new PLPScreen(actions.get());
		plpScreen.clickOnProduct();
		
        pdpScreen = new PDPScreen(actions.get());
        pdpScreen.btnAddToCartPDP();

        pdpScreen.addToCartBtnPopup();
        pdpScreen.btnCheckoutPopup();

        cartScreen = new CartScreen(actions.get());
        
        cartScreen.btnCheckoutOnCart();
		
		checkoutScreen = new CheckoutScreen(actions.get());
		checkoutScreen.createAddress();
		
        checkoutScreen.btnBankTransferCheckout();
        checkoutScreen.btnProceedToReviewCheckout();
        checkoutScreen.btnPurchaseCheckout();
		
		Assert.assertEquals(checkoutScreen.txtOrderPlaced().equalsIgnoreCase("Thank you, your order has been placed."), true);   
	}
}