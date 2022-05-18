package com.tradeling.mobile.edukaanApp.test;

import com.tradeling.apis.requests.edukaanApp.RegistrationRequests;
import com.tradeling.apis.requests.edukaanApp.RequestUtility;
import com.tradeling.data.edukaanApp.EdukaanData;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.edukaanApp.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Search extends EnvironmentSetup {
    HomePage homePage;
    SearchResultScreen searchResultScreen;
    EdukaanLaunchScreen edukaanLaunchScreen;
    RegistrationRequests registrationRequests;
    PhoneNumberScreen phoneNumberScreen;
    OTPScreen otpScreen;

    @Test
    public void userCanSearchSuccessfully() {
        registrationRequests = new RegistrationRequests();
        edukaanLaunchScreen = new EdukaanLaunchScreen(actions.get());
        phoneNumberScreen = new PhoneNumberScreen(actions.get());
        otpScreen = new OTPScreen(actions.get());
        homePage = new HomePage(actions.get());
        searchResultScreen = new SearchResultScreen(actions.get());

        edukaanLaunchScreen.skipEdukaanLaunchScreen();
        registrationRequests.userLogin();
        registrationRequests.verifyOTP();
        registrationRequests.registerEdukaanUser();
        registrationRequests.uploadTradeLicenceDocument();
        registrationRequests.loginToBackoffice();
        registrationRequests.approveTheUserFromBackoffice();
        phoneNumberScreen.addPhoneNumberInPhoneNumberScreen(RequestUtility.phoneNumber);
        otpScreen.addOTPValue(EdukaanData.otpValue);
        homePage.searchWithProductName(EdukaanData.productName);
        Assert.assertTrue(searchResultScreen.getTheProductCardText().contains(EdukaanData.productName));

    }
}
