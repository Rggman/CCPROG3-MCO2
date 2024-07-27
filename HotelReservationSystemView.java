import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class HotelReservationSystemView extends JFrame {

    // For the MainMenu
    private JButton btnCreateHotel, btnViewHotel, btnManageHotel, btnSimulateBooking, btnBackToMainMenu;

    private JPanel northPanel, westPanel, centerPanel;
    private JPanel mainMenuPanel, createHotelPanel;
    private JButton btnCreateSubmit;

    public HotelReservationSystemView() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(800, 600);

        initMainMenuPanel();
        initCreateHotelPanel();

        // Initialize center panel and set main menu as the default view
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(mainMenuPanel, BorderLayout.CENTER);

        // Add north and west panels to the frame
        initNorthPanel();
        initWestPanel();

        this.add(northPanel, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initNorthPanel() {
        northPanel = new JPanel();
        northPanel.setBackground(Color.LIGHT_GRAY);
        northPanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Hotel Reservation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(30, 144, 255)); // Dark blue color

        northPanel.add(titleLabel);
    }

    private void initWestPanel() {
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
        westPanel.add(btnSimulateBooking, gbc);
        westPanel.add(btnBackToMainMenu, gbc);
    }

    private void initMainMenuPanel() {
        mainMenuPanel = new JPanel();
        mainMenuPanel.setBackground(new Color(30, 144, 255));
    }

    private void initCreateHotelPanel() {
        createHotelPanel = new JPanel(new BorderLayout());

        JPanel createFormPanel = new JPanel(new GridLayout(6, 2, 0, 35));
        createFormPanel.setBorder(new EmptyBorder(10, 25, 20, 25));
        createFormPanel.setPreferredSize(new Dimension(635, 453));
        createFormPanel.setBackground(Color.GRAY);

        JTextField hotelNameField = new JTextField();
        JTextField numberOfStandardRoomsField = new JTextField();
        JTextField numberOfDeluxeRoomsField = new JTextField();
        JTextField numberOfExecutiveRoomsField = new JTextField();
        JTextField basePriceField = new JTextField();

        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font fieldFont = new Font("Arial", Font.BOLD, 20);

        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        hotelNameLabel.setFont(labelFont);
        createFormPanel.add(hotelNameLabel);
        hotelNameField.setFont(fieldFont);
        createFormPanel.add(hotelNameField);

        JLabel standardRoomsLabel = new JLabel("Number of Standard Rooms:");
        standardRoomsLabel.setFont(labelFont);
        createFormPanel.add(standardRoomsLabel);
        numberOfStandardRoomsField.setFont(fieldFont);
        createFormPanel.add(numberOfStandardRoomsField);

        JLabel deluxeRoomsLabel = new JLabel("Number of Deluxe Rooms:");
        deluxeRoomsLabel.setFont(labelFont);
        createFormPanel.add(deluxeRoomsLabel);
        numberOfDeluxeRoomsField.setFont(fieldFont);
        createFormPanel.add(numberOfDeluxeRoomsField);

        JLabel executiveRoomsLabel = new JLabel("Number of Executive Rooms:");
        executiveRoomsLabel.setFont(labelFont);
        createFormPanel.add(executiveRoomsLabel);
        numberOfExecutiveRoomsField.setFont(fieldFont);
        createFormPanel.add(numberOfExecutiveRoomsField);

        JLabel basePriceLabel = new JLabel("Base Price:");
        basePriceLabel.setFont(labelFont);
        createFormPanel.add(basePriceLabel);
        basePriceField.setFont(fieldFont);
        createFormPanel.add(basePriceField);

        JPanel submitButtonPanel = new JPanel(new FlowLayout());
        btnCreateSubmit = new JButton("Submit");
        btnCreateSubmit.setBackground(Color.GRAY); // Dark blue color
        btnCreateSubmit.setForeground(Color.WHITE);
        btnCreateSubmit.setFocusPainted(false);
        btnCreateSubmit.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnCreateSubmit.setFont(labelFont);
        btnCreateSubmit.setPreferredSize(new Dimension(150, 50));
        btnCreateSubmit.setActionCommand("Submit");

        submitButtonPanel.add(btnCreateSubmit);

        createHotelPanel.add(createFormPanel, BorderLayout.CENTER);
        createHotelPanel.add(submitButtonPanel, BorderLayout.SOUTH);
    }

    private JButton createMenuButton(String btnLabel) {
        JButton button = new JButton(btnLabel);
        button.setPreferredSize(new Dimension(150, 50));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.GRAY); // Dark blue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }

    public void displayMainMenu() {
        updateCenterPanel(mainMenuPanel);
    }

    public void displayCreateHotelForm() {
        updateCenterPanel(createHotelPanel);
    }

    public void updateCenterPanel(JPanel panel) {
        centerPanel.removeAll();
        centerPanel.add(panel, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void setMainMenuActionListener(ActionListener listener) {
        btnCreateHotel.addActionListener(listener);
        btnViewHotel.addActionListener(listener);
        btnManageHotel.addActionListener(listener);
        btnSimulateBooking.addActionListener(listener);
        btnBackToMainMenu.addActionListener(listener);
        btnCreateSubmit.addActionListener(listener);
    }


}
