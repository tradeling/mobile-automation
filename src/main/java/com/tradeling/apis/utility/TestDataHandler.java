package com.tradeling.apis.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestDataHandler {

    public static Map<String, String >  testData = new HashMap<String, String>();

    public static void writeDataToProperties(){
        Properties properties = new Properties();
        properties.putAll(testData);
        saveProperties(properties);
    }

    private static void saveProperties(Properties p)
    {

        try{
            FileOutputStream fr = new FileOutputStream(new File("src/main/resources/buyerApp/testData/testData.properties"));;
            p.store(fr, "Test Data");
            fr.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
