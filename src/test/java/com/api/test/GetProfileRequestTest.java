package com.api.test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetProfileRequestTest {
    @Test(description = "Verify User Profile API is working....")
    public void getProfileInfoTest(){
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("rahulk15","#Rahulk15"));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(loginResponse.getToken());

        UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
        response= userProfileManagementService.getProfile(loginResponse.getToken());
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        System.out.println(userProfileResponse.getUsername());

    }
}
