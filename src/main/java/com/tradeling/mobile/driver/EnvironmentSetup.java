package com.tradeling.mobile.driver;

import com.tradeling.reporting.Reporting;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class EnvironmentSetup {

    MobileDriver mobDriver = new MobileDriver();
    Reporting reporting;
    public static ThreadLocal<String> env = new ThreadLocal<String>();

    public ThreadLocal<MobileActions> actions = new ThreadLocal<MobileActions>();

    @BeforeSuite(alwaysRun = true)
    public void initEnvironment() {
        reporting = new Reporting();
        reporting.initiateReport();
        mobDriver.appiumInit();
    }


    @BeforeTest(alwaysRun = true)
    @Parameters("deviceType")
    public void initDriver(String deviceType) {
        env.set(deviceType);
        if(deviceType.equalsIgnoreCase("android")) {
            actions.set( new MobileActions(new AndroidDriver().createDriver()));
        }
        else if(deviceType.equalsIgnoreCase("ios")) {
            actions.set( new MobileActions(new IosDriver().createDriver()));
        }

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method)
    {
        reporting.setLogger(method.getName());

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        reporting.closeReporting();
        mobDriver.killAppiumService();
    }
}
