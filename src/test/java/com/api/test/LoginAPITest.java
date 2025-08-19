package com.api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginAPITest {

    @Test(description = "Verify if Login API is working....")
    public void loginTest(){
        RestAssured.baseURI = "http://64.227.160.186:8080";
        RequestSpecification x = RestAssured.given();
        RequestSpecification y = x.header("Content-Type","application/json");
        RequestSpecification z = y.body("{\n" +
                "  \"username\": \"string\",\n" +
                "  \"password\": \"string\"\n" +
                "}");
        Response response = z.post("/api/auth/login");
        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
