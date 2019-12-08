package rest.assured.demo;

import org.junit.Test;
import rest.assured.demo.actions.MyJsonServerActions;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;

public class SimpleTest extends BaseTest {

    private MyJsonServerActions myJsonServer = new MyJsonServerActions();

    @Test
    public void checkLottoId() {
        myJsonServer.getLotto()
                .then()
                .body("lotto.lottoId", equalTo(5));
    }

    @Test
    public void checkWinnerId() {
        myJsonServer.getLotto()
                .then()
                .body("lotto.winners.winnerId", hasItems(23, 54));
    }

    @Test
    public void checkPriceFloat() {
        myJsonServer.getNumbers()
                .then()
                .body("price", is(12.12f));
    }

    @Test
    public void checkPriceBigDecimal() {
        MyJsonServerActions myJsonServer = new MyJsonServerActions();
        myJsonServer.setConfigNumbersAsBigDecimal();
        myJsonServer.getNumbers()
                .then()
                .body("price", is(new BigDecimal("12.12")));
    }
}
