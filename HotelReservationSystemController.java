import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelReservationSystemController {

    private HotelReservationSystemView view;
    private HotelReservationSystemModel model;

    public HotelReservationSystemController(HotelReservationSystemView view, HotelReservationSystemModel model) {
        this.view = view;
        this.model = model;

        this.view.addBtnCreateHotelListener(new BtnCreateHotelListener());
        this.view.addBtnViewHotelListener(new BtnViewHotelListener());
        this.view.addBtnManageHotelListener(new BtnManageHotelListener());
        this.view.addBtnSimulateBookingListener(new BtnSimulateBookingListener());
    }

    // Menu button for create hotel
    class BtnCreateHotelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.displayCreateHotelForm();
            view.addBtnCreateSubmitListener(new BtnCreateSubmitListener());
        }
    }
        // Create hotel button for submit
        class BtnCreateSubmitListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = view.getHotelNameInput();
                    int numOfStandardRooms = Integer.parseInt(view.getNumberOfStandardRoomsInput());
                    int numOfDeluxeRooms = Integer.parseInt(view.getNumberOfDeluxeRoomsInput());
                    int numOfExecutiveRooms = Integer.parseInt(view.getNumberOfExecutiveRoomsInput());
                    double basePrice = Double.parseDouble(view.getBasePriceInput());

                    if (!model.isHotelNameUnique(name)) {
                        view.displayMessage("Hotel " + name + " already exists");
                        return;
                    }
                    if (numOfStandardRooms > 50 || numOfDeluxeRooms > 50 || numOfExecutiveRooms > 50 || 
                    (numOfStandardRooms + numOfDeluxeRooms + numOfExecutiveRooms) > 50) {
                        view.displayMessage("Maximuim number of total rooms is 50");
                        return;
                    }
                    if (basePrice < 100) {
                        view.displayMessage("Base price of hotel should be greater than 100");
                        return;
                    }

                    model.createHotel(name, basePrice, numOfStandardRooms, numOfDeluxeRooms, numOfExecutiveRooms);
                    view.displayMessage("Hotel " + name + " has been successfully created with a \nbase price of " + basePrice + " ,\n" 
                    + numOfStandardRooms + " standard rooms, \n" + numOfDeluxeRooms + " deluxe rooms, and \n" + numOfExecutiveRooms +
                    " executive rooms");
                }
                catch (NumberFormatException ex) {
                    view.displayMessage("Please enter valid numbers for rooms and prices");
                    return;
                }
            }
        }


    // Menu button for view hotel
    class BtnViewHotelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.displayViewHotel();
        }
    }

    // Menu button for manage hotel
    class BtnManageHotelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.displayManageHotel();
        }
    }



    // Menu button for simulate booking
    class BtnSimulateBookingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.displaySimulateBooking();
        }
    }

}
