package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewCardScreen
{
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Card holder name'])[2]")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/" +
            "android.view.ViewGroup/android.widget.EditText[1]")
    MobileElement CardHolderName;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='4242 0123 4564 8901'])[3]/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "//*[@text='4242 0123 4564 8901']")
    MobileElement CardNumber;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='MM/YY'])[3]/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "//*[@text='MM/YY']")
    MobileElement ExpiryDate;

    @iOSXCUITFindBy(id = "ADD NEW CARD")
    @AndroidFindBy(xpath = "//*[@text='ADD NEW CARD']")
    MobileElement AddNewCard;

    @iOSXCUITFindBy(xpath = "//*[contains(@label='Card Added Successfully’)]")
    @AndroidFindBy(xpath = "//android.widget.Toast 24")
    MobileElement SuccessToast_confirmationMessage;

    @iOSXCUITFindBy(id = "SAVE CARD")
    @AndroidFindBy(xpath = "//*[@text='SAVE CARD']")
    MobileElement SaveCard;

    MobileActions actions;

    public AddNewCardScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }
    public void EnterCardHolderName(String CardName) {actions.enterText(CardHolderName,CardName);}
    public void EnterCardNumber(String CNumber) {actions.enterText(CardNumber,CNumber);}
    public void EnterExpirationDate(String ExpirationDate) {actions.enterText(ExpiryDate,ExpirationDate);}
    public void ClickOnAddNewCard(){
        actions.waitForElementToDisplay(AddNewCard);
        actions.click(AddNewCard);
}
    public String getAddingCardToastConfirmationMessage(){
        actions.waitForElementToDisplay(SuccessToast_confirmationMessage);
        String toaster = SuccessToast_confirmationMessage.getAttribute("name");
        System.out.println(toaster);
        return actions.getText(SuccessToast_confirmationMessage);
    }
    public void ClickOnSaveCard(){
        actions.waitForElementToDisplay(SaveCard);
        actions.click(SaveCard);
    }
}
