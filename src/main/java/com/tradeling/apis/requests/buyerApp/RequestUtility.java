package com.tradeling.apis.requests.buyerApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeling.data.buyerApp.BuyerRegistrationData;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RequestUtility {

    public String createUserRegistrationPayload(BuyerRegistrationData buyerRegistrationData){
        try{
            Map<String,Object> buyerDetails = readJson("src/main/resources/buyerApp/apiRequestBody/registerWithUserName.json");
            buyerDetails.put("username",buyerRegistrationData.getEmail());
            buyerDetails.put("password",buyerRegistrationData.getPassword());
            buyerDetails.put("confirmPassword",buyerRegistrationData.getPassword());
            buyerDetails.put("firstName",buyerRegistrationData.getFirstName());
            buyerDetails.put("lastName",buyerRegistrationData.getLastName());
            buyerDetails.put("companyName",buyerRegistrationData.getCompanyName());
            return new ObjectMapper().writeValueAsString(buyerDetails);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public String createOtpVerificationPayload(String email){
        try{
            Map<String, Object> verifyOtpPayload = new HashMap<String, Object>();
            verifyOtpPayload.put("username",email);
            verifyOtpPayload.put("code", "111111");
            return new ObjectMapper().writeValueAsString(verifyOtpPayload);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public String createDocumentUploadPayload(){
        try
        {
            Map<?,?> docUploadPayload = readJson("src/main/resources/buyerApp/apiRequestBody/documentUpload.json");
            return new ObjectMapper().writeValueAsString(docUploadPayload);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }



    public Map<String, Object> readJson(String filePath) {
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




}
