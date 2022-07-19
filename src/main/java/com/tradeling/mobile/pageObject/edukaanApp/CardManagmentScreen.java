package com.tradeling.mobile.pageObject.edukaanApp;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class CardManagmentScreen {

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"cta_add_cc\"]")
    @AndroidFindBy(xpath = "//*[@text='ADD NEW CARD']")
    MobileElement AddNewCardCTA;

    @iOSXCUITFindBy(accessibility = "cta_edit_cc-0")
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/child::android.view.ViewGroup//android.widget.TextView[@resource-id='cta_edit_cc-0']")
    MobileElement Edit;

    @iOSXCUITFindBy(accessibility = "cta_delete_cc-0")
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/child::android.view.ViewGroup//android.widget.TextView[@resource-id='cta_delete_cc-0']")
    MobileElement Delete;

    @iOSXCUITFindBy(accessibility = "****9271 Expires on 02/ 23 Edit Delete ****3614 Expires on 08/ 23 Edit Delete ****1111 Expires on 06/ 27 Edit Delete cta_add_cc")
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/child::android.view.ViewGroup//android.widget.TextView[contains(@resource-id,'cta_edit_cc')]")
    MobileElement CardNumberList;

    MobileActions actions;

    public CardManagmentScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void ClickOnAddNewCardCTA (){
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
