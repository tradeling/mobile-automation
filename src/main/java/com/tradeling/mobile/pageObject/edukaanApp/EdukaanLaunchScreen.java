package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class EdukaanLaunchScreen {
    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "SKIP")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SKIP']")
    MobileElement btn_skipLaunchScreen;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"GET STARTED\"])[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='GET STARTED']")
    MobileElement btn_getStartedLaunchScreen;


    public EdukaanLaunchScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    };

    public void skipEdukaanLaunchScreen(){
        if(EnvironmentSetup.platform.get().equals("ios"))
        {
            actions.waitUntilAlertPresent();
            actions.acceptAlert();
        }
        actions.click(btn_skipLaunchScreen);
        actions.click(btn_getStartedLaunchScreen);
    }

//    public void WaitForAlertDisplaying()
//    {
//        actions.waitUntilAlertPresent();
//    }

}