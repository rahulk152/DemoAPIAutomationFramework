package com.api.beforeTest;

import com.api.helper.ConfigReader;
import com.api.helper.RandomUtils;
import com.api.models.request.DepositAmountRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.BankAccountResponse;
import com.api.models.response.LoginResponse;
import com.api.models.response.depositResponse;
import com.api.services.AccountService;
import com.api.services.AuthService;
import com.api.services.TransactionService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class DepositAmountInAccountBeforeTest {
    @Test(description = "Verify deposit amount in the account API is working....")
    public void depositAmountInAccountTest(){
        // Step 1: Login
        AuthService authService = new AuthService();
        Response response = authService.login(
                new LoginRequest(ConfigReader.get("username"), ConfigReader.get("password")));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        // Step 2: Get All Accounts
        AccountService accountService = new AccountService();
        response = accountService.getAllAccounts(loginResponse.getToken());
        List<BankAccountResponse> accounts = Arrays.asList(response.as(BankAccountResponse[].class));

        // Step 3: Pick a random account
        BankAccountResponse randomAccount = RandomUtils.getRandomAccount(accounts);
        String accountNumber = randomAccount.getAccountNumber();
        double oldBalance = randomAccount.getBalance();
        System.out.println("Picked Account: " + accountNumber + " | Old Balance: " + oldBalance);

        // Step 4: Deposit random amount
        int depositAmount = RandomUtils.getRandomAmount(1000, 50000);
        TransactionService transactionService = new TransactionService();
        response = transactionService.deposit(loginResponse.getToken(),
                new DepositAmountRequest(accountNumber, depositAmount, "Random Deposit " + depositAmount));
        depositResponse depositResponse = response.as(depositResponse.class);
        System.out.println("Deposited Amount: " + depositResponse.getAmount());

        // Step 5: Fetch account again (to get updated balance)
        response = accountService.getAccountsNumber(loginResponse.getToken(), accountNumber);
        BankAccountResponse updatedAccount = response.as(BankAccountResponse.class);
        double newBalance = updatedAccount.getBalance();
        System.out.println("New Balance: " + newBalance);

        // Step 6: Assertion
        Assert.assertEquals(newBalance, oldBalance + depositAmount,
                "Balance did not increase correctly after deposit!");
    }

}
