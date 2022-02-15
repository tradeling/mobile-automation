package com.tradeling.mobile.driver;

import com.tradeling.utilities.PropertyFileHandle;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Alert;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class IosDriver {

    PropertyFileHandle propertyFileHandle = new PropertyFileHandle();

    public AppiumDriver<MobileElement> createDriver() {
        AppiumDriver<MobileElement> driver = null;
        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            // condition to handle if for launching directly the app or using apk
            String appPath = propertyFileHandle.getPropertyForIosConfig("appPath");
            String appName = propertyFileHandle.getPropertyForIosConfig("appName");
            if(!appPath.isEmpty()){
                caps.setCapability(MobileCapabilityType.APP, new File(appPath.trim() + "/" + appName.trim()).getAbsolutePath());
            }
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, propertyFileHandle.getPropertyForIosConfig("deviceName"));
            caps.setCapability(MobileCapabilityType.UDID, propertyFileHandle.getPropertyForIosConfig("deviceId"));
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            caps.setCapability("bundleId", propertyFileHandle.getPropertyForIosConfig("bundleId"));
            caps.setCapability(MobileCapabilityType.NO_RESET, "false");

            driver = new IOSDriver<>(new URL(
                    "http://" + propertyFileHandle.getPropertyForAppiumConfig("appiumServer") + ":" + propertyFileHandle.getPropertyForAppiumConfig("appiumPort") + "/wd/hub"), caps);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }
}
