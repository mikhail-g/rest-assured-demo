package rest.assured.demo;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Test;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.given;

public class JsonSchemaTest extends BaseTest {

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
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
            .setValidationConfiguration(ValidationConfiguration.newBuilder()
                .setDefaultVersion(DRAFTV4)
                .freeze())
            .freeze();

        given()
            .spec(jsonPlaceholder)
            .queryParam("id", 1)
            .when()
            .get(USERS_PATH)
            .then()
            .body(JsonSchemaValidator
                .matchesJsonSchemaInClasspath("jp/schema/users_err.json")
                .using(jsonSchemaFactory));
    }
}
