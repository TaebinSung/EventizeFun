package model;

import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a Customer having a name, email, and a shopping cart with no events
public class Customer extends Account implements Writable {
    private ShoppingCart shoppingCart;
    private static final String JSON_STORE = "./data/ShoppingCart.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: construct a customer having name, email and a shopping cart;
    public Customer(String name, String email) {
        super(name, email);
        this.shoppingCart = new ShoppingCart();
        initJson();
        EventLog.getInstance().logEvent(new Event("Customer: " + name + " Created"));
    }

    // MODIFIES: this, shoppingCart
    // EFFECT: add event to users, shoppingCart
    public  void addItemToCart(OrgEvent event) {
        shoppingCart.addItem(event);
    }

    // EFFECT: get the specified event from the shopping cart
    public OrgEvent chooseEventFromCart(int index) {
        return shoppingCart.chooseEventFromCart(index);
    }

    // EFFECT: get information of all the events added to shopping car
    public String getShoppingCartItems() {
        return shoppingCart.getCartItemList();
    }

    // EFFECT: return the customers shopping cart
    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    // EFFECT: return how many items in shopping cart
    public int getShoppingCartNum() {
        return shoppingCart.getNumItems();
    }

    // MODIFIES: this
    // EFFECT: accept a new shopping cart with items inside
    public void setShoppingCart(ShoppingCart sc) {
        this.shoppingCart = sc;
    }


    // EFFECTS: saves the customer to file
    public void saveShoppingCart() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.getShoppingCart());
            jsonWriter.close();
            System.out.println("Saved " + this.getName() + " to " + JSON_STORE);
            EventLog.getInstance().logEvent(new Event("Saved " + getName() + "'s ShoppingCart"));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes Scanner
    private void initJson() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: customer
    // EFFECTS: loads shopping cart from file
    public void loadShoppingCart() {
        try {
            this.setShoppingCart(jsonReader.read());
            EventLog.getInstance().logEvent(new Event("Loaded " + getName() + "'s ShoppingCart"));
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: returns customer JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", getName());
        json.put("email", getEmail());
        json.put("shoppingCart", shoppingCart.toJson());
        return json;
    }
}
