package com.api.services;

import com.api.base.BaseService;
import io.restassured.response.Response;

public class KycService extends BaseService {
    private static final String BASE_PATH = "/api/kyc/";

    public Response status(String endpoint){
       return getRequest(BASE_PATH + "status");
    }

  /*  public Response upload(String payload){

        return postRequest(payload, BASE_PATH + "upload");
    }*/
}
