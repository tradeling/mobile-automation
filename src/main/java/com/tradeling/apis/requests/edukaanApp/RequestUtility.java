package com.tradeling.apis.requests.edukaanApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeling.mobile.pageObject.edukaanApp.EdukaanCommon;
import com.tradeling.utilities.PropertyFileHandle;
import com.tradeling.utilities.Utilities;

import java.util.HashMap;
import java.util.Map;

public class RequestUtility {

    public static String phoneNumber;

    public static String getApiUrl(String mainUrl, String remainingUrl){
       return PropertyFileHandle.getPropertyForEnvironmentURLs(mainUrl)
               + System.getProperty("testingEnvironment")
               + PropertyFileHandle.getPropertyForEnvironmentURLs("common_url")
               + remainingUrl;
    }

    public static String createUserLoginPayload(){
        try {
            phoneNumber = EdukaanCommon.getRandomPhoneNumber();
            Map<String, Object> userLoginPayload = new HashMap<String, Object>();
            userLoginPayload.put("phoneNumber", phoneNumber);
            userLoginPayload.put("phoneNumberCode", "971");
            return new ObjectMapper().writeValueAsString(userLoginPayload);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String createVerifyOTPPayload(){
        try {
            Map<String, Object> verifyOTPPayload = new HashMap<String, Object>();
            verifyOTPPayload.put("phoneNumber", phoneNumber);
            verifyOTPPayload.put("phoneNumberCode", "971");
            verifyOTPPayload.put("code", "1234");
            return new ObjectMapper().writeValueAsString(verifyOTPPayload);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String createRegisterUserPayload(){
        try {
            Map<String, Object> registerUserPayload = new HashMap<String, Object>();
            registerUserPayload = Utilities.readJson("src/main/resources/edukaanApp/apiRequestBody/userRegistration.json");
            registerUserPayload.put("phoneNumber", phoneNumber);
            return new ObjectMapper().writeValueAsString(registerUserPayload);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String createTradeLicenceUploadPayload() {
        return Utilities.convertJsonFileToStringPayload("src/main/resources/edukaanApp/apiRequestBody/uploadTradeLicence.json");

    }

    public static String createLoginToBackofficePayload() {
        return Utilities.convertJsonFileToStringPayload("src/main/resources/edukaanApp/apiRequestBody/loginToBackoffice.json");

    }

    public static String createApproveUserFromBackofficePayload() {
        try {
            Map<String, Object> approveUserFromBackofficePayload = new HashMap<String, Object>();
            approveUserFromBackofficePayload = Utilities.readJson("src/main/resources/edukaanApp/apiRequestBody/updateUserFromBackoffice.json");
            approveUserFromBackofficePayload.put("companyName", RegistrationRequests.registeredCompanyName);
            approveUserFromBackofficePayload.put("firstName", RegistrationRequests.registeredfirstName);
            approveUserFromBackofficePayload.put("id", RegistrationRequests.registeredID);
            approveUserFromBackofficePayload.put("lastName", RegistrationRequests.registeredlastName);
            approveUserFromBackofficePayload.put("phoneNumber", phoneNumber);
            approveUserFromBackofficePayload.put("username", "971"+phoneNumber);
            return new ObjectMapper().writeValueAsString(approveUserFromBackofficePayload);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String createUserAddToCartPayload(int productQuantity){
        try {
            Map<String, Object> addProductToTheCartPayload = new HashMap<String, Object>();
            addProductToTheCartPayload = Utilities.readJson("src/main/resources/edukaanApp/apiRequestBody/addToCart.json");
            addProductToTheCartPayload.put("qty", productQuantity);
            addProductToTheCartPayload.put("cartId", RegistrationRequests.cartId);
            return new ObjectMapper().writeValueAsString(addProductToTheCartPayload);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String AddNewCard(){
        try {
            Map<String, Object> AddNewCardPayload = new HashMap<String, Object>();
            AddNewCardPayload = Utilities.readJson("src/main/resources/edukaanApp/apiRequestBody/AddCard.json");
            return new ObjectMapper().writeValueAsString(AddNewCardPayload);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
