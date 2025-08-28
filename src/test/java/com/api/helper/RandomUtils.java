package com.api.helper;

import com.api.models.response.BankAccountResponse;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static String getRandomAccountType() {
        String accountTypesStr = ConfigReader.get("accountTypes"); // "SAVING,CURRENT,SALARY"
        String[] accountTypes = accountTypesStr.split(",");
        int randomIndex = ThreadLocalRandom.current().nextInt(accountTypes.length);
        return accountTypes[randomIndex].trim();
    }

    public static String getRandomBranch() {
        String branchNameStr = ConfigReader.get("branch"); // Main Branch, South Branch, North Branch
        String[] branchName = branchNameStr.split(",");
        int randomIndex = ThreadLocalRandom.current().nextInt(branchName.length);
        return branchName[randomIndex].trim();
    }
    // Pick a random account from a list
    public static BankAccountResponse getRandomAccount(List<BankAccountResponse> accounts) {
        int randomIndex = ThreadLocalRandom.current().nextInt(accounts.size());
        return accounts.get(randomIndex);
    }

    // Generate random deposit amount between min & max
    public static int getRandomAmount(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
