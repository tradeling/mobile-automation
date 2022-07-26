package com.tradeling.mobile.pageObject.edukaanApp;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class CardMangmentScreen {

    @iOSXCUITFindBy(accessibility = "cta_add_cc")
    @AndroidFindBy(accessibility = "cta_add_cc")
    MobileElement AddNewCardCTA;

    @iOSXCUITFindBy(accessibility = "cta_edit_cc-0")
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/child::android.view.ViewGroup//android.widget.TextView[@resource-id='cta_edit_cc-0']")
    MobileElement Edit;

    @iOSXCUITFindBy(accessibility = "cta_delete_cc-0")
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/child::android.view.ViewGroup//android.widget.TextView[@resource-id='cta_delete_cc-0']")
    MobileElement Delete;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"DELETE\"]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='DELETE']")
    MobileElement ConfirmDeletion;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"****4242\"]")
    @AndroidFindBy(xpath = "//*[@text='****4242']")
    MobileElement NewCardAddedNumber;

    @iOSXCUITFindBy(accessibility = "Expires on 07/ 27")
    @AndroidFindBy(xpath = "//*[@text='Expires on 07/ 27']")
    MobileElement EditedCard;

    @iOSXCUITFindBy(accessibility = "Add credit / debit card to make your checkout process more convenient.")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add credit / debit card to make your checkout process more convenient.']")
    MobileElement DeletedCardMsg;

    @iOSXCUITFindBy(xpath = "//*[contains(@label='Card Added Successfully’)]")
    @AndroidFindBy(xpath = "toast_wrapper")
    MobileElement SuccessToast_confirmationMessage;

    MobileActions actions;

    public CardMangmentScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void ClickOnAddNewCardCTA (){
        actions.click(AddNewCardCTA);
    }
    public void EditExistingCard (){
        actions.click(Edit);
    }
    public void DeleteCard (){
        actions.click(Delete);
        actions.click(ConfirmDeletion);
    }

    public String CardAddedSuccessfully(){
        actions.waitForElementToDisplay(NewCardAddedNumber);
        return   actions.getText(NewCardAddedNumber);
    }
    public String CardDeletedSuccessfully(){
        actions.waitForElementToDisplay(DeletedCardMsg);
        return   actions.getText(DeletedCardMsg);
    }
    public String CardEditedSuccessfully(){
        actions.waitForElementToDisplay(EditedCard);
        return   actions.getText(EditedCard);
    }

}
