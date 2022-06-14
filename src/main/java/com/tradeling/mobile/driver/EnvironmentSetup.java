package com.tradeling.mobile.driver;

import com.tradeling.apis.requests.buyerApp.PreRequisites;
import com.tradeling.apis.utility.TestDataHandler;
import com.tradeling.reporting.Reporting;
import com.tradeling.utilities.PropertyFileHandle;
import org.testng.annotations.*;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.lang.reflect.Method;

public class EnvironmentSetup {

    public static PropertyFileHandle propertyFileHandle = new PropertyFileHandle();
    public static String env = propertyFileHandle.getPropertyForExecution("appEnv");
    public static String targetApp = propertyFileHandle.getPropertyForExecution("targetApp");
    public static String executionPlatform = propertyFileHandle.getPropertyForExecution("executionPlatform");

    MobileDriver mobDriver = new MobileDriver();
    static Reporting reporting;
    public static ThreadLocal<String> platform = new ThreadLocal<String>();

    public ThreadLocal<MobileActions> actions = new ThreadLocal<MobileActions>();

    @BeforeSuite(alwaysRun = true)
    public void initEnvironment() {
        reporting = new Reporting();
        reporting.initiateReport();
        if(env.equalsIgnoreCase("local")) {
//            mobDriver.appiumInit();
        }
        if(targetApp.equalsIgnoreCase("buyerApp")){
            PreRequisites preRequisites = new PreRequisites();
            preRequisites.createUnverifiedBuyer();
            TestDataHandler.writeDataToPropertiesBuyerApp();
        }
    }


    @BeforeClass(alwaysRun = true)
    public void initDriver() throws IOException, InterruptedException, JadbException {
        platform.set(propertyFileHandle.getPropertyForExecution("executionPlatform"));
        Driver driver = null;
        if(executionPlatform.equalsIgnoreCase("android")) {
            driver = new AndroidDriver();
        }
        else if(executionPlatform.equalsIgnoreCase("ios")) {
            driver = new IosDriver();
        }
        if(env.equalsIgnoreCase("local")){
            actions.set(new MobileActions(driver.createLocalDriver()));
        }
        else if(env.equalsIgnoreCase("remote")){
            actions.set(new MobileActions(driver.createRemoteDriver()));
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method)
    {
        try {
            String test = method.getName();
            reporting.setLogger(method.getName() + " ("+ System.getProperty("deviceType")+")");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest()
    {
        actions.get().getDriver().quit();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        reporting.closeReporting();
        if(env.equalsIgnoreCase("local")) {
//            mobDriver.killAppiumService();
        }
    }
}
