package ui.pageui;

import model.Customer;
import model.OrgEvent;
import model.Organizer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a graphic user Interface of customer Page
public class CustomerPage extends JPanel implements ActionListener {

    Organizer organizer;
    Customer customer;
    JPanel panelCont;
    private final DefaultListModel<OrgEvent> eventListModel;
    private final JList<OrgEvent> eventList;
    JButton addEventToCartButton = new JButton("Add Event to cart");
    JButton showShoppingCartButton = new JButton("Show Shopping cart");
    JButton goBackButton = new JButton("Go back");
    JTextArea messages = new JTextArea();
    private final CardLayout cl;
    JPanel inputPanel;

    // EFFECTS: construct a Customer Page JPanel having a panel Container, CardLayout, Organizer, Customer, and
    // create/initialize other components
    public CustomerPage(JPanel panelCont, CardLayout cl, Organizer organizer, Customer customer) {
        this.organizer = organizer;
        this.eventListModel = new DefaultListModel<>();
        this.eventList = new JList<>(eventListModel);
        this.customer = customer;
        this.panelCont = panelCont;
        this.cl = cl;

        createInputPanel();
        addActionListener();
        updateEventList();
        addToMainPanel();
    }

    //MODIFIES: inputPanel
    //EFFECT: adds Input Buttons and initialize basic components of this panel
    public void createInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(addEventToCartButton);
        inputPanel.add(showShoppingCartButton);
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    }

    //MODIFIES: this
    //EFFECT: adds all sub panels and J components to this JPanel
    public void addToMainPanel() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Customer Page");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(titleLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.EAST);
        add(goBackButton, BorderLayout.WEST);
        add(eventList, BorderLayout.CENTER);
        add(messages, BorderLayout.PAGE_END);
    }

    //MODIFIES: goBackButton, addEventToCardButton, showShoppingCartButton
    //EFFECT: adds actionEventListener to above-mentioned buttons
    public void addActionListener() {
        goBackButton.addActionListener(e -> cl.show(panelCont, "l"));
        addEventToCartButton.addActionListener(this);
        showShoppingCartButton.addActionListener(e -> {
            messages.setText("");
            panelCont.add(new ShoppingCartPage(panelCont,cl,customer), "s");
            cl.show(panelCont,"s");
        });
    }

    //MODIFIES: eventListModel
    //EFFECT: adds all events hosted by organizer to this JList
    public void updateEventList() {
        eventListModel.clear();
        for (OrgEvent event : organizer.getEventList()) {
            eventListModel.addElement(event);
        }
    }

    //MODIFIES: addEventToCart
    //EFFECT: add selected event to shopping cart
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEventToCartButton) {
            OrgEvent selectedEvent = eventList.getSelectedValue();
            if (selectedEvent != null) {
                customer.addItemToCart(selectedEvent);
                messages.append(selectedEvent + " added to shopping cart\n");
            }

        }
    }
}

