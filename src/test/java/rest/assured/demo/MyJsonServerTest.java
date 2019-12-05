package rest.assured.demo;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class MyJsonServerTest extends BaseTest {

    @Test
    public void getProducts() {
        given()
            .spec(myJsonServer)
            .when()
            .get("/products")
            .then()
            .statusCode(200);
    }
}
