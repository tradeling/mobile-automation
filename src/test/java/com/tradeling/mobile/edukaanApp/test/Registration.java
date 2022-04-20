package com.tradeling.mobile.edukaanApp.test;

import com.tradeling.data.edukaanApp.EdukaanData;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.edukaanApp.*;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Registration extends EnvironmentSetup {
    EdukaanLaunchScreen edukaanLaunchScreen;
    PhoneNumberScreen phoneNumberScreen;
    OTPScreen otpScreen;
    ProfileScreen profileScreen;
    ShopScreen shopScreen;
    LocationScreen locationScreen;
    ShopLocationScreen shopLocationScreen;
    UploadTradeLicenseScreen uploadTradeLicenseScreen;
    RegistrationSuccessfulScreen registrationSuccessfulScreen;

    @Test
    public void userCanRegisterSuccessfully() throws InterruptedException{
        edukaanLaunchScreen = new EdukaanLaunchScreen(actions.get());
        phoneNumberScreen = new PhoneNumberScreen(actions.get());
        otpScreen = new OTPScreen(actions.get());
        profileScreen = new ProfileScreen(actions.get());
        shopScreen = new ShopScreen(actions.get());
        locationScreen = new LocationScreen(actions.get());
        shopLocationScreen = new ShopLocationScreen(actions.get());
        uploadTradeLicenseScreen = new UploadTradeLicenseScreen(actions.get());
        registrationSuccessfulScreen = new RegistrationSuccessfulScreen(actions.get());

        edukaanLaunchScreen.skipEdukaanLaunchScreen();
        phoneNumberScreen.addPhoneNumberInPhoneNumberScreen(EdukaanCommon.getRandomPhoneNumber());
        otpScreen.addOTPValue(EdukaanData.otpValue);
        profileScreen.fillTheProfileScreen(EdukaanData.firstNameValue, EdukaanData.lastNameValue);
        shopScreen.fillYourShopScreen(EdukaanData.shopName, EdukaanData.taxRegistrationNumber);
        locationScreen.selectLocation(EdukaanData.locationNameValue);
        shopLocationScreen.fillShopLocationScreen(EdukaanData.shopLocationCity, EdukaanData.shopLocationArea, EdukaanData.shopLocationStreet, EdukaanData.shopLocationCityBuildingName);
        uploadTradeLicenseScreen.uploadTradelicense();
        Assert.assertEquals(registrationSuccessfulScreen.getSuccessfulScreenMessage(), EdukaanData.registrationSuccessfulMessage);
    }
}
