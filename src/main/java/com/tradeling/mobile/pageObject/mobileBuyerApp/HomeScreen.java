package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.data.buyerApp.Constants;
import com.tradeling.mobile.driver.MobileActions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen {

    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "MyAccount_tab_bar")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Account']")
    MobileElement link_account;

	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@resource-id='company-logo']/following-sibling::android.view.ViewGroup/android.widget.EditText")
	MobileElement txt_searchBox;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='CANCEL']/parent::android.view.ViewGroup/preceding-sibling::android.widget.EditText")
	MobileElement txt_searchBox2;

    public HomeScreen(MobileActions action) {
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }


    public boolean isHomeScreen(){
        boolean flag = false;
        if(actions.waitForElementToDisplay(link_account)){
            flag = true;
        }
        return flag;
    }

    public void navigateToAccountScreen(){
        actions.click(link_account);
    }

    public void searchBox(String search_Keyword){
    	txt_searchBox.click();
    	actions.click(txt_searchBox2);
    	actions.enterText(txt_searchBox2, search_Keyword);
		actions.sendKeys(Keys.ENTER);
    	
    }
}
