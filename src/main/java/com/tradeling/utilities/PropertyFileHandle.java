package com.tradeling.utilities;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class PropertyFileHandle {

    static Properties property = new Properties();

    public static String getPropertyValue(String key, String filePath, String fileName){
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

    public String getPropertyForExecution(String key){
        return  getPropertyValue(key,"src/main/resources/setup","ExecutionSetup.properties");
    }

    public String getPropertyForTestData(String key){
        return  getPropertyValue(key,"src/main/resources/buyerApp/testData","testData.properties");

    }

    /*
    This method load config property file for browserstack and returns the value of the key
     */
    public String getPropertyForRemoteConfig(String key){
        return  getPropertyValue(key,"src/main/resources/setup","browserStack.properties");
    }


    /*
    This method load config property file for android and ios device details and returns the value of the key
     */
    public String getPropertyForDeviceDetails(String key){
        return  getPropertyValue(key,"src/main/resources/setup","deviceDetails.properties");
    }

    public static String getPropertyForEnvironmentURLs(String key){
        return  getPropertyValue(key,"src/main/resources/setup","environmentURLs.properties");
    }

}
