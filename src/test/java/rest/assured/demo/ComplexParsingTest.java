package rest.assured.demo;

import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import rest.assured.demo.actions.MyJsonServerActions;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ComplexParsingTest extends BaseTest {

    private MyJsonServerActions myJsonServer = new MyJsonServerActions();

    @Test
    public void findAllLessThen() {
        myJsonServer.getStore()
                .then()
                .body("store.book.findAll { it.price < 10 }.title",
                        hasItems("Sayings of the Century", "Moby Dick"));
    }

    @Test
    public void allAuthorsNamesLength() {
        myJsonServer.getStore()
                .then()
                .body("store.book.author*.length().sum()", greaterThan(50));
    }

    @Test
    public void getGroovyManipulationsResult() {
        String response = myJsonServer.getStore().asString();
        int sumOfAllAuthorLength = JsonPath.from(response)
                .getInt("store.book.author*.length().sum()");
        assertThat(sumOfAllAuthorLength, is(53));
    }

    @Test
    public void deserializationWithGenerics() {
        List<Map<String, Object>> products = myJsonServer.getProducts()
                .as(new TypeRef<List<Map<String, Object>>>() {
                });

        assertThat(products, hasSize(2));
        assertThat(products.get(0).get("id"), equalTo(2));
        assertThat(products.get(0).get("name"), equalTo("An ice sculpture"));
        assertThat(products.get(0).get("price"), equalTo(12.5));
        assertThat(products.get(1).get("id"), equalTo(3));
        assertThat(products.get(1).get("name"), equalTo("A blue mouse"));
        assertThat(products.get(1).get("price"), equalTo(25.5));
    }
}
