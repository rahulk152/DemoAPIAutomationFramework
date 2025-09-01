package com.api.testBeforeRefactoring;

import com.api.helper.ConfigReader;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import com.api.services.AuthService;
import com.api.services.UserProfileManagementService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetProfileRequestTest {
    @Test(description = "Verify User Profile API is working....")
    public void getProfileInfoTest(){
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(ConfigReader.get("username"), ConfigReader.get("password")));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(loginResponse.getToken());

        UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
        response= userProfileManagementService.getProfile(loginResponse.getToken());
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        System.out.println(userProfileResponse.getUsername());

    }
}
