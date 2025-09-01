package com.api.test;

import com.api.base.BaseTest;
import com.api.helper.RandomUtils;
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

public class GetAccountTest extends BaseTest {
    @Test(description = "Verify User Account via Account Number API is working....")
    public void getAccountTest(){

        // Step 1: Login
        // Step 2: Get All Accounts
        Response response = accountService.getAllAccounts(token);
        List<BankAccountResponse> accounts = Arrays.asList(response.as(BankAccountResponse[].class));

        // Step 2: Pick a random account
        BankAccountResponse randomAccount = RandomUtils.getRandomAccount(accounts);
        String accountNumber = randomAccount.getAccountNumber();
        double oldBalance = randomAccount.getBalance();


        System.out.println("Account Number: " + accountNumber);

        // Now use that account number
        response = accountService.getAccountsNumber(token, accountNumber);

    }
}
