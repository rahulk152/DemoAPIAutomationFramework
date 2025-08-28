package com.api.test;

import com.api.base.BaseTest;
import com.api.helper.AssertionHelper.TransactionAssertions;
import com.api.services.AccountService;
import com.api.services.AuthService;
import com.api.services.TransactionService;
import com.api.helper.ConfigReader;
import com.api.helper.RandomUtils;
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

public class DepositAmountInAccountTest extends BaseTest {
    @Test(description = "Verify deposit amount in the account API is working....")
    public void depositAmountInAccountTest(){
        // Step 1: Get All Accounts
        Response response = accountService.getAllAccounts(token);
        List<BankAccountResponse> accounts = Arrays.asList(response.as(BankAccountResponse[].class));

        // Step 2: Pick a random account
        BankAccountResponse randomAccount = RandomUtils.getRandomAccount(accounts);
        String accountNumber = randomAccount.getAccountNumber();
        double oldBalance = randomAccount.getBalance();

        // Step 3: Deposit random amount
        int depositAmount = RandomUtils.getRandomAmount(1000, 50000);
        response = transactionService.deposit(token,
                new DepositAmountRequest(accountNumber, depositAmount, "Random Deposit " + depositAmount));
        depositResponse depositResponse = response.as(depositResponse.class);

        // Step 4: Fetch updated account
        response = accountService.getAccountsNumber(token, accountNumber);
        BankAccountResponse updatedAccount = response.as(BankAccountResponse.class);

        // ✅ Centralized Assertions
        TransactionAssertions.assertDeposit(updatedAccount, oldBalance, depositAmount);
    }

}
