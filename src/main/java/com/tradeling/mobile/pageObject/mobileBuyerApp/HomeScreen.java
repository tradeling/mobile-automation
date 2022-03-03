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
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Account']")
    MobileElement link_account;




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

    public void navigateToAccountScreen(){
        actions.click(link_account);
    }


}
