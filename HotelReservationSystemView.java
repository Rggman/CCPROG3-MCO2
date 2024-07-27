import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelReservationSystemView extends JFrame {




    //For the MainMenu
    private JButton btnCreateHotel, btnViewHotel, btnManageHotel, btnSimulateBooking, btnBackToMainMenu;

    private JPanel northPanel, westPanel, centerPanel, southPanel;






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


    private void displayMainMenu() {
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
        centerPanel.setLayout(new FlowLayout());




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

    public void setMainMenuActionLister (ActionListener listener) {
        btnCreateHotel.addActionListener(listener);
        btnViewHotel.addActionListener(listener);
        btnManageHotel.addActionListener(listener);
        btnSimulateBooking.addActionListener(listener);
        btnBackToMainMenu.addActionListener(listener);
    }

}