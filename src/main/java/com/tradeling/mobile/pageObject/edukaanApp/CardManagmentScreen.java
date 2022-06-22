package com.tradeling.mobile.pageObject.edukaanApp;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class CardManagmentScreen {

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='ADD NEW CARD'])[1]")
    @AndroidFindBy(xpath = "//*[@text='ADD NEW CARD']")
    MobileElement AddNewCardCTA;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Edit'])[1]")
    @AndroidFindBy(xpath = "//*[@text='Edit']")
    MobileElement Edit;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Delete'])[1]")
    @AndroidFindBy(xpath = "//*[@text='Delete']")
    MobileElement Delete;

    MobileActions actions;

    public CardManagmentScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void AddNewCard (){
        actions.waitForElementToDisplay(AddNewCardCTA);
        actions.click(AddNewCardCTA);
    }
    public void EditExistingCard (){
        actions.waitForElementToDisplay(Edit);
        actions.click(Edit);
    }
    public void DeleteCard (){
        actions.waitForElementToDisplay(Delete);
        actions.click(Delete);
    }


}
