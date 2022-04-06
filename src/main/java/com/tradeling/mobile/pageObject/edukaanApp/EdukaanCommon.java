package com.tradeling.mobile.pageObject.edukaanApp;

import com.github.javafaker.Faker;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;

import java.util.Random;

public class EdukaanCommon extends EnvironmentSetup {

    MobileActions actions;
    MobileElement itemInDropDownListEle;

    public EdukaanCommon(MobileActions action) {
        this.actions = action;
    }

    public void selectDropdown(MobileElement dropdown, MobileElement searchBox, String valueToSearch, MobileElement valueToSelect){
        actions.click(dropdown);
        actions.enterText(searchBox,valueToSearch);
        actions.click(valueToSelect);
        actions.click(valueToSelect);
    }

    public void selectFromDropDownListWithoutSearch(MobileElement dropdown, MobileElement dropDownMenu, String itemInDropDownListText) {
        actions.click(dropdown);
        actions.waitForElementToDisplay(dropDownMenu);
        if (platform.get().equalsIgnoreCase("android")){
             itemInDropDownListEle = (MobileElement) actions.getDriver().findElementByXPath("//*[@text='"+itemInDropDownListText+"']");
        }
        if (platform.get().equalsIgnoreCase("ios")){
             itemInDropDownListEle = (MobileElement) actions.getDriver().findElementByXPath("//*[@name='"+itemInDropDownListText+"']");
        }
        actions.click(itemInDropDownListEle);
    }

    public static String getRandomPhoneNumber(){
        Faker faker = new Faker();
        int[] acceptableSecondNumbers = {0,1,2,4,5,6,8};
        int rndSecondNumber = acceptableSecondNumbers[new Random().nextInt(acceptableSecondNumbers.length-1)];
        return "5" + String.valueOf(rndSecondNumber) + faker.number().digits(7);
    }
}
