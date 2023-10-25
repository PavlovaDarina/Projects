package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import static com.telerikacademy.api.tests.Constants.*;
import static com.telerikacademy.api.tests.Endpoints.SEARCH_ENDPOINT;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class BaseTestSetup implements ITest {

    private static ThreadLocal<String> TEST_NAME = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        if (STORY_KEY == null) {
            STORY_KEY = getLatestIssueKeyByType(STORY_NAME);
        }

        if (BUG_KEY == null) {
            BUG_KEY = getLatestIssueKeyByType(BUG_NAME);
        }

        if (ACCOUNT_ID == null) {
            ACCOUNT_ID = DEFAULT_ACCOUNT_ID;
        }
    }

    @BeforeMethod(alwaysRun = true, onlyForGroups = "dataProvider")
    public void setUpTestName(Method method, Object[] testData) {
        if (testData.length != 0) {
            TEST_NAME.set(method.getName() + "_" + testData[0]);
        } else {
            TEST_NAME.set(method.getName());
        }

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Override
    public String getTestName() {
        return TEST_NAME.get();
    }


    @AfterMethod(alwaysRun = true)
    public void clearTestName() {
        TEST_NAME = new ThreadLocal<>();
    }

    public RequestSpecification getApplicationJSONSpecification() {

        return given()
                .auth().preemptive().basic(EMAIL, JIRA_API_TOKEN)
                .contentType("application/json")
                .when();
    }

    private String getLatestIssueKeyByType(String issueType) {

        baseURI = format("%s%s", BASE_URL, SEARCH_ENDPOINT);

        String jql = format("project = %s AND type = %s ORDER BY created DESC", PROJECT_KEY, issueType);

        Response response = getApplicationJSONSpecification()
                .queryParam("jql", jql)
                .queryParam("maxResults", 1)
                .get();

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, "Incorrect status code. Expected 200.");
        return response.getBody().jsonPath().get("issues.key[0]");
    }
}