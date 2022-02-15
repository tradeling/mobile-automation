package com.tradeling.mobile.driver;

import com.tradeling.reporting.Reporting;
import com.tradeling.utilities.Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class MobileActions {

    Duration timeOutDuration = Duration.of(200, ChronoUnit.SECONDS);
    Duration pollingDuration = Duration.of(250, ChronoUnit.MILLIS);
    public static long timeout = 120;

    static AppiumDriver<MobileElement> driver;

    public MobileActions(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void click(MobileElement ele) {
        try {
            waitForElementToDisplay(ele);
            ele.click();
            Reporting.getLogger().logPass("Click on field '" + Utilities.getElementNameString(ele) + "'");
        } catch (Exception e) {
            e.printStackTrace();
            Reporting.getLogger().logFail("Exception occurred while performing Click on field '" + Utilities.getElementNameString(ele) + "'", e);
        }
    }

    public String getText(MobileElement ele){
        String text = "";
        try{
            waitForElementToDisplay(ele);
            text = ele.getText();
            Reporting.getLogger().logPass("Fetched text from element '"+ Utilities.getElementNameString(ele) + "': '" + text + "'");

        }catch (Exception e){
            e.printStackTrace();
            Reporting.getLogger().logFail("Exception occurred while fetching text from element '" + Utilities.getElementNameString(ele) + "'", e);

        }
        return text;
    }

    public void enterText(MobileElement ele, String text) {
        try {
            if(waitForElementIsEnabled(ele)) {
                ele.setValue(text);
                Reporting.getLogger().logPass("Entered text '" + text + "' in field '" + Utilities.getElementNameString(ele) + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Reporting.getLogger().logFail("Exception occurred while performing Enter Text in field '" + Utilities.getElementNameString(ele) + "'", e);
        }
    }

    public void waitUntilAlertPresent(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(){
        try{
            Alert alert = getDriver().switchTo().alert();
            alert.accept();
        }
        catch (Exception e){
            Reporting.getLogger().logFail("Error while accepting alert", e);
            e.printStackTrace();
        }
    }

    public void dismissAlert(){
        try{
            Alert alert = getDriver().switchTo().alert();
            alert.dismiss();
        }
        catch (Exception e){
            Reporting.getLogger().logFail("Error while dismissing alert", e);
            e.printStackTrace();
        }
    }

    public String getTextFromAlert(){
        String text ="";
        try{
            Alert alert = getDriver().switchTo().alert();
           text = alert.getText();
        }
        catch (Exception e){
            Reporting.getLogger().logFail("Error while dismissing alert", e);
            e.printStackTrace();
        }
        return text;
    }

    public void waitFor(){
        try {
            getDriver().wait(2000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void hideKeyboard() {
        try{
            getDriver().getKeyboard().sendKeys("\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public boolean waitForElementToDisplay(MobileElement ele) {

            FluentWait<MobileElement> _waitForElement = new FluentWait<MobileElement>(ele);
            _waitForElement.pollingEvery(pollingDuration);
            _waitForElement.withTimeout(timeOutDuration);
            _waitForElement.ignoring(NoSuchElementException.class);
            _waitForElement.ignoring(StaleElementReferenceException.class);
            _waitForElement.ignoring(ElementNotVisibleException.class);
            Function<WebElement, Boolean> elementVisibility =null;
        try {
           elementVisibility = new Function<WebElement, Boolean>() {

                public Boolean apply(WebElement element) {
                    // TODO Auto-generated method stub
                    return element.isDisplayed();
                }

            };
            return _waitForElement.until(elementVisibility);


        } catch (Exception e) {
            e.printStackTrace();
            Reporting.getLogger().logFail("Element " + Utilities.getElementNameString(ele) + " is not visible/enabled", e);
            return false;
        }

    }

    public boolean waitForElementIsEnabled(MobileElement ele) {

        FluentWait<MobileElement> _waitForElement = new FluentWait<MobileElement>(ele);
        _waitForElement.pollingEvery(pollingDuration);
        _waitForElement.withTimeout(timeOutDuration);
        _waitForElement.ignoring(NoSuchElementException.class);
        _waitForElement.ignoring(StaleElementReferenceException.class);
        _waitForElement.ignoring(ElementNotVisibleException.class);
        Function<MobileElement, Boolean> elementEnable =null;
        try {
            elementEnable = new Function<MobileElement, Boolean>() {

                public Boolean apply(MobileElement element) {
                    // TODO Auto-generated method stub
                    return element.isEnabled();
                }

            };
            return _waitForElement.until(elementEnable);


        } catch (Exception e) {
            e.printStackTrace();
            Reporting.getLogger().logFail("Element " + Utilities.getElementNameString(ele) + " is not visible/enabled", e);
            return false;
        }

    }



    public AppiumDriver<MobileElement> getDriver(){
        return driver;
    }

    public static String getScreenShot(){
        String screenshotPath = Reporting.screenShotLoc+"/screenshot"+ Utilities.createUniqueId(99999) +".jpg";
        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            File destinationFile = new File(screenshotPath);
            FileUtils.copyFile(file, destinationFile);
            return destinationFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
