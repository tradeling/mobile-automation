package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.mobile.pageObject.helper.GeneralMethods;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadTradeLicenseScreen extends EnvironmentSetup {
    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "Trade license Upload")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[2]")
    MobileElement btn_uploadTradeLicense;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup")
    MobileElement link_camera;

    @iOSXCUITFindBy(accessibility = "Upload from phone")
    MobileElement link_uploadFromPhone;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name=\"sample, pdf\"]/XCUIElementTypeOther[2]")
    MobileElement link_file;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Shutter']")
    MobileElement link_screenShotAction;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Done']")
    MobileElement link_screenShotDone;

    @iOSXCUITFindBy(accessibility = "VERIFY")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView")
    MobileElement btn_verify;

    public UploadTradeLicenseScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void uploadTradelicense() throws InterruptedException {
        actions.click(btn_uploadTradeLicense);
        if (platform.get().equalsIgnoreCase("android")){
            actions.click(link_camera);
            actions.click(link_screenShotAction);
            actions.click(link_screenShotDone);
            GeneralMethods.scrollDown(0.8, 0.1, actions);
        }
        if (platform.get().equalsIgnoreCase("ios")){
            actions.click(link_uploadFromPhone);
            actions.click(link_file);
        }
        actions.click(btn_verify);
    }
}
