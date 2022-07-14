package com.tradeling.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.tradeling.mobile.driver.MobileActions;

public class Logger{

    ExtentTest extentTest;

    public Logger(ExtentTest extentTest) {
        this.extentTest =  extentTest;
    }

    public void logPass(String description){
        extentTest.pass(description);
    }

    public void logInfo(String description){
        extentTest.info(description);
    }

    public void logError(String description){
        extentTest.warning(description);
    }

    public void logFail(String description){
        extentTest.fail(description).fail(MediaEntityBuilder.createScreenCaptureFromPath(MobileActions.getScreenShot()).build());
    }

    public void logFail(String description, Exception exception){
        extentTest.fail(description).fail(exception, MediaEntityBuilder.createScreenCaptureFromPath(MobileActions.getScreenShot()).build());
    }
}
