package ui.pageui;

import model.Customer;
import model.OrgEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a graphic user Interface of Shopping cart Page
public class ShoppingCartPage extends JPanel implements ActionListener {
    Customer customer;
    JPanel panelCont;
    JPanel summaryPanel;

    private final CardLayout cl;
    private final DefaultListModel<OrgEvent> eventListModel;
    private final JList<OrgEvent> eventList;
    private JButton goBackButton;
    private JButton removeItemButton;
    private JButton saveCartButton;
    private JButton loadCartButton;
    JLabel totalPriceLabel;
    JLabel noOfItemsLabel;

    // EFFECTS: construct a Shopping Cart JPanel having a panel Container, CardLayout, Customer, and
    // create/initialize other components
    public ShoppingCartPage(JPanel panelCont, CardLayout cl, Customer customer) {
        this.customer = customer;
        this.panelCont = panelCont;
        this.cl = cl;
        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);

        createSummaryPanel();
        createShoppingList();
        createEventButtons();
        addButtonActionListener();
        addToMainPanel();
    }

    //MODIFIES: goBackButton, removeItemButton, saveCartButton, loadCartButton
    //EFFECT initializes JButtons mentioned above
    private void createEventButtons() {
        goBackButton = new JButton("Go back");
        removeItemButton = new JButton("Remove Item");
        saveCartButton = new JButton("Save Cart");
        loadCartButton = new JButton("Load Cart");
    }

    //MODIFIES: noOfItemsLabel, summaryPanel
    //EFFECT: createsSummaryPanel
    private void createSummaryPanel() {
        totalPriceLabel = new JLabel("Total checkout price: " + customer.getShoppingCart().getTotalCheckoutPrice());
        noOfItemsLabel = new JLabel(customer.getShoppingCartNum() + " Items");
        summaryPanel = new JPanel();
        summaryPanel.add(noOfItemsLabel);
        summaryPanel.add(totalPriceLabel);
    }

    //MODIFIES: noOfItemsLabel, summaryPanel
    //EFFECT initializes JButtons mentioned above
    private void updateSummaryPanel() {
        totalPriceLabel.setText("Total checkout price: " + customer.getShoppingCart().getTotalCheckoutPrice());
        noOfItemsLabel.setText(customer.getShoppingCartNum() + " Items");
    }

    //MODIFIES:this
    // EFFECTS: adds the sub JPanels and Components to the main Panel
    private void addToMainPanel() {
        JLabel titleLabel = new JLabel(customer.getName() + "'s Shopping Cart");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titleLabel, BorderLayout.PAGE_START);

        JPanel eastPanel = new JPanel();
        eastPanel.add(removeItemButton);
        eastPanel.add(saveCartButton);
        eastPanel.add(loadCartButton);

        setLayout(new BorderLayout());
        add(eastPanel, BorderLayout.EAST);
        add(eventList, BorderLayout.CENTER);
        add(goBackButton, BorderLayout.PAGE_END);
        add(summaryPanel, BorderLayout.WEST);

    }

    //MODIFIES: goBackButton, removeItemButton, saveCartButton, loadCartButton
    //EFFECT adds ActionLister to JButtons mentioned above
    private void addButtonActionListener() {
        goBackButton.addActionListener(e -> cl.show(panelCont, "c"));
        removeItemButton.addActionListener(this);
        saveCartButton.addActionListener((e -> askToSaveShoppingCart()));
        loadCartButton.addActionListener(e -> {
            askToLoadShoppingCart();
            createShoppingList();
        });
    }


    public void askToLoadShoppingCart() {
        String saveMessage = "Do you want to load your shopping cart?";
        int result = JOptionPane.showConfirmDialog(null, saveMessage, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            customer.loadShoppingCart();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void askToSaveShoppingCart() {
        String saveMessage = "Do you want to save your shopping cart?";
        int result = JOptionPane.showConfirmDialog(null, saveMessage, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            customer.saveShoppingCart();
        }
    }

    //MODIFIES: eventListModel
    //EFFECT updates/create JList according to the customers shopping Cart
    public void createShoppingList() {
        eventListModel.clear();
        for (OrgEvent event : customer.getShoppingCart().getCartItems()) {
            eventListModel.addElement(event);
        }
    }

    //MODIFIES: removeItemButton
    //EFFECTS: defines ActionListener functionality to removeItemButton
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeItemButton) {
            OrgEvent selectedEvent = eventList.getSelectedValue();
            customer.getShoppingCart().removeItemFromCart(selectedEvent);
            eventListModel.removeElement(selectedEvent);
        }
        updateSummaryPanel();
    }

}
