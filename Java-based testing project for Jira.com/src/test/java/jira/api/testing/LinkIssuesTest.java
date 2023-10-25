package jira.api.testing;

import base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.telerikacademy.api.tests.Constants.*;
import static com.telerikacademy.api.tests.Endpoints.ISSUE_ENDPOINT;
import static com.telerikacademy.api.tests.Helper.isValid;
import static com.telerikacademy.api.tests.JSONRequests.BLOCK_ISSUE;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LinkIssuesTest extends BaseTestSetup {

    @Test
    public void bugBlocksStoryTest() {

        baseURI = format("%s%s/%s", BASE_URL, ISSUE_ENDPOINT, BUG_KEY);

        String requestBody = (format(BLOCK_ISSUE, STORY_KEY));
        assertTrue(isValid(requestBody), "Body is not a valid JSON");

        Response response = getApplicationJSONSpecification()
                .body(requestBody)
                .put();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_NO_CONTENT, "Incorrect status code. Expected 204.");
        assertEquals(response.body().asString(), "", "Response body is not empty");

        System.out.printf("Story with key %s was blocked by bug with key %s%n%n", STORY_KEY, BUG_KEY);
    }
}
