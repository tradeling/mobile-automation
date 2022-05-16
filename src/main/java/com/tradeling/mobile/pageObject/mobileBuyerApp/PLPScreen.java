package com.tradeling.mobile.pageObject.mobileBuyerApp;

import org.openqa.selenium.support.PageFactory;

import com.tradeling.mobile.driver.MobileActions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PLPScreen {

    MobileActions actions;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Lavazza blue coffee capsules']")
	    MobileElement productPLP;


	    public PLPScreen(MobileActions action) {
	        this.actions = action;
	        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
	    }


	    public void clickOnProduct(){
	        actions.click(productPLP);
	    }
}
