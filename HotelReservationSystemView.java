import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class HotelReservationSystemView extends JFrame {




    //For the MainMenu
    private JButton btnCreateHotel, btnViewHotel, btnManageHotel, btnSimulateBooking, btnBackToMainMenu;

    private JPanel northPanel, westPanel, centerPanel;




    public HotelReservationSystemView() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(800, 600);

        displayMainMenu();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void displayMainMenu() {
            //North Panel - For Title Heading
        northPanel = new JPanel();
        northPanel.setBackground(Color.LIGHT_GRAY);
        northPanel.setLayout(new FlowLayout());


        JLabel titleLabel = new JLabel("Hotel Reservation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(30, 144, 255)); // Dark blue color

        northPanel.add(titleLabel);



        // West Panel - Main Menu Buttons (Create, View, Manage, Simulate Booking, Back to Main Menu)
        westPanel = new JPanel();
        westPanel.setBackground(Color.DARK_GRAY);
        westPanel.setLayout(new GridBagLayout());
        westPanel.setPreferredSize(new Dimension(150, 600));


        btnCreateHotel = createMenuButton("Create Hotel");
        btnViewHotel = createMenuButton("View Hotel");
        btnManageHotel = createMenuButton("Manage Hotel");
        btnSimulateBooking = createMenuButton("Book Hotel");
        btnBackToMainMenu = createMenuButton("Back to Main");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0); // Spacing between buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;

        westPanel.add(btnCreateHotel, gbc);
        westPanel.add(btnViewHotel, gbc);
        westPanel.add(btnManageHotel, gbc);
        westPanel.add(btnSimulateBooking, gbc);// Spacing between buttons
        westPanel.add(btnBackToMainMenu, gbc);


        // Center Panel - For Content
        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(30, 144, 255));





        // Adding panels to frame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
    }


    private JButton createMenuButton(String btnLabel) {
        JButton button = new JButton(btnLabel);
        button.setPreferredSize(new Dimension(150, 50));

        // Customizing main menu button appearance
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.GRAY); // Dark blue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        return button;
    }

    public void setMainMenuActionListener (ActionListener listener) {
        btnCreateHotel.addActionListener(listener);
        btnViewHotel.addActionListener(listener);
        btnManageHotel.addActionListener(listener);
        btnSimulateBooking.addActionListener(listener);
        btnBackToMainMenu.addActionListener(listener);
    }


    public void showCreateHotelForm() {
        JPanel createHotelPanel = new JPanel(new BorderLayout());


        JPanel createFormPanel = new JPanel(new GridLayout(6, 2, 0, 35));
        createFormPanel.setBorder(new EmptyBorder(10, 25, 20, 25));
        createFormPanel.setPreferredSize(new Dimension(635, 453));
        createFormPanel.setBackground(Color.GRAY);

        JTextField hotelNameField = new JTextField();
        JTextField numberOfStandardRoomsField = new JTextField();
        JTextField numberOfDeluxeRoomsField = new JTextField();
        JTextField numberOfExecutiveRoomsField = new JTextField();
        JTextField basePriceField = new JTextField();


// Create a Font object with the desired size
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font fieldFont = new Font("Arial", Font.BOLD, 20);

// Create and add labels with the new font
        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        hotelNameLabel.setFont(labelFont);
        createFormPanel.add(hotelNameLabel);

// Create and configure the text field for hotel name
        hotelNameField.setFont(fieldFont);
        createFormPanel.add(hotelNameField);

        JLabel standardRoomsLabel = new JLabel("Number of Standard Rooms:");
        standardRoomsLabel.setFont(labelFont);
        createFormPanel.add(standardRoomsLabel);

// Create and configure the text field for standard rooms
        numberOfStandardRoomsField.setFont(fieldFont);
        createFormPanel.add(numberOfStandardRoomsField);

        JLabel deluxeRoomsLabel = new JLabel("Number of Deluxe Rooms:");
        deluxeRoomsLabel.setFont(labelFont);
        createFormPanel.add(deluxeRoomsLabel);

// Create and configure the text field for deluxe rooms
        numberOfDeluxeRoomsField.setFont(fieldFont);
        createFormPanel.add(numberOfDeluxeRoomsField);

        JLabel executiveRoomsLabel = new JLabel("Number of Executive Rooms:");
        executiveRoomsLabel.setFont(labelFont);
        createFormPanel.add(executiveRoomsLabel);

// Create and configure the text field for executive rooms
        numberOfExecutiveRoomsField.setFont(fieldFont);
        createFormPanel.add(numberOfExecutiveRoomsField);

        JLabel basePriceLabel = new JLabel("Base Price:");
        basePriceLabel.setFont(labelFont);
        createFormPanel.add(basePriceLabel);

// Create and configure the text field for base price
        basePriceField.setFont(fieldFont);
        createFormPanel.add(basePriceField);

// Create a panel for the submit button
        JPanel submitButtonPanel = new JPanel(new FlowLayout());
        JButton btnCreateSubmit = new JButton("Submit");
        btnCreateSubmit.setBackground(Color.GRAY); // Dark blue color
        btnCreateSubmit.setForeground(Color.WHITE);
        btnCreateSubmit.setFocusPainted(false);
        btnCreateSubmit.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnCreateSubmit.setFont(labelFont);
        btnCreateSubmit.setPreferredSize(new Dimension(150, 50));

        submitButtonPanel.add(btnCreateSubmit);

// Add the form panel and submit button panel to the main panel
        createHotelPanel.add(createFormPanel, BorderLayout.CENTER);
        createHotelPanel.add(submitButtonPanel, BorderLayout.SOUTH);

// Optionally, if updateCenterPanel is a method to adjust layout or settings,
// ensure it is called after all components are added to the panel.
        updateCenterPanel(createHotelPanel);
    }

    public void updateCenterPanel(JPanel panel) {
        // Clear the current components of the center panel
        centerPanel.removeAll();
        centerPanel.add(panel);
        // Revalidate and repaint the center panel to reflect the changes
        centerPanel.revalidate();
        centerPanel.repaint();
    }

}