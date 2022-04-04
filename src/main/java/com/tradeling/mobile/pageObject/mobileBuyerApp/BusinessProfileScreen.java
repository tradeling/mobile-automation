package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.reporting.Reporting;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class BusinessProfileScreen extends Common {

    MobileActions actions;

    @iOSXCUITFindBy(accessibility = "First Name* account_info_first_name")
    @AndroidFindBy(accessibility = "account_info_first_name")
    MobileElement textBox_firstName;

    @iOSXCUITFindBy(accessibility = "Last Name* account_info_last_name")
    @AndroidFindBy(accessibility = "account_info_last_name")
    MobileElement textBox_lastName;

    @iOSXCUITFindBy(accessibility = "Department*")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Department*']")
    MobileElement dropdown_department;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Which option best describes your department? CLOSE'])[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Which option best describes your department?']/following-sibling::android.view.ViewGroup//android.widget.EditText")
    MobileElement textBox_searchDepartment;

    @iOSXCUITFindBy(accessibility = "Industry*")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Industry*']")
    MobileElement dropdown_industry;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Which industry best describes your business? CLOSE'])[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Which industry best describes your business?']/following-sibling::android.view.ViewGroup//android.widget.EditText")
    MobileElement textBox_searchIndustry;

    @iOSXCUITFindBy(accessibility = "Size of the business*")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Size of the business*']")
    MobileElement dropdown_businessSize;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='What size is your business? CLOSE'])[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='What size is your business?']/following-sibling::android.view.ViewGroup//android.widget.EditText")
    MobileElement textBox_searchBusinessSize;

    @iOSXCUITFindBy(accessibility = "contact_us_phone_number")
    @AndroidFindBy(accessibility = "contact_us_phone_number")
    MobileElement textBox_phoneNumber;

    @iOSXCUITFindBy(accessibility = "REGISTER")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='REGISTER']")
    MobileElement button_register;

    @iOSXCUITFindBy(accessibility = "+971")
    @AndroidFindBy(accessibility = "+971")
    MobileElement dropdown_countryCode;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
    @AndroidFindBy(accessibility = "search_country")
    MobileElement textBox_searchCountryCode;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"User with the provided email address already exists.\"`]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='User with the provided email address already exists.']")
    MobileElement label_existingUserError;


    public BusinessProfileScreen(MobileActions action) {
        super(action);
        this.actions = action;
        try {
            PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addCustomerName(String firstName, String lastName){
        actions.enterText(textBox_firstName, firstName);
        actions.enterText(textBox_lastName, lastName);
        actions.hideKeyboard();
    }

    public void addBusinessDetails(String department, String industry, String businessSize){

        selectDropdown(dropdown_department, textBox_searchDepartment, department, "xpath-ios:(//XCUIElementTypeOther[@name='"+department+"'])[2]", "xpath-android://android.widget.TextView[@text='"+department+"']" );
        selectDropdown(dropdown_industry, textBox_searchIndustry, industry, "xpath-ios:(//XCUIElementTypeOther[@name='"+industry+"'])[2]", "xpath-android://android.widget.TextView[@text='"+industry+"']" );
        selectDropdown(dropdown_businessSize, textBox_searchBusinessSize, businessSize, "xpath-ios:(//XCUIElementTypeOther[@name='"+businessSize+"'])[2]", "xpath-android://android.widget.TextView[@text='"+businessSize+"']" );
    }

    public void addPhoneNumber(String countryCode,String phoneNumber){
//        MobileElement element_countryCode = actions.getDriver().findElement(By.name(countryCode));
//        selectDropdown(dropdown_countryCode, textBox_searchCountryCode, countryCode, element_countryCode);

        actions.enterText(textBox_phoneNumber, phoneNumber);
        actions.hideKeyboard();
    }

    public void submitBusinessProfile(){
        actions.click(button_register);
    }

    public boolean verifyExistingUserError(){
        boolean flag = false;
        try {
            if (actions.waitForElementToDisplay(label_existingUserError)) {
                flag = true;
                Reporting.getLogger().logPass("The error message '" + actions.getText(label_existingUserError) + "' was displayed");
            } else {
                Reporting.getLogger().logFail("Existing user error message was not displayed");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Reporting.getLogger().logFail("Exception while verifying existing user error", e);
        }
        return flag;
    }


}
