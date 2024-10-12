package model;

import java.util.ArrayList;

// Represents an Organizer having a name, email, and the list of events hosting
public class Organizer extends Account {
    private final ArrayList<OrgEvent> eventList;
    private double income;

    // EFFECTS: construct an Organizer having a name, email, and the list of events hosting
    public Organizer(String name, String email) {
        super(name, email);
        eventList = new ArrayList<>();
        this.income = 0;
        EventLog.getInstance().logEvent(new Event("Organizer: " + name + " Created"));
    }

    // MODIFIES: this
    // EFFECTS: create Event and add to list of events organizer is hosting
    public void addEvent(String organizer, String eventName, int numTotalTickets, double genericTicketPrice) {
        this.eventList.add(new OrgEvent(organizer, eventName, numTotalTickets, genericTicketPrice));
        EventLog.getInstance().logEvent(new Event(getName() + " added " + eventName + " to Event List"));
    }

    // MODIFIES: this
    // EFFECTS: gets Event and add to list of events organizer is hosting
    public void addEvent(OrgEvent event) {
        this.eventList.add(event);
        EventLog.getInstance().logEvent(new Event(getName() + " added " + event.getEventName() + " to Event List"));
    }

    // REQUIRES: is a valid index
    // MODIFIES: this
    // EFFECTS: remove specified Event  from list of events organizer is hosting
    public void removeEvent(int index) {
        EventLog.getInstance().logEvent(new Event(getName() + " removed "
                + eventList.get(index).getEventName() + " to Event List"));
        this.eventList.remove(index);
    }

    public void removeEvent(OrgEvent event) {
        EventLog.getInstance().logEvent(new Event(getName() + " removed "
                + event.getEventName() + " to Event List"));
        this.eventList.remove(event);
    }

    // REQUIRES: is a valid index
    // MODIFIES: this
    // EFFECTS: get specified Event from list of events organizer is hosting
    public OrgEvent getEvent(int index) {
        return this.eventList.get(index);
    }

    // EFFECTS: return how many events there are
    public int getNumberOfHostedEvents() {
        return eventList.size();
    }

    // EFFECTS: returns the total income earned
    public double getIncome() {
        checkTotalIncome();
        return income;
    }

    //***************************PRIVATE METHODS*******************************//

    // MODIFIES: this
    // EFFECTS: update the total income the organizer has made so far
    private void checkTotalIncome() {
        double incomeFromEvents = 0;
        for (OrgEvent e: eventList) {
            incomeFromEvents += e.getTicketPrice() * e.getNumTicketsSold();
        }
        this.income = incomeFromEvents;
    }

    public ArrayList<OrgEvent> getEventList() {
        return eventList;
    }


}
