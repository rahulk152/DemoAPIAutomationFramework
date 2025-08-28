package com.api.helper.AssertionHelper;

import com.api.models.response.BankAccountResponse;
import org.testng.Assert;

public class TransactionAssertions {
    public static void assertDeposit(BankAccountResponse updatedAccount,
                                     double oldBalance,
                                     double depositAmount) {
        double expectedBalance = oldBalance + depositAmount;
        Assert.assertEquals(updatedAccount.getBalance(), expectedBalance,
                "Deposit not reflected in balance!");
    }

    public static void assertTransfer(BankAccountResponse fromAccount,
                                      BankAccountResponse toAccount,
                                      double oldFromBalance,
                                      double oldToBalance,
                                      double transferAmount) {
        Assert.assertEquals(fromAccount.getBalance(),
                oldFromBalance - transferAmount,
                "From account balance not deducted correctly!");

        Assert.assertEquals(toAccount.getBalance(),
                oldToBalance + transferAmount,
                "To account balance not credited correctly!");
    }
}
