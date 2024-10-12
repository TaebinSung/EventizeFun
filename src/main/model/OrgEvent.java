package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an Event having an organizer, event name, number of total tickets,
// number of tickets sold and ticket prices
public class OrgEvent implements Writable {
    private final String organizer;
    private final String eventName;
    private final double ticketPrice;
    private final int numTotalTickets;
    private final int numTicketsSold;

    // EFFECTS: construct an event having an organizer, event name, number of total tickets,
    //          number of tickets sold and ticket prices
    public OrgEvent(String organizer, String eventName, int numTotalTickets, double genericTicketPrice) {
        this.organizer = organizer;
        this.eventName = eventName;
        this.numTotalTickets = numTotalTickets;
        this.ticketPrice = genericTicketPrice;
        this.numTicketsSold = 0;

    }

    // MODIFIES: this
    // EFFECT: calculates the tickets available and return
    public int getTicketsLeft() {
        return numTotalTickets - numTicketsSold;
    }

    public int getNumTotalTickets() {
        return this.numTotalTickets;
    }

    public String getEventName() {
        return this.eventName;
    }

    public double getTicketPrice() {
        return this.ticketPrice;
    }

    public String getOrganizerName() {
        return organizer;
    }

    public int getNumTicketsSold() {
        return this.numTicketsSold;
    }

    // EFFECT: returns Event as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("organizer", organizer);
        json.put("eventName", eventName);
        json.put("ticketPrice", ticketPrice);
        json.put("numTotalTickets", numTotalTickets);
        json.put("numTicketsSold", numTicketsSold);
        return json;
    }

    @Override
    public String toString() {
        return getEventName() + "\t" + getTicketPrice() + "\t" + getTicketsLeft();
    }
}
