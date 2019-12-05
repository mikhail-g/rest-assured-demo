package rest.assured.demo;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class SimpleTest extends BaseTest {

    @Test
    public void getUsers() {
        given()
            .spec(jsonPlaceholder)
            .get(USERS_PATH)
            .then()
            .statusCode(200);
    }
}
