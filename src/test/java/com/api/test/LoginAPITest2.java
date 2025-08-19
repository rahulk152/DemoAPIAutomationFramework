package com.api.test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

// why we do the static import for Rest Assured is because of readability

public class LoginAPITest2 {

    @Test(description = "Verify if Login API is working....")
    public void loginTest(){
        Response response = given()
                .baseUri("http://64.227.160.186:8080")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"username\": \"string\",\n" +
                        "  \"password\": \"string\"\n" +
                        "}")
                .post("/api/auth/login");
        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
