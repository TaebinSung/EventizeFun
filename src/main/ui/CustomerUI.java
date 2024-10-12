package ui;

import model.Customer;
import model.Organizer;

import java.util.Scanner;

// Represents a user Interface of Customer
public class CustomerUI {
    private Customer customer;
    private Scanner input;

    public CustomerUI() {
        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes Scanner
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: displays the account details of customer
    public void viewAccountDetails() {
        System.out.println(customer.getUserAccountDetails());
    }

    // EFFECT: Display all command options available to customer
    public void displayPageOptions() {
        System.out.println("\td -> View Account Details:");
        System.out.println("\tv -> View Events Available");
        System.out.println("\ta -> Add to shopping cart");
        System.out.println("\ts -> View shopping cart");
        System.out.println("\tb -> Go back");
    }

    // MODIFIES: this, customer
    // EFFECTS: create customer account
    public Customer makeAccount() {
        System.out.println("Enter your name: ");
        String name = input.next();
        System.out.println("Enter your email: ");
        String email = input.next();

        customer = new Customer(name, email);
        return customer;
    }


    // MODIFIES: this
    // EFFECTS: processes customer commands for customer page
    public void displayCustomerPage(Organizer organizer) {
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
                processCustomerPageCommand(command, organizer);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes customer commands
    private void processCustomerPageCommand(String command, Organizer organizer) {
        switch (command) {
            case "d":
                viewAccountDetails();
                break;
            case "v":
                displayAllEventsAvailable(organizer);
                break;
            case "a":
                addEventToCart(organizer);
                break;
            case "s":
                viewShoppingCart();
                break;
            default:
                selectionNotValidNotify();
        }
    }

    // MODIFIES: this, customer, shoppingCart
    // EFFECT: add the hosted event to customers shopping cart
    private void addEventToCart(Organizer organizer) {
        System.out.println("Enter the event number you want to add:");
        int eventIndex = input.nextInt();
        if (eventIndex > organizer.getNumberOfHostedEvents()) {
            selectionNotValidNotify();
        } else {
            customer.addItemToCart(organizer.getEvent(eventIndex - 1));
            System.out.println("\nAdded " + organizer.getEvent(eventIndex - 1).getEventName()
                    + " to your shopping cart\n");
        }

    }

    // EFFECTS: prints the lists of events in shopping cart
    public void viewShoppingCart() {
        System.out.println(customer.getShoppingCartItems());
    }

    // EFFECT: Display all the events available for the organizer
    private void displayAllEventsAvailable(Organizer organizer) {
        System.out.println("THERE IS NO HOSTED EVENT SO FAR\n");
    }

    // EFFECTS: prints on console that selection is not valid
    private void selectionNotValidNotify() {
        System.out.println("Selection not valid...");
    }
}
