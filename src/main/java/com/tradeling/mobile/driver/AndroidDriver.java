package com.tradeling.mobile.driver;

import com.tradeling.utilities.PropertyFileHandle;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import se.vidstige.jadb.JadbException;

import java.io.File;
import java.io.IOException;
import java.lang.management.MonitorInfo;
import java.net.URL;

public class AndroidDriver extends Driver{

    PropertyFileHandle propertyFileHandle = new PropertyFileHandle();
    DeviceManager deviceManager = new DeviceManager();

    @Override
    public AppiumDriver<MobileElement> createLocalDriver() throws IOException, InterruptedException, JadbException {
        String deviceID = deviceManager.getDeviceId();
        String deviceName = deviceManager.getDeviceName();
        AppiumDriver<MobileElement> driver = null;
        DesiredCapabilities caps = new DesiredCapabilities();
        try {
            // condition to handle if for launching directly the app or using apk
            String appPath = System.getProperty("appPath");
            String appName = System.getProperty("androidApp");
            if(!appPath.isEmpty()){
                // caps.setCapability(MobileCapabilityType.APP, new File(appPath.trim() + "/" + appName.trim()).getAbsolutePath());
            }
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            caps.setCapability(MobileCapabilityType.UDID, deviceID);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, propertyFileHandle.getPropertyForDeviceDetails("android_version"));
            caps.setCapability("appActivity", propertyFileHandle.getPropertyForExecution("appActivity"));
            caps.setCapability("appPackage", propertyFileHandle.getPropertyForExecution("appPackage"));
            caps.setCapability("autoGrantPermissions", true);
            caps.setCapability("ignoreUnimportantViews", true);
            caps.setCapability("skipDeviceInitialization", true);
            caps.setCapability("skipServerInstallation",true);
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
            caps.setCapability("unicodeKeyboard", true);
            caps.setCapability("resetKeyboard", true);
            driver = new io.appium.java_client.android.AndroidDriver<MobileElement>(new URL(
                    "http://" + propertyFileHandle.getPropertyForAppiumConfig("appiumServer") + ":" + propertyFileHandle.getPropertyForAppiumConfig("appiumPort") + "/wd/hub"), caps);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return driver;
    }


    @Override
    public AppiumDriver<MobileElement> createRemoteDriver() {

        AppiumDriver<MobileElement> driver = null;
        DesiredCapabilities caps = new DesiredCapabilities();
        try{
            caps.merge(super.caps);
            // Specify device and os_version for testing
            caps.setCapability("app", "bs://" + androidRemoteApp);
            caps.setCapability("device", remoteAndroidDevice);
            caps.setCapability("os_version", remoteAndroidVersion);
            caps.setCapability("autoGrantPermissions", true);
            driver = new io.appium.java_client.android.AndroidDriver<MobileElement>(
                    new URL(remoteUrl), caps);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return driver;
    }
}
