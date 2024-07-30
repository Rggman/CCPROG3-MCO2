import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;


/**
 * The HotelReservationSystemView class represents the graphical user interface (GUI)
 * for the hotel reservation system. It provides panels, buttons, and input fields for
 * creating hotels, viewing hotel details, managing hotels, and simulating bookings.
 */
public class HotelReservationSystemView extends JFrame {

    // Buttons for the MainMenu
    private JButton btnCreateHotel, btnViewHotel, btnManageHotel, btnSimulateBooking;
    private JPanel northPanel, westPanel, centerPanel;
    private JPanel mainMenuPanel;

    // For Create Hotel
    private JPanel createHotelPanel;
    private JButton btnCreateSubmit = new JButton("Submit");
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
    private JButton btnLowLevelInfo = new JButton("More Information");
    private JButton btnPriceBreakDown = new JButton("Price Breakdown");

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


    /**
     * Constructs a new HotelReservationSystemView instance and initializes the GUI components.
     */

    public HotelReservationSystemView() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setBackground(new Color(245, 245, 245));

        initMainMenuPanel();
        initCreateHotelPanel();
        initViewHotelPanel();
        initLowLevelInformation();
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

    /**
     * Initializes the North Panel
     */

    private void initNorthPanel() {
        northPanel = new JPanel();
        northPanel.setBackground(Color.decode("#004AAD"));
        northPanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Hotel Reservation System");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);

        northPanel.add(titleLabel);
    }


    /**
     * Initializes the West Panel
     */
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

    /**
     * Initializes the Main Menu Panel
     */
    private void initMainMenuPanel() {
        mainMenuPanel = new JPanel(new BorderLayout());
        mainMenuPanel.setBackground(Color.WHITE);

        // Load the image
        ImageIcon imageIcon = new ImageIcon("HamiltonHotels.png");

        // Resize the image
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(635, 510, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledImageIcon);

        mainMenuPanel.add(imageLabel, BorderLayout.CENTER);
    }

    /**
     * Initializes the Create Hotel Panel
     */
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

    /**
     * Initializes the View Hotel Panel
     */
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

    /**
     * Initializes the Low Level Information Panel
     */
    private void initLowLevelInformation() {

        lowLevelInformationPanel = new JPanel(new BorderLayout());
        lowLevelInformationPanel.setPreferredSize(new Dimension(550, 500));
        lowLevelInformationPanel.setBackground(new Color(30, 144, 255));

        JPanel LowLevelButtonPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        LowLevelButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        dateBox2.setBackground(Color.WHITE);
        roomNumberBox.setBackground(Color.WHITE);
        reservationsBox2.setBackground(Color.WHITE);

        btnCheckAvailability.setBackground(Color.DARK_GRAY);
        btnCheckAvailability.setForeground(Color.WHITE);
        btnRoomInfo.setBackground(Color.DARK_GRAY);
        btnRoomInfo.setForeground(Color.WHITE);
        btnReservationInfo.setBackground(Color.DARK_GRAY);
        btnReservationInfo.setForeground(Color.WHITE);


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


        Font infoFont = new Font("Sans-Serif", Font.PLAIN, 18);

        newInfoPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        lowLevelLabel1.setFont(infoFont);
        lowLevelLabel2.setFont(infoFont);
        lowLevelLabel3.setFont(infoFont);
        lowLevelLabel4.setFont(infoFont);
        lowLevelLabel5.setFont(infoFont);
        lowLevelLabel6.setFont(infoFont);
        lowLevelLabel7.setFont(infoFont);


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



        lowLevelInformationPanel.add(LowLevelButtonPanel, BorderLayout.NORTH);
        lowLevelInformationPanel.add(newInfoPanel, BorderLayout.CENTER);
    }

    public void showBtnPriceBreakdown() {
        btnPriceBreakDown.setBackground(Color.DARK_GRAY);
        btnPriceBreakDown.setForeground(Color.WHITE);
        lowLevelInformationPanel.add(btnPriceBreakDown, BorderLayout.SOUTH);
    }
    /**
     * Initializes the Manage Hotel Panel
     */
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

        gbc.gridy = 6;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Delete Hotel:"), gbc);
        gbc.gridx = 1;
        reservationsBox.setBackground(Color.WHITE);
        formPanel.add(reservationsBox, gbc);
        gbc.gridx = 2;
        btnRemoveReservation.setBackground(Color.DARK_GRAY);
        btnRemoveReservation.setForeground(Color.WHITE);
        btnRemoveReservation.setFont(labelFont);
        formPanel.add(btnRemoveReservation, gbc);
        gbc.gridx = 3;
        btnRemoveAllReservation.setBackground(Color.DARK_GRAY);
        btnRemoveAllReservation.setForeground(Color.WHITE);
        btnRemoveAllReservation.setFont(labelFont);
        formPanel.add(btnRemoveAllReservation, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
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
        btnDatePrice.setBackground(Color.DARK_GRAY);
        btnDatePrice.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(dates, gbc);
        gbc.gridy = 9;
        formPanel.add(chooseDate, gbc);
        gbc.gridx = 1;
        formPanel.add(datesBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        formPanel.add(choosePercent, gbc);
        gbc.gridx = 1;
        formPanel.add(datesPercentBox, gbc);
        gbc.gridx = 2;
        formPanel.add(btnDatePrice, gbc);

        manageHotelsPanel.add(formPanel, BorderLayout.CENTER);
    }

    /**
     * Initializes Simulate Booking Panel
     */
    private void initSimulateBookingPanel() {

        simulateBookingPanel = new JPanel(new GridLayout(7, 2, 10, 40)); // Adjusted to 7 rows to accommodate all components
        simulateBookingPanel.setBorder(new EmptyBorder(10, 25, 10, 25));
        simulateBookingPanel.setBackground(new Color(245, 245, 245));

        // Fonts for labels and fields
        Font labelFont = new Font("Sanserif", Font.BOLD, 18);
        Font fieldFont = new Font("Sanserif", Font.PLAIN, 16);

        // Components

        hotelComboBox2.setBackground(Color.WHITE);
        roomTypeBox.setBackground(Color.WHITE);
        checkInDateBox.setBackground(Color.WHITE);
        checkOutDateBox.setBackground(Color.WHITE);


        // Hotel Label and ComboBox
        JLabel hotelLabel = new JLabel("Select Hotel:");
        hotelLabel.setFont(labelFont);
        hotelComboBox2.setFont(fieldFont);
        hotelComboBox2.setPreferredSize(new Dimension(200, 30));


        // Customer Name Label and TextField
        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setFont(labelFont);
        customerNameField.setFont(fieldFont);
        customerNameField.setHorizontalAlignment(JTextField.CENTER);
        customerNameField.setPreferredSize(new Dimension(200, 30));

        // Room Type Label and ComboBox
        JLabel roomTypeLabel = new JLabel("Select Room Type:");
        roomTypeLabel.setFont(labelFont);
        roomTypeBox.setFont(fieldFont);
        roomTypeBox.setPreferredSize(new Dimension(200, 30));
        roomTypeBox.setBackground(Color.WHITE);

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
        JLabel couponCodeLabel = new JLabel("Coupon Code (if any):");
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


    /**
     * Configures the visual appearance and behavior of a given JButton. for the Main Menu Buttons
     *
     * @param button The JButton to be configured.
     * @return The configured JButton with the applied settings.
     */

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
                button.setForeground(Color.decode("#004AAD"));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.WHITE);
            }
        });

        return button;
    }

    /**
     * Removes all action listeners from a given JButton.
     *
     * @param button The JButton to be configured.
     * @return The configured JButton with the applied settings.
     */
    public void removeListeners(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }

    /**
     * Updates the center panel of the view to display the main menu.
     */

    public void displayMainMenu() {
        updateCenterPanel(mainMenuPanel);
    }

    /**
     * Updates the center panel of the view to display the form for creating a new hotel.
     */
    public void displayCreateHotelForm() {
        updateCenterPanel(createHotelPanel);
    }

    /**
     * Updates the center panel of the view to display the form for viewing a hotel.
     */
    public void displayViewHotel() {
        updateCenterPanel(viewHotelPanel);
    }

    /**
     * Updates the center panel of the view to display the low-level information panel.
     */
    public void displayLowLevelInfo() {
        updateCenterPanel(lowLevelInformationPanel);
    }

    /**
     * Updates the center panel of the view to display the form for managing a hotel.
     */
    public void displayManageHotel() {
        updateCenterPanel(manageHotelsPanel);
    }


    /**
     * Updates the center panel of the view to display the form for simulating a booking.
     */
    public void displaySimulateBooking() {
        updateCenterPanel(simulateBookingPanel);
    }

    /**
     * Updates the center panel of the view to display the new information panel.
     */
    public void displayNewInfo() {
        updateCenterPanel(newInfoPanel);
    }


    /**
     * Updates the center panel of the view with a new panel.
     *
     * @param panel the new JPanel to be displayed in the center panel
     */
    public void updateCenterPanel(JPanel panel) {
        centerPanel.removeAll();
        centerPanel.add(panel, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * Clears the text from all low-level information labels.
     */

    public void clearInfo() {
        lowLevelLabel1.setText(null);
        lowLevelLabel2.setText(null);
        lowLevelLabel3.setText(null);
        lowLevelLabel4.setText(null);
        lowLevelLabel5.setText(null);
        lowLevelLabel6.setText(null);
        lowLevelLabel7.setText(null);
    }

    /**
     * Adds an ActionListener to the create hotel button.
     * * @param listener the ActionListener to be added to the create hotel button
     */
    public void addBtnCreateHotelListener(ActionListener listener) {
        btnCreateHotel.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the create submit button.
     * * @param listener the ActionListener to be added to the create submit  button
     */

    public void addBtnCreateSubmitListener(ActionListener listener) {
        btnCreateSubmit.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the view hotel button.
     * * @param listener the ActionListener to be added to the view hotel  button
     */
    public void addBtnViewHotelListener(ActionListener listener) {
        btnViewHotel.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the view details button.
     *
     * @param listener the ActionListener to be added to the view details button
     */
    public void addBtnViewListener(ActionListener listener) {
        btnViewDetails.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the low-level info button.
     *
     * @param listener the ActionListener to be added to the low-level info button
     */
    public void addBtnLowLevelInfoListener(ActionListener listener) {
        btnLowLevelInfo.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the check availability button.
     *
     * @param listener the ActionListener to be added to the check availability button
     */

    public void addBtnCheckAvailabilityListener(ActionListener listener) {
        btnCheckAvailability.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the room info button.
     *
     * @param listener the ActionListener to be added to the room info button
     */
    public void addBtnRoomInfoListener(ActionListener listener) {
        btnRoomInfo.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the reservation info button.
     *
     * @param listener the ActionListener to be added to the reservation info button
     */
    public void addBtnReservationInfoListener(ActionListener listener) {
        btnReservationInfo.addActionListener(listener);
    }


    public void addBtnPriceBreakdownListener(ActionListener listener) {
        btnPriceBreakDown.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the manage hotel button.
     *
     * @param listener the ActionListener to be added to the manage hotel button
     */
    public void addBtnManageHotelListener(ActionListener listener) {
        btnManageHotel.addActionListener(listener);
    }

    /**
     * Adds an ItemListener to the rooms combo box to handle item selection changes.
     *
     * @param listener the ItemListener to be added to the rooms combo box
     */
    public void populateRoomsComboBox(ItemListener listener) {
        hotelComboBox.addItemListener(listener);
    }

    /**
     * Adds an ItemListener to the reservations combo box to handle item selection changes.
     *
     * @param listener the ItemListener to be added to the reservations combo box
     */
    public void populateReservationsComboBox(ItemListener listener) {
        hotelComboBox.addItemListener(listener);
    }

    /**
     * Adds an ActionListener to the remove reservation button.
     *
     * @param listener the ActionListener to be added to the remove reservation button
     */
    public void addBtnRemoveReservationListener(ActionListener listener) {
        btnRemoveReservation.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the remove all reservations button.
     *
     * @param listener the ActionListener to be added to the remove all reservations button
     */
    public void addBtnRemoveAllReservationListener(ActionListener listener) {
        btnRemoveAllReservation.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the add room button.
     *
     * @param listener the ActionListener to be added to the add room button
     */
    public void addBtnAddRoomsListener(ActionListener listener) {
        btnAddRoom.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the remove room button.
     *
     * @param listener the ActionListener to be added to the remove room button
     */
    public void addBtnRemoveRoomsListener(ActionListener listener) {
        btnRemoveRoom.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the change name button.
     *
     * @param listener the ActionListener to be added to the change name button
     */
    public void addBtnChangeNameListener(ActionListener listener) {
        btnChangeName.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the change price button.
     *
     * @param listener the ActionListener to be added to the change price button
     */
    public void addBtnChangePriceListener(ActionListener listener) {
        btnChangePrice.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the delete hotel button.
     *
     * @param listener the ActionListener to be added to the delete hotel button
     */
    public void addBtnDeleteListener(ActionListener listener) {
        btnDeleteHotel.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the date price button.
     *
     * @param listener the ActionListener to be added to the date price button
     */
    public void addBtnDatePriceListener(ActionListener listener) {
        btnDatePrice.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the simulate booking button.
     *
     * @param listener the ActionListener to be added to the simulate booking button
     */
    public void addBtnSimulateBookingListener(ActionListener listener) {
        btnSimulateBooking.addActionListener(listener);
    }

    /**
     * Adds an ItemListener to the room type box to handle item selection changes based on the state of the hotel combo box.
     *
     * @param listener the ItemListener to be added to the room type box
     */
    public void populateRoomTypeBox(ItemListener listener) {
        hotelComboBox2.addItemListener(listener);
    }

    /**
     * Adds an ItemListener to the check-in date box to handle item selection changes.
     *
     * @param listener the ItemListener to be added to the check-in date box
     */
    public void populateCheckInDateBox(ItemListener listener) {
        roomTypeBox.addItemListener(listener);
    }

    /**
     * Adds an ItemListener to the check-out date box to handle item selection changes.
     *
     * @param listener the ItemListener to be added to the check-out date box
     */
    public void populateCheckOutDateBox(ItemListener listener) {
        checkInDateBox.addItemListener(listener);
    }

    /**
     * Adds an ActionListener to the book button.
     *
     * @param listener the ActionListener to be added to the book button
     */
    public void addBtnBookListener(ActionListener listener) {
        btnBook.addActionListener(listener);
    }

    /**
     * Retrieves the text input from the hotel name field.
     *
     * @return the text from the hotel name field
     */

    public String getHotelNameInput() {
        return hotelNameField.getText();
    }

    /**
     * Retrieves the text input from the number of standard rooms field.
     *
     * @return the text from the number of standard rooms field
     */
    public String getNumberOfStandardRoomsInput() {
        return numberOfStandardRoomsField.getText();
    }

    /**
     * Retrieves the text input from the number of deluxe rooms field.
     *
     * @return the text from the number of deluxe rooms field
     */
    public String getNumberOfDeluxeRoomsInput() {
        return numberOfDeluxeRoomsField.getText();
    }

    /**
     * Retrieves the text input from the number of executive rooms field.
     *
     * @return the text from the number of executive rooms field
     */
    public String getNumberOfExecutiveRoomsInput() {
        return numberOfExecutiveRoomsField.getText();
    }

    /**
     * Retrieves the JTextField for base price input.
     *
     * @return the JTextField for base price input
     */
    public JTextField getBasePriceInput() {
        return basePriceField;
    }

    /**
     * Displays a message in a JOptionPane.
     *
     * @param message the message to be displayed
     */
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Clears the create hotel form.
     */
    public void clearCreateHotelForm() {
        hotelNameField.setText("");
        numberOfStandardRoomsField.setText("");
        numberOfDeluxeRoomsField.setText("");
        numberOfExecutiveRoomsField.setText("");
        basePriceField.setText("");
    }

    /**
     * Clears the view hotel form.
     */
    public void clearViewHotelForm() {
        nameLabel.setText("");
        numOfRoomsLabel.setText("");
        numOfReservationsLabel.setText("");
        estimatedEarningsLabel.setText("");
        numOfAvailableRoomsLabel.setText("");
    }

    /**
     * Clears the low level form.
     */
    public void clearLowLevelForm() {
        lowLevelLabel1.setText("");
        lowLevelLabel2.setText("");
        lowLevelLabel3.setText("");
        lowLevelLabel4.setText("");
        lowLevelLabel5.setText("");
        lowLevelLabel6.setText("");
        lowLevelLabel7.setText("");
        dateBox2.setSelectedItem(null);
        roomNumberBox.setSelectedItem(null);
        reservationsBox2.setSelectedItem(null);
    }

    /**
     * Clears the manage hotel form.
     */
    public void clearManageHotelForm() {
        hotelComboBox.setSelectedItem(null);
        typeOfRoomBox.setSelectedItem(null);
        addRoomsBox.setSelectedItem(null);
        removeRoomsBox.setSelectedItem(null);
        reservationsBox.setSelectedItem(null);
        datesBox.setSelectedItem(null);
        datesPercentBox.setSelectedItem(null);
        changeNameTextField.setText("");
        changePriceTextField.setText("");
    }

    /**
     * Clears the simulate booking form.
     */
    public void clearSimulateBookingForm() {
        customerNameField.setText("");
        hotelComboBox2.setSelectedItem(null);
        roomTypeBox.setSelectedItem(null);
        checkInDateBox.setSelectedItem(null);
        checkOutDateBox.setSelectedItem(null);
        couponCodeField.setText("");
    }

    /**
     * Returns the JComboBox used for selecting hotels.
     *
     * @return the hotelComboBox JComboBox
     */
    public JComboBox<String> getHotelComboBox() {
        return hotelComboBox;
    }

    /**
     * Returns the JComboBox used for selecting room types.
     *
     * @return the typeOfRoomBox JComboBox
     */
    public JComboBox<String> getTypeOfRoomBox() {
        return typeOfRoomBox;
    }

    /**
     * Returns the JComboBox used for adding rooms.
     *
     * @return the addRoomsBox JComboBox
     */
    public JComboBox<String> getAddRoomBox() {
        return addRoomsBox;
    }

    /**
     * Returns the JComboBox used for removing rooms.
     *
     * @return the removeRoomsBox JComboBox
     */
    public JComboBox<String> getRemoveRoomBox() {
        return removeRoomsBox;
    }

    /**
     * Returns the JTextField used for inputting the changed hotel name.
     *
     * @return the changeNameTextField JTextField
     */
    public JTextField getChangedNameInput() {
        return changeNameTextField;
    }

    /**
     * Returns the JTextField used for inputting the changed price.
     *
     * @return the changePriceTextField JTextField
     */
    public JTextField getChangedPriceInput() {
        return changePriceTextField;
    }

    /**
     * Returns the JComboBox used for selecting dates.
     *
     * @return the datesBox JComboBox
     */
    public JComboBox<String> getDatesBox() {
        return datesBox;
    }

    /**
     * Returns the JComboBox used for selecting dates.
     *
     * @return the datesPercentBox JComboBox
     */
    public JComboBox<String> getDatesPercentBox() {
        return datesPercentBox;
    }

    /**
     * Returns the JComboBox used for selecting reservations.
     *
     * @return the reservationsBox JComboBox
     */
    public JComboBox<String> getReservationsBox() {
        return reservationsBox;
    }

    /**
     * Returns the JComboBox used for selecting hotels.
     *
     * @return the hotelComboBox2 JComboBox
     */
    public JComboBox<String> getHotelComboBox2() {
        return hotelComboBox2;
    }

    /**
     * Returns the JComboBox used for selecting room types.
     *
     * @return the roomTypeBox JComboBox
     */
    public JComboBox<String> getRoomTypeBox() {
        return roomTypeBox;
    }

    /**
     * Returns the JComboBox used for selecting check-in and check-out dates.
     *
     * @return the checkInDateBox JComboBox
     */
    public JComboBox<String> getCheckInDateBox() {
        return checkInDateBox;
    }

    /**
     * Returns the JComboBox used for selecting check-in and check-out dates.
     *
     * @return the checkOutDateBox JComboBox
     */
    public JComboBox<String> getCheckOutDateBox() {
        return checkOutDateBox;
    }

    /**
     * Returns the JTextField used for inputting the customer name.
     *
     * @return the customerNameField JTextField
     */
    public JTextField getCustomerNameFieldInput() {
        return customerNameField;
    }

    /**
     * Returns the JTextField used for inputting the coupon code.
     *
     * @return the couponCodeField JTextField
     */
    public JTextField getCouponCodeFieldInput() {
        return couponCodeField;
    }

    /**
     * Returns the JLabel used for displaying the hotel name.
     *
     * @return the nameLabel JLabel
     */
    public JLabel getNameLabel() {
        return nameLabel;
    }

    /**
     * Returns the JLabel used for displaying the number of rooms.
     *
     * @return the numOfRoomsLabel JLabel
     */
    public JLabel getNumOfRoomsLabel() {
        return numOfRoomsLabel;
    }

    /**
     * Returns the JLabel used for displaying the number of reservations.
     *
     * @return the numOfReservationsLabel JLabel
     */
    public JLabel getNumOfReservationsLabel() {
        return numOfReservationsLabel;
    }

    /**
     * Returns the JLabel used for displaying the estimated earnings.
     *
     * @return the estimatedEarningsLabel JLabel
     */
    public JLabel getEstimatedEarningsLabel() {
        return estimatedEarningsLabel;
    }

    /**
     * Returns the JLabel used for displaying the number of available rooms.
     *
     * @return the numOfAvailableRoomsLabel JLabel
     */
    public JLabel getUnreservedRoomsLabel() {
        return numOfAvailableRoomsLabel;
    }

    /**
     * Returns the second JComboBox used for selecting hotels.
     *
     * @return the hotelComboBox2 JComboBox
     */
    public JComboBox<String> getHotelComboBox3() {
        return hotelComboBox3;
    }

    /**
     * Returns the second JComboBox used for selecting dates.
     *
     * @return the dateBox2 JComboBox
     */
    public JComboBox<String> getDateBox2() {
        return dateBox2;
    }

    /**
     * Returns the second JComboBox used for selecting room numbers.
     *
     * @return the roomNumberBox JComboBox
     */
    public JComboBox<String> getRoomNumberBox() {
        return roomNumberBox;
    }

    /**
     * Returns the second JComboBox used for selecting reservations.
     *
     * @return the reservationsBox2 JComboBox
     */
    public JComboBox<String> getReservationsBox2() {
        return reservationsBox2;
    }

    /**
     * Returns the JLabel used for displaying low-level information (1).
     *
     * @return the lowLevelLabel1 JLabel
     */
    public JLabel getLowLevelLabel1() {
        return lowLevelLabel1;
    }

    /**
     * Returns the JLabel used for displaying low-level information (2).
     *
     * @return the lowLevelLabel2 JLabel
     */
    public JLabel getLowLevelLabel2() {
        return lowLevelLabel2;
    }

    /**
     * Returns the JLabel used for displaying low-level information (3).
     *
     * @return the lowLevelLabel3 JLabel
     */
    public JLabel getLowLevelLabel3() {
        return lowLevelLabel3;
    }

    /**
     * Returns the JLabel used for displaying low-level information (4).
     *
     * @return the lowLevelLabel4 JLabel
     */
    public JLabel getLowLevelLabel4() {
        return lowLevelLabel4;
    }

    /**
     * Returns the JLabel used for displaying low-level information (5).
     *
     * @return the lowLevelLabel5 JLabel
     */
    public JLabel getLowLevelLabel5() {
        return lowLevelLabel5;
    }

    /**
     * Returns the JLabel used for displaying low-level information (6).
     *
     * @return the lowLevelLabel6 JLabel
     */
    public JLabel getLowLevelLabel6() {
        return lowLevelLabel6;
    }

    /**
     * Returns the JLabel used for displaying low-level information (7).
     *
     * @return the lowLevelLabel7 JLabel
     */
    public JLabel getLowLevelLabel7() {
        return lowLevelLabel7;
    }


    /**
     * Returns the JButton used for submitting hotel creation.
     *
     * @return the btnCreateSubmit JButton
     */
    public JButton getBtnCreateSubmit() {
        return btnCreateSubmit;
    }

    /**
     * Returns the JButton used for viewing details.
     *
     * @return the btnViewDetails JButton
     */
    public JButton getBtnView() {
        return btnViewDetails;
    }

    /**
     * Returns the JButton used for displaying low-level information.
     *
     * @return the btnLowLevelInfo JButton
     */
    public JButton getBtnLowLevelInfo() {
        return btnLowLevelInfo;
    }

    /**
     * Returns the JButton used for checking availability.
     *
     * @return the btnCheckAvailability JButton
     */
    public JButton getBtnCheckAvailability() {
        return btnCheckAvailability;
    }

    /**
     * Returns the JButton used for displaying room information.
     *
     * @return the btnRoomInfo JButton
     */
    public JButton getBtnRoomInfo() {
        return btnRoomInfo;
    }

    /**
     * Returns the JButton used for displaying reservation information.
     *
     * @return the btnReservationInfo JButton
     */
    public JButton getBtnReservationInfo() {
        return btnReservationInfo;
    }

    /**
     * Returns the JButton used for displaying reservation information.
     *
     * @return the btnReservationInfo JButton
     */
    public JButton getBtnAddRooms() {
        return btnAddRoom;
    }

    /**
     * Returns the JButton used for displaying reservation information.
     *
     * @return the btnReservationInfo JButton
     */
    public JButton getBtnRemoveRooms() {
        return btnRemoveRoom;
    }

    /**
     * Returns the JButton used for displaying reservation information.
     *
     * @return the btnReservationInfo JButton
     */
    public JButton getBtnChangeName() {
        return btnChangeName;
    }

    /**
     * Returns the JButton used for displaying reservation information.
     *
     * @return the btnReservationInfo JButton
     */
    public JButton getBtnChangePrice() {
        return btnChangePrice;
    }

    /**
     * Returns the JButton used for removing a reservation.
     *
     * @return the btnRemoveReservation JButton
     */
    public JButton getBtnRemoveReservation() {
        return btnRemoveReservation;
    }

    /**
     * Returns the JButton used for removing all reservations.
     *
     * @return the btnRemoveAllReservation JButton
     */
    public JButton getBtnRemoveAllReservation() {
        return btnRemoveAllReservation;
    }

    /**
     * Returns the JButton used for deleting a hotel.
     *
     * @return the btnDeleteHotel JButton
     */
    public JButton getBtnDelete() {
        return btnDeleteHotel;
    }

    /**
     * Returns the JButton used for setting date-based prices.
     *
     * @return the btnDatePrice JButton
     */
    public JButton getBtnDatePrice() {
        return btnDatePrice;
    }

    /**
     * Returns the JButton used for booking rooms.
     *
     * @return the btnBook JButton
     */
    public JButton getBtnBookListener() {
        return btnBook;
    }
}