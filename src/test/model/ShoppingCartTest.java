package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    ShoppingCart sc;
    OrgEvent e1;
    Organizer o1;
    @BeforeEach
    void runBefore() {
        sc = new ShoppingCart();
        o1 = new Organizer("Org", "org@org.ca");
        e1 = new OrgEvent("Org", "FUN HAH", 100, 50);
    }

    @Test
    void constructorTest() {
        assertEquals(0, sc.getNumItems());
        sc.calculateTotalPrice();
        assertEquals(0, sc.getTotalCheckoutPrice());
    }

    @Test
    void addSingleItemTest() {
        sc.addItem(e1);
        assertEquals(e1, sc.chooseEventFromCart(0));
        sc.calculateTotalPrice();
        assertEquals(1, sc.getNumItems());
    }

    @Test
    void removeSingleItemFromCart() {
        sc.addItem(e1);
        sc.addItem(new OrgEvent("o1","e2",1,1));
        sc.removeItemFromCart(new OrgEvent("o1","e1",1,1));
        assertEquals(2, sc.getCartItems().size());
        sc.removeItemFromCart(e1);
        assertEquals(1, sc.getCartItems().size());
    }

    @Test
    void addSameItemTwiceTest() {
        sc.addItem(e1);
        sc.addItem(e1);
        assertEquals(e1, sc.chooseEventFromCart(0));
        assertEquals(e1, sc.chooseEventFromCart(1));
        sc.calculateTotalPrice();
        assertEquals(2, sc.getNumItems());
        assertEquals(100, sc.getTotalCheckoutPrice());
    }

    @Test
    void addMultipleItemTest() {
        OrgEvent e2 = new OrgEvent(o1.getName(), "EVENT2", 100, 120);
        sc.addItem(e1);
        sc.addItem(e2);
        assertEquals(e1, sc.chooseEventFromCart(0));
        assertEquals(e2, sc.chooseEventFromCart(1));
        sc.calculateTotalPrice();
        assertEquals(2, sc.getNumItems());
        assertEquals(170, sc.getTotalCheckoutPrice());
    }

    @Test
    void getCartItemListTest() {
        sc.addItem(e1);

        String hostedEventDetails = "***********************************\n" + "Items in Cart:\n" +
                1 + " - " + e1.getEventName() +
                "\tPrice: $" + e1.getTicketPrice() +
                "\tTickets Left: " + e1.getTicketsLeft() + "\n" +
                "***********************************\n";
        assertEquals(hostedEventDetails, sc.getCartItemList());
    }

    @Test
    void getEmptyCartItemListTest() {
        assertEquals("\nNo item in shopping cart\n", sc.getCartItemList());
    }
}
