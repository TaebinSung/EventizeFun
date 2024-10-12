package ui.pageui;

import model.Customer;
import model.Organizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a graphic user Interface of log in page
public class LoginPage extends JPanel implements ActionListener {
    Organizer organizer;
    Customer customer;
    private final JTextField usernameTF = new JTextField("Username");
    private final JTextField emailTF = new JTextField("Email");
    private final JButton loginButton = new JButton("Login");
    JButton goBackButton = new JButton("Go back");
    JRadioButton organizerRadioBtn = new JRadioButton("Organizer");
    JRadioButton customerRadioBtn = new JRadioButton("Customer");
    ButtonGroup buttonGroup = new ButtonGroup();
    JPanel radioButtonPanel;
    JPanel infoEntryPanel;
    CardLayout cl;
    JPanel panelCont;

    // EFFECTS: construct an LoginPage JPanel having a panel Container and CardLayout and
    // create/initialize other components
    public LoginPage(JPanel panelContainer, CardLayout cl) {
        this.panelCont = panelContainer;
        this.cl = cl;
        setLayout(new BorderLayout());
        setSubPanels();
        addToMainPanel();
        addActionListener();
    }

    //MODIFIES: buttonGroup, organizerRadioBtn, infoEntryPanel, UsernameTF, emailTF
    //EFFECT: initialize and set basic components of above classes
    public void setSubPanels() {
        buttonGroup.add(organizerRadioBtn);
        buttonGroup.add(customerRadioBtn);
        organizerRadioBtn.setSelected(true);

        radioButtonPanel = new JPanel();
        radioButtonPanel.add(organizerRadioBtn);
        radioButtonPanel.add(customerRadioBtn);

        infoEntryPanel = new JPanel();
        usernameTF.setColumns(15);
        emailTF.setColumns(15);
        usernameTF.setHorizontalAlignment(JTextField.CENTER);
        emailTF.setHorizontalAlignment(JTextField.CENTER);
        infoEntryPanel.add(usernameTF);
        infoEntryPanel.add(emailTF);
    }

    //MODIFIES: this
    //EFFECT: adds all sub panels and J components to this JPanel
    public void addToMainPanel() {
        add(radioButtonPanel, BorderLayout.PAGE_START);
        add(infoEntryPanel, BorderLayout.PAGE_END);
        add(loginButton, BorderLayout.EAST);
        add(goBackButton, BorderLayout.WEST);
        add(new JLabel(new ImageIcon("data/TicketBackground.jpg")), BorderLayout.CENTER);
    }

    //MODIFIES: goBackButton, loginButton
    //EFFECT: adds the action listener to mentioned above classes
    public void addActionListener() {
        goBackButton.addActionListener(e -> cl.show(panelCont, "main"));
        loginButton.addActionListener(this);
    }

    //EFFECT: returns the character of selected radioButton
    public String getSelectedRole() {
        if (organizerRadioBtn.isSelected()) {
            return "o";
        }
        return "c";
    }

    //MODIFIES: this
    //EFFECT: Overrides the functionality of the loginButton according to the account
    // option selected by the radio button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String name = usernameTF.getText();
            String email = emailTF.getText();
            String selectedRole = getSelectedRole();

            if (selectedRole.equals("o")) {
                if (this.organizer == null) {
                    organizer = new Organizer(name, email);
                    OrganizerPage organizerPanel = new OrganizerPage(panelCont, cl, organizer);
                    panelCont.add(organizerPanel, "o");
                }
                cl.show(panelCont, "o");
            } else if (selectedRole.equals("c")) {
                if (this.organizer != null) {
                    if (this.customer == null) {
                        customer = new Customer(name, email);
                        CustomerPage customerPanel = new CustomerPage(panelCont, cl, organizer, customer);
                        panelCont.add(customerPanel, "c");
                    }
                    cl.show(panelCont, "c");
                }
            }
        }
    }
}
