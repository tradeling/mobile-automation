package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.data.edukaanApp.EdukaanData;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationSuccessfulScreen {

    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "We are verifying your account")
    @AndroidFindBy(xpath = "//*[@text='We are verifying your account']")
    MobileElement txt_successfulMessage;

    public RegistrationSuccessfulScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public String getSuccessfulScreenMessage() {
        return actions.getText(txt_successfulMessage);
    }
}
