package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class PhoneNumberScreen {

    MobileActions actions;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"581234567\"])[5]/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.widget.EditText")
    MobileElement txt_phoneNumber;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"CONTINUE\"])[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CONTINUE']")
    MobileElement btn_continuePhoneNumber;

    public PhoneNumberScreen(MobileActions action){
            this.actions = action;
            PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void addPhoneNumberInPhoneNumberScreen(String phoneNumber){
        actions.enterText(txt_phoneNumber, phoneNumber);
        actions.click(btn_continuePhoneNumber);
    }

}
