package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewCardScreen
{
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"txt_card_holder_name\"]")
    @AndroidFindBy(accessibility = "txt_card_holder_name")
    MobileElement CardHolderName;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"txt_card_number\"]")
    @AndroidFindBy(accessibility = "txt_card_number")
    MobileElement CardNumber;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"txt_expiry_date\"]")
    @AndroidFindBy(accessibility = "txt_expiry_date")
    MobileElement ExpiryDate;

    @iOSXCUITFindBy(id = "ADD NEW CARD")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"cta_submit_card\"]/android.view.ViewGroup")
    MobileElement AddNewCard;

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
    public void ClickOnSaveCard(){
        actions.waitForElementToDisplay(SaveCard);
        actions.click(SaveCard);
    }
}