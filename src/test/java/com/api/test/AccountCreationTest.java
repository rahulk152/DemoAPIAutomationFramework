package com.api.test;

import com.api.base.AuthService;
import com.api.models.request.SignupRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class AccountCreationTest {
    @Test(description = "Verify Signup API ")
    public void createAccountTest(){
       SignupRequest signupRequest = new SignupRequest.Builder()
                .userName("tejas152")
                .password("tejas152")
                .email("tejas@yahoo.in")
                .firstName("Tejas")
                .lastName("Yadav")
                .mobileNumber("7894561230")
                .Build();
        AuthService authService = new AuthService();
        Response response = authService.signUp(signupRequest);
        System.out.println(response.asPrettyString());

    }
}
