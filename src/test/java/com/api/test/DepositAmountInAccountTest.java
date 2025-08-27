package com.api.test;

import com.api.base.AccountService;
import com.api.base.AuthService;
import com.api.base.TransactionService;
import com.api.helper.ConfigReader;
import com.api.models.request.LoginRequest;
import com.api.models.request.DepositAmountRequest;
import com.api.models.response.BankAccountResponse;
import com.api.models.response.LoginResponse;
import com.api.models.response.depositResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DepositAmountInAccountTest {
    @Test(description = "Verify deposit amount in the account API is working....")
    public void depositAmountInAccountTest(){
        // Step 1: Login
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(ConfigReader.get("username"),ConfigReader.get("password")));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        // Step 2: Get All Account
        AccountService accountService = new AccountService();
        response= accountService.getAllAccounts(loginResponse.getToken());

        // Step 3: Convert JSON Array → List of BankAccountResponse
        List<BankAccountResponse> accounts = Arrays.asList(response.as(BankAccountResponse[].class));

        // Step 4: Pick a random account
        BankAccountResponse randomAccount = accounts.get(ThreadLocalRandom.current().nextInt(accounts.size()));
        String accountNumber = randomAccount.getAccountNumber();
        double oldBalance = randomAccount.getBalance(); // current balance
        System.out.println("Picked Account: " + accountNumber + " | Old Balance: " + oldBalance);

        // Step 5: Deposit random amount
        int depositAmount = ThreadLocalRandom.current().nextInt(1000, 50000); // random between 100-5000
        TransactionService transactionService = new TransactionService();
        response = transactionService.deposit(loginResponse.getToken(),
                new DepositAmountRequest(accountNumber, depositAmount, "Random Deposit " + depositAmount));
        depositResponse depositResponse = response.as(depositResponse.class);
        System.out.println("Deposited Amount: " + depositResponse.getAmount());

        // Step 6: Fetch account again (to get updated balance)
        response = accountService.getAccountsNumber(loginResponse.getToken(), accountNumber);
        BankAccountResponse updatedAccount = response.as(BankAccountResponse.class);
        double newBalance = updatedAccount.getBalance();
        System.out.println("New Balance: " + newBalance);

        // Step 7: Assertion - check balance increased correctly
        Assert.assertEquals(newBalance, oldBalance + depositAmount,
                "Balance did not increase correctly after deposit!");
    }

}
