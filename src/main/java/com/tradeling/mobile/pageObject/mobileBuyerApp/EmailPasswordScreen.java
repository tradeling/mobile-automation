package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.reporting.Reporting;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailPasswordScreen {

    MobileActions actions;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Email & Password\"`]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email & Password']")
    MobileElement header_emailPassword;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"Current password\"`][3]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Current password']/parent::android.view.ViewGroup/preceding-sibling::android.widget.EditText")
    MobileElement textBox_currentPassword;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"New password\"`][3]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='New password']/parent::android.view.ViewGroup/preceding-sibling::android.widget.EditText")
    MobileElement textBox_newPassword;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"New password again\"`][3]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='New password again']/parent::android.view.ViewGroup/preceding-sibling::android.widget.EditText")
    MobileElement textBox_newPasswordAgain;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"SAVE\"`][2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SAVE']")
    MobileElement button_save;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"Password Changed Successfully\"`][1]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Password Changed Successfully']")
    MobileElement label_successMessage;

    public EmailPasswordScreen(MobileActions action) {
        try {
            this.actions = action;
            PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()), this);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean verifyEmailPasswordScreen(){
        boolean flag = false;
        try{
            if(actions.waitForElementToDisplay(header_emailPassword)){
                flag = true;
                Reporting.getLogger().logPass("Successfully landed on 'Email/Password' screen");
            }
            else{
                Reporting.getLogger().logFail("'Email/Password' screen not found");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Reporting.getLogger().logFail("Exception while navigating to 'Email/Password' screen", e);
        }
        return flag;
    }

    public void updatePassword(String oldPassword, String newPassword){
        actions.enterText(textBox_currentPassword, oldPassword);
        actions.enterText(textBox_newPassword, newPassword);
        actions.enterText(textBox_newPasswordAgain, newPassword);
        actions.click(button_save);
    }

    public boolean verifyPasswordUpdateIsSuccessful(){
        boolean flag = false;
        try{
            if(actions.waitForElementToDisplay(label_successMessage)){
                flag = true;
                Reporting.getLogger().logPass("The password was changed successfully");
            }
            else{
                Reporting.getLogger().logFail("Failed to reset the password");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Reporting.getLogger().logFail("Exception occurred while password change success message verification",e);
        }
        return flag;
    }

}
