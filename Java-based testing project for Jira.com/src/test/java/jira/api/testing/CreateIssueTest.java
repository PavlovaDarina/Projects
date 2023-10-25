package jira.api.testing;

import base.BaseTestSetup;
import dataproviders.IssueFields;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.telerikacademy.api.tests.Constants.*;
import static com.telerikacademy.api.tests.Endpoints.ISSUE_ENDPOINT;
import static com.telerikacademy.api.tests.Helper.isValid;
import static com.telerikacademy.api.tests.JSONRequests.ISSUE;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateIssueTest extends BaseTestSetup {

    @Test(priority = 1)
    public void createStoryTest() {

        baseURI = format("%s%s", BASE_URL, ISSUE_ENDPOINT);

        String requestBody = (format(ISSUE, PROJECT_KEY, STORY_SUMMARY, STORY_MULTILINE_DESCRIPTION, STORY_NAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = getApplicationJSONSpecification()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_CREATED, "Incorrect status code. Expected 201.");

        STORY_KEY = response.getBody().jsonPath().get("key");

        System.out.printf("Story with key %s was created%n%n", STORY_KEY);
    }

    @Test(priority = 2)
    public void createBugTest() {

        baseURI = format("%s%s", BASE_URL, ISSUE_ENDPOINT);

        String requestBody = (format(ISSUE, PROJECT_KEY, BUG_SUMMARY, BUG_DESCRIPTION, BUG_NAME));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = getApplicationJSONSpecification()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_CREATED, "Incorrect status code. Expected 201.");

        BUG_KEY = response.getBody().jsonPath().get("key");

        System.out.printf("Bug with key %s was created%n%n", BUG_KEY);
    }

    @Test(dataProvider = "fieldsPerIssue", dataProviderClass = IssueFields.class, groups = "dataProvider")
    public void createIssueTest(String scenarioDescription, String projectKey, String summary, String description, String issueName) {

        System.out.println(scenarioDescription);

        baseURI = format("%s%s", BASE_URL, ISSUE_ENDPOINT);

        String requestBody = (format(ISSUE, projectKey, summary, description, issueName));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = getApplicationJSONSpecification()
                .body(requestBody)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_CREATED, "Incorrect status code. Expected 201.");

        String key = response.getBody().jsonPath().get("key");

        if (issueName.equals("Story")) {
            STORY_KEY = key;
        } else {
            BUG_KEY = key;
        }

        System.out.printf("%s with key %s was created%n%n", issueName, key);
    }
}
