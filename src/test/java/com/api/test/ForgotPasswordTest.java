package com.api.test;

import com.api.services.AuthService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ForgotPasswordTest {


    @Test(description = "Verify forgot API ")
    public void forgotPasswordTest() {
        AuthService authService = new AuthService();
        Response response = authService.forgotPassword("ram@yahoo.in");
        System.out.println(response.asPrettyString());

    }

}
