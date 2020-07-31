package com.sapience.admin.automation.helpers;

import com.google.gson.GsonBuilder;
import com.sapience.admin.automation.common.Configuration;
import com.sapience.admin.automation.common.Constants;
import com.sapience.admin.automation.factory.Log4JFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class TeamApiHelper {

    private RequestSpecification requestSpecification;
    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());
    String requestID = "";

    public TeamApiHelper() {
        requestSpecification = with()
                .contentType(ContentType.JSON)
                .baseUri(Configuration.apiServer);
    }

    public Response getAllTeams(String token, int statusCode, Map<String, Object> testData) throws Exception {
        requestID = CommonHelper.getRequestID(Constants.COMPANY_ID,0);
        final Response response = given(requestSpecification)
                .header(Constants.V_REQUEST_ID_HEADER,requestID)
                .queryParam("PageNumber",testData.get("PageNumber"))
                .queryParam("PageSize",testData.get("PageSize"))
                .queryParam("OrderBy",testData.get("OrderBy"))
                .queryParam("SortDirection",testData.get("SortDirection"))
                .queryParam("SearchText",testData.get("SearchText"))
                .queryParam("ManagerIds",testData.get("ManagerId"))
                .auth().oauth2(token)
                .get("/org/teams");
        LogHelper.printAPIResponseInfoLogs(logger,requestID, Configuration.apiServer + "/org/teams", response, "");
        response.then().assertThat().statusCode(statusCode);

        return response;
    }

    public Response getManagerTeamsUsers(String token, int statusCode, Map<String, Object> testData) throws Exception {
        requestID = CommonHelper.getRequestID(Constants.COMPANY_ID,0);
        final Response response = given(requestSpecification)
                .header(Constants.V_REQUEST_ID_HEADER,requestID)
                .queryParam("PageNumber",testData.get("PageNumber"))
                .queryParam("PageSize",testData.get("PageSize"))
                .queryParam("OrderBy",testData.get("OrderBy"))
                .queryParam("SortDirection",testData.get("SortDirection"))
                .auth().oauth2(token)
                .get("/org/teams/"+testData.get("managerId")+"/users");
        LogHelper.printAPIResponseInfoLogs(logger,requestID, Configuration.apiServer + "/org/teams/"+testData.get("managerId")+"/users", response, "");
        response.then().assertThat().statusCode(statusCode);

        return response;
    }

    public Response getManagerTeamsUsersCount(String token, int statusCode, Map<String, Object> testData) throws Exception {
        requestID = CommonHelper.getRequestID(Constants.COMPANY_ID,0);
        final Response response = given(requestSpecification)
                .header(Constants.V_REQUEST_ID_HEADER,requestID)
                .auth().oauth2(token)
                .get("/org/teams/"+testData.get("managerId")+"/users/count");
        LogHelper.printAPIResponseInfoLogs(logger,requestID, Configuration.apiServer + "/org/teams/"+testData.get("managerId")+"/users/count", response, "");
        response.then().assertThat().statusCode(statusCode);

        return response;
    }

    public Response getTeamList(String token, int statusCode) throws Exception {
        requestID = CommonHelper.getRequestID(Constants.COMPANY_ID,0);
        final Response response = given(requestSpecification)
                .header(Constants.V_REQUEST_ID_HEADER,requestID)
                .auth().oauth2(token)
                .get("/org/teams/list");
        LogHelper.printAPIResponseInfoLogs(logger,requestID, Configuration.apiServer + "/org/teams/list", response, "");
        response.then().assertThat().statusCode(statusCode);

        return response;
    }

    public Response getTeamChangeLog(String token, int statusCode, Map<String, Object> testData) throws Exception {
        requestID = CommonHelper.getRequestID(Constants.COMPANY_ID,0);
        final Response response = given(requestSpecification)
                .header(Constants.V_REQUEST_ID_HEADER,requestID)
                .auth().oauth2(token)
                .get("/org/teams/changelog/"+testData.get("managerId"));
        LogHelper.printAPIResponseInfoLogs(logger,requestID, Configuration.apiServer + "/org/teams/changelog/"+testData.get("managerId"), response, "");
        response.then().assertThat().statusCode(statusCode);

        return response;
    }

}
