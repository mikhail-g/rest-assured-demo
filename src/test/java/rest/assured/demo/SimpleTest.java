package rest.assured.demo;

import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class SimpleTest extends BaseTest {

    private Response getLotto() {
        return given()
            .spec(myJsonServer)
            .get("/lotto");
    }

    private Response getProducts() {
        return given()
            .config(RestAssuredConfig
                .config()
                .jsonConfig(jsonConfig()
                    .numberReturnType(BIG_DECIMAL)))
            .spec(myJsonServer)
            .get("/products");
    }


    @Test
    public void checkLottoId() {
        getLotto()
            .then()
            .body("lotto.lottoId", equalTo(5));
    }

    @Test
    public void checkWinnerId() {
        getLotto()
            .then()
            .body("lotto.winners.winnerId", hasItems(23, 54));
    }

    @Test
    public void checkPriceBigDecimal() {
        getProducts()
            .then()
            .body(" find { it.@id == 2 }", hasItems("price"));
    }
}
