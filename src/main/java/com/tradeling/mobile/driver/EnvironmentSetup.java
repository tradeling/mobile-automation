package com.tradeling.mobile.driver;

import com.tradeling.apis.requests.buyerApp.PreRequisites;
import com.tradeling.apis.utility.TestDataHandler;
import com.tradeling.reporting.Reporting;
import com.tradeling.utilities.PropertyFileHandle;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class EnvironmentSetup {

    public static String env = System.getProperty("environment");
    MobileDriver mobDriver = new MobileDriver();
    static Reporting reporting;
    public static PropertyFileHandle propertyFileHandle = new PropertyFileHandle();
    public static ThreadLocal<String> platform = new ThreadLocal<String>();

    public ThreadLocal<MobileActions> actions = new ThreadLocal<MobileActions>();

    @BeforeSuite(alwaysRun = true)
    public void initEnvironment() {
        reporting = new Reporting();
        reporting.initiateReport();
        if(env.equalsIgnoreCase("local")) {
            mobDriver.appiumInit();
        }
        if(System.getProperty("appName").equalsIgnoreCase("buyerApp")){
            PreRequisites preRequisites = new PreRequisites();
            preRequisites.createUnverifiedBuyer();
            TestDataHandler.writeDataToPropertiesBuyerApp();
        }
    }

    @BeforeTest(alwaysRun = true)
    public void initDriver() {
        platform.set(System.getProperty("deviceType"));
        Driver driver = null;
        if(System.getProperty("deviceType").equalsIgnoreCase("android")) {
            driver = new AndroidDriver();
        }
        else if(System.getProperty("deviceType").equalsIgnoreCase("ios")) {
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

    @AfterTest(alwaysRun = true)
    public void afterTest()
    {
        actions.get().getDriver().quit();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        reporting.closeReporting();
        if(env.equalsIgnoreCase("local")) {
//            mobDriver.killAppiumService();
        }
    }
}
