package com.tradeling.mobile.edukaanApp.test;
import com.tradeling.apis.requests.edukaanApp.CardRequest;
import com.tradeling.apis.requests.edukaanApp.RegistrationRequests;
import com.tradeling.apis.requests.edukaanApp.RequestUtility;
import com.tradeling.data.edukaanApp.EdukaanData;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.edukaanApp.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CardMangment extends EnvironmentSetup {

    EdukaanLaunchScreen edukaanLaunchScreen;
    RegistrationRequests registrationRequests;
    PhoneNumberScreen phoneNumberScreen;
    OTPScreen otpScreen;
    HomePage homePage;
    MyAccountScreen MyAccount ;
    CardRequest cardRequest;
    CardMangmentScreen Card;
    AddNewCardScreen Add;

    public void Initalization(){

        registrationRequests = new RegistrationRequests();
        edukaanLaunchScreen = new EdukaanLaunchScreen(actions.get());
        phoneNumberScreen = new PhoneNumberScreen(actions.get());
        otpScreen = new OTPScreen(actions.get());
        homePage = new HomePage(actions.get());
        MyAccount = new MyAccountScreen(actions.get());
        Add = new AddNewCardScreen(actions.get());
        Card = new CardMangmentScreen(actions.get());

        // edukaanLaunchScreen.WaitForAlertDisplaying();
        edukaanLaunchScreen.skipEdukaanLaunchScreen();
        registrationRequests.userLogin();
        registrationRequests.verifyOTP();
        registrationRequests.registerEdukaanUser();
        registrationRequests.uploadTradeLicenceDocument();
        registrationRequests.loginToBackoffice();
        registrationRequests.approveTheUserFromBackoffice();
        phoneNumberScreen.addPhoneNumberInPhoneNumberScreen(RequestUtility.phoneNumber);
        otpScreen.addOTPValue(EdukaanData.otpValue);
        actions.get().waitFor(3000);
        homePage.pressOnMyAccountLogo();
        MyAccount.ClickOnCardManagement();
    }

    @Test
    public void UserAddingCardSuccessfully() {
        Initalization();
        Card.ClickOnAddNewCardCTA();
        Add.EnterCardHolderName(EdukaanData.CardName);
        Add.EnterCardNumber(EdukaanData.CardNumber);
        Add.EnterExpirationDate(EdukaanData.ExpiryDate);
        Add.ClickOnAddNewCard();
        Card.CardAddedSuccessfully();
        Assert.assertEquals(Card.CardAddedSuccessfully(),"****4242");
    }

    @Test
    public void UserDeletingCardSuccessfully() {
        Initalization();
        cardRequest.userAddNewCard();
        Card.DeleteCard();
        Assert.assertEquals(Card.CardDeletedSuccessfully(),"Add credit / debit card to make your checkout process more convenient.");
    }

    @Test
    public void UserEditCardSuccessfully() {
        Initalization();
        cardRequest.userAddNewCard();
        Card.EditExistingCard();
        Add.EnterCardHolderName(EdukaanData.CardNameEdited);
        Add.EnterExpirationDate(EdukaanData.ExpiryDateEdited);
        Add.ClickOnSaveCard();
        Assert.assertEquals(Card.CardEditedSuccessfully(),"Expires on 07/ 27");
    }
}