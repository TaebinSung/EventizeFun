package persistence;

import org.json.JSONObject;

/* Writable interface to fit the model of the EventFunApp
 ***************************************************************************************
 *    Title: JsonSerializationDemo.java
 *    Author: Carter, Paul
 *    Date: Oct 18th, 2023
 *    Code version: <0df5e7cb84b79f9186b4c6f4aa339ae0f8c8c148> (REVISION NUMBER)
 *    Availability: CPSC 210 GitHub
 ***************************************************************************************/
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
