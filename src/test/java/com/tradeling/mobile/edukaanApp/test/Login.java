package com.tradeling.mobile.edukaanApp.test;

import com.tradeling.apis.requests.edukaanApp.RegistrationRequests;
import com.tradeling.apis.requests.edukaanApp.RequestUtility;
import com.tradeling.data.edukaanApp.EdukaanData;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.edukaanApp.EdukaanLaunchScreen;
import com.tradeling.mobile.pageObject.edukaanApp.HomePage;
import com.tradeling.mobile.pageObject.edukaanApp.OTPScreen;
import com.tradeling.mobile.pageObject.edukaanApp.PhoneNumberScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends EnvironmentSetup {
    EdukaanLaunchScreen edukaanLaunchScreen;
    RegistrationRequests registrationRequests;
    PhoneNumberScreen phoneNumberScreen;
    OTPScreen otpScreen;
    HomePage homePage;

    @Test
    public void userCanLoginSuccessfully() {
        registrationRequests = new RegistrationRequests();
        edukaanLaunchScreen = new EdukaanLaunchScreen(actions.get());
        phoneNumberScreen = new PhoneNumberScreen(actions.get());
        otpScreen = new OTPScreen(actions.get());
        homePage = new HomePage(actions.get());

        edukaanLaunchScreen.skipEdukaanLaunchScreen();
        registrationRequests.userLogin();
        registrationRequests.verifyOTP();
        registrationRequests.registerEdukaanUser();
        registrationRequests.uploadTradeLicenceDocument();
        registrationRequests.loginToBackoffice();
        registrationRequests.approveTheUserFromBackoffice();
        phoneNumberScreen.addPhoneNumberInPhoneNumberScreen(RequestUtility.phoneNumber);
        otpScreen.addOTPValue(EdukaanData.otpValue);
        Assert.assertTrue(homePage.searchComponentIsDisplayed());
    }
}
