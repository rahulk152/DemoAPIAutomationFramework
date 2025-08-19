package com.api.base;

import io.restassured.response.Response;

public class ReportService extends BaseService{
    private static final String BASE_PATH = "/api/reports/statement";

    public Response excel(String endpoint){
        return getRequest(BASE_PATH + "excel");
    }
    public Response pdf(String endpoint){
        return getRequest(BASE_PATH + "pdf");
    }
}
