package com.tradeling.mobile.driver;

import com.beust.jcommander.Parameter;
import com.tradeling.apis.requests.buyerApp.PreRequisites;
import com.tradeling.apis.utility.TestDataHandler;
import com.tradeling.data.buyerApp.EnvironmentCredintiols;
import com.tradeling.mobile.pageObject.mobileBuyerApp.*;
import com.tradeling.reporting.Reporting;
import com.tradeling.utilities.PropertyFileHandle;
import org.testng.annotations.*;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.lang.reflect.Method;

public class EnvironmentSetup {

    public SearchScreen searchScreen;
    public LoginScreen loginScreen;
    public LaunchScreen launchScreen;
    public HomeScreen homeScreen;
    public AccountScreen accountScreen;
    public EmailPasswordScreen emailPasswordScreen;
    public OTPScreen otpScreen;
    public DocumentUploadScreen documentUploadScreen;
    public RegistrationScreen registrationScreen;
    public BusinessProfileScreen businessProfileScreen;
    public CompanyProfileScreen companyProfileScreen;
    public static PropertyFileHandle propertyFileHandle = new PropertyFileHandle();
    public static String env = System.getProperty("environment");
    public static String targetApp = System.getProperty("appName");
//    public static String targetApp ="buyerApp";

    public EnvironmentCredintiols environmentCredintiols = new EnvironmentCredintiols();
//    public static String executionPlatform = "android";
    public static String executionPlatform = System.getProperty("deviceType");

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


    @BeforeMethod(alwaysRun = true)
    public void initDriver(Method method) throws IOException, InterruptedException, JadbException {
        platform.set(executionPlatform);
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
        loginScreen = new LoginScreen(actions.get());
        launchScreen = new LaunchScreen(actions.get());
        homeScreen = new HomeScreen(actions.get());
        accountScreen = new AccountScreen(actions.get());
        emailPasswordScreen = new EmailPasswordScreen(actions.get());
        searchScreen = new SearchScreen(actions.get());
        otpScreen = new OTPScreen(actions.get());
        documentUploadScreen = new DocumentUploadScreen(actions.get());
        registrationScreen = new RegistrationScreen(actions.get());
        businessProfileScreen = new BusinessProfileScreen(actions.get());
        companyProfileScreen = new CompanyProfileScreen(actions.get());
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
