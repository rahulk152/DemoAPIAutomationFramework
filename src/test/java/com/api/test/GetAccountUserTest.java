package com.api.test;

import com.api.base.BaseTest;
import com.api.services.AccountService;
import com.api.services.AuthService;
import com.api.helper.ConfigReader;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetAccountUserTest extends BaseTest {
        @Test(description = "Verify User all Account API is working....")
        public void getAccountUserTest(){
            Response response= accountService.getAllAccounts(token);
            System.out.println(response.getStatusCode());

        }
}
