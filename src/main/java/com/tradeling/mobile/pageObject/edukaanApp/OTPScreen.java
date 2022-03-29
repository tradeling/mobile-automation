package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class OTPScreen {

    MobileActions actions;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[1]/android.widget.EditText")
    MobileElement txt_otpInput;

    public OTPScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void addOTPValue(String otp){
        actions.enterText(txt_otpInput, otp);
    }
}
