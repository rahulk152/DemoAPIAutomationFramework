package com.api.test;

import com.api.base.BaseTest;
import com.api.helper.AssertionHelper.Assertions;
import com.api.models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetProfileRequestTest extends BaseTest {
    @Test(description = "Verify User Profile API is working....")
    public void getProfileInfoTest(){
        // Step 1: Call profile API
        Response response = userProfileManagementService.getProfile(token);

        // Step 2: Deserialize response
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);

        // Step 3: Assertions
        Assertions.validateUserProfile(response, userProfileResponse);



    }
}
