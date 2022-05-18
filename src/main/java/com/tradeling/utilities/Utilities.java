package com.tradeling.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.MobileElement;

import java.nio.file.Paths;
import java.time.Year;
import java.util.Map;
import java.util.Random;

public class Utilities {

    static Random unique = new Random();

    public static String getElementNameString(MobileElement element){
        if(element.toString().contains("By.chained")){
            return element.toString().replace("By.chained({By.","").replace("})","");
        }
        else{
            return element.toString().split("->")[1];
        }


    }

    public static String createUniqueId(int i) {
        return "" + unique.nextInt(i);
    }

    public static String getYear(int yearAheadOfNow){
        Year year= Year.now().plusYears(yearAheadOfNow);
        return year + "";
    }

    public static String getRandomDate(){
        return "" + new Random().nextInt(28);
    }

    public static Map<String, Object> readJson(String filePath) {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON file to map
            Map<String, Object> map = mapper.readValue(Paths.get(filePath).toFile(), Map.class);

            return map;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String convertJsonFileToStringPayload(String jsonFilePath){
        try
        {
            Map<?,?> docUploadPayload = Utilities.readJson(jsonFilePath);
            return new ObjectMapper().writeValueAsString(docUploadPayload);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
