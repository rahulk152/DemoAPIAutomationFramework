package com.api.base;

import com.api.helper.ConfigReader;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import com.api.services.AccountService;
import com.api.services.AuthService;
import com.api.services.TransactionService;
import com.api.services.UserProfileManagementService;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;

//Class is Declared as abstract, You cannot create an object of BaseTest directly.
//Instead, other test classes extend it.
public abstract class BaseTest {

    //Declared as protected → accessible in child test classes but hidden from external packages.
    protected AuthService authService;
    protected AccountService accountService;
    protected TransactionService transactionService;
    protected String token;
    protected UserProfileManagementService userProfileManagementService;

    //Runs before each @Test method in your test class.
    @BeforeMethod
    public void setUp() {
        authService = new AuthService();
        accountService = new AccountService();
        transactionService = new TransactionService();
        userProfileManagementService = new UserProfileManagementService();

        Response response = authService.login(
                new LoginRequest(ConfigReader.get("username"), ConfigReader.get("password")));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        token = loginResponse.getToken();
    }
}
