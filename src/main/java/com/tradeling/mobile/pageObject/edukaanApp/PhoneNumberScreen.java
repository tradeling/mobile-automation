package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class PhoneNumberScreen {

    MobileActions actions;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"txt_phone_number\"]")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"txt_phone_number\"]")
    MobileElement txt_phoneNumber;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"txt_phone_number_cta\"])[2]")
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
