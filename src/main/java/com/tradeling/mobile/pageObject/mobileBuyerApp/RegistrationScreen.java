package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.reporting.Reporting;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationScreen {

    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "Create your Business profile")
    @AndroidFindBy(accessibility = "Create your Business profile")
    MobileElement label_createOwnProfile;

    @iOSXCUITFindBy(accessibility = "auth_want_to_buy_register")
    @AndroidFindBy(accessibility = "auth_want_to_buy_register")
    MobileElement link_iWantToBuy;

    @iOSXCUITFindBy(accessibility = "auth_want_to_sell_register")
    @AndroidFindBy(accessibility = "auth_want_to_sell_register")
    MobileElement link_iWantToSell;

    @iOSXCUITFindBy(accessibility = "Company name* auth_email_address_register")
    @AndroidFindBy(accessibility = "Company name* auth_email_address_register")
    MobileElement textBox_companyName;

    @iOSXCUITFindBy(accessibility = "auth_select_country_register")
    @AndroidFindBy(accessibility = "auth_select_country_register")
    MobileElement dropdown_countryOfOperation;

    @iOSXCUITFindBy(accessibility = "auth_search_country_register")
    @AndroidFindBy(accessibility = "auth_search_country_register")
    MobileElement textBox_searchCountry;

    @iOSXCUITFindBy(accessibility = "auth_on_select_country_register")
    @AndroidFindBy(accessibility = "auth_on_select_country_register")
    MobileElement link_searchedCountry;

    @iOSXCUITFindBy(accessibility = "NEXT")
    @AndroidFindBy(accessibility = "NEXT")
    MobileElement button_next;

    @iOSXCUITFindBy(accessibility = "Email address auth_email_address_register")
    @AndroidFindBy(accessibility = "auth_email_address_register")
    MobileElement textBox_email;

    @iOSXCUITFindBy(accessibility = "Password auth_password_register")
    @AndroidFindBy(accessibility = "auth_password_register")
    MobileElement textBox_password;

    @iOSXCUITFindBy(accessibility = "auth_register_next_button")
    @AndroidFindBy(accessibility = "SUBMIT AND CONTINUE")
    MobileElement button_submit;



    public RegistrationScreen(MobileActions action) {
        this.actions = action;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
    }

    public boolean verifyUserLandedRegistrationScreen(){
        boolean flag = false;
        try {
            flag = actions.waitForElementToDisplay(label_createOwnProfile);
            if(flag){
                Reporting.getLogger().logPass("Successfully landed on 'Registration screen'");
            }
            else {
                Reporting.getLogger().logFail("'Registration Screen' was not found");
            }
        }catch (Exception e){
            e.printStackTrace();
            Reporting.getLogger().logFail("Exception while navigation to Registration Screen", e);
        }
        return flag;
    }

    public void addUserTypeAndCompanyName(String userType, String companyName)
    {
        try{
            if(userType.equalsIgnoreCase("buyer")){
                actions.click(link_iWantToBuy);
            }else if(userType.equalsIgnoreCase("seller")){
                actions.click(link_iWantToSell);
            }
            actions.enterText(textBox_companyName,companyName);

        }catch (Exception e)
        {
            Reporting.getLogger().logFail("Exception while adding user type and company name", e);
        }
    }

    public void selectCountryOfOperation(String country){

        actions.click(dropdown_countryOfOperation);
        actions.enterText(textBox_searchCountry, country);
        actions.hideKeyboard();
        actions.click(link_searchedCountry);
    }

    public boolean submitAndNavigateToAddCredentials(){
        boolean flag = false;

        actions.click(button_next);

        if(actions.waitForElementToDisplay(textBox_email)){
            flag = true;
            Reporting.getLogger().logPass("Successfully landed on 'Add user email and password' screen");
        }
        else
        {
            Reporting.getLogger().logFail("Failed to navigate to 'Add user email and password' screen");
        }

        return flag;
    }

    public void addLoginCredentials(String email, String password){
        actions.enterText(textBox_email, email);
        actions.waitFor();
        actions.enterText(textBox_password, password);
        actions.hideKeyboard();
        actions.click(button_submit);
    }
}
