package com.api.test;

import com.api.base.AccountService;
import com.api.base.AuthService;
import com.api.models.request.CreateBankAccountRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.BankAccountResponse;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetAccountTest {
    @Test(description = "Verify User Account via Account Number API is working....")
    public void getAccountTest(){
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("rahulk15","#Rahulk15"));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        AccountService accountService = new AccountService();
        CreateBankAccountRequest createBankAccountRequest = new CreateBankAccountRequest("SALARY","South Branch");
        response= accountService.accounts(loginResponse.getToken(),createBankAccountRequest);

        BankAccountResponse bankAccountResponse = response.as(BankAccountResponse.class);
        response= accountService.getaccountsNumber(loginResponse.getToken(), bankAccountResponse.getAccountNumber());

    }
}
