package persistence;

import model.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    Customer c;
    @BeforeEach
    void runBefore() {
        c = new Customer("Taebin", "xo@gmail.com");
    }
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            c.setShoppingCart(reader.read());
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyShoppingCart() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyShoppingCart.json");
        try {
            c.setShoppingCart(reader.read());
            assertEquals(0, c.getShoppingCartNum());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralShoppingCart.json");
        try {
            c.setShoppingCart(reader.read());
            assertEquals(2, c.getShoppingCartNum());

            checkEvent("Taebin", "House party", 50, 10,  c.getShoppingCart().chooseEventFromCart(0));
            checkEvent("Rita", "Halloween party", 40, 25,  c.getShoppingCart().chooseEventFromCart(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}