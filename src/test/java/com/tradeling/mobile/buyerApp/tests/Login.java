package com.tradeling.mobile.buyerApp.tests;

import com.tradeling.apis.requests.buyerApp.RegistrationRequests;
import com.tradeling.data.buyerApp.BuyerRegistrationData;
import com.tradeling.data.buyerApp.Constants;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.mobileBuyerApp.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends EnvironmentSetup {
    LaunchScreen launchScreen;
    LoginScreen loginScreen;
    RegistrationScreen registrationScreen;
    HomeScreen homeScreen;
    BusinessProfileScreen businessProfileScreen;
    DocumentUploadScreen documentUploadScreen;
    OTPScreen otpScreen;
    AccountScreen accountScreen;

    @Test
    private void login_buyer()
    {
        RegistrationRequests registrationRequests = new RegistrationRequests();
        BuyerRegistrationData buyerRegistrationData = new BuyerRegistrationData("buyer","");
        registrationRequests.registerBuyer(buyerRegistrationData);
        Response response = registrationRequests.verifyOtpForUser(buyerRegistrationData.getEmail());
        launchScreen = new LaunchScreen(actions.get());
        if(platform.get().equalsIgnoreCase("ios")) {
            launchScreen.acceptNotificationAlert(false);
        }
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);

        loginScreen = new LoginScreen(actions.get());
        loginScreen.enterUserAndPass(buyerRegistrationData.getEmail(), buyerRegistrationData.getPassword());
        homeScreen = new HomeScreen(actions.get());
        homeScreen.navigateToAccountScreen();
        accountScreen = new AccountScreen(actions.get());
        Assert.assertTrue(accountScreen.verifyUserRegistered(buyerRegistrationData.getCompanyName(), false));
        }
}
