package com.api.base;

import io.restassured.response.Response;
import org.testng.Assert;

public class BaseAssertions {
    public static void assertStatusCode(Response response, int expectedStatus) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus,
                "Status code mismatch!");
    }

    public static void assertResponseTime(Response response, long maxMillis) {
        long time = response.getTime();
        Assert.assertTrue(time <= maxMillis,
                "Response time too high! Actual: " + time + "ms, Expected: <= " + maxMillis + "ms");
    }
}
