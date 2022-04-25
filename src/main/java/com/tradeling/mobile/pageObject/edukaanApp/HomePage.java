package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    MobileActions actions;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"What are you looking for today?\"])[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='What are you looking for today?']")
    MobileElement txt_searchComponent;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"What are you looking for today?\"])[2]")
    @AndroidFindBy(xpath = "//android.widget.EditText")
    MobileElement txt_editSearchComponent;

    public HomePage(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public boolean searchComponentIsDisplayed()  {
        actions.waitForElementToDisplay(txt_searchComponent);
      return  actions.waitForElementToDisplay(txt_searchComponent);
    }

    public void searchWithProductName(String productName) {
        actions.click(txt_searchComponent);
        actions.enterTextWithHideKeyboard(txt_editSearchComponent, productName);
        if (EnvironmentSetup.platform.get().equalsIgnoreCase("android")){
            actions.click(txt_editSearchComponent);
            actions.hideKeyboard();
        }
    }
}
