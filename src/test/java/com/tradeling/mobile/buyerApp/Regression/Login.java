package com.tradeling.mobile.buyerApp.Regression;

import com.tradeling.apis.utility.TestDataHandler;
import com.tradeling.data.buyerApp.Constants;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.utilities.PropertyFileHandle;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Login extends EnvironmentSetup {

    String username = "new-buyer-stage@tradeling.com";
    String password = "new-buyer-stage@tradeling.com";
    String invalidUserName = "test@invalid.com";
    String invalidPassWord = "test@invalid.com";
    String Unverusername;
    String Unverpassword;
    String newPassword;

    /**
     * @URL https://tradeling.atlassian.net/browse/QTM-4549
     * @throws InterruptedException
     */
    @Test(description = "Verify that buyer can login using valid email")
    public void QTM4549() throws InterruptedException {
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);
        loginScreen.typeUserName(username);
        loginScreen.typePassword(password);
        loginScreen.tabLogin();
        homeScreen.navigateToAccountScreen();
        Assert.assertTrue(accountScreen.verifyUserRegisteredWithDoc());
    }

    /**
     * @URL https://tradeling.atlassian.net/browse/QTM-4550
     * @throws InterruptedException
     */
    @Test(description = "Verify that user can't login with invalid data")
    public void QTM4550() throws InterruptedException {
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);
        loginScreen.typeUserName(invalidUserName);
        loginScreen.typePassword(invalidPassWord);
        loginScreen.tabLogin();
        Assert.assertTrue(loginScreen.verifyInvalidLoginError());
    }

    /**
     * URL https://tradeling.atlassian.net/browse/QTM-5089
     * @throws InterruptedException
     */
    @Test(description = "Verify that the user can successfully request rest password")
    public void QTM5089() throws InterruptedException{
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);
        loginScreen.forgettPassword();
    }

    /**
     * URL https://tradeling.atlassian.net/browse/QTM-5086
     * @throws InterruptedException
     */
    @Test(description = "Verify that the user can successfully change the password")
    public void QTM5086() throws InterruptedException{
        QTM5086Prep();
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);
        loginScreen.enterUserAndPass(Unverusername, Unverpassword);
        homeScreen.navigateToAccountScreen();
        Assert.assertTrue(accountScreen.verifyUserRegistered(Unverusername, true));
        accountScreen.navigateToEmailPassword();
        Assert.assertTrue(emailPasswordScreen.verifyEmailPasswordScreen());
        emailPasswordScreen.updatePassword(Unverpassword, newPassword);
        Assert.assertTrue(emailPasswordScreen.verifyPasswordUpdateIsSuccessful());
    }

    /**
     * <P>used to handle all string assignments for TC5086</P>
     */
    void QTM5086Prep(){
        Unverusername = PropertyFileHandle.getPropertyValue("unverifiedBuyerEmail", Constants.buyerTestDataFilePath,Constants.buyerTestDataFile);
        Unverpassword = PropertyFileHandle.getPropertyValue("unverifiedBuyerPassword", Constants.buyerTestDataFilePath,Constants.buyerTestDataFile);
        newPassword = "new"+password;
        TestDataHandler.testData.put("unverifiedBuyerPassword", newPassword);
        TestDataHandler.writeDataToPropertiesBuyerApp();
        if(platform.get().equalsIgnoreCase("ios")) {
            launchScreen.acceptNotificationAlert(false);
        }
    }
}
