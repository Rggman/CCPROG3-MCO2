import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelReservationSystemController implements ActionListener {

    private HotelReservationSystemView view;
    private HotelReservationSystemModel model;

    public HotelReservationSystemController(HotelReservationSystemView view, HotelReservationSystemModel model) {
        this.view = view;
        this.model = model;

        view.setMainMenuActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Create Hotel":
                view.displayCreateHotelForm();
                break;
            case "View Hotel":
                view.displayViewHotel() ;
                break;
            case "Manage Hotel":
                view.displayManageHotel();
                break;
            case "Book Hotel":
                view.displaySimulateBooking();
                break;
            case "Back to Main":
                view.displayMainMenu();
                break;
            case "Submit":
                System.out.println("Submit Button Clicked");
                break;
            case "View Details":
                System.out.println("View Details Clicked");
                break;
            case "Check Availability":
                System.out.println("Check Availability Clicked");
                break;
            case "Room Information":
                System.out.println("Room Info Clicked");
                break;
            case "Reservation Information":
                System.out.println("Reservation Info Clicked");
                break;
            case "Add":
                System.out.println("Add Button Clicked");
                break;
            case "Remove":
                System.out.println("Remove Button Clicked");
                break;
            case "Change Hotel Name":
                System.out.println("Change Hotel Name Clicked");
                break;
            case "Change Price":
                System.out.println("Change Price Clicked");
                break;
            case "Delete Hotel":
                System.out.println("Delete Hotel Clicked");
                break;
            case "Modify":
                System.out.println("Price Modifier Clicked");
                break;
            case "Book":
                System.out.println("Book Button Clicked");
                break;

        }


    }







}
