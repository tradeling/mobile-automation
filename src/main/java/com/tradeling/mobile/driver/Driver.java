package com.tradeling.mobile.driver;

import com.tradeling.reporting.Reporting;
import com.tradeling.utilities.PropertyFileHandle;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.util.Locale;

public abstract class Driver {

    static String remoteUser;
    static String remoteAccessKey;
    static String remoteUrl;
    static String remoteAppiumVersion;
    static String remoteAndroidDevice;
    static String remoteAndroidVersion;
    static String remoteIosDevice;
    static String remoteIosVersion;
    static String androidRemoteApp;
    static String iosRemoteApp;

    DesiredCapabilities caps = new DesiredCapabilities();
    PropertyFileHandle propertyFileHandle = EnvironmentSetup.propertyFileHandle;
    public Driver() {

        if(EnvironmentSetup.env.equalsIgnoreCase("remote")){
            setRemoteDriverDetails();
            setRemoteCapabilities();
        }
    }

    public void setRemoteDriverDetails(){
        remoteUser = propertyFileHandle.getPropertyForRemoteConfig("remoteUser");
        remoteAccessKey = propertyFileHandle.getPropertyForRemoteConfig("remoteAccessKey");
        remoteUrl = propertyFileHandle.getPropertyForRemoteConfig("remoteUrl");
        remoteAppiumVersion = propertyFileHandle.getPropertyForRemoteConfig("remoteAppiumVersion");
        remoteAndroidDevice = propertyFileHandle.getPropertyForRemoteConfig("remoteAndroidDevice");
        remoteAndroidVersion = propertyFileHandle.getPropertyForRemoteConfig("remoteAndroidVersion");
        remoteIosDevice = propertyFileHandle.getPropertyForRemoteConfig("remoteIosDevice");
        remoteIosVersion = propertyFileHandle.getPropertyForRemoteConfig("remoteIosVersion");
        androidRemoteApp = propertyFileHandle.getPropertyForRemoteConfig("androidApp");
        iosRemoteApp = propertyFileHandle.getPropertyForRemoteConfig("iosApp");
    }

    private void setRemoteCapabilities(){
        caps.setCapability("browserstack.user", remoteUser);
        caps.setCapability("browserstack.key", remoteAccessKey);
        caps.setCapability("browserstack.appium_version", remoteAppiumVersion);
        caps.setCapability("project", "Mobile Buyer App");
        caps.setCapability("build", EnvironmentSetup.platform.get().toUpperCase(Locale.ROOT));
        caps.setCapability("name", "Execution "+ Reporting.runId);
    }

    public abstract AppiumDriver<MobileElement> createLocalDriver() throws IOException, InterruptedException, JadbException;

    public abstract AppiumDriver<MobileElement> createRemoteDriver();
}
