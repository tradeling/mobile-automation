package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;

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
        try {
            Thread.sleep(500);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        actions.hideKeyboard();
        MobileElement ele = getElement(locatorIos, locatorAndroid);
        actions.click(ele);
    }

    public MobileElement getElement(String locatorIos, String locatorAndroid)
    {
        if(EnvironmentSetup.platform.get().equalsIgnoreCase("ios")){
            return actions.getLocator(locatorIos);
        }
        else if(EnvironmentSetup.platform.get().equalsIgnoreCase("android")){
            return actions.getLocator(locatorAndroid);
        }
        return null;
    }


}
