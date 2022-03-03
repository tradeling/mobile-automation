package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.reporting.Reporting;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OTPScreen {

    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "Verify your account")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Verify your account']")
    MobileElement label_verifyAccount;
//(//XCUIElementTypeOther[@name="|"])[2]/XCUIElementTypeOther[2]/XCUIElementTypeTextField

//    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='VERIFY']/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == 'VERIFY'`][1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]")
    @AndroidFindBy(xpath = "//android.widget.EditText/following-sibling::android.view.ViewGroup")
    MobileElement textBox_otp;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='VERIFY']/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]")
    @AndroidFindBy(xpath = "//android.widget.EditText/following-sibling::android.view.ViewGroup")
    MobileElement textBox_otp1;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='VERIFY']/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]")
    @AndroidFindBy(xpath = "//android.widget.EditText/following-sibling::android.view.ViewGroup")
    MobileElement textBox_otp2;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='VERIFY']/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[4]")
    @AndroidFindBy(xpath = "//android.widget.EditText/following-sibling::android.view.ViewGroup")
    MobileElement textBox_otp3;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='VERIFY']/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[5]")
    @AndroidFindBy(xpath = "//android.widget.EditText/following-sibling::android.view.ViewGroup")
    MobileElement textBox_otp4;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='VERIFY']/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[6]")
    @AndroidFindBy(xpath = "//android.widget.EditText/following-sibling::android.view.ViewGroup")
    MobileElement textBox_otp5;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='VERIFY']/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[7]")
    @AndroidFindBy(xpath = "//android.widget.EditText/following-sibling::android.view.ViewGroup")
    MobileElement textBox_otp6;

    @iOSXCUITFindBy(accessibility = "auth_verify_button")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='auth_verify_button']")
    MobileElement button_verify;

    public OTPScreen(MobileActions action) {
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public boolean verifyOtpScreen(){
        boolean flag = false;
        if(EnvironmentSetup.platform.get().equalsIgnoreCase("android")){
            actions.hideKeyboard();
        }
        if(actions.waitForElementToDisplay(label_verifyAccount)){
            flag = true;
            Reporting.getLogger().logPass("Successfully landed on OTP screen");
        }
        else{
            Reporting.getLogger().logFail("Failed to navigate to OTP screen");
        }
        return flag;
    }

    public void inputOtpAndSubmit(String otp){
        if(EnvironmentSetup.platform.get().equalsIgnoreCase("android")) {
            actions.click(textBox_otp);
        }
        actions.getDriver().getKeyboard().sendKeys(otp);
        actions.click(button_verify);
    }

}
