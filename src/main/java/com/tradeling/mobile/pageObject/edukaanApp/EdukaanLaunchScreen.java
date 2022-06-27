package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class EdukaanLaunchScreen {
    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "SKIP")
    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView")
    MobileElement btn_skipLaunchScreen;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"GET STARTED\"])[2]")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView")
    MobileElement btn_getStartedLaunchScreen;


    public EdukaanLaunchScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    };

    public void skipEdukaanLaunchScreen(){
        actions.acceptAlert();
        actions.click(btn_skipLaunchScreen);
        actions.click(btn_getStartedLaunchScreen);
    }

    public void WaitForAlertDisplaying()
    {
        actions.waitUntilAlertPresent();
    }

}
