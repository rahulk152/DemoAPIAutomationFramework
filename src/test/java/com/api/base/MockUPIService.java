package com.api.base;

import io.restassured.response.Response;

public class MockUPIService extends BaseService{
    private static final String BASE_PATH = "/api/upi/";

  /*  public Response initiate(String payload){

        return postRequest(payload, BASE_PATH + "payment/initiate");
    }*/
    public Response statusTransactionRef(String endpoint){
        return getRequest(BASE_PATH + "status/{transactionRef}");
    }
  /*  public Response qrCodeGenerate(String payload){

        return postRequest(payload, BASE_PATH + "qr-code/generate");
    }

    public Response verify(String payload){

        return postRequest(payload, BASE_PATH + "verify");
    }*/

}
