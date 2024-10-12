package persistence;

import model.Customer;
import model.OrgEvent;
import model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    OrgEvent e1;
    OrgEvent e2;
    @BeforeEach
    void runBefore() {
        e1 = new OrgEvent("Org1", "Fun party", 100, 15);
        e2 = new OrgEvent("Org2", "Bad party", 5, 1);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

//    @Test
//    void testWriterEmptyWorkroom() {
//        try {
//            Customer c = new Customer("Tae", "xoq");
//            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShoppingCart.json");
//            writer.open();
//            writer.write(c.getShoppingCart());
//            writer.close();
//
//            ShoppingCart sc;
//            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
//            sc = reader.read();
//            assertEquals(0, sc.getNumItems());
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Customer c = new Customer("Tae", "xoq@gmail.com");
            c.addItemToCart(e1);
            c.addItemToCart(e2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoppingCart.json");
            writer.open();
            writer.write(c.getShoppingCart());
            writer.close();

            ShoppingCart sc;
            JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingCart.json");
             sc = reader.read();
            assertEquals(2, sc.getNumItems());

            checkEvent("Org1", "Fun party", 100, 15,  c.getShoppingCart().chooseEventFromCart(0));
            checkEvent("Org2", "Bad party", 5, 1,  c.getShoppingCart().chooseEventFromCart(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}