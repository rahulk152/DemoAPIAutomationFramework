package com.api.helper.AssertionHelper;

import io.restassured.response.Response;
import org.testng.Assert;

public class ErrorAssertions {
    public static void assertUnauthorized(Response response) {
        Assert.assertEquals(response.getStatusCode(), 401,
                "Expected UNAUTHORIZED status!");
    }

    public static void assertBadRequest(Response response) {
        Assert.assertEquals(response.getStatusCode(), 400,
                "Expected BAD REQUEST status!");
    }
}
