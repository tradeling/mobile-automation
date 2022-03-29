package com.tradeling.mobile.driver;

import com.tradeling.reporting.Reporting;
import com.tradeling.utilities.Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

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
            waitForElementIsClickable(ele);
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
            Thread.sleep(500);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void hideKeyboard() {
        try{
            if(EnvironmentSetup.platform.get().equalsIgnoreCase("ios")) {
                getDriver().getKeyboard().sendKeys("\n");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public boolean waitForElementToDisplay(MobileElement ele) {

        try{
            WebDriverWait wait = new WebDriverWait(getDriver(), 20);
            wait.until(ExpectedConditions.visibilityOf(ele));
            return ele.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            Reporting.getLogger().logFail("Element " + Utilities.getElementNameString(ele) + " is not visible/enabled", e);
            return false;
        }

    }

    public boolean waitForElementIsEnabled(MobileElement ele) {

        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 20);
            wait.until(ExpectedConditions.visibilityOf(ele));
            return ele.isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            Reporting.getLogger().logFail("Element " + Utilities.getElementNameString(ele) + " is not visible/enabled", e);
            return false;
        }

    }


    public boolean waitForElementToDisplay(MobileElement ele, int timeout) {

        try{
            WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
            wait.until(ExpectedConditions.visibilityOf(ele));
            return ele.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            Reporting.getLogger().logFail("Element " + Utilities.getElementNameString(ele) + " is not visible/enabled", e);
            return false;
        }

    }


    public boolean waitForElementIsClickable(MobileElement ele) {

        try {

            WebDriverWait wait = new WebDriverWait(getDriver(), 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            return ele.isEnabled();
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

    public MobileElement getLocator(String locator) {
        String locatorValue = locator.split(":",2)[1];
        try {
            switch (locator.split("-")[0]) {
                case "xpath":
                    return getDriver().findElement(By.xpath(locatorValue));
                case "id":
                    return getDriver().findElement(By.id(locatorValue));
                case "name":
                    return getDriver().findElement(By.name(locatorValue));
                default:
                    return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Reporting.getLogger().logFail("Element with locator '" + locator + "' is not found", e);
            return null;
        }
    }

    public void killDriver()
    {
        getDriver().quit();
    }

}
