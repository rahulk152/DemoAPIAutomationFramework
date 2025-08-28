package com.api.helper.AssertionHelper;

import com.api.models.response.BankAccountResponse;
import org.testng.Assert;

public class AccountAssertions {
    public static void assertValidAccount(BankAccountResponse account) {
        Assert.assertNotNull(account.getAccountNumber(), "Account number is null!");
        Assert.assertTrue(account.getBalance() >= 0, "Balance cannot be negative!");
    }

    public static void assertAccountType(BankAccountResponse account, String expectedType) {
        Assert.assertEquals(account.getAccountType(), expectedType,
                "Account type mismatch!");
    }

    public static void assertBranch(BankAccountResponse account, String expectedBranch) {
        Assert.assertEquals(account.getBranch(), expectedBranch,
                "Branch mismatch!");
    }
}
