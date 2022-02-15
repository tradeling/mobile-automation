package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class Common {

    MobileActions actions;

    public Common(MobileActions action) {
        this.actions = action;
    }

    public void selectDropdown(MobileElement dropdown, MobileElement searchBox, String valueToSearch, MobileElement valueToSelect){
        actions.click(dropdown);
        actions.enterText(searchBox,valueToSearch);
        actions.click(valueToSelect);
    }

    public void selectDropdown(MobileElement dropdown, MobileElement searchBox, String valueToSearch, String locatorIos, String locatorAndroid){
        actions.click(dropdown);
        actions.enterText(searchBox,valueToSearch);
        actions.hideKeyboard();
        actions.click(getElement(locatorIos, locatorAndroid));
    }

    public MobileElement getElement(String locatorIos, String locatorAndroid)
    {
        if(EnvironmentSetup.env.get().equalsIgnoreCase("ios")){
            return getLocator(locatorIos);
        }
        else if(EnvironmentSetup.env.get().equalsIgnoreCase("android")){
            return getLocator(locatorAndroid);
        }
        return null;
    }

    public MobileElement getLocator(String locator){
        String locatorValue= locator.split(":")[1];
        switch (locator.split("-")[0]){
            case "xpath" : return actions.getDriver().findElement(By.xpath(locatorValue));
            case "ID" : return actions.getDriver().findElement(By.id(locatorValue));
            default: return null;
        }
    }
}
