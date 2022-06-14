package com.tradeling.mobile.buyerApp.tests;

import com.tradeling.apis.utility.TestDataHandler;
import com.tradeling.data.buyerApp.Constants;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.mobileBuyerApp.*;
import com.tradeling.utilities.PropertyFileHandle;
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
    EmailPasswordScreen emailPasswordScreen;

    //QTM - 6524
    @Test
	private void login_buyer()
    {
    	loginScreen = new LoginScreen(actions.get());
    	String username = loginScreen.login_buyer(platform);
    	
        homeScreen = new HomeScreen(actions.get());
        homeScreen.navigateToAccountScreen();
        accountScreen = new AccountScreen(actions.get());
        Assert.assertTrue(accountScreen.verifyUserRegistered(username, false));
   }

    //QTM - 6525
    @Test
    private void login_invalid_buyer()
    {
        String username = PropertyFileHandle.getPropertyValue("unverifiedBuyerEmail", Constants.buyerTestDataFilePath,Constants.buyerTestDataFile);
        String password = Constants.invalidPassword;
        launchScreen = new LaunchScreen(actions.get());
        if(platform.get().equalsIgnoreCase("ios")) {
            launchScreen.acceptNotificationAlert(false);
        }
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);

        loginScreen = new LoginScreen(actions.get());
        loginScreen.enterUserAndPass(username, password);
        Assert.assertTrue(loginScreen.verifyInvalidLoginError());

    }

    //QTM - 6527
    @Test
    private void verify_user_is_able_to_change_password_from_profile(){
        String username = PropertyFileHandle.getPropertyValue("unverifiedBuyerEmail", Constants.buyerTestDataFilePath,Constants.buyerTestDataFile);
        String password = PropertyFileHandle.getPropertyValue("unverifiedBuyerPassword", Constants.buyerTestDataFilePath,Constants.buyerTestDataFile);
        String newPassword = "new"+password;
        TestDataHandler.testData.put("unverifiedBuyerPassword", newPassword);
        TestDataHandler.writeDataToPropertiesBuyerApp();
        launchScreen = new LaunchScreen(actions.get());
        if(platform.get().equalsIgnoreCase("ios")) {
            launchScreen.acceptNotificationAlert(false);
        }
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);

        loginScreen = new LoginScreen(actions.get());
        loginScreen.enterUserAndPass(username, password);
        homeScreen = new HomeScreen(actions.get());
        homeScreen.navigateToAccountScreen();
        accountScreen = new AccountScreen(actions.get());
        Assert.assertTrue(accountScreen.verifyUserRegistered(username, true));
        accountScreen.navigateToEmailPassword();
        emailPasswordScreen = new EmailPasswordScreen(actions.get());
        Assert.assertTrue(emailPasswordScreen.verifyEmailPasswordScreen());
        emailPasswordScreen.updatePassword(password, newPassword);
        Assert.assertTrue(emailPasswordScreen.verifyPasswordUpdateIsSuccessful());
    }

   

}
