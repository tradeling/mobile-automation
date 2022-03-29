package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.reporting.Reporting;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class CompanyProfileScreen {


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Company Profile']")
    MobileElement label_companyProfile;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Upload trade license']")
    MobileElement button_uploadTradeLicense;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Upload vat certificate']")
    MobileElement button_uploadVatCertificate;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pending Verification']")
    MobileElement label_pendingVerification;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Company Profile']/preceding-sibling::android.view.ViewGroup")
    MobileElement button_navigateBack;

    MobileActions actions;

    public CompanyProfileScreen(MobileActions action) {
        this.actions = action;
        try {
            PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void navigateToUploadTradeLicense(){
        actions.click(button_uploadTradeLicense);
    }

    public void navigateToUploadVatCertificate(){
        actions.click(button_uploadVatCertificate);
    }

    public boolean verifyCompanyProfileScreen(){
        boolean flag = false;
        try{
            if(actions.waitForElementToDisplay(label_companyProfile)){
                flag = true;
                Reporting.getLogger().logPass("Successfully landed on Company Profile Screen");
            }
            else{
                Reporting.getLogger().logFail("Company Profile Screen not loaded");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Reporting.getLogger().logFail("Exception occurred while navigating to Company Profile Screen", e);
        }
        return flag;
    }

    public boolean verifyDocumentUploaded(){
        boolean flag = false;
        try{
            if(actions.waitForElementToDisplay(label_pendingVerification)){
                flag =true;
                Reporting.getLogger().logPass("Document uploaded successfully");
            }
            else {
                Reporting.getLogger().logFail("Document upload failed");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public void navigateBack(){
        actions.click(button_navigateBack);
    }
}
