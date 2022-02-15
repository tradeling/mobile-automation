package com.tradeling.mobile.driver;

import com.google.common.collect.ImmutableMap;
import com.tradeling.utilities.PropertyFileHandle;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class MobileDriver {

    AppiumDriverLocalService appiumService;
    AppiumServiceBuilder serviceBuilder;
    PropertyFileHandle propertyFileHandle = new PropertyFileHandle();

    public void appiumInit(){
        try {
            // Set Capabilities for appium i.e. command timeout
            DesiredCapabilities appiumCapabilities;
            appiumCapabilities = new DesiredCapabilities();
            appiumCapabilities.setCapability("noReset", "false");
            appiumCapabilities.setCapability("newCommandTimeout", Integer.parseInt(propertyFileHandle.getPropertyForAppiumConfig("appiumCommandTimeout")));

            // Build the Appium service
            serviceBuilder = new AppiumServiceBuilder();
            serviceBuilder.withIPAddress(propertyFileHandle.getPropertyForAppiumConfig("appiumServer"));
            serviceBuilder.usingPort(Integer.parseInt(propertyFileHandle.getPropertyForAppiumConfig("appiumPort")));
            serviceBuilder.usingDriverExecutable(new File(propertyFileHandle.getPropertyForAppiumConfig("nodeJsPath")));
            serviceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "info");
            serviceBuilder.withCapabilities(appiumCapabilities);
            serviceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            serviceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
            serviceBuilder.withAppiumJS(new File(propertyFileHandle.getPropertyForAppiumConfig("appiumJsPath")));
            serviceBuilder.withEnvironment(ImmutableMap.of("ANDROID_HOME", propertyFileHandle.getPropertyForAppiumConfig("androidSdkPath")));
            // Start the server with the builder
            appiumService = AppiumDriverLocalService.buildService(serviceBuilder);
            appiumService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void killAppiumService(){
        if(appiumService.isRunning()) {
            appiumService.stop();
        }
    }
}
