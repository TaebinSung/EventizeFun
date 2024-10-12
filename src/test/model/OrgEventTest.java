package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OrgEventTest {
    Customer c1;
    Organizer o1;
    OrgEvent e1;
    OrgEvent e2;

    @BeforeEach
    void runBefore() {
        o1 = new Organizer("Organizer", "org@party.com");
        e1 = new OrgEvent(o1.getName(), "Fun party", 100, 15);
        e2 = new OrgEvent(o1.getName(), "Bad party", 5, 1);
        c1 = new Customer("Tae", "xoqlsdi7@gmail.com");
    }

    @Test
    void constructorTest() {
        e1 = new OrgEvent(o1.getName(),"Best Party Ever", 200, 20);
        assertEquals("Best Party Ever", e1.getEventName());
        assertEquals("Organizer", e1.getOrganizerName());
        assertEquals(200, e1.getTicketsLeft());
        assertEquals(20, e1.getTicketPrice());
    }

    @Test
    void toStringTest() {
        assertEquals("Fun party\t15.0\t100", e1.toString());
    }
}
