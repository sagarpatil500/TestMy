package com.sapience.admin.automation.helpers;

import io.restassured.response.Response;
import org.apache.log4j.Logger;

public class LogHelper {
    public static void printAPIResponseInfoLogs(Logger logger,String requestID, String url, Response response, String jsonStr)
    {
        logger.debug("Request URL :- "+ url);
        logger.debug("Request body :- "+jsonStr);
        logger.debug("Response :- "+response.asString());
        logger.debug("vRequestID :- "+requestID);
        logger.debug("Response StatusCode :- "+response.getStatusCode());
    }

    public static void printDebugLogs(Logger logger,String message)
    {
        logger.debug(message);
    }
}