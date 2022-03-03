package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.google.common.collect.ImmutableMap;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.TapOptions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class LaunchScreen {

    MobileActions actions;


    @iOSXCUITFindBy(accessibility = "CONTINUE")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CONTINUE']")
    MobileElement btn_continue;

    @iOSXCUITFindBy(accessibility = "English")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='English']")
    MobileElement label_englishLang;

    @iOSXCUITFindBy(accessibility = "العربية")
    @AndroidFindBy(accessibility = "العربية")
    MobileElement label_arabicLang;

//    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Allow\"`]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Allow']")
    @AndroidFindBy(accessibility = "Allow")
    MobileElement button_allowNotification;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Don’t Allow\"`]")
    @AndroidFindBy(accessibility = "Don’t Allow")
    MobileElement button_dontAllowNotification;


    public LaunchScreen(MobileActions action) {
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void selectLanguageAndRegion(String lang, String region)
    {
        if(lang.equalsIgnoreCase("english")){
            actions.click(label_englishLang);
        }
        else if(lang.equalsIgnoreCase("arabic")){
            actions.click(label_arabicLang);
    }
        actions.click(btn_continue);
    }

    public void acceptNotificationAlert(boolean isAllow){
        actions.waitUntilAlertPresent();
        if(isAllow){
            actions.acceptAlert();
        }
        else{
            actions.dismissAlert();
        }
    }




}
