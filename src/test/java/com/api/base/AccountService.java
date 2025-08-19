package com.api.base;

import io.restassured.response.Response;

public class AccountService extends BaseService{
    private static final String BASE_PATH = "/api/accounts";

   /* public Response accounts(String payload){

        return postRequest(payload, BASE_PATH);
    }*/

    public Response accountsNumber(String endpoint){

        return getRequest(BASE_PATH + "/{accountNumber}");
    }
    public Response accountsUser(String endpoint){

        return getRequest(BASE_PATH + "/user");
    }

}
