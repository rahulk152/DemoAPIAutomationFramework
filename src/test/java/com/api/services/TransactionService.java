package com.api.services;

import com.api.base.BaseService;
import com.api.models.request.DepositAmountRequest;
import com.api.models.request.TransferAmountRequest;
import io.restassured.response.Response;

public class TransactionService extends BaseService {
    private static final String BASE_PATH_TRANSACTION = "/api/transactions/";
    private static final String BASE_PATH_TRANSFER = "/api/transactions/transfer";

    public Response deposit(String token, DepositAmountRequest payload){
        setAuthToken(token);
        return postRequest(payload, BASE_PATH_TRANSACTION + "deposit");
    }

   /*  public Response history(String endpoint){
        return getRequest(BASE_PATH_TRANSACTION + "history");
    }

   public Response withdraw(String payload){

        return postRequest(payload, BASE_PATH_TRANSACTION + "withdraw");
    }*/

    public Response transfer(String token, TransferAmountRequest payload){
        setAuthToken(token);
        return postRequest(payload, BASE_PATH_TRANSFER);
    }

   /* public Response complete(String payload){

        return postRequest(payload, BASE_PATH_TRANSFER + "complete");
    }

    public Response initiate(String payload){

        return postRequest(payload, BASE_PATH_TRANSFER + "initiate");
    }*/

}
