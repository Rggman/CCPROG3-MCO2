import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
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
    private JPanel viewHotelPanel, lowLevelInformationPanel;
    JComboBox<String> hotelComboBox3 = new JComboBox<>();
    JComboBox<String> dateBox2 = new JComboBox<>();
    JComboBox<String> roomNumberBox = new JComboBox<>();
    JComboBox<String> reservationsBox2 = new JComboBox<>();
    private JButton btnViewDetails = new JButton("View Details");
    private JButton btnCheckAvailability = new JButton("Check Availability");
    private JButton btnRoomInfo = new JButton("Check Room Info");
    private JButton btnReservationInfo = new JButton("Check Reservation Info");
    private JButton btnBackToView = new JButton("Add");
    private JButton btnLowLevelInfo = new JButton("More Information");
    private JLabel nameLabel = new JLabel("Hotel Name: ");
    private JLabel numOfRoomsLabel = new JLabel("Number of Rooms: ");
    private JLabel numOfReservationsLabel = new JLabel("Number of Reservations: ");
    private JLabel estimatedEarningsLabel = new JLabel("Estimated Earnings: ");
    private JLabel numOfAvailableRoomsLabel = new JLabel("Number of Unbooked Rooms: ");
    private JPanel newInfoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
    private JLabel lowLevelLabel1 = new JLabel();
    private JLabel lowLevelLabel2 = new JLabel();
    private JLabel lowLevelLabel3 = new JLabel();
    private JLabel lowLevelLabel4 = new JLabel();
    private JLabel lowLevelLabel5 = new JLabel();
    private JLabel lowLevelLabel6 = new JLabel();
    private JLabel lowLevelLabel7 = new JLabel();

    // For Manage Hotel
    private JPanel manageHotelsPanel;
    private JButton btnAddRoom = new JButton("Add");
    private JButton btnRemoveRoom = new JButton("Remove");
    private JButton btnChangeName = new JButton("Change");
    private JButton btnChangePrice = new JButton("Change");
    private JButton btnDeleteHotel = new JButton("Delete");
    private JButton btnDatePrice = new JButton("Modify");
    private JComboBox<String> hotelComboBox = new JComboBox<>();
    private JComboBox<String> typeOfRoomBox = new JComboBox<>();
    private JComboBox<String> addRoomsBox = new JComboBox<>();
    private JComboBox<String> removeRoomsBox = new JComboBox<>();
    private JComboBox<String> reservationsBox = new JComboBox<>();
    private JButton btnRemoveReservation = new JButton("Remove");
    private JButton btnRemoveAllReservation = new JButton("Remove All");
    private JTextField changeNameTextField = new JTextField(15);
    private JTextField changePriceTextField = new JTextField(15);
    private JComboBox<String> datesBox = new JComboBox<>();
    private JComboBox<String> datesPercentBox = new JComboBox<>();

    // For Simulate Booking
    private JPanel simulateBookingPanel;
    private JButton btnBook = new JButton("Book");
    private JTextField customerNameField = new JTextField();
    private JComboBox<String> hotelComboBox2 = new JComboBox<>();
    private JComboBox<String> roomTypeBox = new JComboBox<>();
    private JComboBox<String> checkInDateBox = new JComboBox<>();
    private JComboBox<String> checkOutDateBox = new JComboBox<>();
    private JTextField couponCodeField = new JTextField();

    public HotelReservationSystemView() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(800, 600);

        initMainMenuPanel();
        initCreateHotelPanel();
        initViewHotelPanel();
        initLowLevelInformation ();
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
        northPanel.setBackground(new Color(45, 45, 45));
        northPanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Hotel Reservation System");
        titleLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 32));
        titleLabel.setForeground(new Color(255, 105, 180));

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
        // Create Hotel Panel
        createHotelPanel = new JPanel(new BorderLayout());

        // Background color
        Color backgroundColor = new Color(245, 245, 245);

        // Create form panel setup
        JPanel createFormPanel = new JPanel(new GridLayout(6, 2, 0, 20));
        createFormPanel.setBorder(new EmptyBorder(60, 25, 30, 25));
        createFormPanel.setPreferredSize(new Dimension(635, 453));
        createFormPanel.setBackground(backgroundColor); // Off-white color

        // Fonts for labels and fields
        Font labelFont = new Font("Sans-Serif", Font.BOLD, 18);
        Font fieldFont = new Font("Sans-Serif", Font.PLAIN, 18);

        // Hotel Name
        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        hotelNameLabel.setFont(labelFont);
        createFormPanel.add(hotelNameLabel);
        hotelNameField.setFont(fieldFont);
        hotelNameField.setHorizontalAlignment(JTextField.CENTER); // Center text
        createFormPanel.add(hotelNameField);

        // Standard Rooms
        JLabel standardRoomsLabel = new JLabel("Number of Standard Rooms:");
        standardRoomsLabel.setFont(labelFont);
        createFormPanel.add(standardRoomsLabel);
        numberOfStandardRoomsField.setFont(fieldFont);
        numberOfStandardRoomsField.setHorizontalAlignment(JTextField.CENTER); // Center text
        createFormPanel.add(numberOfStandardRoomsField);

        // Deluxe Rooms
        JLabel deluxeRoomsLabel = new JLabel("Number of Deluxe Rooms:");
        deluxeRoomsLabel.setFont(labelFont);
        createFormPanel.add(deluxeRoomsLabel);
        numberOfDeluxeRoomsField.setFont(fieldFont);
        numberOfDeluxeRoomsField.setHorizontalAlignment(JTextField.CENTER); // Center text
        createFormPanel.add(numberOfDeluxeRoomsField);

        // Executive Rooms
        JLabel executiveRoomsLabel = new JLabel("Number of Executive Rooms:");
        executiveRoomsLabel.setFont(labelFont);
        createFormPanel.add(executiveRoomsLabel);
        numberOfExecutiveRoomsField.setFont(fieldFont);
        numberOfExecutiveRoomsField.setHorizontalAlignment(JTextField.CENTER); // Center text
        createFormPanel.add(numberOfExecutiveRoomsField);

        // Base Price
        JLabel basePriceLabel = new JLabel("Base Price:");
        basePriceLabel.setFont(labelFont);
        createFormPanel.add(basePriceLabel);
        basePriceField.setFont(fieldFont);
        basePriceField.setHorizontalAlignment(JTextField.CENTER);
        createFormPanel.add(basePriceField);

        // Submit Button Panel
        JPanel submitButtonPanel = new JPanel(new FlowLayout());
        submitButtonPanel.setBackground(backgroundColor);
        btnCreateSubmit = new JButton("Submit");
        btnCreateSubmit.setBackground(Color.DARK_GRAY);
        btnCreateSubmit.setForeground(Color.WHITE);
        btnCreateSubmit.setFocusPainted(false);
        btnCreateSubmit.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnCreateSubmit.setFont(labelFont);
        btnCreateSubmit.setPreferredSize(new Dimension(150, 50));
        btnCreateSubmit.setActionCommand("Submit");

        submitButtonPanel.add(btnCreateSubmit);

        // Add panels to the main panel
        createHotelPanel.add(createFormPanel, BorderLayout.CENTER);
        createHotelPanel.add(submitButtonPanel, BorderLayout.SOUTH);
    }

    private void initViewHotelPanel() {
        // Main panel setup
        viewHotelPanel = new JPanel(new BorderLayout());
        viewHotelPanel.setPreferredSize(new Dimension(550, 500));
        viewHotelPanel.setBackground(new Color(245, 245, 245));
        viewHotelPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Hotel selection panel setup
        JPanel hotelSelectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        hotelSelectionPanel.setBackground(Color.DARK_GRAY);

        // Low level button panel setup
        JPanel lowLevelButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lowLevelButtonPanel.setBackground(Color.DARK_GRAY);

        // Font setup
        Font labelFont = new Font("Sans-Serif", Font.BOLD, 20);

        // ComboBox setup
        hotelComboBox3.setBackground(Color.WHITE);
        hotelComboBox3.setFont(labelFont);
        hotelComboBox3.setPreferredSize(new Dimension(200, 30));

        // Button setup
        btnViewDetails = new JButton("View Details");
        btnViewDetails.setBackground(Color.WHITE);
        btnViewDetails.setForeground(Color.DARK_GRAY);
        btnViewDetails.setFont(labelFont);
        btnViewDetails.setPreferredSize(new Dimension(200, 30)); // Fixed size for the button

        // Label setup
        JLabel hotelLabel = new JLabel("Select Hotel:");
        hotelLabel.setFont(labelFont);
        hotelLabel.setPreferredSize(new Dimension(10, 5));

        // Panel to center the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setPreferredSize(new Dimension(5, 5)); // Fixed size for the panel
        buttonPanel.add(btnViewDetails);

        // Info panel setup
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical arrangement
        infoPanel.setBackground(new Color(245, 245, 245));
        infoPanel.setPreferredSize(new Dimension(400, 450));

        // Info labels setup
        JLabel hotelDetailsLabel = new JLabel("High-Level Information:");
        hotelDetailsLabel.setFont(new Font("Sanserif", Font.BOLD, 30));
        infoPanel.add(hotelDetailsLabel);

        nameLabel.setFont(labelFont);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        numOfRoomsLabel.setFont(labelFont);
        numOfRoomsLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        numOfReservationsLabel.setFont(labelFont);
        numOfReservationsLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        estimatedEarningsLabel.setFont(labelFont);
        estimatedEarningsLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        numOfAvailableRoomsLabel.setFont(labelFont);
        numOfAvailableRoomsLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Add info labels to info panel
        infoPanel.add(nameLabel);
        infoPanel.add(numOfRoomsLabel);
        infoPanel.add(numOfReservationsLabel);
        infoPanel.add(estimatedEarningsLabel);
        infoPanel.add(numOfAvailableRoomsLabel);

        // Low level info button setup
        btnLowLevelInfo.setFont(labelFont);
        btnLowLevelInfo.setBackground(Color.WHITE);
        btnLowLevelInfo.setForeground(Color.DARK_GRAY);
        lowLevelButtonPanel.add(btnLowLevelInfo);

        // Add components to hotel selection panel
        hotelSelectionPanel.add(hotelComboBox3, BorderLayout.NORTH);
        hotelSelectionPanel.add(btnViewDetails, BorderLayout.NORTH);

        // Add panels to main panel
        viewHotelPanel.add(hotelSelectionPanel, BorderLayout.NORTH);
        viewHotelPanel.add(infoPanel, BorderLayout.CENTER);
        viewHotelPanel.add(lowLevelButtonPanel, BorderLayout.SOUTH);
    }

    private void initLowLevelInformation () {

        lowLevelInformationPanel = new JPanel(new BorderLayout());
        lowLevelInformationPanel.setPreferredSize(new Dimension(550, 500));
        lowLevelInformationPanel.setBackground(new Color(30, 144, 255));
        lowLevelInformationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel LowLevelButtonPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        LowLevelButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        LowLevelButtonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel checkDateAvailabilityLabel = new JLabel("Choose Date:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        LowLevelButtonPanel.add(checkDateAvailabilityLabel, gbc);
        gbc.gridx = 1;
        LowLevelButtonPanel.add(dateBox2, gbc);
        gbc.gridx = 2;
        LowLevelButtonPanel.add(btnCheckAvailability, gbc);
        dateBox2.setSelectedItem(null);

        JLabel roomInfoLabel = new JLabel("Choose Room Number:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        LowLevelButtonPanel.add(roomInfoLabel, gbc);
        gbc.gridx = 1;
        LowLevelButtonPanel.add(roomNumberBox, gbc);
        gbc.gridx = 2;
        LowLevelButtonPanel.add(btnRoomInfo, gbc);
        roomNumberBox.setSelectedItem(null);

        JLabel reservationInfoLabel = new JLabel("Choose Guest Name:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        LowLevelButtonPanel.add(reservationInfoLabel, gbc);
        gbc.gridx = 1;
        LowLevelButtonPanel.add(reservationsBox2, gbc);
        gbc.gridx = 2;
        LowLevelButtonPanel.add(btnReservationInfo, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        reservationsBox2.setSelectedItem(null);

        newInfoPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        newInfoPanel.add(lowLevelLabel1, gbc);
        gbc.gridy = 1;
        newInfoPanel.add(lowLevelLabel2, gbc);
        gbc.gridy = 2;
        newInfoPanel.add(lowLevelLabel3, gbc);
        gbc.gridy = 3;
        newInfoPanel.add(lowLevelLabel4, gbc);
        gbc.gridy = 4;
        newInfoPanel.add(lowLevelLabel5, gbc);
        gbc.gridy = 5;
        newInfoPanel.add(lowLevelLabel6, gbc);
        gbc.gridy = 6;
        newInfoPanel.add(lowLevelLabel7, gbc);

        JPanel BackToViewButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        BackToViewButtonPanel.setSize(new Dimension(5, 5)); // Fixed size for the panel


        btnBackToView = new JButton("Back to View");
        btnBackToView.setFont(new Font("Arial", Font.BOLD, 16));
        btnBackToView.setSize(new Dimension(30, 5)); // Fixed size for the button

        BackToViewButtonPanel.add(btnBackToView);

        lowLevelInformationPanel.add(LowLevelButtonPanel, BorderLayout.NORTH);
        lowLevelInformationPanel.add(newInfoPanel, BorderLayout.CENTER);
        lowLevelInformationPanel.add(BackToViewButtonPanel, BorderLayout.SOUTH);
    }

    private void initManageHotelsPanel() {
        Color background = new Color(245, 245, 245);
        Font labelFont = new Font("Sans-Serif", Font.BOLD, 12);
        Font fieldFont = new Font("Sans-Serif", Font.PLAIN, 12);

        btnChangeName.setBackground(Color.DARK_GRAY);
        btnChangeName.setForeground(Color.WHITE);
        btnChangeName.setFont(labelFont);
        btnChangePrice.setBackground(Color.DARK_GRAY);
        btnChangePrice.setForeground(Color.WHITE);
        btnChangePrice.setFont(labelFont);
        btnDeleteHotel.setBackground(Color.DARK_GRAY);
        btnDeleteHotel.setForeground(Color.WHITE);
        btnDeleteHotel.setFont(labelFont);

        manageHotelsPanel = new JPanel(new BorderLayout());
        manageHotelsPanel.setPreferredSize(new Dimension(550, 500));
        manageHotelsPanel.setBackground(background);
        manageHotelsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(background);

        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Select Hotel:"), gbc);
        gbc.gridx = 1;
        hotelComboBox.setBackground(Color.WHITE);
        formPanel.add(hotelComboBox, gbc);

        typeOfRoomBox.setBackground(Color.WHITE);
        typeOfRoomBox.addItem("Standard");
        typeOfRoomBox.addItem("Deluxe");
        typeOfRoomBox.addItem("Executive");
        typeOfRoomBox.setSelectedItem(null);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Select Room Type:"), gbc);
        gbc.gridx = 1;
        formPanel.add(typeOfRoomBox, gbc);

        addRoomsBox.setBackground(Color.WHITE);
        addRoomsBox.setSelectedItem(null);
        btnAddRoom.setFont(labelFont);
        btnAddRoom.setBackground(Color.DARK_GRAY);
        btnAddRoom.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Add Rooms:"), gbc);
        gbc.gridx = 1;
        formPanel.add(addRoomsBox, gbc);
        gbc.gridx = 2;
        formPanel.add(btnAddRoom, gbc);

        removeRoomsBox.setBackground(Color.WHITE);
        removeRoomsBox.setSelectedItem(null);
        btnRemoveRoom.setFont(labelFont);
        btnRemoveRoom.setBackground(Color.DARK_GRAY);
        btnRemoveRoom.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Remove Rooms:"), gbc);
        gbc.gridx = 1;
        formPanel.add(removeRoomsBox, gbc);
        gbc.gridx = 2;
        formPanel.add(btnRemoveRoom, gbc);

        changeNameTextField.setFont(fieldFont);
        JLabel changeNameLabel = new JLabel("Change Hotel Name to:");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        formPanel.add(changeNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(changeNameTextField, gbc);
        gbc.gridx = 2;
        formPanel.add(btnChangeName, gbc);

        changePriceTextField.setFont(fieldFont);
        JLabel changePriceLabel = new JLabel("Change Hotel Base Price to (min. of 100):");

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        formPanel.add(changePriceLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(changePriceTextField, gbc);
        gbc.gridx = 2;
        formPanel.add(btnChangePrice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(btnDeleteHotel, gbc);

        for (int i = 1; i <= 31; i++) {
            datesBox.addItem(Integer.toString(i));
        }

        for (int i = 50; i <= 150; i++) {
            datesPercentBox.addItem(Integer.toString(i) + "%");
        }

        datesBox.setBackground(Color.WHITE);
        datesBox.setSelectedItem(null);
        datesPercentBox.setBackground(Color.WHITE);
        datesPercentBox.setSelectedItem(null);

        JLabel dates = new JLabel("Modify Date Price:");
        JLabel chooseDate = new JLabel("Choose Date:");
        JLabel choosePercent = new JLabel("Choose Percent to Increase or Decrease Price:");
        btnDatePrice = new JButton("Modify");
        btnDatePrice.setBackground(Color.DARK_GRAY);
        btnDatePrice.setForeground(Color.WHITE);

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

        simulateBookingPanel = new JPanel(new GridLayout(7, 2, 10, 40)); // Adjusted to 7 rows to accommodate all components
        simulateBookingPanel.setBorder(new EmptyBorder(10, 25, 10, 25));
        // simulateBookingPanel.setPreferredSize(new Dimension(550, 500));
        simulateBookingPanel.setBackground(new Color(245, 245, 245));

        // Fonts for labels and fields
        Font labelFont = new Font("Sanserif", Font.BOLD, 18);
        Font fieldFont = new Font("Sanserif", Font.PLAIN, 16);

        // Components
        JComboBox<String> hotelComboBox = new JComboBox<>();
        hotelComboBox.setBackground(Color.WHITE);
        JTextField customerNameField = new JTextField();
        JComboBox<String> roomTypeComboBox = new JComboBox<>();
        roomTypeComboBox.setBackground(Color.WHITE);
        checkInDateBox.setBackground(Color.WHITE);
        checkOutDateBox.setBackground(Color.WHITE);
        JTextField couponCodeField = new JTextField();



        // Hotel Label and ComboBox
        JLabel hotelLabel = new JLabel("Select Hotel:");
        hotelLabel.setFont(labelFont);
        hotelComboBox.setFont(fieldFont);
        hotelComboBox.setPreferredSize(new Dimension(200, 30));

        // Customer Name Label and TextField
        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setFont(labelFont);
        customerNameField.setFont(fieldFont);
        customerNameField.setHorizontalAlignment(JTextField.CENTER);
        customerNameField.setPreferredSize(new Dimension(200, 30));

        // Room Type Label and ComboBox
        JLabel roomTypeLabel = new JLabel("Select Room Type:");
        roomTypeLabel.setFont(labelFont);
        roomTypeComboBox.setFont(fieldFont);
        roomTypeComboBox.setPreferredSize(new Dimension(200, 30));

        // Check-in Date Label and ComboBox
        JLabel checkInDateLabel = new JLabel("Choose Check-in Date:");
        checkInDateLabel.setFont(labelFont);
        checkInDateBox.setFont(fieldFont);
        checkInDateBox.setPreferredSize(new Dimension(200, 30));

        // Check-out Date Label and ComboBox
        JLabel checkOutDateLabel = new JLabel("Choose Check-out Date:");
        checkOutDateLabel.setFont(labelFont);
        checkOutDateBox.setFont(fieldFont);
        checkOutDateBox.setPreferredSize(new Dimension(200, 30));

        // Coupon Code Label and TextField
        JLabel couponCodeLabel = new JLabel("Coupon Code (None = 0):");
        couponCodeLabel.setFont(labelFont);
        couponCodeField.setFont(fieldFont);
        couponCodeField.setHorizontalAlignment(JTextField.CENTER);
        couponCodeField.setPreferredSize(new Dimension(200, 30));

        // Book Button
        btnBook.setBackground(Color.DARK_GRAY);
        btnBook.setForeground(Color.WHITE);
        btnBook.setFocusPainted(false);
        btnBook.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnBook.setFont(labelFont);
        btnBook.setPreferredSize(new Dimension(200, 60));

        // Add components to the panel
        simulateBookingPanel.add(hotelLabel);
        simulateBookingPanel.add(hotelComboBox2);
        simulateBookingPanel.add(customerNameLabel);
        simulateBookingPanel.add(customerNameField);
        simulateBookingPanel.add(roomTypeLabel);
        simulateBookingPanel.add(roomTypeBox);
        simulateBookingPanel.add(checkInDateLabel);
        simulateBookingPanel.add(checkInDateBox);
        simulateBookingPanel.add(checkOutDateLabel);
        simulateBookingPanel.add(checkOutDateBox);
        simulateBookingPanel.add(couponCodeLabel);
        simulateBookingPanel.add(couponCodeField);
        simulateBookingPanel.add(new JLabel()); // Empty cell for spacing
        simulateBookingPanel.add(btnBook);
    }


    private JButton setButtonElements(JButton button) {
        button.setPreferredSize(new Dimension(150, 50));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));


        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(255, 105, 180));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.WHITE);
            }
        });

        return button;
    }

    public JLabel setJLabel(String info) {
        JLabel jlabel = new JLabel(info);
        return jlabel;
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

    public void displayLowLevelInfo() {
        updateCenterPanel(lowLevelInformationPanel);
    }

    public void displayManageHotel() {
        updateCenterPanel(manageHotelsPanel);
    }

    public void displaySimulateBooking() {
        updateCenterPanel(simulateBookingPanel);
    }

    public void displayNewInfo() {
        updateCenterPanel(newInfoPanel);
    }

    public void updateCenterPanel(JPanel panel) {
        centerPanel.removeAll();
        centerPanel.add(panel, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void newInfoPanel(JPanel panel) {
        lowLevelInformationPanel.removeAll();
        lowLevelInformationPanel.add(panel, BorderLayout.CENTER);
        lowLevelInformationPanel.revalidate();
        lowLevelInformationPanel.repaint();
    }

    public void clearInfo() {
        lowLevelLabel1.setText(null);
        lowLevelLabel2.setText(null);
        lowLevelLabel3.setText(null);
        lowLevelLabel4.setText(null);
        lowLevelLabel5.setText(null);
        lowLevelLabel6.setText(null);
        lowLevelLabel7.setText(null);
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

        public void addBtnViewListener(ActionListener listener) {
            btnViewDetails.addActionListener(listener);
        }

        public void addBtnLowLevelInfoListener(ActionListener listener) {
            btnLowLevelInfo.addActionListener(listener);
        }

        public void addBtnBackToViewListener(ActionListener listener) {
            btnBackToView.addActionListener(listener);
        }

        public void addBtnCheckAvailabilityListener(ActionListener listener) {
            btnCheckAvailability.addActionListener(listener);
        }

        public void addBtnRoomInfoListener(ActionListener listener) {
            btnRoomInfo.addActionListener(listener);
        }

        public void addBtnReservationInfoListener(ActionListener listener) {
            btnReservationInfo.addActionListener(listener);
        }

    public void addBtnManageHotelListener(ActionListener listener) {
        btnManageHotel.addActionListener(listener);
    }

        public void populateRoomsComboBox(ItemListener listener) {
            hotelComboBox.addItemListener(listener);
        }

        public void populateReservationsComboBox(ItemListener listener) {
            hotelComboBox.addItemListener(listener);
        }

        public void addBtnRemoveReservationListener(ActionListener listener) {
            btnRemoveReservation.addActionListener(listener);
        }

        public void addBtnRemoveAllReservationListener(ActionListener listener) {
            btnRemoveAllReservation.addActionListener(listener);
        }

        public void addBtnAddRoomsListener(ActionListener listener) {
            btnAddRoom.addActionListener(listener);
        }

        public void addBtnRemoveRoomsListener(ActionListener listener) {
            btnRemoveRoom.addActionListener(listener);
        }

        public void addBtnChangeNameListener(ActionListener listener) {
            btnChangeName.addActionListener(listener);
        }

        public void addBtnChangePriceListener(ActionListener listener) {
            btnChangePrice.addActionListener(listener);
        }

        public void addBtnDeleteListener(ActionListener listener) {
            btnDeleteHotel.addActionListener(listener);
        }

        public void  addBtnDatePriceListener(ActionListener listener) {
            btnDatePrice.addActionListener(listener);
        }

    public void addBtnSimulateBookingListener(ActionListener listener) {
        btnSimulateBooking.addActionListener(listener);
    }
        // Changes available choices for typeOfRoomBox when state of hotelComboBox2 is changed
        public void populateRoomTypeBox(ItemListener listener) {
            hotelComboBox2.addItemListener(listener);
        }

        public void populateCheckInDateBox(ItemListener listener) {
            roomTypeBox.addItemListener(listener);
        }

        public void populateCheckOutDateBox(ItemListener listener) {
            checkInDateBox.addItemListener(listener);
        }

        public void addBtnBookListener(ActionListener listener) {
            btnBook.addActionListener(listener);
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

    public String getNumberOfStandardRoomsInput() {
        return numberOfStandardRoomsField.getText();
    }

    public String getNumberOfDeluxeRoomsInput() {
        return numberOfDeluxeRoomsField.getText();
    }

    public String getNumberOfExecutiveRoomsInput() {
        return numberOfExecutiveRoomsField.getText();
    }

    public String getBasePriceInput() {
        return basePriceField.getText();
    }

    public void displayMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public void clearCreateHotelForm() {
        hotelNameField.setText("");
        numberOfStandardRoomsField.setText("");
        numberOfDeluxeRoomsField.setText("");
        numberOfExecutiveRoomsField.setText("");
        basePriceField.setText("");
    }

    public JComboBox<String> getHotelComboBox() {
        return hotelComboBox;
    }

    public JComboBox<String> getTypeOfRoomBox() {
        return typeOfRoomBox;
    }

    public JComboBox<String> getAddRoomBox() {
        return addRoomsBox;
    }

    public JComboBox<String> getRemoveRoomBox() {
        return removeRoomsBox;
    }

    public String getChangedNameInput() {
        return changeNameTextField.getText();
    }

    public String getChangedPriceInput() {
        return changePriceTextField.getText();
    }

    public JComboBox<String> getDatesBox() {
        return datesBox;
    }

    public JComboBox<String> getDatesPercentBox() {
        return datesPercentBox;
    }

    public JComboBox<String> getReservationsBox() {
        return reservationsBox;
    }

    public JComboBox<String> getHotelComboBox2() {
        return hotelComboBox2;
    }

    public JComboBox<String> getRoomTypeBox() {
        return roomTypeBox;
    }

    public JComboBox<String> getCheckInDateBox() {
        return checkInDateBox;
    }

    public JComboBox<String> getCheckOutDateBox() {
        return checkOutDateBox;
    }

    public String getCustomerNameFieldInput() {
        return customerNameField.getText();
    }

    public String getCouponCodeFieldInput() {
        return couponCodeField.getText();
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getNumOfRoomsLabel() {
        return numOfRoomsLabel;
    }

    public JLabel getNumOfReservationsLabel() {
        return numOfReservationsLabel;
    }

    public JLabel getEstimatedEarningsLabel() {
        return estimatedEarningsLabel;
    }

    public JLabel getUnreservedRoomsLabel() {
        return numOfAvailableRoomsLabel;
    }

    public JComboBox<String> getHotelComboBox3() {
        return hotelComboBox3;
    }

    public JComboBox<String> getDateBox2() {
        return dateBox2;
    }

    public JComboBox<String> getRoomNumberBox() {
        return roomNumberBox;
    }

    public JComboBox<String> getReservationsBox2() {
        return reservationsBox2;
    }

    public JLabel getLowLevelLabel1() {
        return lowLevelLabel1;
    }

    public JLabel getLowLevelLabel2() {
        return lowLevelLabel2;
    }

    public JLabel getLowLevelLabel3() {
        return lowLevelLabel3;
    }

    public JLabel getLowLevelLabel4() {
        return lowLevelLabel4;
    }

    public JLabel getLowLevelLabel5() {
        return lowLevelLabel5;
    }

    public JLabel getLowLevelLabel6() {
        return lowLevelLabel6;
    }

    public JLabel getLowLevelLabel7() {
        return lowLevelLabel7;
    }

    public JPanel getNewInfoPanel() {
        return newInfoPanel;
    }
}
