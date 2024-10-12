package ui;

import model.EventLog;
import ui.pageui.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The EventizeFun class represents a GUI for managing events and user accounts.
 * Users can log in as either an Organizer or a Customer, create accounts, and perform various actions
 * such as adding events, viewing event details, managing shopping carts, saving state of shopping cart
 * as well as loading. This class provides a text-based menu-driven interface to interact with the
 * EventizeFun application
 */
public class EventizeFunApp extends JFrame {
    private final JPanel panelCont = new JPanel();
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final CardLayout cl = new CardLayout();
    JButton loginButton = new JButton("Login");

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;


    // EFFECTS: runs application 'EventizeFun'
    public EventizeFunApp() {
        initJFrame();
        initMainPanel();
        setCardLayout();
        addButtonActionListener();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                for (Object element : EventLog.getInstance()) {
                    System.out.println(element);
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECT: initializes the basic JFrame components
    public void initJFrame() {
        setTitle("EventizeFun");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    //MODIFIES: this
    //EFFECT: adds the basic components to Main panel
    public void initMainPanel() {
        ImageIcon background = new ImageIcon("data/TicketBackground.jpg");
        mainPanel.setSize(WIDTH, HEIGHT);
        JLabel title = new JLabel("Welcome to EventizeFun!!!", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        mainPanel.add(title, BorderLayout.PAGE_START);
        mainPanel.add(new JLabel(background), BorderLayout.CENTER);
        mainPanel.add(loginButton, BorderLayout.PAGE_END);
    }

    //MODIFIES: this
    //EFFECT: initializes card layout then add to panelCont
    private void setCardLayout() {
        panelCont.setLayout(cl);
        panelCont.add(mainPanel, "main");
        panelCont.add(new LoginPage(panelCont, cl), "l");
        cl.show(panelCont, "main");

        add(panelCont);
    }

    //EFFECT: move to the login Page panel
    private void addButtonActionListener() {
        loginButton.addActionListener(e -> cl.show(panelCont, "l"));
    }

}