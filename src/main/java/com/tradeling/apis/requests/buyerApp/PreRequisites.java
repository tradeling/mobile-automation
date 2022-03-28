package com.tradeling.apis.requests.buyerApp;

import com.tradeling.apis.utility.TestDataHandler;
import com.tradeling.data.buyerApp.BuyerRegistrationData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PreRequisites {

    public void createUnverifiedBuyer(){
        RegistrationRequests registrationRequests = new RegistrationRequests();
        BuyerRegistrationData buyerRegistrationData = new BuyerRegistrationData("buyer","");
        registrationRequests.registerBuyer(buyerRegistrationData);
        Response response = registrationRequests.verifyOtpForUser(buyerRegistrationData.getEmail());
        if(response.statusCode()==200)
        {
            TestDataHandler.testData.put("unverifiedBuyerEmail",buyerRegistrationData.getEmail());
            TestDataHandler.testData.put("unverifiedBuyerPassword",buyerRegistrationData.getPassword());
        }
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.get("token");
        registrationRequests.uploadDocsForUser(token);
    }

}
