package com.api.test;

import com.api.base.BaseAssertions;
import com.api.base.BaseTest;
import com.api.helper.AssertionHelper.AccountAssertions;
import com.api.helper.RandomUtils;
import com.api.services.AuthService;
import com.api.services.UserProfileManagementService;
import com.api.helper.ConfigReader;
import com.api.models.request.LoginRequest;
import com.api.models.request.ProfileRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateProfileTest extends BaseTest {
    @Test(description = "Verify User update Profile API is working....")
    public void getProfileInfoTest(){
// Step 1: Fetch profile
        Response response = userProfileManagementService.getProfile(token);
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);

        // Assertions for GET profile
        BaseAssertions.assertStatusCode(response, 200);
        AccountAssertions.assertNotNullOrEmpty("Username", userProfileResponse.getUsername());

        // Step 2: Create profile update request with test data
        ProfileRequest profileRequest = new ProfileRequest.Builder()
                .firstName("Rahul")
                .lastName("Kumar")
                .email(RandomUtils.getRandomEmail())   // avoid conflicts by randomizing email
                .mobileNumber(RandomUtils.getRandomMobileNumber()) // utility method
                .Build();

        // Step 3: Update profile
        response = userProfileManagementService.updateProfile(token, profileRequest);
        UserProfileResponse updatedProfile = response.as(UserProfileResponse.class);

        // Step 4: Assertions
        BaseAssertions.assertStatusCode(response, 200);
        AccountAssertions.assertEquals("First Name", updatedProfile.getFirstName(), profileRequest.getFirstName());
        AccountAssertions.assertEquals("Last Name", updatedProfile.getLastName(), profileRequest.getLastName());
        AccountAssertions.assertEquals("Email", updatedProfile.getEmail(), profileRequest.getEmail());
        AccountAssertions.assertEquals("Mobile", updatedProfile.getMobileNumber(), profileRequest.getMobileNumber());
    }
}
