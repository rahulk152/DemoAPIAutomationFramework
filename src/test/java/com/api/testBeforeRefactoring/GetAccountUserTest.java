package com.api.testBeforeRefactoring;

import com.api.base.BaseTest;
import com.api.helper.ConfigReader;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import com.api.services.AccountService;
import com.api.services.AuthService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetAccountUserTest {
        @Test(description = "Verify User all Account API is working....")
        public void getAccountUserTest(){
            AuthService authService = new AuthService();
            Response response = authService.login(new LoginRequest(ConfigReader.get("username"),ConfigReader.get("password")));
            LoginResponse loginResponse = response.as(LoginResponse.class);

            AccountService accountService = new AccountService();
            response= accountService.getAllAccounts(loginResponse.getToken());
            System.out.println(response.getStatusCode());

        }
}
