package com.tradeling.mobile.edukaanApp.test;

import com.tradeling.data.EdukaanData;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.edukaanApp.*;
import org.testng.annotations.Test;

public class Registration extends EnvironmentSetup {
    EdukaanLaunchScreen edukaanLaunchScreen;
    PhoneNumberScreen phoneNumberScreen;
    OTPScreen otpScreen;
    ProfileScreen profileScreen;
    ShopScreen shopScreen;

    @Test
    public void registerAsANewUser(){
        edukaanLaunchScreen = new EdukaanLaunchScreen(actions.get());
        phoneNumberScreen = new PhoneNumberScreen(actions.get());
        otpScreen = new OTPScreen(actions.get());
        profileScreen = new ProfileScreen(actions.get());
        shopScreen = new ShopScreen(actions.get());

        edukaanLaunchScreen.skipEdukaanLaunchScreen();
        phoneNumberScreen.addPhoneNumberInPhoneNumberScreen(EdukaanData.phoneNumberValue);
        otpScreen.addOTPValue(EdukaanData.otpValue);
        profileScreen.fillTheProfileScreen(EdukaanData.firstNameValue, EdukaanData.lastNameValue);
        shopScreen.fillYourShopScreen(EdukaanData.shopName, EdukaanData.taxRegistrationNumber);

    }
}
