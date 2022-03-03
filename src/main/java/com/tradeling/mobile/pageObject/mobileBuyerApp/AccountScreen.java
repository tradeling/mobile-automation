package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.reporting.Reporting;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountScreen {

    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "REGISTER")
    @AndroidFindBy(accessibility = "REGISTER")
    MobileElement btn_register;

    @iOSXCUITFindBy(accessibility = "LOGIN")
    @AndroidFindBy(accessibility = "LOGIN")
    MobileElement btn_login;

    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Pending Verification'")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pending Verification']")
    MobileElement label_pendingVerification;


    public AccountScreen(MobileActions action) {
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void navigateToRegistration()
    {
        actions.click(btn_register);
    }

    public void navigateToLogin()
    {
        actions.click(btn_login);
    }



    public boolean verifyUserRegistered(String companyName){
        boolean flag = false;

        if(actions.waitForElementToDisplay(label_pendingVerification)){
            flag = true;
            Reporting.getLogger().logPass("Company/User successfully registered");
        }
        else {
            Reporting.getLogger().logFail("Company/User registration failed");
        }
        return flag;

    }
}
