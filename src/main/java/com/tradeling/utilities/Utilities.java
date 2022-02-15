package com.tradeling.utilities;

import io.appium.java_client.MobileElement;

import java.time.Year;
import java.util.Date;
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

}
