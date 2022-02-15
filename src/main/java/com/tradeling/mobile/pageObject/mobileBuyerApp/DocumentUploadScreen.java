package com.tradeling.mobile.pageObject.mobileBuyerApp;

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
    @AndroidFindBy(accessibility = "Document upload")
    MobileElement label_documentUpload;

    @iOSXCUITFindBy(accessibility = "UPLOAD LATER")
    @AndroidFindBy(accessibility = "auth_login_button")
    MobileElement button_uploadLater;

    @iOSXCUITFindBy(accessibility = "auth_login_button")
    @AndroidFindBy(accessibility = "auth_login_button")
    MobileElement button_uploadNow;


    @iOSXCUITFindBy(accessibility = "UPLOAD TRADE LICENSE")
    @AndroidFindBy(accessibility = "UPLOAD TRADE LICENSE")
    MobileElement button_uploadTradeLicense;


    @iOSXCUITFindBy(accessibility = "Upload Trade License")
    @AndroidFindBy(accessibility = "Upload Trade License")
    MobileElement label_uploadTradeLicense;

    @iOSXCUITFindBy(accessibility = "Upload from gallery")
    @AndroidFindBy(accessibility = "Upload from gallery")
    MobileElement link_uploadFromGallery;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage")
    @AndroidFindBy(xpath = "Upload from gallery")
    MobileElement icon_docUpload;

    @iOSXCUITFindBy(accessibility = "Trade license expiry date")
    @AndroidFindBy(accessibility = "Trade license expiry date")
    MobileElement link_tradeLicenseExpiry;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel")
    @AndroidFindBy(accessibility = "")
    List<MobileElement> datePickerWheel;

    @iOSXCUITFindBy(accessibility = "Confirm")
    @AndroidFindBy(accessibility = "Confirm")
    MobileElement button_confirmDate;

    @iOSXCUITFindBy(accessibility = "upload_next_verification")
    @AndroidFindBy(accessibility = "upload_next_verification")
    MobileElement button_nextDocUpload;

    @iOSXCUITFindBy(accessibility = "Upload Identity Document")
    @AndroidFindBy(accessibility = "Upload Identity Document")
    MobileElement label_uploadIdentityDoc;

    @iOSXCUITFindBy(accessibility = "UPLOAD IDENTITY DOCUMENT")
    @AndroidFindBy(accessibility = "UPLOAD IDENTITY DOCUMENT")
    MobileElement button_uploadIdentityDoc;

    @iOSXCUITFindBy(accessibility = "Expiry date")
    @AndroidFindBy(accessibility = "Expiry date")
    MobileElement link_identityExpiry;

    @iOSXCUITFindBy(accessibility = "Upload VAT Certificate")
    @AndroidFindBy(accessibility = "Upload VAT Certificate")
    MobileElement label_uploadVatCertificate;

    @iOSXCUITFindBy(accessibility = "UPLOAD VAT CERTIFICATE")
    @AndroidFindBy(accessibility = "UPLOAD VAT CERTIFICATE")
    MobileElement button_uploadVatCert;

    @iOSXCUITFindBy(accessibility = "VAT Reference Number")
    @AndroidFindBy(accessibility = "VAT Reference Number")
    MobileElement textBox_vatReferenceNum;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='My business is not eligible for VAT certificate'])[2]/XCUIElementTypeOther")
    @AndroidFindBy(accessibility = "")
    MobileElement checkBox_notEligibleVatCert;

    @iOSXCUITFindBy(accessibility = "UPLOAD VAT EXEMPTION")
    @AndroidFindBy(accessibility = "UPLOAD VAT EXEMPTION")
    MobileElement button_uploadVatExemption;

    @iOSXCUITFindBy(accessibility = "Please review and agree to our Terms & Conditions.")
    @AndroidFindBy(accessibility = "Please review and agree to our Terms & Conditions.")
    MobileElement label_termAndConditions;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='I agree to the User Terms & Conditions and Privacy Policy'])[2]/preceding-sibling::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    MobileElement checkBox_agreeTerms;

    @iOSXCUITFindBy(accessibility = "undefined")
    @AndroidFindBy(accessibility = "undefined")
    MobileElement button_termContinue;


    public DocumentUploadScreen(MobileActions action) {
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
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


    public void selectDate(String month, String date, String year){

        actions.enterText(datePickerWheel.get(0),month);
        actions.enterText(datePickerWheel.get(1), date);
        actions.enterText(datePickerWheel.get(2), year);
        actions.click(button_confirmDate);

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
