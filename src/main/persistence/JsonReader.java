package persistence;

import model.OrgEvent;

import model.ShoppingCart;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;

/* Referenced JsonReader functionalities to fit the model of the EventFunApp
 ***************************************************************************************
 *    Title: JsonSerializationDemo.java
 *    Author: Carter, Paul
 *    Date: Oct 18th, 2023
 *    Code version: <0df5e7cb84b79f9186b4c6f4aa339ae0f8c8c148> (REVISION NUMBER)
 *    Availability: CPSC 210 GitHub
 ***************************************************************************************/

// Represents a reader that reads shopping cart from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShoppingCart read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShoppingCart(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses shoppingCart from JSON object and returns it
    private ShoppingCart parseShoppingCart(JSONObject jsonObject) {
        ShoppingCart sc = new ShoppingCart();
        if (jsonObject.getJSONArray("shoppingCartItems").isEmpty()) {
            return sc;
        }
        JSONArray jsonArray = jsonObject.getJSONArray("shoppingCartItems");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            addEvent(sc, nextEvent);
        }
        return sc;
    }

    // MODIFIES: sc
    // EFFECTS: parses shoppingCart from JSON object and adds it to workroom
    private void addEvent(ShoppingCart sc, JSONObject jsonObject) {
        String organizer = jsonObject.getString("organizer");
        String eventName = jsonObject.getString("eventName");
        int numTotalTickets = jsonObject.getInt("numTotalTickets");
        double ticketPrice = jsonObject.getDouble("ticketPrice");
        sc.addItem(new OrgEvent(organizer,  eventName,  numTotalTickets, ticketPrice));
    }
}
