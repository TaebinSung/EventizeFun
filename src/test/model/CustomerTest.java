package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CustomerTest {
    Customer c1;
    Organizer o1;
    OrgEvent e1;
    OrgEvent e2;

    @BeforeEach
    void runBefore() {
        c1 = new Customer("Tae", "xoqlsdi7@gmail.com");
        o1 = new Organizer("Organizer", "org@party.com");
        e1 = new OrgEvent(o1.getName(), "Fun party", 100, 15);
        e2 = new OrgEvent(o1.getName(), "Bad party", 5, 1);
    }
    @Test
    void constructorTest() {
        c1 = new Customer("sun", "sun1123@gmail.com");
        assertEquals("sun", c1.getName());
        assertEquals("sun1123@gmail.com", c1.getEmail());
        assertEquals(0, c1.getShoppingCartNum());
    }
    @Test
    void addSingleItemToCart() {
        c1.addItemToCart(e1);
        assertEquals(e1, c1.chooseEventFromCart(0));
        assertEquals(1, c1.getShoppingCartNum());
    }

    @Test
    void addMultipleItemToCart() {
        c1.addItemToCart(e1);
        c1.addItemToCart(e2);
        assertEquals(e1, c1.chooseEventFromCart(0));
        assertEquals(e2, c1.chooseEventFromCart(1));
        assertEquals(2, c1.getShoppingCartNum());
    }

    @Test
    void setNameTest() {
        c1.setName("Wow");
        assertEquals("Wow", c1.getName());
    }

    @Test
    void toJsonTest() {
        JSONObject json = new JSONObject();
        json.put("name", c1.getName());
        json.put("email", c1.getEmail());
        json.put("shoppingCart", c1.getShoppingCart().toJson());

        assertEquals(json.toString(), c1.toJson().toString());
    }


    @Test
    void getShoppingCartItemsTest() {
        c1.addItemToCart(e1);

        String hostedEventDetails = "***********************************\n" + "Items in Cart:\n" +
                1 + " - " + e1.getEventName() +
                "\tPrice: $" + e1.getTicketPrice() +
                "\tTickets Left: " + e1.getTicketsLeft() + "\n" +
                "***********************************\n";
        assertEquals(hostedEventDetails, c1.getShoppingCartItems());
    }

    @Test
    void getEmptyShoppingCartItemsTest() {
        assertEquals("\nNo item in shopping cart\n", c1.getShoppingCartItems());
    }

    @Test
    void testAccountDetails() {
        Account account = new Customer("Tae", "xoq@gmail.com");
        String accountInfo = "\nAccount Details:" +
                "\n***********************************\n" +
                "\nName: " + account.getName() +
                "\nEmail: " + account.getEmail() +
                "\n***********************************\n";

        assertEquals(accountInfo, account.getUserAccountDetails().toString());
    }

    @Test
    // EFFECTS: saves the customer to file
    public void saveShoppingCart() {
        c1.addItemToCart(e1);
        c1.saveShoppingCart();
        c1.getShoppingCart().removeItemFromCart(e1);
        c1.loadShoppingCart();
        assertEquals(e1.getEventName(), c1.chooseEventFromCart(0).getEventName());
    }

}
