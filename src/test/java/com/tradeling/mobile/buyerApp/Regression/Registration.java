package com.tradeling.mobile.buyerApp.Regression;

import com.tradeling.apis.requests.buyerApp.RegistrationRequests;
import com.tradeling.apis.utility.TestDataHandler;
import com.tradeling.data.buyerApp.BuyerRegistrationData;
import com.tradeling.data.buyerApp.Constants;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.mobileBuyerApp.*;
import com.tradeling.utilities.PropertyFileHandle;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Registration extends EnvironmentSetup {


    SoftAssert softAssert = new SoftAssert();
    // QTM-6519
    @Test
    public void QTM6519(){
        testPrep();
        BuyerRegistrationData buyerData = new BuyerRegistrationData(Constants.USERTYPE_BUYER, "United Arab Emirates");
        softAssert.assertTrue(registrationScreen.verifyUserLandedRegistrationScreen());
        registrationScreen.addUserTypeAndCompanyName(buyerData.getUserType(), buyerData.getCompanyName());
        registrationScreen.selectCountryOfOperation(buyerData.getCountryOfOperations());
        softAssert.assertTrue(registrationScreen.submitAndNavigateToAddCredentials());
        registrationScreen.addLoginCredentials(buyerData.getEmail(), buyerData.getPassword());
        businessProfileScreen.addCustomerName(buyerData.getFirstName(), buyerData.getLastName());
        businessProfileScreen.addBusinessDetails(buyerData.getDepartment(), buyerData.getIndustry(), buyerData.getBusinessSize());
        businessProfileScreen.addPhoneNumber(buyerData.getCountryCode(), buyerData.getPhone());
        businessProfileScreen.submitBusinessProfile();
        softAssert.assertTrue(otpScreen.verifyOtpScreen());
        otpScreen.inputOtpAndSubmit(Constants.OTP);
        softAssert.assertTrue(documentUploadScreen.verifyDocumentUploadScreen());
        softAssert.assertTrue(documentUploadScreen.navigateToUploadNow());
        documentUploadScreen.uploadTradeLicenseAndExpiry(buyerData.getTradeLicenseExpiryMonth(), buyerData.getTradeLicenseExpiryDate(), buyerData.getTradeLicenseExpiryYear());
        softAssert.assertTrue(documentUploadScreen.navigateToUploadIdentityDoc());
        documentUploadScreen.uploadIdentityAndExpiry(buyerData.getIdExpiryMonth(), buyerData.getIdExpiryDate(), buyerData.getIdExpiryYear());
        softAssert.assertTrue(documentUploadScreen.navigateToUploadVatCertificate());
        documentUploadScreen.uploadVatCertificate(false);
        documentUploadScreen.submitAndMoveNextFromVatUpload();
        softAssert.assertTrue(documentUploadScreen.verifyTermsAndConditionScreen());
        documentUploadScreen.acceptTermsAndCondition();
        homeScreen.navigateToAccountScreen();
        softAssert.assertTrue(accountScreen.verifyUserRegistered(buyerData.getCompanyName(), true));
    }

    //QTM-6520

    /**
     * url https://tradeling.atlassian.net/browse/QTM-6520
     */
    @Test
    public void QTM6520(){
        testPrep();
        BuyerRegistrationData buyerData = new BuyerRegistrationData(Constants.USERTYPE_BUYER, "United Arab Emirates");
        softAssert.assertTrue(registrationScreen.verifyUserLandedRegistrationScreen());
        registrationScreen.addUserTypeAndCompanyName(buyerData.getUserType(), buyerData.getCompanyName());
        registrationScreen.selectCountryOfOperation(buyerData.getCountryOfOperations());
        softAssert.assertTrue(registrationScreen.submitAndNavigateToAddCredentials());
        registrationScreen.addLoginCredentials(buyerData.getEmail(), buyerData.getPassword());
        businessProfileScreen.addCustomerName(buyerData.getFirstName(), buyerData.getLastName());
        businessProfileScreen.addBusinessDetails(buyerData.getDepartment(), buyerData.getIndustry(), buyerData.getBusinessSize());
        businessProfileScreen.addPhoneNumber(buyerData.getCountryCode(), buyerData.getPhone());
        businessProfileScreen.submitBusinessProfile();
        softAssert.assertTrue(otpScreen.verifyOtpScreen());
        otpScreen.inputOtpAndSubmit(Constants.OTP);
        softAssert.assertTrue(documentUploadScreen.verifyDocumentUploadScreen());
        documentUploadScreen.navigateTopUploadLater();
        softAssert.assertTrue(documentUploadScreen.verifyTermsAndConditionScreen());
        documentUploadScreen.acceptTermsAndCondition();
        homeScreen.navigateToAccountScreen();
        softAssert.assertTrue(accountScreen.verifyUserRegistered(buyerData.getCompanyName(), false));
    }

    //QTM-6521
    @Test
    private void verify_user_can_upload_document_from_accounts_screen(){
        RegistrationRequests registrationRequests = new RegistrationRequests();
        BuyerRegistrationData buyerRegistrationData = new BuyerRegistrationData("buyer","");
        registrationRequests.registerBuyer(buyerRegistrationData);
        Response response = registrationRequests.verifyOtpForUser(buyerRegistrationData.getEmail());

        if(platform.get().equalsIgnoreCase("ios")) {
            launchScreen.acceptNotificationAlert(false);
        }
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);
        loginScreen.enterUserAndPass(buyerRegistrationData.getEmail(), buyerRegistrationData.getPassword());
        homeScreen.navigateToAccountScreen();
        accountScreen = new AccountScreen(actions.get());
        softAssert.assertTrue(accountScreen.verifyUserRegistered(buyerRegistrationData.getCompanyName(), false));
        accountScreen.navigateToDocumentUpload();
        companyProfileScreen = new CompanyProfileScreen(actions.get());
        softAssert.assertTrue(companyProfileScreen.verifyCompanyProfileScreen());
        companyProfileScreen.navigateToUploadTradeLicense();
        documentUploadScreen = new DocumentUploadScreen(actions.get());
        softAssert.assertTrue(documentUploadScreen.verifyUploadTradeLicenseScreen());
        documentUploadScreen.uploadTradeLicenseAndExpiry(buyerRegistrationData.getTradeLicenseExpiryMonth(), buyerRegistrationData.getTradeLicenseExpiryDate(), buyerRegistrationData.getTradeLicenseExpiryYear());
        documentUploadScreen.submitDocumentUpload();
        companyProfileScreen.navigateToUploadVatCertificate();
        documentUploadScreen.uploadVatCertificate(false);
        documentUploadScreen.submitDocumentUpload();
        softAssert.assertTrue(companyProfileScreen.verifyDocumentUploaded());
        companyProfileScreen.navigateBack();
        softAssert.assertTrue(accountScreen.verifyUserRegistered(buyerRegistrationData.getCompanyName(), true));
    }
    //QTM - 6522
    @Test
    private void verify_already_registered_user_cannot_register(){
        String username = PropertyFileHandle.getPropertyValue("unverifiedBuyerEmail", Constants.buyerTestDataFilePath,Constants.buyerTestDataFile);
        String password = PropertyFileHandle.getPropertyValue("unverifiedBuyerPassword", Constants.buyerTestDataFilePath,Constants.buyerTestDataFile);
        testPrep();
        BuyerRegistrationData buyerData = new BuyerRegistrationData(Constants.USERTYPE_BUYER, "United Arab Emirates");
        softAssert.assertTrue(registrationScreen.verifyUserLandedRegistrationScreen());
        registrationScreen.addUserTypeAndCompanyName(buyerData.getUserType(), buyerData.getCompanyName());
        registrationScreen.selectCountryOfOperation(buyerData.getCountryOfOperations());
        softAssert.assertTrue(registrationScreen.submitAndNavigateToAddCredentials());
        registrationScreen.addLoginCredentials(username, password);
        businessProfileScreen.addCustomerName(buyerData.getFirstName(), buyerData.getLastName());
        businessProfileScreen.addBusinessDetails(buyerData.getDepartment(), buyerData.getIndustry(), buyerData.getBusinessSize());
        businessProfileScreen.addPhoneNumber(buyerData.getCountryCode(), buyerData.getPhone());
        businessProfileScreen.submitBusinessProfile();
        softAssert.assertTrue(businessProfileScreen.verifyExistingUserError());
    }

    void testPrep(){
        if(platform.get().equalsIgnoreCase("ios")) {
            launchScreen.acceptNotificationAlert(false);
        }
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);
        loginScreen.navigateToRegistration();
    }

}
