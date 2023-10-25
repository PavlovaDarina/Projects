package jira.api.testing;

import base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.telerikacademy.api.tests.Constants.*;
import static com.telerikacademy.api.tests.Endpoints.ISSUE_ENDPOINT;
import static com.telerikacademy.api.tests.Helper.isValid;
import static com.telerikacademy.api.tests.JSONRequests.ASSIGNEE_ACCOUNT_ID;
import static com.telerikacademy.api.tests.JSONRequests.ASSIGNEE_ID;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AssigneeTest extends BaseTestSetup {

    @Test
    public void setAssigneeToStoryByAccountIdTest() {

        baseURI = format("%s%s/%s", BASE_URL, ISSUE_ENDPOINT, STORY_KEY);

        String requestBody = (format(ASSIGNEE_ACCOUNT_ID, ACCOUNT_ID));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = getApplicationJSONSpecification()
                .body(requestBody)
                .put();

        assertResponse(response);

        System.out.printf("Story with key: %s was assigned to user with assignee_account_id: %s and email: %s%n%n", STORY_KEY, ACCOUNT_ID, EMAIL);
    }

    @Test
    public void setAssigneeToBugByIdTest() {

        baseURI = format("%s%s/%s", BASE_URL, ISSUE_ENDPOINT, BUG_KEY);

        String requestBody = (format(ASSIGNEE_ID, ACCOUNT_ID));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = getApplicationJSONSpecification()
                .body(requestBody)
                .put()
                .then()
                .assertThat()
                .statusCode(SC_NO_CONTENT)
                .extract().response();

        assertResponse(response);

        System.out.printf("Story with key: %s was assigned to user with assignee_id: %s and email: %s%n%n", STORY_KEY, ACCOUNT_ID, EMAIL);
    }

    private void assertResponse(Response response) {
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_NO_CONTENT, "Incorrect status code. Expected 204.");
        assertEquals(response.body().asString(), "", "Response body is not empty");
    }
}
