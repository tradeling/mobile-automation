package com.tradeling.apis.requests.edukaanApp;

import com.tradeling.apis.utility.RestUtility;
import com.tradeling.utilities.Utilities;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class CartRequests {
    public void userAddProductToTheCart(int productQuantity) {
        Map<String, Object> header = new HashMap<String, Object>();
        header = Utilities.readJson("src/main/resources/edukaanApp/apiHeader/apiHeader.json");
        header.put("x-jwt-token", RegistrationRequests.userToken);
        Response response =
                RestUtility.postRequest(RequestUtility.createUserAddToCartPayload(productQuantity),
                        header,
                        RequestUtility.getApiUrl("tradeling", "module-oms/v1-add-cart-item"));
    }
}
