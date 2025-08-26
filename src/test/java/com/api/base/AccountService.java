package com.api.base;

import com.api.models.request.CreateBankAccountRequest;
import com.api.models.response.BankAccountResponse;
import io.restassured.response.Response;

public class AccountService extends BaseService{
    private static final String BASE_PATH = "/api/accounts";

   public Response accounts(String token, CreateBankAccountRequest payload){
        setAuthToken(token);
        return postRequest(payload, BASE_PATH);
    }

    public Response getaccountsNumber(String token, String accountNumber){
        setAuthToken(token);
        return getRequest(BASE_PATH + "/" + accountNumber);
    }
    public Response getallaccounts(String token){
        setAuthToken(token);
        return getRequest(BASE_PATH + "/user");
    }

}
