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
    // Not null or empty check
    public static void assertNotNullOrEmpty(String fieldName, String value) {
        Assert.assertNotNull(value, fieldName + " should not be null");
        Assert.assertFalse(value.trim().isEmpty(), fieldName + " should not be empty");
    }

    // Generic equals check
    public static void assertEquals(String fieldName, String actual, String expected) {
        Assert.assertEquals(actual, expected, fieldName + " mismatch!");
    }
}
