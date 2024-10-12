package persistence;

import model.ShoppingCart;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/* Referenced JsonWriter functionalities to fit the model of the EventFunApp
 ***************************************************************************************
 *    Title: JsonSerializationDemo.java
 *    Author: Carter, Paul
 *    Date: Oct 18th, 2023
 *    Code version: <0df5e7cb84b79f9186b4c6f4aa339ae0f8c8c148> (REVISION NUMBER)
 *    Availability: CPSC 210 GitHub
 ***************************************************************************************/

// Represents a writer that writes JSON representation of customer to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // REQUIRES: Shopping cart is not empty
    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(ShoppingCart sc) {
        JSONObject json = sc.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
