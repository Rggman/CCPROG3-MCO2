import javax.swing.*;
import java.awt.*;


public class HotelReservationSystemView extends JFrame {


    private JFrame mainFrame;

    //For the MainMenu
    private JButton btnCreateHotel, btnViewHotel, btnManageHotel, btnSimulateBooking, btnBackToMainMenu;



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
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.LIGHT_GRAY);
        northPanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Hotel Reservation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(30, 144, 255)); // Dark blue color

        northPanel.add(titleLabel);



        // West Panel - Main Menu Buttons (Create, View, Manage, Simulate Booking, Back to Main Menu)
        JPanel westPanel = new JPanel();
        westPanel.setBackground(Color.DARK_GRAY);
        westPanel.setLayout(new GridBagLayout());
        westPanel.setPreferredSize(new Dimension(150, 600));

        // Center Panel - For Content
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(30, 144, 255));
        centerPanel.setLayout(new FlowLayout());




        //
        this.add(northPanel, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
    }
}