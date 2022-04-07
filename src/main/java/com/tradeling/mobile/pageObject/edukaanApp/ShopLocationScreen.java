package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopLocationScreen extends EdukaanCommon {
    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "Dubai")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup")
    MobileElement ddl_city;

    @iOSXCUITFindBy(accessibility = "Abu Dhabi Dubai Sharjah Ajman Al Ain Fujairah Ras Al-Khaimah Umm Al Quwain Horizontal scroll bar, 1 page")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]")
    MobileElement menu_city;

    @iOSXCUITFindBy(accessibility = "Please select your area")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup")
    MobileElement ddl_area;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"Search for country\"])[5]/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText")
    MobileElement txt_searchArea;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"Al Jafiliya\"])[2]")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    MobileElement link_searchAreaResult;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"e.g Al Wasl Rd\"])[3]/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.EditText[1]")
    MobileElement txt_street;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"e.g Nahda Tower, G Floor, Shop3\"])[3]/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.EditText[2]")
    MobileElement txt_buildingName;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"REGISTER\"])[1]")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup/android.view.ViewGroup")
    MobileElement btn_register;

    public ShopLocationScreen(MobileActions action){
        super(action);
        this.actions = action;
        try {
            PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void fillShopLocationScreen(String city, String area, String street, String buildName){
        if (EnvironmentSetup.platform.get().equalsIgnoreCase("android")){
            selectFromDropDownListWithoutSearch(ddl_city, menu_city, city);
        }
        selectDropdown(ddl_area, txt_searchArea, area, link_searchAreaResult);
        actions.enterTextWithHideKeyboard(txt_street, street);
        actions.enterTextWithHideKeyboard(txt_buildingName, buildName);
        actions.click(btn_register);
    }
}
