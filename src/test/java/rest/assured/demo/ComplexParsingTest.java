package rest.assured.demo;

import org.junit.Test;
import rest.assured.demo.actions.MyJsonServerActions;

import static org.hamcrest.Matchers.hasItems;

public class ComplexParsingTest extends BaseTest {

    private MyJsonServerActions myJsonServer = new MyJsonServerActions();

    @Test
    public void checkPriceBigDecimal() {
        myJsonServer.getProducts()
                .then()
                .body(" find { it.@id == 2 }", hasItems("price"));
    }
}
