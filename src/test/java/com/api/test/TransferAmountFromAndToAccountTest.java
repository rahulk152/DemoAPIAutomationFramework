package com.api.test;

import com.api.services.AccountService;
import com.api.services.AuthService;
import com.api.services.TransactionService;
import com.api.helper.ConfigReader;
import com.api.models.request.LoginRequest;
import com.api.models.request.TransferAmountRequest;
import com.api.models.response.BankAccountResponse;
import com.api.models.response.LoginResponse;
import com.api.models.response.depositResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TransferAmountFromAndToAccountTest {
    @Test(description = "Verify User is able to transfer amount from and to Account API is working....")
    public void transferAmountFromAndToAccountTest(){
        // Step 1: Login
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(
                ConfigReader.get("username"),
                ConfigReader.get("password")));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        // Step 2: Get All Account
        AccountService accountService = new AccountService();
        response = accountService.getAllAccounts(loginResponse.getToken());
        List<BankAccountResponse> accounts = Arrays.asList(response.as(BankAccountResponse[].class));

        // Step 3: Pick random "from account" with non-zero balance
        BankAccountResponse fromAccount = null;
        for (int i = 0; i < accounts.size(); i++) {
            BankAccountResponse candidate = accounts.get(ThreadLocalRandom.current().nextInt(accounts.size()));
            if (candidate.getBalance() > 0) {
                fromAccount = candidate;
                break;
            }
        }

        if (fromAccount == null) {
            throw new RuntimeException("No account with balance found to transfer from!");
        }

        String fromAccountNumber = fromAccount.getAccountNumber();
        double oldBalanceFromAccount = fromAccount.getBalance();
        System.out.println("From Account: " + fromAccountNumber + " | Old Balance: " + oldBalanceFromAccount);

        // Step 4: Pick random "to account" (different from 'fromAccount')
        BankAccountResponse toAccount;
        do {
            toAccount = accounts.get(ThreadLocalRandom.current().nextInt(accounts.size()));
        } while (toAccount.getAccountNumber().equals(fromAccountNumber));

        String toAccountNumber = toAccount.getAccountNumber();
        double oldBalanceToAccount = toAccount.getBalance();
        System.out.println("To Account: " + toAccountNumber + " | Old Balance: " + oldBalanceToAccount);

        // Step 5: Pick transfer amount (must be <= available balance)
        int transferAmount = ThreadLocalRandom.current().nextInt(100, (int) oldBalanceFromAccount + 1);
        TransactionService transactionService = new TransactionService();
        response = transactionService.transfer(
                loginResponse.getToken(),
                new TransferAmountRequest(fromAccountNumber, toAccountNumber, transferAmount,
                        "Transfer Amount " + transferAmount));

        depositResponse depositResponse = response.as(depositResponse.class);
        System.out.println("Transferred Amount: " + depositResponse.getAmount());

        // Step 6: Verify deducted balance in fromAccount
        response = accountService.getAccountsNumber(loginResponse.getToken(), fromAccountNumber);
        BankAccountResponse deductedAccount = response.as(BankAccountResponse.class);
        double deductedBalance = deductedAccount.getBalance();
        System.out.println("From Account New Balance: " + deductedBalance);

        // Step 7: Verify credited balance in toAccount
        response = accountService.getAccountsNumber(loginResponse.getToken(), toAccountNumber);
        BankAccountResponse updatedAccount = response.as(BankAccountResponse.class);
        double newBalance = updatedAccount.getBalance();
        System.out.println("To Account New Balance: " + newBalance);

        // Step 8: Assertions
        Assert.assertEquals(deductedBalance, oldBalanceFromAccount - transferAmount,
                "From Account balance not deducted correctly!");
        Assert.assertEquals(newBalance, oldBalanceToAccount + transferAmount,
                "To Account balance not credited correctly!");
    }
}
