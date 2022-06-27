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
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]")
    MobileElement txt_searchComponent;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"What are you looking for today?\"])[2]")
    @AndroidFindBy(xpath = "//android.widget.EditText")
    MobileElement txt_editSearchComponent;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"10 AED 150.00 Excl. VAT View Cart\"])[2]")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup")
    MobileElement btn_viewCart;

    @iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"25 AED 298.71 Excl. VAT View Cart What are you looking for today? Shop Type: Barber Shop Deals for you VIEW ALL 60% 0 Volga Sugar-1 Kg AED 2.50 AED 1.00 Excl. VAT Barber Shop categories Cans & Jars Chips & Snacks Pasta, Rice & More Manicure & Pedicure Essentials Drinks & Beverages Hair Care Body Care Shaving & Grooming Tissues & Disposables Hair Tools & Accessories Face Care Hair Colouring Test category 1 Salon Disposables Nail Tools & Accessories Hair Removal Hair Styling Sugar Salon Disinfectants & Cleaners Salon Furniture & Equipment Vertical scroll bar, 4 pages Horizontal scroll bar, 1 page\"])[11]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup")
    MobileElement MyAccountLogo;
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

    public void pressOnViewCart() {
        actions.click(btn_viewCart);
    }

    public void pressOnMyAccountLogo() {
        actions.waitForElementToDisplay(MyAccountLogo);
        actions.click(MyAccountLogo);
    }

}
