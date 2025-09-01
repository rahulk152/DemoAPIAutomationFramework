package com.api.test;

import com.api.base.BaseTest;
import com.api.helper.AssertionHelper.Assertions;
import com.api.services.AccountService;
import com.api.services.AuthService;
import com.api.helper.ConfigReader;
import com.api.helper.RandomUtils;
import com.api.models.request.CreateBankAccountRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.BankAccountResponse;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BankAccountCreationTest extends BaseTest {
    @Test(description = "Verify User create Account API is working....")
    public void bankAccountCreationTest(){
        // Step 1: Login
        // Step 2: Pick a random account type
        String randomAccountType = RandomUtils.getRandomAccountType();
        System.out.println("Randomly selected Account Type: " + randomAccountType);

        //Steps 3: Pick a random branch
        String randomBranchName = RandomUtils.getRandomBranch();
        System.out.println("Randomly selected Branch Name: " + randomBranchName);

        // Step 4: Create an account
        CreateBankAccountRequest createBankAccountRequest =
                new CreateBankAccountRequest(randomAccountType, randomBranchName);
        Response response = accountService.createAccount(token, createBankAccountRequest);

        // Step 5: Parse response
        BankAccountResponse bankAccountResponse = response.as(BankAccountResponse.class);
        System.out.println("Created Account Number: " + bankAccountResponse.getAccountNumber());
        System.out.println("Created Account Type: " + bankAccountResponse.getAccountType());

        // Step 6: Assertions
        Assertions.validateAccountCreation(response,bankAccountResponse,randomAccountType,randomBranchName);

    }
}
