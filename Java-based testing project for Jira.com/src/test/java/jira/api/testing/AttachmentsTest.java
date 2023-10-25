package jira.api.testing;

import base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static com.telerikacademy.api.tests.Constants.*;
import static com.telerikacademy.api.tests.Endpoints.ISSUE_ENDPOINT;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class AttachmentsTest extends BaseTestSetup {

    private static final String JIRA_API_TOKEN = "";

    @Test
    public void addAttachmentTest() {

        baseURI = format("%s%s/%s/attachments", BASE_URL, ISSUE_ENDPOINT, BUG_KEY);

        Response response = given()
                .auth().preemptive().basic(EMAIL, JIRA_API_TOKEN)
                .header("X-Atlassian-Token", "no-check")
                .contentType("multipart/form-data")
                .multiPart("file", new File("src/test/resources/StayTuned.png"))
                .when()
                .post();

        assertResponse(response);

        System.out.printf("Attachment %s was added to story with key: %s%n%n", "StayTuned.png", STORY_KEY);
    }

    @Test
    public void addAttachmentTest_override() {

        baseURI = format("%s%s/%s/attachments", BASE_URL, ISSUE_ENDPOINT, STORY_KEY);

        Response response = getApplicationJSONSpecification()
                .header("X-Atlassian-Token", "no-check")
                .contentType("multipart/form-data")
                .multiPart("file", new File("src/test/resources/StayTuned.png"))
                .post();

        assertResponse(response);

        System.out.printf("Attachment %s was added to story with key: %s%n%n", "StayTuned.png", STORY_KEY);
    }

    private void assertResponse(Response response) {

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, "Incorrect status code.");
        assertEquals(response.getBody().jsonPath().get("mimeType[0]"), "image/png", "Mime types do not match.");
    }
}
