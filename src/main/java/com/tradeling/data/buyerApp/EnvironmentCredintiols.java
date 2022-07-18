package com.tradeling.data.buyerApp;

public class EnvironmentCredintiols {
    String env = System.getProperty("testingEnvironment");
    String testingUserName = "new-buyer-testing@tradeling.com";
    String testingPassword = "new-buyer-testing@tradeling.com";

    String stageUserName = "new-buyer-stage@tradeling.com";
    String stagePassword = "new-buyer-stage@tradeling.com";

    String liveUserName = "gamal.amir+7@tradeling.com";
    String livePassword = "qwerty";

    public String GetUserName(){
        switch (env){
            case "test":
                return testingUserName;
            case "stage":
                return stageUserName;
            case "live":
                return liveUserName;
            default:
                return "no env adjusted";
        }
    }

    public String GetPassword(){
        switch (env){
            case "test":
                return testingPassword;
            case "stage":
                return stagePassword;
            case "live":
                return livePassword;
            default:
                return "no env adjusted";
        }
    }
}
