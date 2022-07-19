package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountScreen
{
    @iOSXCUITFindBy(accessibility = "Manage cards")
    @AndroidFindBy(xpath = "//*[@text='Manage cards']")
    MobileElement CardManagement;

    MobileActions actions;

    public MyAccountScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void ClickOnCardManagement(){
        actions.waitForElementToDisplay(CardManagement);
        actions.click(CardManagement);
    }
}