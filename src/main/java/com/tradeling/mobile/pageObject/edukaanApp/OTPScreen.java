package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class OTPScreen {

    MobileActions actions;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField[@name=\"textInput\"])[1]")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.EditText")
    MobileElement txt_otpInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"The OTP code is invalid.\"]")
    @AndroidFindBy(xpath = "//*[@text='The OTP code is invalid.']")
    MobileElement InvalidOtp;

    public OTPScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void addOTPValue(String otp){
        actions.enterText(txt_otpInput, otp);
    }
    public boolean GetInvalidOTPMessage(){

        actions.waitForElementToDisplay(InvalidOtp);
        return  actions.waitForElementToDisplay(InvalidOtp);
    }
}
