package com.tradeling.mobile.edukaanApp.test;
import com.tradeling.apis.requests.edukaanApp.CardRequest;
import com.tradeling.apis.requests.edukaanApp.RegistrationRequests;
import com.tradeling.apis.requests.edukaanApp.RequestUtility;
import com.tradeling.data.edukaanApp.EdukaanData;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.pageObject.edukaanApp.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CardMangment extends EnvironmentSetup {

    EdukaanLaunchScreen edukaanLaunchScreen;
    RegistrationRequests registrationRequests;
    PhoneNumberScreen phoneNumberScreen;
    OTPScreen otpScreen;
    HomePage homePage;
    MyAccountScreen MyAccount ;
    CardRequest cardRequest;
    CardManagmentScreen Card;
    AddNewCardScreen Add;

    @BeforeMethod
    public void Initalization(){

        registrationRequests = new RegistrationRequests();
        edukaanLaunchScreen = new EdukaanLaunchScreen(actions.get());
        phoneNumberScreen = new PhoneNumberScreen(actions.get());
        otpScreen = new OTPScreen(actions.get());
        homePage = new HomePage(actions.get());
        MyAccount = new MyAccountScreen(actions.get());
        Add = new AddNewCardScreen(actions.get());

        edukaanLaunchScreen.WaitForAlertDisplaying();
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
    public void UserAddingCardSuccessfully() throws InterruptedException {

        Card.ClickOnAddNewCardCTA();
        Add.EnterCardHolderName(EdukaanData.CardName);
        Add.EnterCardNumber(EdukaanData.CardNumber);
        Add.EnterExpirationDate(EdukaanData.ExpiryDate);
        Add.ClickOnAddNewCard();
        Assert.assertEquals(Add.getAddingCardToastConfirmationMessage(),"NEW CARD ADDED");
    }

    @Test
    public void UserDeletingCardSuccessfully() {
        cardRequest.userAddNewCard();
        Card.DeleteCard();
        Assert.assertEquals(Add.getAddingCardToastConfirmationMessage(),"Card deleted successfully");
    }

    @Test
    public void UserEditCardSuccessfully() {

        cardRequest.userAddNewCard();
        Card.EditExistingCard();
        Add.EnterCardHolderName(EdukaanData.CardNameEdited);
        Add.ClickOnSaveCard();
        Assert.assertEquals(Add.getAddingCardToastConfirmationMessage(),"Card edited successfully");
    }
}
