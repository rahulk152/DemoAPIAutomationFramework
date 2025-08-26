package com.api.test;

import com.api.base.AccountService;
import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.helper.ConfigReader;
import com.api.models.request.CreateBankAccountRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.BankAccountResponse;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BankAccountCreationTest {
    @Test(description = "Verify User create Account API is working....")
    public void bankAccountCreationTest(){
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(ConfigReader.get("username"),ConfigReader.get("password")));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        AccountService accountService = new AccountService();
        CreateBankAccountRequest createBankAccountRequest = new CreateBankAccountRequest(ConfigReader.get("accountType"),ConfigReader.get("branch"));
        response= accountService.accounts(loginResponse.getToken(),createBankAccountRequest);

        BankAccountResponse bankAccountResponse = response.as(BankAccountResponse.class);
        System.out.println(bankAccountResponse.getAccountNumber());
        System.out.println(bankAccountResponse.getAccountType());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(bankAccountResponse.getAccountType(),"SALARY");
        Assert.assertEquals(bankAccountResponse.getBranch(),"South Branch");
    }
}
