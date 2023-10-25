package jira.api.testing;

import base.BaseTestSetup;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static com.telerikacademy.api.tests.Constants.BASE_URL;
import static com.telerikacademy.api.tests.Constants.STORY_KEY;
import static com.telerikacademy.api.tests.Endpoints.ISSUE_ENDPOINT;
import static com.telerikacademy.api.tests.Helper.isValid;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TransitionsTest extends BaseTestSetup {

    @Test
    public void moveStoryToInProgressTest() {

        baseURI = format("%s%s/%s/transitions", BASE_URL, ISSUE_ENDPOINT, STORY_KEY);

        String transitionId = getInProgressTransitionId();
        JsonObject id = new JsonObject();
        id.addProperty("id", transitionId);

        JsonObject requestParams = new JsonObject();
        requestParams.add("transition", id);

        assertTrue(isValid(String.valueOf(requestParams)), "Body is not a valid JSON");

        Response response = getApplicationJSONSpecification()
                .body(requestParams)
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_NO_CONTENT, "Incorrect status code. Expected 204.");
        assertEquals(response.body().asString(), "", "Response body is not empty");

        System.out.printf("Story with key %s was moved to `In Progress`%n%n", STORY_KEY);
    }

    private String getInProgressTransitionId() {

        String transitionId = null;
        Response response = getApplicationJSONSpecification()
                .get();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, "Incorrect status code. Expected 200.");
        List<LinkedHashMap<String, Object>> transitions = response.getBody().jsonPath().get("transitions");
        for (LinkedHashMap<String, Object> transition : transitions) {
            if (transition.get("name").equals("In Progress")) {
                transitionId = String.valueOf(transition.get("id"));
            }
        }

        return transitionId;
    }
}

