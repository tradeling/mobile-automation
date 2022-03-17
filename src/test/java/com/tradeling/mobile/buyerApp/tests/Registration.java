package com.tradeling.mobile.buyerApp.tests;

import com.tradeling.data.BuyerRegistrationData;
import com.tradeling.data.Constants;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.mobileBuyerApp.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
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

        Assert.assertTrue(documentUploadScreen.verifyTermsAndConditionScreen());
        documentUploadScreen.acceptTermsAndCondition();

        homeScreen = new HomeScreen(actions.get());
        homeScreen.navigateToAccountScreen();
        accountScreen = new AccountScreen(actions.get());
        Assert.assertTrue(accountScreen.verifyUserRegistered(buyerData.getCompanyName()));

    }

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
        Assert.assertTrue(accountScreen.verifyUserRegistered(buyerData.getCompanyName()));

    }



}
