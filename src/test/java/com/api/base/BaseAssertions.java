package com.api.base;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class BaseAssertions {

    private static final Logger logger = LoggerFactory.getLogger(BaseAssertions.class);

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode,
                "Expected status code: " + expectedStatusCode + " but found: " + actualStatusCode);

        String msg = "✅ Status Code Validation Passed: " + actualStatusCode;
        logger.info(msg);
        Reporter.log(msg, true);
    }

    public static void assertResponseTime(Response response, long maxResponseTimeMs) {
        long responseTime = response.time();
        Assert.assertTrue(responseTime <= maxResponseTimeMs,
                "Expected response time < " + maxResponseTimeMs + "ms but found: " + responseTime + "ms");

        String msg = "✅ Response Time Validation Passed: " + responseTime + " ms";
        logger.info(msg);
        Reporter.log(msg, true);
    }
}
