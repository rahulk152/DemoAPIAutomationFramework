package com.api.test;

import com.api.base.AccountService;
import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetAccountUserTest {
        @Test(description = "Verify User all Account API is working....")
        public void getAccountUserTest(){
            AuthService authService = new AuthService();
            Response response = authService.login(new LoginRequest("rahulk15","#Rahulk15"));
            LoginResponse loginResponse = response.as(LoginResponse.class);

            AccountService accountService = new AccountService();
            response= accountService.accountsUser(loginResponse.getToken());
            System.out.println(response.getStatusCode());


        }
}
