package com.tradeling.apis.requests.edukaanApp;

import com.tradeling.apis.utility.RestUtility;
import com.tradeling.utilities.Utilities;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import java.util.HashMap;
import java.util.Map;

public class RegistrationRequests {
    public String userToken;
    public String backofficeUserToken;
    public static String registeredCompanyName;
    public static String registeredfirstName;
    public static String registeredID;
    public static String registeredlastName;

    public void userLogin() {
        Response response =
                RestUtility.postRequest(RequestUtility.createUserLoginPayload(),
                        Utilities.readJson("src/main/resources/edukaanApp/apiHeader/apiHeader.json"),
                        RequestUtility.getApiUrl("tradeling", "module-account/v1-edukaan-login"));
    }

    public void verifyOTP() {
        Response response =
                RestUtility.postRequest(RequestUtility.createVerifyOTPPayload(),
                        Utilities.readJson("src/main/resources/edukaanApp/apiHeader/apiHeader.json"),
                        RequestUtility.getApiUrl("tradeling", "module-account/v1-edukaan-verify-otp"));
    }

    public void registerEdukaanUser() {
        Response response =
                RestUtility.postRequest(RequestUtility.createRegisterUserPayload(),
                        Utilities.readJson("src/main/resources/edukaanApp/apiHeader/apiHeader.json"),
                        RequestUtility.getApiUrl("tradeling", "module-account/v1-register-edukaan-user"));
        JsonPath jsonPathEvaluator = response.jsonPath();
        userToken = (String) jsonPathEvaluator.get("token");
        registeredCompanyName = (String) jsonPathEvaluator.get("user.companyName");
        registeredfirstName = (String) jsonPathEvaluator.get("user.firstName");
        registeredID = (String) jsonPathEvaluator.get("user._id");
        registeredlastName = (String) jsonPathEvaluator.get("user.lastName");

    }

    public void uploadTradeLicenceDocument() {
        Map<String, Object> header = new HashMap<String, Object>();
        header = Utilities.readJson("src/main/resources/edukaanApp/apiHeader/apiHeader.json");
        header.put("x-jwt-token", userToken);
        Response response =
                RestUtility.postRequest(RequestUtility.createTradeLicenceUploadPayload(),
                        header,
                        RequestUtility.getApiUrl("tradeling", "module-buyer-center/v1-update-company-setting"));
    }

    public void loginToBackoffice(){
        Map<String, Object> header = new HashMap<String, Object>();
        header = Utilities.readJson("src/main/resources/edukaanApp/apiHeader/backofficeHeader.json");
        Response response =
                RestUtility.postRequest(RequestUtility.createLoginToBackofficePayload(),
                        header,
                        RequestUtility.getApiUrl("backoffice", "module-backoffice/v1-login-by-username"));
        JsonPath jsonPathEvaluator = response.jsonPath();
        backofficeUserToken = (String) jsonPathEvaluator.get("token");
    }

    public void approveTheUserFromBackoffice() {
        Map<String, Object> header = new HashMap<String, Object>();
        header = Utilities.readJson("src/main/resources/edukaanApp/apiHeader/backofficeHeader.json");
        header.put("x-jwt-token", backofficeUserToken);
        Response response =
                RestUtility.postRequest(RequestUtility.createApproveUserFromBackofficePayload(),
                        header,
                        RequestUtility.getApiUrl("backoffice", "module-backoffice/v1-update-customer"));
    }
}
