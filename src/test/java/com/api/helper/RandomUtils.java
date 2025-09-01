package com.api.helper;

import com.api.models.response.BankAccountResponse;

import java.util.List;
import java.util.Random;
import java.util.UUID;
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
    // Generate random email
    public static String getRandomEmail() {
        return "test_" + UUID.randomUUID().toString().substring(0, 6) + "@example.com";
    }

    // Generate random mobile number (10-digit)
    public static String getRandomMobileNumber() {
        Random random = new Random();
        StringBuilder mobile = new StringBuilder("9"); // start with 9
        for (int i = 0; i < 9; i++) {
            mobile.append(random.nextInt(10));
        }
        return mobile.toString();
    }

    // Example utility for random name
    public static String getRandomString(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

    // Random number in a range
    public static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
