package com.tradeling.apis.requests.buyerApp;

import com.tradeling.apis.utility.RestUtility;
import com.tradeling.data.buyerApp.BuyerRegistrationData;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class RegistrationRequests {

    RequestUtility requestUtility = new RequestUtility();
    RestUtility restUtility = new RestUtility();

    public void registerBuyer(BuyerRegistrationData buyerRegistrationData){
        Map<String, String> header = new HashMap<String, String>();
        header.put("x-store-id","tcom-mobile-ae");
        header.put("g-recaptcha-neglect","ignore");
       Response response =
               RestUtility.postRequest(requestUtility.createUserRegistrationPayload(buyerRegistrationData),header,"https://accounts.tradelingstage.com/api/module-account/v1-register-buyer-by-username");

    }

    public Response verifyOtpForUser(String email){
        Response response =
               RestUtility.postRequest(requestUtility.createOtpVerificationPayload(email), null,"https://accounts.tradelingstage.com/api/module-account/v1-verify-by-username");
    return response;
    }

    public void uploadDocsForUser(String token){
        Map<String, String> header = new HashMap<String, String>();
        header.put("x-jwt-token", token);
        Response response =
                RestUtility.postRequest(requestUtility.createDocumentUploadPayload(), header, "https://accounts.tradelingstage.com/api/module-buyer-center/v1-update-company-setting");
    }




}
