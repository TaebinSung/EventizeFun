package ui.pageui;

import model.Organizer;
import model.OrgEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a graphic user Interface of Organizer page
public class OrganizerPage extends JPanel implements ActionListener {
    Organizer organizer;
    private final DefaultListModel<OrgEvent> eventListModel;
    private final JList<OrgEvent> eventList;
    JButton addEventButton = new JButton("Add Event");
    JButton removeEventButton = new JButton("Remove Event");
    JTextField nameTF = new JTextField("Event name");
    JTextField numTicketTF = new JTextField("1");
    JTextField ticketPriceTF = new JTextField("10");
    JPanel inputPanel;
    CardLayout cl;
    JPanel panelCont;

    JButton goBackButton = new JButton("Go back");

    // EFFECTS: construct an Organizer Page JPanel having a panel Container, CardLayout, Organizer
    // then create/initialize other components
    public OrganizerPage(JPanel panelCont, CardLayout cl, Organizer organizer) {
        this.organizer = organizer;
        this.eventListModel = new DefaultListModel<>();
        this.eventList = new JList<>(eventListModel);
        this.panelCont = panelCont;
        this.cl = cl;
        createInputPanel();
        createMainPanel();
        createActionListener();
    }

    //MODIFIES: addEventButton, removeEventButton, goBackButton
    //EFFECT: adds Action Listener to above-mentioned buttons
    public void createActionListener() {
        addEventButton.addActionListener(this);
        removeEventButton.addActionListener(this);
        goBackButton.addActionListener(e -> cl.show(panelCont, "l"));
    }

    //MODIFIES: this
    //EFFECT: adds all sub panels and J components to this JPanel
    public void createMainPanel() {
        setLayout(new BorderLayout());
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(inputPanel, BorderLayout.EAST);
        add(goBackButton, BorderLayout.SOUTH);
        add(eventList, BorderLayout.CENTER);
    }

    //MODIFIES: inputPanel
    //EFFECT: adds all input related sub panels and J components to this JPanel
    public void createInputPanel() {
        nameTF.setPreferredSize(new Dimension(80, 25));
        numTicketTF.setPreferredSize(new Dimension(80, 25));
        ticketPriceTF.setPreferredSize(new Dimension(80, 25));
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(new JLabel("Event name"));
        inputPanel.add(nameTF);
        inputPanel.add(new JLabel("Number of Tickets"));
        inputPanel.add(numTicketTF);
        inputPanel.add(new JLabel("Ticket Price"));
        inputPanel.add(ticketPriceTF);
        inputPanel.add(addEventButton);
        inputPanel.add(removeEventButton);
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    }

    //MODIFIES: addEventButton, removeEventButton
    //EFFECT: add and remove selected events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEventButton) {
            String eventName = nameTF.getText();
            int numTickets = Integer.parseInt(numTicketTF.getText());
            double ticketPrice = Double.parseDouble(ticketPriceTF.getText());
            OrgEvent newEvent = new OrgEvent(this.organizer.getName(), eventName, numTickets, ticketPrice);
            this.organizer.addEvent(newEvent);

            eventListModel.addElement(newEvent);
        } else if (e.getSource() == removeEventButton) {
            OrgEvent selectedEvent = eventList.getSelectedValue();
            organizer.removeEvent(selectedEvent);
            eventListModel.removeElement(selectedEvent);
        }
    }
}
