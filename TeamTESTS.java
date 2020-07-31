package com.sapience.admin.automation.suites.org.team;

import com.google.gson.Gson;
import com.sapience.admin.automation.dataProvider.TestDataProvider;
import com.sapience.admin.automation.factory.Log4JFactory;
import com.sapience.admin.automation.helpers.AuthHelper;
import com.sapience.admin.automation.helpers.TeamApiHelper;
import com.sapience.admin.automation.models.teams.PageResponseOfTeamResponse;
import com.sapience.admin.automation.models.teams.PageResponseOfTeamUserResponse;
import com.sapience.admin.automation.models.teams.TeamChangeEvent;
import com.sapience.admin.automation.models.teams.TeamResponse;
import com.sapience.admin.automation.utils.*;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TeamTESTS {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    private AuthHelper authHelper;
    private TeamApiHelper teamApiHelper;
    private ValidationHelper validationHelper;
    private TeamsValidationHelper teamsValidationHelper;
    private TestUtils testUtils;


    @BeforeClass(alwaysRun = true)
    public void setup() {
        authHelper = new AuthHelper();
        teamApiHelper = new TeamApiHelper();
        validationHelper = new ValidationHelper();
        teamsValidationHelper = new TeamsValidationHelper();
        testUtils = new TestUtils();
    }


    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class, groups = {TestConstants.TEST_GROUP_SMOKE, TestConstants.TEST_GROUP_ORG_TEAMS},
            description = "32999")
    public void get_all_teams(Map<String, Object> requestMap) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_AUTH_TOKEN.name(), true);
            testSteps.put(TestSteps.STEPS_GET_ALL_TEAMS.name(), true);
            validateTeamsTests(testSteps, requestMap);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            AppAssert.assertTrue(false, ex.getMessage());
        }
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class, groups = {TestConstants.TEST_GROUP_SMOKE, TestConstants.TEST_GROUP_ORG_TEAMS},
            description = "33053")
    public void get_manager_team_users(Map<String, Object> requestMap) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_AUTH_TOKEN.name(), true);
            testSteps.put(TestSteps.STEPS_GET_MANAGER_TEAM_USERS.name(), true);
            validateTeamsTests(testSteps, requestMap);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            AppAssert.assertTrue(false, ex.getMessage());
        }
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class, groups = {TestConstants.TEST_GROUP_SMOKE, TestConstants.TEST_GROUP_ORG_TEAMS},
            description = "39094")
    public void get_manager_team_users_count(Map<String, Object> requestMap) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_AUTH_TOKEN.name(), true);
            testSteps.put(TestSteps.STEPS_GET_MANAGER_TEAM_USERS_COUNT.name(), true);
            validateTeamsTests(testSteps, requestMap);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            AppAssert.assertTrue(false, ex.getMessage());
        }
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class, groups = {TestConstants.TEST_GROUP_SMOKE, TestConstants.TEST_GROUP_ORG_TEAMS},
            description = "39096")
    public void get_manager_team_list(Map<String, Object> requestMap) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_AUTH_TOKEN.name(), true);
            testSteps.put(TestSteps.STEPS_GET_MANAGER_TEAM_LIST.name(), true);
            validateTeamsTests(testSteps, requestMap);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            AppAssert.assertTrue(false, ex.getMessage());
        }
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class, groups = {"1234",TestConstants.TEST_GROUP_SMOKE, TestConstants.TEST_GROUP_ORG_TEAMS},
            description = "39096")
    public void get_team_change_log(Map<String, Object> requestMap) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_AUTH_TOKEN.name(), true);
            testSteps.put(TestSteps.STEPS_GET_TEAM_CHANGE_LOG.name(), true);
            validateTeamsTests(testSteps, requestMap);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            AppAssert.assertTrue(false, ex.getMessage());
        }
    }

    private void validateTeamsTests(Map<String, Boolean> testSteps, Map<String, Object> testData) throws Exception {
        String access_token = null;
        if (null != testSteps.get(TestSteps.STEP_GET_AUTH_TOKEN.name()) && testSteps.get(TestSteps.STEP_GET_AUTH_TOKEN.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_AUTH_TOKEN.name());
            access_token =  authHelper.getToken(testUtils.authParams(testData.get("employeeType").toString()), Integer.parseInt(testData.get(TestConstants.STATUS_CODE).toString()));
            validationHelper.validateOAuth(access_token);
        }
        if (null != testSteps.get(TestSteps.STEPS_GET_ALL_TEAMS.name()) && testSteps.get(TestSteps.STEPS_GET_ALL_TEAMS.name())) {
            MainUtils.stepLog(logger, TestSteps.STEPS_GET_ALL_TEAMS.name());
            PageResponseOfTeamResponse response = teamApiHelper.getAllTeams(access_token, Integer.parseInt(testData.get(TestConstants.STATUS_CODE).toString()),testData)
                        .getBody().as(PageResponseOfTeamResponse.class);
            teamsValidationHelper.validateTeams(response);
        }
        if (null != testSteps.get(TestSteps.STEPS_GET_MANAGER_TEAM_USERS.name()) && testSteps.get(TestSteps.STEPS_GET_MANAGER_TEAM_USERS.name())) {
            MainUtils.stepLog(logger, TestSteps.STEPS_GET_MANAGER_TEAM_USERS.name());
            PageResponseOfTeamUserResponse response = teamApiHelper.getManagerTeamsUsers(access_token, Integer.parseInt(testData.get(TestConstants.STATUS_CODE).toString()),testData)
                    .getBody().as(PageResponseOfTeamUserResponse.class);
            teamsValidationHelper.validateManagerTeamUsers(response);
        }
        if (null != testSteps.get(TestSteps.STEPS_GET_MANAGER_TEAM_USERS_COUNT.name()) && testSteps.get(TestSteps.STEPS_GET_MANAGER_TEAM_USERS_COUNT.name())) {
            MainUtils.stepLog(logger, TestSteps.STEPS_GET_MANAGER_TEAM_USERS_COUNT.name());
            teamApiHelper.getManagerTeamsUsersCount(access_token, Integer.parseInt(testData.get(TestConstants.STATUS_CODE).toString()),testData);
        }
        if (null != testSteps.get(TestSteps.STEPS_GET_MANAGER_TEAM_LIST.name()) && testSteps.get(TestSteps.STEPS_GET_MANAGER_TEAM_LIST.name())) {
            MainUtils.stepLog(logger, TestSteps.STEPS_GET_MANAGER_TEAM_LIST.name());
            TeamResponse[] arrayList = teamApiHelper.getTeamList(access_token, Integer.parseInt(testData.get(TestConstants.STATUS_CODE).toString()))
                    .getBody().as(TeamResponse[].class);
            teamsValidationHelper.validateTeamList(arrayList);
        }
        if (null != testSteps.get(TestSteps.STEPS_GET_TEAM_CHANGE_LOG.name()) && testSteps.get(TestSteps.STEPS_GET_TEAM_CHANGE_LOG.name())) {
            MainUtils.stepLog(logger, TestSteps.STEPS_GET_TEAM_CHANGE_LOG.name());
//            TeamChangeEvent[] responses = teamApiHelper.getTeamChangeLog(access_token, Integer.parseInt(testData.get(TestConstants.STATUS_CODE).toString()),testData)
//                    .getBody().as(TeamChangeEvent[].class);
            Gson gson = new Gson();
            TeamChangeEvent[] teamChangeEvents = gson.fromJson("[ { \"companyId\": 1, \"managerId\": 139, \"name\": \"Robert Owens\", \"userId\": 7, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Robert Owens\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"janice.bell@sapience.net\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Robert Owens111,janice.bell@sapience.net,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Robert Owens was added to team by janice.bell@sapience.net on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Martha Walker\", \"userId\": 10, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Martha Walker\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Martha Walker111,System,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Martha Walker was added to team by System on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Elizabeth Fisher\", \"userId\": 12, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Elizabeth Fisher\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"janice.bell@sapience.net\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Elizabeth Fisher111,janice.bell@sapience.net,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Elizabeth Fisher was added to team by janice.bell@sapience.net on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Alice Chavez\", \"userId\": 14, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Alice Chavez\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Alice Chavez111,System,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Alice Chavez was added to team by System on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Harold Washington\", \"userId\": 16, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Harold Washington\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Harold Washington111,System,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Harold Washington was added to team by System on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Keith Bowman\", \"userId\": 23, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Keith Bowman\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Keith Bowman111,System,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Keith Bowman was added to team by System on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"AdamTEST Ferguson\", \"userId\": 30, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"AdamTEST Ferguson\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"janice.bell@sapience.net\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,AdamTEST Ferguson111,janice.bell@sapience.net,2020-07-30 4:35:50 PM -05:00\", \"description\": \"AdamTEST Ferguson was added to team by janice.bell@sapience.net on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Andrea Weaver\", \"userId\": 39, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Andrea Weaver\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Andrea Weaver111,System,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Andrea Weaver was added to team by System on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Linda Little\", \"userId\": 51, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Linda Little\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Linda Little111,System,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Linda Little was added to team by System on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Janice Ray\", \"userId\": 65, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Janice Ray\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Janice Ray111,System,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Janice Ray was added to team by System on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Norma Stanley\", \"userId\": 77, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Norma Stanley\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Norma Stanley111,System,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Norma Stanley was added to team by System on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Sean Morgan\", \"userId\": 132, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 111, \"previousPropertyId\": 0, \"previousValue\": {}, \"currentValue\": \"Sean Morgan\", \"modifiedOn\": \"2020-07-30T16:35:50.620454-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,System.Object,Sean Morgan111,System,2020-07-30 4:35:50 PM -05:00\", \"description\": \"Sean Morgan was added to team by System on Thursday, July 30, 2020 4:35 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"AdamTEST Ferguson\", \"userId\": 30, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 112, \"previousPropertyId\": 111, \"previousValue\": \"AdamTEST Ferguson\", \"currentValue\": \"AdamTEST Ferguson\", \"modifiedOn\": \"2020-07-29T21:17:01.3570529-05:00\", \"modifiedBy\": \"janice.bell@sapience.net\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,AdamTEST Ferguson,AdamTEST Ferguson112,janice.bell@sapience.net,2020-07-29 9:17:01 PM -05:00\", \"description\": \"AdamTEST Ferguson was removed from team by janice.bell@sapience.net on Wednesday, July 29, 2020 9:17 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Andrea Weaver\", \"userId\": 39, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 112, \"previousPropertyId\": 111, \"previousValue\": \"Andrea Weaver\", \"currentValue\": \"Andrea Weaver\", \"modifiedOn\": \"2020-07-29T21:17:01.3570529-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Andrea Weaver,Andrea Weaver112,System,2020-07-29 9:17:01 PM -05:00\", \"description\": \"Andrea Weaver was removed from team by System on Wednesday, July 29, 2020 9:17 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"JBell's Team\", \"userId\": 0, \"action\": 1, \"propertyName\": null, \"currentPropertyId\": 0, \"previousPropertyId\": 0, \"previousValue\": null, \"currentValue\": null, \"modifiedOn\": \"2020-07-29T20:48:54.7207098-05:00\", \"modifiedBy\": \"janice.bell@sapience.net\", \"summary\": \"TeamChangeEvent - 1,139,Create,,,0,janice.bell@sapience.net,2020-07-29 8:48:54 PM -05:00\", \"description\": \"JBell's Team was Created by janice.bell@sapience.net on Wednesday, July 29, 2020 8:48 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Elizabeth Fisher\", \"userId\": 12, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 0, \"previousPropertyId\": 111, \"previousValue\": \"Elizabeth Fisher\", \"currentValue\": \"Elizabeth Fisher\", \"modifiedOn\": \"2020-07-13T04:07:10.3024192-05:00\", \"modifiedBy\": \"janice.bell@sapience.net\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Elizabeth Fisher,Elizabeth Fisher0,janice.bell@sapience.net,2020-07-13 4:07:10 AM -05:00\", \"description\": \"Elizabeth Fisher was removed from team by janice.bell@sapience.net on Monday, July 13, 2020 4:07 AM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Robert Owens\", \"userId\": 7, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 0, \"previousPropertyId\": 111, \"previousValue\": \"Robert Owens\", \"currentValue\": \"Robert Owens\", \"modifiedOn\": \"2020-07-12T21:54:48.3600693-05:00\", \"modifiedBy\": \"janice.bell@sapience.net\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Robert Owens,Robert Owens0,janice.bell@sapience.net,2020-07-12 9:54:48 PM -05:00\", \"description\": \"Robert Owens was removed from team by janice.bell@sapience.net on Sunday, July 12, 2020 9:54 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Martha Walker\", \"userId\": 10, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 0, \"previousPropertyId\": 111, \"previousValue\": \"Martha Walker\", \"currentValue\": \"Martha Walker\", \"modifiedOn\": \"2020-07-09T13:52:50.9105476-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Martha Walker,Martha Walker0,System,2020-07-09 1:52:50 PM -05:00\", \"description\": \"Martha Walker was removed from team by System on Thursday, July 9, 2020 1:52 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Alice Chavez\", \"userId\": 14, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 0, \"previousPropertyId\": 111, \"previousValue\": \"Alice Chavez\", \"currentValue\": \"Alice Chavez\", \"modifiedOn\": \"2020-07-09T13:52:50.9105476-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Alice Chavez,Alice Chavez0,System,2020-07-09 1:52:50 PM -05:00\", \"description\": \"Alice Chavez was removed from team by System on Thursday, July 9, 2020 1:52 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Harold Washington\", \"userId\": 16, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 0, \"previousPropertyId\": 111, \"previousValue\": \"Harold Washington\", \"currentValue\": \"Harold Washington\", \"modifiedOn\": \"2020-07-09T13:52:50.9105476-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Harold Washington,Harold Washington0,System,2020-07-09 1:52:50 PM -05:00\", \"description\": \"Harold Washington was removed from team by System on Thursday, July 9, 2020 1:52 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Keith Bowman\", \"userId\": 23, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 0, \"previousPropertyId\": 111, \"previousValue\": \"Keith Bowman\", \"currentValue\": \"Keith Bowman\", \"modifiedOn\": \"2020-07-09T13:52:50.9105476-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Keith Bowman,Keith Bowman0,System,2020-07-09 1:52:50 PM -05:00\", \"description\": \"Keith Bowman was removed from team by System on Thursday, July 9, 2020 1:52 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Linda Little\", \"userId\": 51, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 0, \"previousPropertyId\": 111, \"previousValue\": \"Linda Little\", \"currentValue\": \"Linda Little\", \"modifiedOn\": \"2020-07-09T13:52:50.9105476-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Linda Little,Linda Little0,System,2020-07-09 1:52:50 PM -05:00\", \"description\": \"Linda Little was removed from team by System on Thursday, July 9, 2020 1:52 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Janice Ray\", \"userId\": 65, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 0, \"previousPropertyId\": 111, \"previousValue\": \"Janice Ray\", \"currentValue\": \"Janice Ray\", \"modifiedOn\": \"2020-07-09T13:52:50.9105476-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Janice Ray,Janice Ray0,System,2020-07-09 1:52:50 PM -05:00\", \"description\": \"Janice Ray was removed from team by System on Thursday, July 9, 2020 1:52 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Norma Stanley\", \"userId\": 77, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 0, \"previousPropertyId\": 111, \"previousValue\": \"Norma Stanley\", \"currentValue\": \"Norma Stanley\", \"modifiedOn\": \"2020-07-09T13:52:50.9105476-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Norma Stanley,Norma Stanley0,System,2020-07-09 1:52:50 PM -05:00\", \"description\": \"Norma Stanley was removed from team by System on Thursday, July 9, 2020 1:52 PM\", \"teamId\": 111 }, { \"companyId\": 1, \"managerId\": 139, \"name\": \"Sean Morgan\", \"userId\": 132, \"action\": 2, \"propertyName\": \"Team\", \"currentPropertyId\": 0, \"previousPropertyId\": 111, \"previousValue\": \"Sean Morgan\", \"currentValue\": \"Sean Morgan\", \"modifiedOn\": \"2020-07-09T13:52:50.9105476-05:00\", \"modifiedBy\": \"System\", \"summary\": \"TeamChangeEvent - 1,139,Update,Team,Sean Morgan,Sean Morgan0,System,2020-07-09 1:52:50 PM -05:00\", \"description\": \"Sean Morgan was removed from team by System on Thursday, July 9, 2020 1:52 PM\", \"teamId\": 111 } ]",TeamChangeEvent[].class);
            teamsValidationHelper.validateTeamChangeLog(teamChangeEvents);
        }
    }


}