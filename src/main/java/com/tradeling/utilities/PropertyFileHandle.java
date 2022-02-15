package com.tradeling.utilities;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class PropertyFileHandle {

    Properties property = new Properties();

    public String getPropertyValue(String key, String filePath, String fileName){
        String value = "";
        try{
            property.load(new FileReader(new File(filePath+"/"+fileName).getAbsolutePath()));
            value = property.getProperty(key);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    /*
    This method load config property file for appium and returns the value of the key
     */
    public String getPropertyForAppiumConfig(String key){
        return  getPropertyValue(key,"src/main/resources/setup","appiumSetup.properties");
    }

    /*
    This method load config property file for android and returns the value of the key
     */
    public String getPropertyForAndroidConfig(String key){
        return  getPropertyValue(key,"src/main/resources/setup","androidDriver.properties");
    }

    /*
    This method load config property file for ios and returns the value of the key
     */
    public String getPropertyForIosConfig(String key){
        return  getPropertyValue(key,"src/main/resources/setup","iosDriver.properties");
    }

}
