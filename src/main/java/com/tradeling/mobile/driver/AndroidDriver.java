package com.tradeling.mobile.driver;

import com.tradeling.utilities.PropertyFileHandle;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.lang.management.MonitorInfo;
import java.net.URL;

public class AndroidDriver extends Driver{

    PropertyFileHandle propertyFileHandle = new PropertyFileHandle();

    @Override
    public AppiumDriver<MobileElement> createLocalDriver() {

        AppiumDriver<MobileElement> driver = null;
        DesiredCapabilities caps = new DesiredCapabilities();
        try {
            // condition to handle if for launching directly the app or using apk
            String appPath = System.getProperty("appPath");
            String appName = System.getProperty("androidApp");
            if(!appPath.isEmpty()){
                caps.setCapability(MobileCapabilityType.APP, new File(appPath.trim() + "/" + appName.trim()).getAbsolutePath());
            }

            caps.setCapability(MobileCapabilityType.DEVICE_NAME, propertyFileHandle.getPropertyForDeviceDetails("android_deviceName"));
            caps.setCapability(MobileCapabilityType.UDID, propertyFileHandle.getPropertyForDeviceDetails("android_deviceId"));
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, propertyFileHandle.getPropertyForDeviceDetails("android_version"));
            caps.setCapability("appActivity", System.getProperty("appActivity"));
            caps.setCapability("appPackage", System.getProperty("appPackage"));
            caps.setCapability("autoGrantPermissions", true);
            caps.setCapability(MobileCapabilityType.NO_RESET, false);
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
//            caps.setCapability("unicodeKeyboard", true);
//            caps.setCapability("resetKeyboard", false);
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
