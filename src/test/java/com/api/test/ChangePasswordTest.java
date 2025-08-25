package com.api.test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.helper.ConfigReader;
import com.api.models.request.ChangePasswordRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangePasswordTest {
    @Test(description = "Verify User change password API is working....")
    public void changePasswordTest(){
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(ConfigReader.get("username"), ConfigReader.get("password")));
        LoginResponse loginResponse = response.as(LoginResponse.class);
       // System.out.println(loginResponse.getToken());
       // System.out.println(response.asPrettyString());
        System.out.println("--------------------------------");

        UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest.Builder()
                .currentPassword("Tavish@10")
                .newPassword("#Rahulk15")
                .confirmPassword("#Rahulk15")
                .Build();
        response= userProfileManagementService.changePassword(loginResponse.getToken(),changePasswordRequest);
        System.out.println(response.asPrettyString());
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
