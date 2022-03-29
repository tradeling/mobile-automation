package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileScreen {
    MobileActions actions;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.EditText[1]")
    MobileElement txt_firstName;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.EditText[2]")
    MobileElement txt_lastName;

    @iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CONTINUE']")
    MobileElement btn_continueInProfileScreen;

    public ProfileScreen(MobileActions action){
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public void fillTheProfileScreen(String firstName, String lastName) {
        actions.enterText(txt_firstName, firstName);
        actions.enterText(txt_lastName, lastName);
        actions.click(btn_continueInProfileScreen);
    }
}
