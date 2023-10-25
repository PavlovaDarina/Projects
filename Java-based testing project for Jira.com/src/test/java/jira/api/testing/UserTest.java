package jira.api.testing;

import base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.telerikacademy.api.tests.Constants.*;
import static com.telerikacademy.api.tests.Endpoints.USER_SEARCH_ENDPOINT;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class UserTest extends BaseTestSetup {

    @Test
    public void userSearchViaEmailTest() {

        baseURI = format("%s%s", BASE_URL, USER_SEARCH_ENDPOINT);

        Response response = getApplicationJSONSpecification()
                .queryParam("query", EMAIL)
                .get();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, "Incorrect status code. Expected 200.");
        assertEquals(response.getBody().jsonPath().getString("emailAddress[0]"), EMAIL, "Emails do not match.");

        ACCOUNT_ID = response.getBody().jsonPath().getString("accountId[0]");

        System.out.printf("User with email: %s and account_id: %s was found%n", EMAIL, ACCOUNT_ID);
    }
}
