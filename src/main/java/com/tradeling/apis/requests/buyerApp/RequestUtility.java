package com.tradeling.apis.requests.buyerApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeling.data.buyerApp.BuyerRegistrationData;
import com.tradeling.utilities.Utilities;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RequestUtility {

    public String createUserRegistrationPayload(BuyerRegistrationData buyerRegistrationData) {
        try {
            Map<String, Object> buyerDetails = Utilities.readJson("src/main/resources/buyerApp/apiRequestBody/registerWithUserName.json");
            buyerDetails.put("username", buyerRegistrationData.getEmail());
            buyerDetails.put("password", buyerRegistrationData.getPassword());
            buyerDetails.put("confirmPassword", buyerRegistrationData.getPassword());
            buyerDetails.put("firstName", buyerRegistrationData.getFirstName());
            buyerDetails.put("lastName", buyerRegistrationData.getLastName());
            buyerDetails.put("companyName", buyerRegistrationData.getCompanyName());
            return new ObjectMapper().writeValueAsString(buyerDetails);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String createOtpVerificationPayload(String email) {
    try {
            Map<String, Object> verifyOtpPayload = new HashMap<String, Object>();
            verifyOtpPayload.put("username", email);
            verifyOtpPayload.put("code", "111111");
            return new ObjectMapper().writeValueAsString(verifyOtpPayload);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String createDocumentUploadPayload() {
       return Utilities.convertJsonFileToStringPayload("src/main/resources/buyerApp/apiRequestBody/documentUpload.json");

    }


}
