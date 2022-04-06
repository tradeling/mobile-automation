package com.tradeling.mobile.buyerApp.tests;

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

public class Registration extends EnvironmentSetup {

    LaunchScreen launchScreen;
    LoginScreen loginScreen;
    RegistrationScreen registrationScreen;
    HomeScreen homeScreen;
    BusinessProfileScreen businessProfileScreen;
    DocumentUploadScreen documentUploadScreen;
    OTPScreen otpScreen;
    AccountScreen accountScreen;
    CompanyProfileScreen companyProfileScreen;

    // QTM-6519
    @Test
    private void register_new_buyer(){

        launchScreen = new LaunchScreen(actions.get());
        if(platform.get().equalsIgnoreCase("ios")) {
            launchScreen.acceptNotificationAlert(false);
        }
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);

        loginScreen = new LoginScreen(actions.get());
        loginScreen.navigateToRegistration();

        BuyerRegistrationData buyerData = new BuyerRegistrationData(Constants.USERTYPE_BUYER, "United Arab Emirates");

        registrationScreen = new RegistrationScreen(actions.get());
        Assert.assertTrue(registrationScreen.verifyUserLandedRegistrationScreen());
        registrationScreen.addUserTypeAndCompanyName(buyerData.getUserType(), buyerData.getCompanyName());
        registrationScreen.selectCountryOfOperation(buyerData.getCountryOfOperations());
        Assert.assertTrue(registrationScreen.submitAndNavigateToAddCredentials());
        registrationScreen.addLoginCredentials(buyerData.getEmail(), buyerData.getPassword());

        businessProfileScreen = new BusinessProfileScreen(actions.get());
        businessProfileScreen.addCustomerName(buyerData.getFirstName(), buyerData.getLastName());
        businessProfileScreen.addBusinessDetails(buyerData.getDepartment(), buyerData.getIndustry(), buyerData.getBusinessSize());
        businessProfileScreen.addPhoneNumber(buyerData.getCountryCode(), buyerData.getPhone());
        businessProfileScreen.submitBusinessProfile();

        otpScreen = new OTPScreen(actions.get());
        Assert.assertTrue(otpScreen.verifyOtpScreen());
        otpScreen.inputOtpAndSubmit(Constants.OTP);

        documentUploadScreen = new DocumentUploadScreen(actions.get());
        Assert.assertTrue(documentUploadScreen.verifyDocumentUploadScreen());
        Assert.assertTrue(documentUploadScreen.navigateToUploadNow());
        documentUploadScreen.uploadTradeLicenseAndExpiry(buyerData.getTradeLicenseExpiryMonth(), buyerData.getTradeLicenseExpiryDate(), buyerData.getTradeLicenseExpiryYear());
        Assert.assertTrue(documentUploadScreen.navigateToUploadIdentityDoc());
        documentUploadScreen.uploadIdentityAndExpiry(buyerData.getIdExpiryMonth(), buyerData.getIdExpiryDate(), buyerData.getIdExpiryYear());
        Assert.assertTrue(documentUploadScreen.navigateToUploadVatCertificate());
        documentUploadScreen.uploadVatCertificate(false);
        documentUploadScreen.submitAndMoveNextFromVatUpload();

        Assert.assertTrue(documentUploadScreen.verifyTermsAndConditionScreen());
        documentUploadScreen.acceptTermsAndCondition();

        homeScreen = new HomeScreen(actions.get());
        homeScreen.navigateToAccountScreen();
        accountScreen = new AccountScreen(actions.get());
        Assert.assertTrue(accountScreen.verifyUserRegistered(buyerData.getCompanyName(), true));

    }

    //QTM-6520
    @Test
    private void register_new_buyer_without_document(){

        launchScreen = new LaunchScreen(actions.get());
        if(platform.get().equalsIgnoreCase("ios")) {
            launchScreen.acceptNotificationAlert(false);
        }
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);

        loginScreen = new LoginScreen(actions.get());
        loginScreen.navigateToRegistration();

        BuyerRegistrationData buyerData = new BuyerRegistrationData(Constants.USERTYPE_BUYER, "United Arab Emirates");

        registrationScreen = new RegistrationScreen(actions.get());
        Assert.assertTrue(registrationScreen.verifyUserLandedRegistrationScreen());
        registrationScreen.addUserTypeAndCompanyName(buyerData.getUserType(), buyerData.getCompanyName());
        registrationScreen.selectCountryOfOperation(buyerData.getCountryOfOperations());
        Assert.assertTrue(registrationScreen.submitAndNavigateToAddCredentials());
        registrationScreen.addLoginCredentials(buyerData.getEmail(), buyerData.getPassword());

        businessProfileScreen = new BusinessProfileScreen(actions.get());
        businessProfileScreen.addCustomerName(buyerData.getFirstName(), buyerData.getLastName());
        businessProfileScreen.addBusinessDetails(buyerData.getDepartment(), buyerData.getIndustry(), buyerData.getBusinessSize());
        businessProfileScreen.addPhoneNumber(buyerData.getCountryCode(), buyerData.getPhone());
        businessProfileScreen.submitBusinessProfile();

        otpScreen = new OTPScreen(actions.get());
        Assert.assertTrue(otpScreen.verifyOtpScreen());
        otpScreen.inputOtpAndSubmit(Constants.OTP);

        documentUploadScreen = new DocumentUploadScreen(actions.get());
        Assert.assertTrue(documentUploadScreen.verifyDocumentUploadScreen());
        documentUploadScreen.navigateTopUploadLater();

        Assert.assertTrue(documentUploadScreen.verifyTermsAndConditionScreen());
        documentUploadScreen.acceptTermsAndCondition();

        homeScreen = new HomeScreen(actions.get());
        homeScreen.navigateToAccountScreen();
        accountScreen = new AccountScreen(actions.get());
        Assert.assertTrue(accountScreen.verifyUserRegistered(buyerData.getCompanyName(), false));

    }

    //QTM-6521
    @Test
    private void verify_user_can_upload_document_from_accounts_screen(){
        RegistrationRequests registrationRequests = new RegistrationRequests();
        BuyerRegistrationData buyerRegistrationData = new BuyerRegistrationData("buyer","");
        registrationRequests.registerBuyer(buyerRegistrationData);
        Response response = registrationRequests.verifyOtpForUser(buyerRegistrationData.getEmail());
        launchScreen = new LaunchScreen(actions.get());
        if(platform.get().equalsIgnoreCase("ios")) {
            launchScreen.acceptNotificationAlert(false);
        }
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);

        loginScreen = new LoginScreen(actions.get());
        loginScreen.enterUserAndPass(buyerRegistrationData.getEmail(), buyerRegistrationData.getPassword());
        homeScreen = new HomeScreen(actions.get());
        homeScreen.navigateToAccountScreen();
        accountScreen = new AccountScreen(actions.get());
        Assert.assertTrue(accountScreen.verifyUserRegistered(buyerRegistrationData.getCompanyName(), false));
        accountScreen.navigateToDocumentUpload();
        companyProfileScreen = new CompanyProfileScreen(actions.get());
        Assert.assertTrue(companyProfileScreen.verifyCompanyProfileScreen());
        companyProfileScreen.navigateToUploadTradeLicense();
        documentUploadScreen = new DocumentUploadScreen(actions.get());
        Assert.assertTrue(documentUploadScreen.verifyUploadTradeLicenseScreen());
        documentUploadScreen.uploadTradeLicenseAndExpiry(buyerRegistrationData.getTradeLicenseExpiryMonth(), buyerRegistrationData.getTradeLicenseExpiryDate(), buyerRegistrationData.getTradeLicenseExpiryYear());
        documentUploadScreen.submitDocumentUpload();
        companyProfileScreen.navigateToUploadVatCertificate();
        documentUploadScreen.uploadVatCertificate(false);
        documentUploadScreen.submitDocumentUpload();
        Assert.assertTrue(companyProfileScreen.verifyDocumentUploaded());
        companyProfileScreen.navigateBack();
        Assert.assertTrue(accountScreen.verifyUserRegistered(buyerRegistrationData.getCompanyName(), true));
    }

    //QTM - 6522
    @Test
    private void verify_already_registered_user_cannot_register(){
        String username = PropertyFileHandle.getPropertyValue("unverifiedBuyerEmail", Constants.buyerTestDataFilePath,Constants.buyerTestDataFile);
        String password = PropertyFileHandle.getPropertyValue("unverifiedBuyerPassword", Constants.buyerTestDataFilePath,Constants.buyerTestDataFile);

        launchScreen = new LaunchScreen(actions.get());
        if(platform.get().equalsIgnoreCase("ios")) {
            launchScreen.acceptNotificationAlert(false);
        }
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);

        loginScreen = new LoginScreen(actions.get());
        loginScreen.navigateToRegistration();

        BuyerRegistrationData buyerData = new BuyerRegistrationData(Constants.USERTYPE_BUYER, "United Arab Emirates");

        registrationScreen = new RegistrationScreen(actions.get());
        Assert.assertTrue(registrationScreen.verifyUserLandedRegistrationScreen());
        registrationScreen.addUserTypeAndCompanyName(buyerData.getUserType(), buyerData.getCompanyName());
        registrationScreen.selectCountryOfOperation(buyerData.getCountryOfOperations());
        Assert.assertTrue(registrationScreen.submitAndNavigateToAddCredentials());
        registrationScreen.addLoginCredentials(username, password);

        businessProfileScreen = new BusinessProfileScreen(actions.get());
        businessProfileScreen.addCustomerName(buyerData.getFirstName(), buyerData.getLastName());
        businessProfileScreen.addBusinessDetails(buyerData.getDepartment(), buyerData.getIndustry(), buyerData.getBusinessSize());
        businessProfileScreen.addPhoneNumber(buyerData.getCountryCode(), buyerData.getPhone());
        businessProfileScreen.submitBusinessProfile();
        Assert.assertTrue(businessProfileScreen.verifyExistingUserError());



    }

}
