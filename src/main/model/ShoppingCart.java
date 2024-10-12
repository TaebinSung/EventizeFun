package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a shopping cart having an organizer, event name, number of total tickets,
// number of tickets sold and ticket prices
public class ShoppingCart implements Writable {
    private final ArrayList<OrgEvent> cartItems;
    private int numItems;
    private double checkoutPrice;


    // EFFECTS: construct a shopping cart having items of events, number of items,
    //          and the total price of the events in shopping cart
    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
        this.numItems = 0;
        this.checkoutPrice = 0;
    }

    // MODIFIES: this
    // EFFECTS: add Event to cart item and increment number of items in the cart
    public void addItem(OrgEvent event) {
        this.cartItems.add(event);
        this.numItems++;
        EventLog.getInstance().logEvent(new Event("Added " + event.getEventName() + " to Cart"));
    }

    // EFFECTS: return a readable list of events hosted by Organizer
    public String getCartItemList() {
        if (cartItems.isEmpty()) {
            return "\nNo item in shopping cart\n";
        }
        StringBuilder hostedEventDetails = new StringBuilder("***********************************\n");
        hostedEventDetails.append("Items in Cart:\n");

        int i = 1;
        for (OrgEvent e: cartItems) {
            hostedEventDetails.append(i).append(" - ").append(e.getEventName());
            hostedEventDetails.append("\tPrice: $").append(e.getTicketPrice());
            hostedEventDetails.append("\tTickets Left: ").append(e.getTicketsLeft()).append("\n");
            i++;
        }
        hostedEventDetails.append("***********************************\n");
        return hostedEventDetails.toString();
    }

    //EFFECTS: return the cartItem list
    public ArrayList<OrgEvent> getCartItems() {
        return this.cartItems;
    }

    // EFFECTS: get specified event from cart and return
    public OrgEvent chooseEventFromCart(int index) {
        return this.cartItems.get(index);
    }

    //MODIFIES: this
    //EFFECTS: removes event from cart and decrement numItems
    public void removeItemFromCart(OrgEvent event) {
        if (cartItems.contains(event)) {
            EventLog.getInstance().logEvent(new Event("Removed " + event.getEventName() + " from Cart"));
            this.cartItems.remove(event);
            numItems--;
        }
    }

    // MODIFIES: this
    // EFFECTS: checks the price of all Events in cart and calculate total price
    public void calculateTotalPrice() {
        double price = 0;
        for (OrgEvent e: cartItems) {
            price += e.getTicketPrice();
        }
        this.checkoutPrice = price;
    }

    // EFFECTS: returns shopping cart as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numItems", numItems);
        calculateTotalPrice();
        json.put("checkoutPrice", checkoutPrice);
        json.put("shoppingCartItems", shoppingCartToJsonArray());
        return json;
    }

    // EFFECTS: returns events in shopping cart to JsonArray
    private JSONArray shoppingCartToJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (OrgEvent e : cartItems) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    public int getNumItems() {
        return this.numItems;
    }


    public double getTotalCheckoutPrice() {
        calculateTotalPrice();
        return checkoutPrice;
    }

}
