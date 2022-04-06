package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.reporting.Reporting;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountScreen {

    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "REGISTER")
    @AndroidFindBy(accessibility = "REGISTER")
    MobileElement btn_register;

    @iOSXCUITFindBy(accessibility = "LOGIN")
    @AndroidFindBy(accessibility = "LOGIN")
    MobileElement btn_login;

    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Pending Verification'")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pending Verification']")
    MobileElement label_pendingVerification;

    @iOSXCUITFindBy(accessibility = "Upload your business documents")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Upload your business documents']")
    MobileElement label_uploadBusinessDocument;

    @iOSXCUITFindBy(accessibility = "undefined")
    @AndroidFindBy(xpath ="//android.widget.TextView[@text='UPLOAD DOCUMENTS']")
    MobileElement button_uploadDocument;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"Email & Password\"`][3]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email & Password']")
    MobileElement link_emailPassword;

    public AccountScreen(MobileActions action) {
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void navigateToRegistration()
    {
        actions.click(btn_register);
    }

    public void navigateToLogin()
    {
        actions.click(btn_login);
    }



    public boolean verifyUserRegistered(String companyName, boolean isDocUploaded){
        if(isDocUploaded)
        {
            return verifyUserRegisteredWithDoc();
        }
        else
        {
            return verifyUserRegisteredWithoutDoc();
        }


    }

    public boolean verifyUserRegisteredWithDoc(){
        boolean flag = false;
        if(actions.waitForElementToDisplay(label_pendingVerification)){
            flag = true;
            Reporting.getLogger().logPass("Company/User successfully registered");
        }
        else {
            Reporting.getLogger().logFail("Company/User registration failed");
        }
        return flag;
    }

    public boolean verifyUserRegisteredWithoutDoc(){
        boolean flag = false;
        if(actions.waitForElementToDisplay(label_uploadBusinessDocument)){
            flag = true;
            Reporting.getLogger().logPass("Company/User successfully registered");
        }
        else {
            Reporting.getLogger().logFail("Company/User registration failed");
        }
        return flag;
    }

    public void navigateToDocumentUpload(){
        actions.click(button_uploadDocument);
    }

    public void navigateToEmailPassword(){
        actions.click(link_emailPassword);
    }
}
