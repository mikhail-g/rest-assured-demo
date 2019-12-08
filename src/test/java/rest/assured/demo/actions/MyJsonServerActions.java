package rest.assured.demo.actions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

public class MyJsonServerActions {

    private RequestSpecification myJsonServer = new RequestSpecBuilder()
            .setBaseUri("https://my-json-server.typicode.com/mikhail-g/rest-assured-demo")
            .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
            .build();

    public Response getLotto() {
        return given()
                .spec(myJsonServer)
                .get("/lotto");
    }

    public Response getProducts() {
        return given()
                .config(RestAssuredConfig
                        .config()
                        .jsonConfig(jsonConfig()
                                .numberReturnType(BIG_DECIMAL)))
                .spec(myJsonServer)
                .get("/products");
    }
}
