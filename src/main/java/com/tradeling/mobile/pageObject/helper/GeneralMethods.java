package com.tradeling.mobile.pageObject.helper;

import com.tradeling.mobile.driver.MobileActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public class GeneralMethods {
    public static void scrollDown(double startPoint, double endPoint, MobileActions action) throws InterruptedException{
        Thread.sleep(1000);
        Dimension dimension = action.getDriver().manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * startPoint);
        int scrollEnd = (int) (dimension.getHeight() * endPoint);
        TouchAction swipe = new TouchAction(action.getDriver())
                .press(PointOption.point(0,scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(0,scrollEnd))
                .release()
                .perform();
    }
}
