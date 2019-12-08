package rest.assured.demo;

import org.junit.Test;
import rest.assured.demo.actions.MyJsonServerActions;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

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
}
