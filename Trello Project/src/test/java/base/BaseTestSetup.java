package base;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static com.telerikacademy.api.tests.Constants.KEY;
import static com.telerikacademy.api.tests.Constants.TOKEN;
import static com.telerikacademy.api.tests.Endpoints.BASE_URL;
import static com.telerikacademy.api.tests.Endpoints.BOARD_ENDPOINT;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertTrue;

public class BaseTestSetup {

    public static String boardId;
    public static String cardId;
    public static String toDoListId;
    public static String timestamp;
    public static DateTimeFormatter dtf;
    public static String uniqueName;

    @BeforeSuite
    public void initialSetup() {

        baseURI = BASE_URL;

        dtf = DateTimeFormatter.ISO_INSTANT;
        Instant time = Instant.now();
        timestamp = dtf.format(time);

        uniqueName = RandomStringUtils.randomAlphabetic(10);
    }

    @BeforeSuite
    public void setup() {

        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

        basePath = BOARD_ENDPOINT;

        if (isNull(boardId)) {
            return;
        }

        System.out.println("Executed Successfully");

        Response response = getApplicationAuthentication()
                .pathParam("id", boardId)
                .when()
                .delete();

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == SC_OK || statusCode == SC_NOT_FOUND, format("Incorrect status code. Expected %s or %s.", SC_OK, SC_NOT_FOUND));

        System.out.printf("Board with id '%s' was deleted.%n", boardId);
    }

    public RequestSpecification getApplicationAuthentication() {
        return given()
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .log().all();
    }
}
