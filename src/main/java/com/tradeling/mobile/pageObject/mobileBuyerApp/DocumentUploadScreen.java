package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.reporting.Reporting;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DocumentUploadScreen {

    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "Document upload")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Document upload']")
    MobileElement label_documentUpload;

    @iOSXCUITFindBy(accessibility = "UPLOAD LATER")
    @AndroidFindBy(accessibility = "auth_login_button")
    MobileElement button_uploadLater;

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'UPLOAD LATER'")
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@resource-id='auth_login_button'])[1]")
    MobileElement button_uploadNow;


    @iOSXCUITFindBy(accessibility = "UPLOAD TRADE LICENSE")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='UPLOAD TRADE LICENSE']")
    MobileElement button_uploadTradeLicense;


    @iOSXCUITFindBy(accessibility = "Upload Trade License")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Upload Trade License']")
    MobileElement label_uploadTradeLicense;

    @iOSXCUITFindBy(accessibility = "Upload from gallery")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Browse']")
    MobileElement link_uploadFromGallery;


    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage")
    @AndroidFindBy(id = "com.android.documentsui:id/icon_thumb")
    MobileElement icon_docUpload;

    @iOSXCUITFindBy(accessibility = "Trade license expiry date")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Trade license expiry date']")
    MobileElement link_tradeLicenseExpiry;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel")
    @AndroidFindBy(accessibility = "//")
    List<MobileElement> datePickerWheel;

    @iOSXCUITFindBy(accessibility = "upload_next_verification")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='NEXT']")
    MobileElement button_nextDocUpload;

    @iOSXCUITFindBy(accessibility = "Upload Identity Document")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Upload Identity Document']")
    MobileElement label_uploadIdentityDoc;

    @iOSXCUITFindBy(accessibility = "UPLOAD IDENTITY DOCUMENT")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='UPLOAD IDENTITY DOCUMENT']")
    MobileElement button_uploadIdentityDoc;

    @iOSXCUITFindBy(accessibility = "Expiry date")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Expiry date']")
    MobileElement link_identityExpiry;

    @iOSXCUITFindBy(accessibility = "Upload VAT Certificate")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Upload VAT Certificate']")
    MobileElement label_uploadVatCertificate;

    @iOSXCUITFindBy(accessibility = "UPLOAD VAT CERTIFICATE")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='UPLOAD VAT CERTIFICATE']")
    MobileElement button_uploadVatCert;

    @iOSXCUITFindBy(accessibility = "VAT Reference Number")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='VAT Reference Number']/parent::android.view.ViewGroup/preceding-sibling::android.widget.EditText")
    MobileElement textBox_vatReferenceNum;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='My business is not eligible for VAT certificate'])[2]/XCUIElementTypeOther")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My business is not eligible for VAT certificate']/following-sibling::android.view.ViewGroup")
    MobileElement checkBox_notEligibleVatCert;

    @iOSXCUITFindBy(accessibility = "UPLOAD VAT EXEMPTION")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='UPLOAD VAT EXEMPTION']")
    MobileElement button_uploadVatExemption;

    @iOSXCUITFindBy(accessibility = "Please review and agree to our Terms & Conditions.")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Please review and agree to our Terms & Conditions.']")
    MobileElement label_termAndConditions;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='I agree to the User Terms & Conditions and Privacy Policy'])[2]/preceding-sibling::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='I agree to the User']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup")
    MobileElement checkBox_agreeTerms;

    @iOSXCUITFindBy(accessibility = "undefined")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CONTINUE']")
    MobileElement button_termContinue;

    @AndroidFindBy(id = "android:id/date_picker_header_year")
    MobileElement link_androidDatePickerYear;

    @AndroidFindBy(id = "android:id/button1")
    MobileElement button_androidDatePickerOk;

    @iOSXCUITFindBy(accessibility = "Confirm")
    MobileElement button_iosConfirmDate;

    public DocumentUploadScreen(MobileActions action) {
        try {
            this.actions = action;
            PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean verifyDocumentUploadScreen(){
        boolean flag = false;
        if(actions.waitForElementToDisplay(label_documentUpload)){
            flag = true;
            Reporting.getLogger().logPass("Successfully landed on document upload screen");
        }
        else{
            Reporting.getLogger().logFail("Failed to navigate to 'Document Upload' screen");
        }
        return flag;
    }

    public boolean navigateToUploadNow()
    {
        boolean flag = false;
        actions.click(button_uploadNow);
        if(actions.waitForElementToDisplay(label_uploadTradeLicense)){
            flag = true;
            Reporting.getLogger().logPass("Landed on Trade License upload screen");
        }
        else
        {
            Reporting.getLogger().logFail("'Upload Trade License' screen not found");
        }
        return flag;
    }

    public void navigateTopUploadLater()
    {
        actions.click(button_uploadLater);
    }

    public void uploadTradeLicenseAndExpiry(String month, String date, String year){
        actions.click(button_uploadTradeLicense);
        selectDocFromGallery();
        actions.click(link_tradeLicenseExpiry);
        selectDate(month, date, year);
    }

    public void selectDocFromGallery(){
        actions.click(link_uploadFromGallery);
        actions.click(icon_docUpload);
    }


    public boolean navigateToUploadIdentityDoc(){

        boolean flag = false;
        actions.click(button_nextDocUpload);
        if(actions.waitForElementToDisplay(label_uploadIdentityDoc)){
            flag = true;
            Reporting.getLogger().logPass("Landed on 'Identity upload' screen");
        }
        else
        {
            Reporting.getLogger().logFail("'Identity upload' screen not found");
        }
        return flag;
    }

    public boolean navigateToUploadVatCertificate(){

        boolean flag = false;
        actions.click(button_nextDocUpload);
        if(actions.waitForElementToDisplay(label_uploadVatCertificate)){
            flag = true;
            Reporting.getLogger().logPass("Landed on 'Vat Certificate upload' screen");
        }
        else
        {
            Reporting.getLogger().logFail("'Vat Certificate upload' screen not found");
        }
        return flag;
    }

    public void uploadIdentityAndExpiry(String month, String date, String year) {
        actions.click(button_uploadIdentityDoc);
        selectDocFromGallery();
        actions.click(link_identityExpiry);
        selectDate(month, date, year);
    }

    public void selectDate(String month, String date, String year)
    {
        if(EnvironmentSetup.platform.get().equalsIgnoreCase("ios")){
            selectDateForIos(month, date, year);
        }
        else if(EnvironmentSetup.platform.get().equalsIgnoreCase("android")){
            selectDateForAndroid(year);
        }
    }


    public void selectDateForIos(String month, String date, String year){

        actions.enterText(datePickerWheel.get(0),month);
        actions.enterText(datePickerWheel.get(1), date);
        actions.enterText(datePickerWheel.get(2), year);
        actions.click(button_iosConfirmDate);

    }

    public void selectDateForAndroid(String year){

        actions.click(link_androidDatePickerYear);
        MobileElement yearLink = actions.getLocator("xpath-android://android.widget.TextView[@resource-id='android:id/text1' and @text='"+year+"']");
        actions.click(yearLink);
        actions.click(button_androidDatePickerOk);

    }

    public void uploadVatCertificate(boolean isEligibleForVat){

        if(isEligibleForVat)
        {
            actions.click(button_uploadVatCert);
            selectDocFromGallery();
            actions.enterText(textBox_vatReferenceNum, "");
        }
        else {
            actions.click(checkBox_notEligibleVatCert);
            actions.click(button_uploadVatExemption);
            selectDocFromGallery();
        }
        actions.waitForElementIsEnabled(button_nextDocUpload);
        actions.click(button_nextDocUpload);
    }

    public boolean verifyTermsAndConditionScreen(){
        boolean flag = false;
        if(actions.waitForElementToDisplay(label_termAndConditions)){
            flag = true;
            Reporting.getLogger().logPass("Landed on 'Terms and condition' screen");
        }
        else{
            Reporting.getLogger().logFail("Failed to navigate to 'Terms and Condition' screen");
        }
        return flag;
    }

    public void acceptTermsAndCondition(){
        actions.click(checkBox_agreeTerms);
        actions.click(button_termContinue);
    }
}
