package com.api.test;

import com.api.base.AuthService;
import com.api.helper.ConfigReader;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

// why we do the static import for Rest Assured is because of readability
@Listeners(com.api.listeners.TestListener.class)
public class LoginAPITest3 {

    @Test(description = "Verify if Login API is working....")
    public void loginTest(){
        LoginRequest loginRequest = new LoginRequest(ConfigReader.get("username"), ConfigReader.get("password"));
        AuthService authService = new AuthService();
        Response response = authService.login(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(response.asPrettyString());
        System.out.println(loginResponse.getToken());
        System.out.println(loginResponse.getEmail());
        System.out.println(loginResponse.getId());

        Assert.assertTrue(loginResponse.getToken() != null);
        Assert.assertEquals(loginResponse.getEmail(),"rahulkumaraiet@yahoo.in");
        Assert.assertEquals(loginResponse.getId(),2358);
    }
}
