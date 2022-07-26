package com.tradeling.apis.requests.edukaanApp;

import com.tradeling.apis.utility.RestUtility;
import com.tradeling.utilities.Utilities;
import io.restassured.response.Response;

import javax.smartcardio.Card;
import java.util.HashMap;
import java.util.Map;

public class CardRequest {

    public void userAddNewCard() {
        Map<String, Object> header = new HashMap<String, Object>();
        header = Utilities.readJson("src/main/resources/edukaanApp/apiHeader/apiHeader.json");
        header.put("x-jwt-token", RegistrationRequests.userToken);
        Response response =
                RestUtility.postRequest(RequestUtility.AddNewCard(),header,
                        RequestUtility.getApiUrl("tradeling", "api/module-payment/v1-create-card"));
    }

}
