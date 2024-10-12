package ui;

import model.Organizer;

import java.util.Scanner;

// Represents a user Interface of Organizer
public class OrganizerUI {
    private Organizer organizer;
    private Scanner input;

    public OrganizerUI() {
        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes Scanner
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: displays the account details of organizer
    public void viewAccountDetails() {
        System.out.println(organizer.getUserAccountDetails());
    }

    // EFFECTS: processes organizer commands
    public void displayPageOptions() {
        System.out.println("\td -> View Account Details:");
        System.out.println("\ta -> Add Event");
        System.out.println("\th -> Display Hosting Event");
        System.out.println("\tc -> Cancel Event");
        System.out.println("\tb -> Go back");
    }

    // MODIFIES: this, organizer
    // EFFECTS: create organizer account
    public Organizer makeAccount() {
        System.out.println("Enter your name: ");
        String name = input.next();
        System.out.println("Enter your email: ");
        String email = input.next();

        organizer = new Organizer(name, email);
        return organizer;
    }

    // MODIFIES: this
    // EFFECTS: processes organizer inputs for organizer page
    public void displayOrganizerPage() {
        boolean back = false;
        String command;
        while (!back) {
            displayPageOptions();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("b")) {
                System.out.println("GOING BACK");
                back = true;
            } else {
                processOrganizerPageCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes organizer commands
    private void processOrganizerPageCommand(String command) {
        switch (command) {
            case "d":
                viewAccountDetails();
                break;
            case "a":
                addEvent();
                break;
            case "h":
                displayHostingEvents();
                break;
            case "c":
                cancelEvent();
                break;
            default:
                System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this, organizer
    // EFFECTS: processes input, organizer hosts event specified
    private void addEvent() {
        System.out.println("Enter Event name:");
        String eventName = input.next();
        System.out.println("Number of tickets available:");
        int numTotalTickets = input.nextInt();
        System.out.println("Choose Ticket price:");
        double genericTicketPrice = input.nextDouble();

        this.organizer.addEvent(organizer.getName(), eventName, numTotalTickets, genericTicketPrice);
    }

    // EFFECTS: display currently hosted events
    private void displayHostingEvents() {
        if (organizer.getNumberOfHostedEvents() > 0) {
//            System.out.println(organizer.getHostingEvents());
        } else {
            System.out.println("**You are not hosting any events yet!!\n");
        }
    }

    // MODIFIES: this, organizer
    // EFFECTS: processes organizer commands
    private void cancelEvent() {
        if (organizer.getNumberOfHostedEvents() == 0) {
            System.out.println("**You don't have any events to cancel!!\n");
            return;
        }
        System.out.println("Enter Event number to cancel:");
        int eventIndex = input.nextInt();
        if (eventIndex > organizer.getNumberOfHostedEvents()) {
            System.out.println("Wrong selection");
//            System.out.println(organizer.getHostingEvents());
        } else {
            organizer.removeEvent(eventIndex - 1);
        }
    }
}
