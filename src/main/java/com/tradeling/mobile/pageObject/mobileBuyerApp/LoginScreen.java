package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginScreen {

    MobileActions actions;



    @iOSXCUITFindBy(accessibility = "Email address login_email_address")
    @AndroidFindBy(accessibility = "login_email_address")
    MobileElement input_loginEmail;

    @iOSXCUITFindBy(accessibility = "Password login_password")
    @AndroidFindBy(accessibility = "login_password")
    MobileElement input_loginPass;

    @iOSXCUITFindBy(accessibility = "auth_login_button")
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='auth_login_button']")
    MobileElement btn_login;

    @iOSXCUITFindBy(accessibility = "auth_continue_as_guest")
    @AndroidFindBy(accessibility = "auth_continue_as_guest")
    MobileElement link_continueAsGuest;

    @iOSXCUITFindBy(accessibility = "auth_register_by_username")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='REGISTER']")
    MobileElement link_registration;

    public LoginScreen(MobileActions action) {
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void enterUserAndPass(String user, String pass) {
        actions.enterText(input_loginEmail,user);
        actions.enterText(input_loginPass,pass);
        actions.hideKeyboard();
        actions.click(btn_login);
    }


    public void navigateToRegistration(){
        actions.click(link_registration);
    }
}
