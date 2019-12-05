package rest.assured.demo;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class LibraryTest {

    private static final String USERS_PATH = "/users";
    private RequestSpecification jsonPlaceholder = new RequestSpecBuilder()
        .setBaseUri("https://jsonplaceholder.typicode.com")
        .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
        .build();
    private RequestSpecification myJsonServer = new RequestSpecBuilder().setBaseUri("https://my-json-server.typicode.com").build();

    @Ignore
    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void getUsers() {
        given()
            .spec(jsonPlaceholder)
            .get(USERS_PATH)
            .then()
            .statusCode(200);
    }

    @Test
    public void validateSchema() {
        given()
            .spec(jsonPlaceholder)
            .queryParam("id", 1)
            .get(USERS_PATH)
            .then()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jp/schema/users.json"));
    }

    @Test(expected = AssertionError.class)
    public void invalidSchema() {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze()).freeze();

        given()
            .spec(jsonPlaceholder)
            .queryParam("id", 1)
            .when()
            .get(USERS_PATH)
            .then()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jp/schema/users_err.json"));
    }
}
