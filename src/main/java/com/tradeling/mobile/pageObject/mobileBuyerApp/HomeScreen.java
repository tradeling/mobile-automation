package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.reporting.Reporting;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen {

    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "MyAccount_tab_bar")
    @AndroidFindBy(accessibility = "Account")
    MobileElement link_account;

    @iOSXCUITFindBy(accessibility = "REGISTER")
    @AndroidFindBy(accessibility = "REGISTER")
    MobileElement btn_register;

    @iOSXCUITFindBy(accessibility = "LOGIN")
    @AndroidFindBy(accessibility = "LOGIN")
    MobileElement btn_login;


    public HomeScreen(MobileActions action) {
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }


    public boolean isHomeScreen(){
        boolean flag = false;
        if(actions.waitForElementToDisplay(link_account)){
            flag = true;
        }
        return flag;
    }

    public void navigateToRegistration()
    {
        actions.click(link_account);
        actions.click(btn_register);
    }

    public void navigateToLogin()
    {
        actions.click(link_account);
        actions.click(btn_login);
    }

    public boolean verifyUserRegistered(String companyName){
        boolean flag = false;
        actions.click(link_account);

        if(actions.waitForElementToDisplay(actions.getLocator("name-ios:"+companyName))){
            flag = true;
            Reporting.getLogger().logPass("Company/User successfully registered");
        }
        else {
            Reporting.getLogger().logFail("Company/User registration failed");
        }
        return flag;

    }
}
