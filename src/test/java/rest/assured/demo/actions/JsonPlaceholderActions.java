package rest.assured.demo.actions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class JsonPlaceholderActions {

    private static final String USERS_PATH = "/users";
    private RequestSpecification spec;

    public JsonPlaceholderActions() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();
    }

    public Response getUsers() {
        return given()
                .spec(spec)
                .get(USERS_PATH);
    }

    public Response getUsers(String parameterName, Object... parameterValues) {
        return given()
                .spec(spec)
                .queryParam(parameterName, parameterValues)
                .get(USERS_PATH);
    }
}
