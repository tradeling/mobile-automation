package com.tradeling.apis.utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestUtility {

    public static Response postRequest(String body, Map header, String url){

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.body(body);
        if(header!=null) {
            request.headers(header);
        }
        Response response = request.post(url);

        return response;
    }



}
