package rest.assured.demo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;

abstract class BaseTest {

    static final String USERS_PATH = "/users";
    RequestSpecification jsonPlaceholder = new RequestSpecBuilder()
        .setBaseUri("https://jsonplaceholder.typicode.com")
        .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
        .build();
    RequestSpecification myJsonServer = new RequestSpecBuilder()
        .setBaseUri("https://my-json-server.typicode.com/mikhail-g/rest-assured-demo")
        .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
        .build();
}
