package com.tradeling.mobile.edukaanApp.test;
import com.tradeling.apis.requests.edukaanApp.RegistrationRequests;
import com.tradeling.data.edukaanApp.EdukaanData;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.edukaanApp.*;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CardMangment extends EnvironmentSetup {

    EdukaanLaunchScreen edukaanLaunchScreen;
    RegistrationRequests registrationRequests;
    PhoneNumberScreen phoneNumberScreen;
    OTPScreen otpScreen;
    HomePage homePage;
    MyAccountScreen MyAccount ;

    CardManagmentScreen Card;

    AddNewCardScreen Add;


    @Test
    public void UserAddingCardSuccessfully(){
        registrationRequests = new RegistrationRequests();
        edukaanLaunchScreen = new EdukaanLaunchScreen(actions.get());
        phoneNumberScreen = new PhoneNumberScreen(actions.get());
        otpScreen = new OTPScreen(actions.get());
        homePage = new HomePage(actions.get());
        MyAccount = new MyAccountScreen(actions.get());
        Add = new AddNewCardScreen(actions.get());

        edukaanLaunchScreen.skipEdukaanLaunchScreen();
        registrationRequests.userLogin();
        registrationRequests.verifyOTP();
        registrationRequests.registerEdukaanUser();
        registrationRequests.uploadTradeLicenceDocument();
        registrationRequests.loginToBackoffice();
        registrationRequests.approveTheUserFromBackoffice();

        homePage.pressOnMyAccountLogo();
        Card.AddNewCard();
        Add.EnterCardHolderName(EdukaanData.CardName);
        Add.EnterCardNumber(EdukaanData.CardNumber);
        Add.EnterExpirationDate(EdukaanData.ExpiryDate);
        Add.ClickOnAddNewCard();
        Assert.assertEquals(Add.getAddingCardToastConfirmationMessage(),"NEW CARD ADDED");
    }

    @Test
    public void UserDeletingCardSuccessfully() {
        registrationRequests = new RegistrationRequests();
        edukaanLaunchScreen = new EdukaanLaunchScreen(actions.get());
        phoneNumberScreen = new PhoneNumberScreen(actions.get());
        otpScreen = new OTPScreen(actions.get());
        homePage = new HomePage(actions.get());
        MyAccount = new MyAccountScreen(actions.get());
        Add = new AddNewCardScreen(actions.get());

        edukaanLaunchScreen.skipEdukaanLaunchScreen();
        registrationRequests.userLogin();
        registrationRequests.verifyOTP();
        registrationRequests.registerEdukaanUser();
        registrationRequests.uploadTradeLicenceDocument();
        registrationRequests.loginToBackoffice();
        registrationRequests.approveTheUserFromBackoffice();
        homePage.pressOnMyAccountLogo();

        MyAccount.ClickOnCardManagement();
        Card.DeleteCard();
        Assert.assertEquals(Add.getAddingCardToastConfirmationMessage(),"Card deleted successfully");
    }
}
