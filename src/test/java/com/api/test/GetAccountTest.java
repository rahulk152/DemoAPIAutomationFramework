package com.api.test;

import com.api.services.AccountService;
import com.api.services.AuthService;
import com.api.helper.ConfigReader;
import com.api.models.request.LoginRequest;
import com.api.models.response.BankAccountResponse;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class GetAccountTest {
    @Test(description = "Verify User Account via Account Number API is working....")
    public void getAccountTest(){

        // Step 1: Login
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(ConfigReader.get("username"),ConfigReader.get("password")));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        AccountService accountService = new AccountService();

        // Step 2: API returning multiple accounts
        response= accountService.getAllAccounts(loginResponse.getToken());

        // Step 3: Convert JSON Array → List of BankAccountResponse
        List<BankAccountResponse> accounts = Arrays.asList(response.as(BankAccountResponse[].class));
        BankAccountResponse latestAccount = accounts.get(accounts.size() - 1); // last account
        String accountNumber = latestAccount.getAccountNumber();


        System.out.println("Latest Account Number: " + accountNumber);

        // Now use that account number
        response = accountService.getAccountsNumber(loginResponse.getToken(), accountNumber);




    }
}
