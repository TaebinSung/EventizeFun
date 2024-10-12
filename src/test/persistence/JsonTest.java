package persistence;

import model.OrgEvent;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    protected void checkEvent(String organizer, String eventName, int numTotalTickets, double genericTicketPrice, OrgEvent e) {
        assertEquals(organizer, e.getOrganizerName());
        assertEquals(eventName, e.getEventName());
        assertEquals(numTotalTickets, e.getNumTotalTickets());
        assertEquals(genericTicketPrice, e.getTicketPrice());
    }
}
