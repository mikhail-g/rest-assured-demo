package rest.assured.demo;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Test;
import rest.assured.demo.actions.JsonPlaceholderActions;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;

public class JsonSchemaTest extends BaseTest {

    private JsonPlaceholderActions placeholderApi = new JsonPlaceholderActions();

    @Test
    public void validateSchema() {
        placeholderApi.getUsers()
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

        placeholderApi.getUsers("id", 1)
            .then()
            .body(JsonSchemaValidator
                .matchesJsonSchemaInClasspath("jp/schema/users_err.json")
                .using(jsonSchemaFactory));
    }
}
