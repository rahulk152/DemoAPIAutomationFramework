package com.api.helper.AssertionHelper;

import com.api.base.BaseAssertions;
import com.api.models.response.BankAccountResponse;
import com.api.models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class Assertions {
    private static final Logger logger = LoggerFactory.getLogger(Assertions.class);
    // ✅ Common reusable validation for Deposit flow
    public static void validateDepositTransaction(Response response,
                                                  BankAccountResponse updatedAccount,
                                                  double oldBalance,
                                                  double depositAmount) {
        BaseAssertions.assertStatusCode(response, 200);
        BaseAssertions.assertResponseTime(response, 3000);

        AccountAssertions.assertValidAccount(updatedAccount);
        TransactionAssertions.assertDeposit(updatedAccount, oldBalance, depositAmount);
    }

    // ✅ Common reusable validation for Transfer flow
    public static void validateTransferTransaction(Response response,
                                                   BankAccountResponse fromAccount,
                                                   BankAccountResponse toAccount,
                                                   double oldFromBalance,
                                                   double oldToBalance,
                                                   double transferAmount) {
        BaseAssertions.assertStatusCode(response, 200);

        AccountAssertions.assertValidAccount(fromAccount);
        AccountAssertions.assertValidAccount(toAccount);

        TransactionAssertions.assertTransfer(fromAccount, toAccount,
                oldFromBalance, oldToBalance, transferAmount);
    }

    // ✅ Account creation
    public static void validateAccountCreation(Response response,
                                               BankAccountResponse account,
                                               String expectedType,
                                               String expectedBranch) {
        BaseAssertions.assertStatusCode(response, 200);
        AccountAssertions.assertValidAccount(account);
        AccountAssertions.assertAccountType(account, expectedType);
        AccountAssertions.assertBranch(account, expectedBranch);
    }
    // ✅ Validate User Profile
    public static void validateUserProfile(Response response, UserProfileResponse profile) {
        // Base checks
        BaseAssertions.assertStatusCode(response, 200);
        BaseAssertions.assertResponseTime(response, 3000);

        // Profile-specific checks
        Assert.assertNotNull(profile.getUsername(), "Username should not be null");
        Assert.assertTrue(profile.getUsername().length() >= 3,
                "Username should be at least 3 characters long");

        Assert.assertNotNull(profile.getEmail(), "Email should not be null");
        Assert.assertTrue(profile.getEmail().contains("@"),
                "Email should contain '@' symbol");

    }


    // ✅ Error flows
    public static void validateUnauthorized(Response response) {
        ErrorAssertions.assertUnauthorized(response);
    }

    public static void validateBadRequest(Response response) {
        ErrorAssertions.assertBadRequest(response);
    }
}
