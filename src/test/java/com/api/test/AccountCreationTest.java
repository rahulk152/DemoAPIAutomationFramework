package com.api.test;

import com.api.services.AuthService;
import com.api.helper.ConfigReader;
import com.api.models.request.SignupRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class AccountCreationTest {
    @Test(description = "Verify Signup API ")
    public void createAccountTest(){
       SignupRequest signupRequest = new SignupRequest.Builder()
                .userName(ConfigReader.get("newUsername"))
                .password(ConfigReader.get("newPassword"))
                .email(ConfigReader.get("newEmail"))
                .firstName(ConfigReader.get("newFirstName"))
                .lastName(ConfigReader.get("newLastName"))
                .mobileNumber(ConfigReader.get("newMobileNumber"))
                .Build();
        AuthService authService = new AuthService();
        Response response = authService.signUp(signupRequest);
        System.out.println(response.asPrettyString());

    }
}
