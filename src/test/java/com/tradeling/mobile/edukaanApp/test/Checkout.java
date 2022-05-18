package com.tradeling.mobile.edukaanApp.test;

import com.tradeling.apis.requests.edukaanApp.CartRequests;
import com.tradeling.apis.requests.edukaanApp.RegistrationRequests;
import com.tradeling.apis.requests.edukaanApp.RequestUtility;
import com.tradeling.data.edukaanApp.EdukaanData;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.edukaanApp.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Checkout extends EnvironmentSetup {
    EdukaanLaunchScreen edukaanLaunchScreen;
    RegistrationRequests registrationRequests;
    PhoneNumberScreen phoneNumberScreen;
    OTPScreen otpScreen;
    HomePage homePage;
    CartRequests cartRequests;
    CartScreen cartScreen;
    CheckoutScreen checkoutScreen;
    CheckoutConfirmationScreen checkoutConfirmationScreen;

    @Test
    public void userCanCheckoutSuccessfully() {
        registrationRequests = new RegistrationRequests();
        edukaanLaunchScreen = new EdukaanLaunchScreen(actions.get());
        phoneNumberScreen = new PhoneNumberScreen(actions.get());
        otpScreen = new OTPScreen(actions.get());
        homePage = new HomePage(actions.get());
        cartRequests = new CartRequests();
        cartScreen = new CartScreen(actions.get());
        checkoutScreen = new CheckoutScreen(actions.get());
        checkoutConfirmationScreen = new CheckoutConfirmationScreen(actions.get());

        edukaanLaunchScreen.skipEdukaanLaunchScreen();
        registrationRequests.userLogin();
        registrationRequests.verifyOTP();
        registrationRequests.registerEdukaanUser();
        registrationRequests.uploadTradeLicenceDocument();
        registrationRequests.loginToBackoffice();
        registrationRequests.approveTheUserFromBackoffice();
        cartRequests.userAddProductToTheCart(10);
        phoneNumberScreen.addPhoneNumberInPhoneNumberScreen(RequestUtility.phoneNumber);
        otpScreen.addOTPValue(EdukaanData.otpValue);
        actions.get().waitFor(30000);
        homePage.pressOnViewCart();
        cartScreen.pressOnContinueToCheckout();
        checkoutScreen.pressOnPlaceOrder();
        Assert.assertEquals(checkoutConfirmationScreen.getTheConfirmationMessage(), "Thank you, your order has been placed.");
    }
}
