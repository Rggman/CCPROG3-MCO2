import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HotelReservationSystemView extends JFrame {

    // Buttons for the MainMenu
    private JButton btnCreateHotel, btnViewHotel, btnManageHotel, btnSimulateBooking, btnBackToMainMenu;

    private JPanel northPanel, westPanel, centerPanel;
    private JPanel mainMenuPanel;

    // For Create Hotel
    private JPanel createHotelPanel;
    private JButton btnCreateSubmit;
    private JTextField hotelNameField = new JTextField();
    private JTextField numberOfStandardRoomsField = new JTextField();
    private JTextField numberOfDeluxeRoomsField = new JTextField();
    private JTextField numberOfExecutiveRoomsField = new JTextField();
    private JTextField basePriceField = new JTextField();

    //For View Hotel
    private JPanel viewHotelPanel;
    private JButton btnViewDetails, btnCheckAvailability, btnRoomInfo, btnReservationInfo;

    // For Mange Hotel
    private JPanel manageHotelsPanel;
    private JButton btnAddRoom, btnRemoveRoom, btnChangeName,btnChangePrice, btnDeleteHotel, btnDatePrice;

    // For Simulate Booking
    private JPanel simulateBookingPanel;
    private JButton btnBook;


    public HotelReservationSystemView() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(800, 600);

        initMainMenuPanel();
        initCreateHotelPanel();
        initViewHotelPanel();
        initManageHotelsPanel();
        initSimulateBookingPanel();


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

        btnCreateHotel = new JButton("Create Hotel");
        btnViewHotel = new JButton("View Hotel");
        btnManageHotel = new JButton("Manage Hotel");
        btnSimulateBooking = new JButton("Book Hotel");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0); // Spacing between buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setButtonElements(btnCreateHotel);
        setButtonElements(btnViewHotel);
        setButtonElements(btnManageHotel);
        setButtonElements(btnSimulateBooking);

        westPanel.add(btnCreateHotel, gbc);
        westPanel.add(btnViewHotel, gbc);
        westPanel.add(btnManageHotel, gbc);
        westPanel.add(btnSimulateBooking, gbc);
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

    private void initViewHotelPanel() {
        viewHotelPanel = new JPanel(new BorderLayout());
        viewHotelPanel.setPreferredSize(new Dimension(550, 500));
        viewHotelPanel.setBackground(new Color(30, 144, 255));
        viewHotelPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel hotelSelectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel lowLevelButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);



        JComboBox<String> hotelComboBox = new JComboBox<>();
        btnViewDetails = new JButton("View Details");

        JLabel hotelLabel = new JLabel("Select Hotel:");
        hotelLabel.setFont(labelFont);
        hotelLabel.setPreferredSize(new Dimension(10, 5));
        hotelComboBox.setFont(fieldFont);
        hotelComboBox.setSize(new Dimension(100, 30));

        // Create a panel to center the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setSize(new Dimension(5, 5)); // Fixed size for the panel
        btnViewDetails.setFont(new Font("Arial", Font.BOLD, 16));
        btnViewDetails.setSize(new Dimension(30, 5)); // Fixed size for the button
        buttonPanel.add(btnViewDetails);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical arrangement
        infoPanel.setBackground(new Color(30, 144, 255));
        infoPanel.setPreferredSize(new Dimension(400, 450));

        JLabel hotelDetailsLabel = new JLabel("High-Level Information:");
        hotelDetailsLabel.setFont(new Font("Arial", Font.BOLD, 30));
        infoPanel.add(hotelDetailsLabel);


        JLabel nameLabel = new JLabel();
        JLabel numOfRoomsLabel = new JLabel();
        JLabel numOfReservationsLabel = new JLabel();
        JLabel estimatedEarningsLabel = new JLabel();
        JLabel numOfAvailableRoomsLabel = new JLabel();

        nameLabel.setText("Hotel Name: ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        numOfRoomsLabel.setText("Number of Rooms: ");
        numOfRoomsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        numOfRoomsLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        numOfReservationsLabel.setText("Number of Reservations: ");
        numOfReservationsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        numOfReservationsLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        estimatedEarningsLabel.setText("Estimated Earnings: " );
        estimatedEarningsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        estimatedEarningsLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        numOfAvailableRoomsLabel.setText("Number of Available Rooms: ");
        numOfAvailableRoomsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        numOfAvailableRoomsLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Add these labels to the infoPanel
        infoPanel.add(nameLabel);
        infoPanel.add(numOfRoomsLabel);
        infoPanel.add(numOfReservationsLabel);
        infoPanel.add(estimatedEarningsLabel);
        infoPanel.add(numOfAvailableRoomsLabel);

         btnCheckAvailability = new JButton("Check Availability");
         btnRoomInfo = new JButton("Room Information");
         btnReservationInfo = new JButton("Reservation Information");

        // Add buttons to the infoPanel
        lowLevelButtonPanel.add(btnCheckAvailability);
        lowLevelButtonPanel.add(btnRoomInfo);
        lowLevelButtonPanel.add(btnReservationInfo);

        hotelSelectionPanel.add(hotelComboBox, BorderLayout.NORTH);
        hotelSelectionPanel.add(btnViewDetails, BorderLayout.NORTH);
        viewHotelPanel.add(hotelSelectionPanel, BorderLayout.NORTH);
        viewHotelPanel.add(infoPanel, BorderLayout.CENTER);
        viewHotelPanel.add(lowLevelButtonPanel, BorderLayout.SOUTH);


    }

    private void initManageHotelsPanel() {

        manageHotelsPanel = new JPanel(new BorderLayout());
        manageHotelsPanel.setPreferredSize(new Dimension(550, 500));
        manageHotelsPanel.setBackground(new Color(30, 144, 255));
        manageHotelsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JComboBox<String> hotelComboBox = new JComboBox<>();
        hotelComboBox.setSelectedItem(null);



        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Select Hotel:"), gbc);
        gbc.gridx = 1;
        formPanel.add(hotelComboBox, gbc);
        gbc.gridx = 2;

        JComboBox<String> typeOfRoomBox = new JComboBox<>();

        typeOfRoomBox.addItem("Standard");
        typeOfRoomBox.addItem("Deluxe");
        typeOfRoomBox.addItem("Executive");
        typeOfRoomBox.setSelectedItem(null);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Select Room Type:"), gbc);
        gbc.gridx = 1;
        formPanel.add(typeOfRoomBox, gbc);

        JComboBox<String> addRoomsBox = new JComboBox<>();
        addRoomsBox.setSelectedItem(null);
         btnAddRoom = new JButton("Add");
        btnAddRoom.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Add Rooms:"), gbc);
        gbc.gridx = 1;
        formPanel.add(addRoomsBox, gbc);
        gbc.gridx = 2;
        btnAddRoom.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnAddRoom, gbc);

        JComboBox<String> removeRoomsBox = new JComboBox<>();
        removeRoomsBox.setSelectedItem(null);
         btnRemoveRoom = new JButton("Remove");
        btnRemoveRoom.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Remove Rooms:"), gbc);
        gbc.gridx = 1;
        formPanel.add(removeRoomsBox, gbc);
        gbc.gridx = 2;
        formPanel.add(btnRemoveRoom, gbc);

        JLabel changeNameLabel = new JLabel("Change Hotel Name to:");
        JTextField changeNameTextField = new JTextField(15);
         btnChangeName = new JButton("Change Hotel Name");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        formPanel.add(changeNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(changeNameTextField, gbc);
        gbc.gridx = 2;
        formPanel.add(btnChangeName, gbc);

        JLabel changePriceLabel = new JLabel("Change Hotel Base Price to (price >= 100):");
        JTextField changePriceTextField = new JTextField(15);
         btnChangePrice = new JButton("Change Price");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        formPanel.add(changePriceLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(changePriceTextField, gbc);
        gbc.gridx = 2;
        formPanel.add(btnChangePrice, gbc);

         btnDeleteHotel = new JButton("Delete Hotel");
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(btnDeleteHotel, gbc);

        ArrayList<String> datePriceList = new ArrayList<>();
        for (int i = 1; i <= 31; i++)
            datePriceList.add(Integer.toString(i));

        ArrayList<String> datePercentList = new ArrayList<>();
        for (int i = 50; i <= 150; i++)
            datePercentList.add(Integer.toString(i) + "%");

        JLabel dates = new JLabel("Modify Date Price:");
        JLabel chooseDate = new JLabel("Choose Date:");
        JLabel choosePercent = new JLabel("Choose Percent to Increase or Decrease Price:");
        JComboBox<String> datesBox = new JComboBox<>(datePriceList.toArray(new String[0]));
        datesBox.setSelectedItem(null);
        JComboBox<String> datesPercentBox = new JComboBox<>(datePercentList.toArray(new String[0]));
        datesPercentBox.setSelectedItem(null);
         btnDatePrice = new JButton("Modify");
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(dates, gbc);
        gbc.gridy = 8;
        formPanel.add(chooseDate, gbc);
        gbc.gridx = 1;
        formPanel.add(datesBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        formPanel.add(choosePercent, gbc);
        gbc.gridx = 1;
        formPanel.add(datesPercentBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        formPanel.add(btnDatePrice, gbc);

        manageHotelsPanel.add(formPanel, BorderLayout.CENTER);
    }

    private void initSimulateBookingPanel() {

        simulateBookingPanel = new JPanel(new GridLayout(7, 3, 10, 50));  // Adjusted to 7 rows to accommodate all components
        simulateBookingPanel.setBorder(new EmptyBorder(10, 25, 10, 25));
        simulateBookingPanel.setPreferredSize(new Dimension(550, 500));
        simulateBookingPanel.setBackground(new Color(30, 144, 255));

        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        JComboBox<String> hotelComboBox = new JComboBox<>();
        JTextField customerNameField = new JTextField();
        JComboBox<String> roomTypeComboBox = new JComboBox<>();
        JComboBox checkInDateBox = new JComboBox();
        JComboBox checkOutDateBox = new JComboBox();
        JTextField couponCodeField = new JTextField();

        JLabel hotelLabel = new JLabel("Select Hotel:");
        hotelLabel.setFont(labelFont);
        hotelComboBox.setFont(fieldFont);
        hotelComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setFont(labelFont);
        customerNameField.setFont(fieldFont);
        customerNameField.setPreferredSize(new Dimension(200, 30));

        JLabel roomTypeLabel = new JLabel("Select Room Type:");
        roomTypeLabel.setFont(labelFont);
        roomTypeComboBox.setFont(fieldFont);
        roomTypeComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel checkInDateLabel = new JLabel("Choose Check-in Date:");
        checkInDateLabel.setFont(labelFont);
        checkInDateBox.setFont(fieldFont);
        checkInDateBox.setPreferredSize(new Dimension(200, 30));

        JLabel checkOutDateLabel = new JLabel("Choose Check-out Date:");
        checkOutDateLabel.setFont(labelFont);
        checkOutDateBox.setFont(fieldFont);
        checkOutDateBox.setPreferredSize(new Dimension(200, 30));

        JLabel couponCodeLabel = new JLabel("Coupon Code (None = 0):");
        couponCodeLabel.setFont(labelFont);
        couponCodeField.setFont(fieldFont);
        couponCodeField.setPreferredSize(new Dimension(200, 30));



        btnBook = new JButton("Book");
        btnBook.setBackground(Color.GRAY);
        btnBook.setForeground(Color.WHITE);
        btnBook.setFocusPainted(false);
        btnBook.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnBook.setFont(labelFont);
        btnBook.setSize(new Dimension(200, 60));

        // Add components vertically
        simulateBookingPanel.add(hotelLabel);
        simulateBookingPanel.add(hotelComboBox);

        simulateBookingPanel.add(customerNameLabel);
        simulateBookingPanel.add(customerNameField);

        simulateBookingPanel.add(roomTypeLabel);
        simulateBookingPanel.add(roomTypeComboBox);

        simulateBookingPanel.add(checkInDateLabel);
        simulateBookingPanel.add(checkInDateBox);

        simulateBookingPanel.add(checkOutDateLabel);
        simulateBookingPanel.add(checkOutDateBox);

        simulateBookingPanel.add(couponCodeLabel);
        simulateBookingPanel.add(couponCodeField);

        simulateBookingPanel.add(new JLabel()); // Empty cell
        simulateBookingPanel.add(btnBook);

    }
    private JButton setButtonElements(JButton button) {
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

    public void displayViewHotel() {
        updateCenterPanel(viewHotelPanel);
    }

    public void displayManageHotel() {
        updateCenterPanel(manageHotelsPanel);
    }


    public void displaySimulateBooking() {
        updateCenterPanel(simulateBookingPanel);
    }

    public void updateCenterPanel(JPanel panel) {
        centerPanel.removeAll();
        centerPanel.add(panel, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void addBtnCreateHotelListener(ActionListener listener) {
        btnCreateHotel.addActionListener(listener);
    }

        public void addBtnCreateSubmitListener(ActionListener listener) {
            btnCreateSubmit.addActionListener(listener);
        }

    public void addBtnViewHotelListener(ActionListener listener) {
        btnViewHotel.addActionListener(listener);
    }

    public void addBtnManageHotelListener(ActionListener listener) {
        btnManageHotel.addActionListener(listener);
    }

    public void addBtnSimulateBookingListener(ActionListener listener) {
        btnSimulateBooking.addActionListener(listener);
    }

    public void setMainMenuActionListener(ActionListener listener) {
        btnCreateHotel.addActionListener(listener);
        btnViewHotel.addActionListener(listener);
        btnManageHotel.addActionListener(listener);
        btnSimulateBooking.addActionListener(listener);
        btnBackToMainMenu.addActionListener(listener);
        btnCreateSubmit.addActionListener(listener);
        btnViewDetails.addActionListener(listener);
        btnCheckAvailability.addActionListener(listener);
        btnRoomInfo.addActionListener(listener);
        btnReservationInfo.addActionListener(listener);

        btnAddRoom.addActionListener(listener);
        btnRemoveRoom.addActionListener(listener);
        btnChangeName.addActionListener(listener);
        btnChangePrice.addActionListener(listener);
        btnDeleteHotel.addActionListener(listener);
        btnDatePrice.addActionListener(listener);

        btnBook.addActionListener(listener);
    }

    public String getHotelNameInput() {
        return hotelNameField.getText();
    }

    public int getNumberOfStandardRoomsInput() {
        return Integer.parseInt(numberOfStandardRoomsField.getText());
    }

    public int getNumberOfDeluxeRoomsInput() {
        return Integer.parseInt(numberOfDeluxeRoomsField.getText());
    }

    public int getNumberOfExecutiveRoomsInput() {
        return Integer.parseInt(numberOfExecutiveRoomsField.getText());
    }

    public double getBasePriceInput() {
        return Double.parseDouble(basePriceField.getText());
    }

    public void displayMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
