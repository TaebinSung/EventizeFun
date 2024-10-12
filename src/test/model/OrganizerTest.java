package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizerTest {
    Organizer o1;
    OrgEvent e1;

    @BeforeEach
    void runBefore() {
        o1 = new Organizer("Organizer", "org@party.com");
        e1 = new OrgEvent(o1.getName(), "Fun party", 100, 15);
        o1.addEvent(e1);
    }

    @Test
    void constructorTest() {
        o1 = new Organizer("tun", "tun1123@org.com");
        assertEquals("tun", o1.getName());
        assertEquals("tun1123@org.com", o1.getEmail());
        assertEquals(0, o1.getNumberOfHostedEvents());
        assertEquals(0, o1.getIncome());
    }

    @Test
    void removeEventByObject() {
        o1.removeEvent(e1);
        assertEquals(0, o1.getNumberOfHostedEvents());
    }

    @Test
    void addSingleEventTest() {
        o1.addEvent(o1.getName(),"Bad party", 5, 1);
        assertEquals("Fun party", o1.getEvent(0).getEventName());
        assertEquals("Bad party", o1.getEvent(1).getEventName());
        assertEquals(2, o1.getNumberOfHostedEvents());
        assertEquals(0, o1.getIncome());
    }

    @Test
    void addMultipleEventTest() {
        o1.addEvent(o1.getName(),"Bad party", 5, 1);
        o1.addEvent(o1.getName(),"Eh party", 15, 2);
        assertEquals("Bad party", o1.getEvent(1).getEventName());
        assertEquals("Eh party", o1.getEvent(2).getEventName());
        assertEquals(3, o1.getEventList().size());
        assertEquals(3, o1.getNumberOfHostedEvents());
        assertEquals(0, o1.getIncome());
    }

    @Test
    void cancelEventTest() {
        o1.removeEvent(0);
        assertEquals(0, o1.getNumberOfHostedEvents());
    }
    @Test
    void cancelMiddleEventTest() {
        o1.addEvent(o1.getName(),"Bad party", 5, 1);
        o1.addEvent(o1.getName(),"Eh party", 15, 2);
        o1.removeEvent(1);
        assertEquals("Fun party", o1.getEvent(0).getEventName());
        assertEquals("Eh party", o1.getEvent(1).getEventName());
        assertEquals(2, o1.getNumberOfHostedEvents());
    }



}

