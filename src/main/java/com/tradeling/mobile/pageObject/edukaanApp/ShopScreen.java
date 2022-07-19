package com.tradeling.mobile.pageObject.edukaanApp;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopScreen extends EdukaanCommon{
    MobileActions actions;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Shop name *\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField")
    @AndroidFindBy(accessibility = "txt_shop_name")
    MobileElement txt_shopName;

    @iOSXCUITFindBy(accessibility = "Please enter your shop type")
    @AndroidFindBy(accessibility = "select_shop_type")
    MobileElement ddl_shopType;

    @iOSXCUITFindBy(accessibility = "Final Regression Collection Regression Collection Restaurant Cafeteria Wholesaler Supermarket Minimarket Grocery Store Barber Shop Beauty Salon Tea Shop Baqala")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"select_shop_type\"]/android.view.ViewGroup")
    MobileElement menu_shopType;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"Please provide your TRN\"])[3]/XCUIElementTypeTextField")
    @AndroidFindBy(accessibility = "txt_trn")
    MobileElement txt_taxRegistrationNumber;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"CONTINUE\"])[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CONTINUE']")
    MobileElement btn_continueInShopScreen;

    public ShopScreen(MobileActions action){
        super(action);
        this.actions = action;
        try {
            PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void fillYourShopScreen(String shopName, String taxRegistrationNumber) throws InterruptedException {
        actions.click(txt_shopName);
        actions.enterText(txt_shopName, shopName);
        selectFromDropDownListWithoutSearch(ddl_shopType, menu_shopType, "Restaurant");
        actions.enterTextWithHideKeyboard(txt_taxRegistrationNumber, taxRegistrationNumber);
        actions.click(btn_continueInShopScreen);
    }

}
