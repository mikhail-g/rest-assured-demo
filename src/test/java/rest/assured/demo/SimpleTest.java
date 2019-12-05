package rest.assured.demo;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class SimpleTest extends BaseTest {

    @Test
    public void checkLottoId() {
        given()
            .spec(myJsonServer)
            .get("/lotto")
            .then()
            .body("lotto.lottoId", equalTo(5));
    }
}
