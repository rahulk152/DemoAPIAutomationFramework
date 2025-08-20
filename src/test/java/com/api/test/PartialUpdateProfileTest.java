package com.api.test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.request.LoginRequest;
import com.api.models.request.ProfileRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PartialUpdateProfileTest {
    @Test(description = "Verify User Partial update Profile API is working....")
    public void getProfileInfoTest(){
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("rahulk15","#Rahulk15"));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(response.asPrettyString());

        System.out.println("------------------------------------------------");

        UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
        response= userProfileManagementService.getProfile(loginResponse.getToken());
        System.out.println(response.asPrettyString());
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        Assert.assertEquals(userProfileResponse.getUsername(),"rahulk15");
        System.out.println("---------------------------------------------------");

        ProfileRequest profileRequest = new ProfileRequest.Builder()
                .firstName("Ram")
                .mobileNumber("3534546456")
                .Build();

        response= userProfileManagementService.partialUpdateProfile(loginResponse.getToken(),profileRequest);
        System.out.println(response.asPrettyString());
    }
}
