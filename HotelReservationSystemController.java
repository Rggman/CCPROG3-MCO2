import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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

        this.view.addBtnBackToViewListener(new BtnBackToViewListener());


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
                    view.clearCreateHotelForm();
                }
                catch (NumberFormatException ex) {
                    view.displayMessage("Please enter valid numbers for rooms and prices");
                    System.out.print(ex);
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
            if (model.getHotels().isEmpty()) {
                view.displayMessage("No available hotels");
                return;
            }
            view.displayManageHotel();
            view.getHotelComboBox().removeAllItems();
            // Used to populate the hotel combo box with available hotels
            for (Hotel hotel : model.getHotels())
                view.getHotelComboBox().addItem(hotel.getHotelName());
            view.getHotelComboBox().setSelectedItem(null);
            view.getTypeOfRoomBox().setSelectedItem(null);

            view.populateRoomsComboBox(new PopulateRoomsBox());
            view.addBtnAddRoomsListener(new BtnAddRoomsListener());
            view.addBtnRemoveRoomsListener(new BtnAddRoomsListener());
            view.addBtnChangeNameListener(new BtnChangeNameListener());
            view.addBtnChangePriceListener(new BtnChangePriceListener());
            view.populateReservationsComboBox(new PopulateReservationsBox());
            view.addBtnRemoveReservationListener(new BtnRemoveReservationListener());
            view.addBtnRemoveAllReservationListener(new BtnRemoveAllReservationListener());
            view.addBtnDeleteListener(new BtnDeleteListener());
            view.addBtnDatePriceListener(new BtnDatePriceListener());
        }
    }

        // Used to populate add rooms box and remove rooms box
        class PopulateRoomsBox implements ItemListener {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String name = (String)view.getHotelComboBox().getSelectedItem();
                    Hotel hotel = model.getHotel(name);
                    view.getAddRoomBox().removeAllItems();
                    view.getRemoveRoomBox().removeAllItems();
                    for (int i = 1; i <= 50 - hotel.getHotelNumOfRooms(); i ++)
                        view.getAddRoomBox().addItem(Integer.toString(i));

                    for (int i = 1; i <= hotel.getHotelNumOfRooms(); i++)
                        view.getRemoveRoomBox().addItem(Integer.toString(i));

                    view.getAddRoomBox().setSelectedItem(null);
                    view.getRemoveRoomBox().setSelectedItem(null);
                }
            }
        }

        class PopulateReservationsBox implements ItemListener {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String name = (String) view.getHotelComboBox().getSelectedItem();
                    Hotel hotel = model.getHotel(name);
                    for (CustomerReservation r : hotel.getHotelReservations()) {
                        view.getReservationsBox().addItem(r.getCustomerName());
                    }
                    view.getReservationsBox().setSelectedItem(null);
                }
            }
        }

        class BtnAddRoomsListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) view.getHotelComboBox().getSelectedItem();
                Hotel hotel = model.getHotel(name);
                hotel.addRooms(Integer.parseInt((String) view.getAddRoomBox().getSelectedItem()), (String) view.getTypeOfRoomBox().getSelectedItem());
                view.displayMessage("Successfully added " + (String)view.getAddRoomBox().getSelectedItem() + " rooms");
            }
        }

        class BtnRemoveRoomsListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)view.getHotelComboBox().getSelectedItem();
                Hotel hotel = model.getHotel(name);
                hotel.removeRooms(Integer.parseInt((String) view.getRemoveRoomBox().getSelectedItem()),
                (String) view.getTypeOfRoomBox().getSelectedItem());
                view.displayMessage("Successfully removed " + (String)view.getRemoveRoomBox().getSelectedItem() + " rooms");
            }
        }

        class BtnChangeNameListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)view.getHotelComboBox().getSelectedItem();
                Hotel hotel = model.getHotel(name);
                if (!model.isHotelNameUnique(view.getChangedNameInput())) {
                    view.displayMessage("Hotel " + view.getChangedNameInput() + " already exists");
                    return;
                }
                view.displayMessage("Hotel " + hotel.getHotelName() + " has been changed to " + view.getChangedNameInput());
                hotel.setHotelName(view.getChangedNameInput());
            }
        }

        class BtnChangePriceListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)view.getHotelComboBox().getSelectedItem();
                Hotel hotel = model.getHotel(name);
                try {
                    if (Double.parseDouble(view.getChangedPriceInput()) < 100) {
                        view.displayMessage("Base price of hotel should be greater than 100");
                        return;
                    }
                    hotel.setRoomPrice(Double.parseDouble(view.getChangedPriceInput()));
                    view.displayMessage("Hotel " + hotel.getHotelName() + " base price is now " + view.getChangedPriceInput());
                }
                catch (NumberFormatException ex) {
                    view.displayMessage("Please enter valid number for price");
                    System.out.print(ex);
                }
            }
        }

        class BtnDeleteListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)view.getHotelComboBox().getSelectedItem();
                Hotel hotel = model.getHotel(name);
                if (hotel.getHotelReservations().size() != 0) {
                    view.displayMessage("There are still reservations!\nHotel cannot be deleted");
                    return;
                }
                model.removeHotel(name);
                JOptionPane.showMessageDialog(null, "Hotel " + name + " successfully deleted!");
            }
        }

        class BtnDatePriceListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)view.getHotelComboBox().getSelectedItem();
                Hotel hotel = model.getHotel(name);
                int date = Integer.parseInt((String) view.getDatesBox().getSelectedItem());
                String percentString = (String) view.getDatesPercentBox().getSelectedItem();

                percentString = percentString.replace("%", "");
                float percent = Float.parseFloat(percentString) / 100.00f;
                hotel.setPercent(date, percent);
                for (CustomerReservation reservation : hotel.getHotelReservations()) {
                    reservation.setDatePercent(date, percent);
                }
                view.displayMessage(
                        "Date " + date + " price has been changed to " + (String) view.getDatesPercentBox().getSelectedItem());
            }
        }

        class BtnRemoveReservationListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) view.getHotelComboBox().getSelectedItem();
                Hotel hotel = model.getHotel(name);
                hotel.removeReservation((String) view.getReservationsBox().getSelectedItem());
                view.displayMessage("Reservation for " + (String) view.getReservationsBox().getSelectedItem() + "successfully removed");
            }
        }

        class BtnRemoveAllReservationListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)view.getHotelComboBox().getSelectedItem();
                Hotel hotel = model.getHotel(name);
                hotel.clearReservations();
                view.displayMessage("All reservations for " + name + " have been removed");
            }
        }

    // Menu button for back to view
    class BtnBackToViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.displayViewHotel();
        }
    }

    // Menu button for simulate booking
    class BtnSimulateBookingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.getHotels().isEmpty()) {
                view.displayMessage("No available hotels");
                return;
            }
            view.displaySimulateBooking();

            view.getHotelComboBox2().removeAllItems();
            // Used to populate the hotel combo box with available hotels
            for (Hotel hotel : model.getHotels())
                view.getHotelComboBox2().addItem(hotel.getHotelName());
            view.getHotelComboBox2().setSelectedItem(null);
            view.getRoomTypeBox().setSelectedItem(null);

            view.populateRoomTypeBox(new PopulateRoomTypeBox());
            view.populateCheckInDateBox(new PopulateCheckInDateBox());
            view.populateCheckOutDateBox(new PopulateCheckOutDateBox());
            view.addBtnBookListener(new BtnBookListener());
        }
    }

        class PopulateRoomTypeBox implements ItemListener {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedHotelName = (String) view.getHotelComboBox2().getSelectedItem();
                    Hotel hotel = model.getHotel(selectedHotelName);
                    view.getRoomTypeBox().removeAllItems();

                    if (hotel.getNumOfStandardRooms() > 0) {
                        view.getRoomTypeBox().addItem("Standard");
                    }
                    if (hotel.getNumOfDeluxeRooms() > 0) {
                        view.getRoomTypeBox().addItem("Deluxe");
                    }
                    if (hotel.getNumOfExecutiveRooms() > 0) {
                        view.getRoomTypeBox().addItem("Executive");
                    }
                    view.getRoomTypeBox().setSelectedItem(null);
                }
            }
        }

        class PopulateCheckInDateBox implements ItemListener {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedHotelName = (String) view.getHotelComboBox2().getSelectedItem();
                    Hotel hotel = model.getHotel(selectedHotelName);
                    boolean[] isAvailable = new boolean[31];
                    view.getCheckInDateBox().removeAllItems();

                    for (Room room : hotel.getRooms()) {
                        for (int i = 0; i < 31; i++) {
                            if (room.getRoomType().equals((String) view.getRoomTypeBox().getSelectedItem()) && !room.getIsReserved(i + 1)) {
                                isAvailable[i] = true;
                            }
                        }
                    }
                    for (int i = 0; i < isAvailable.length - 1; i++) {
                        if (isAvailable[i]) {
                            view.getCheckInDateBox().addItem(Integer.toString(i + 1));
                        }
                    }
                    view.getCheckInDateBox().setSelectedItem(null);
                }
            }
        }

        class PopulateCheckOutDateBox implements ItemListener {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedHotelName = (String)view.getHotelComboBox2().getSelectedItem();
                    Hotel hotel = model.getHotel(selectedHotelName);
                    view.getCheckOutDateBox().removeAllItems();
                    boolean[] isAvailable = new boolean[31];

                    for (Room room : hotel.getRooms()) {
                        for (int i = 0; i < 31; i++) {
                            if (room.getRoomType().equals((String) view.getRoomTypeBox().getSelectedItem()) && !room.getIsReserved(i + 1)) {
                                isAvailable[i] = true;
                            }
                        }
                    }
                    for (int i = Integer.parseInt(
                        (String) view.getCheckInDateBox().getSelectedItem()); i < isAvailable.length; i++) {
                        if (isAvailable[i]) {
                            view.getCheckOutDateBox().addItem(Integer.toString(i + 1));
                        }
                    }
                    view.getCheckOutDateBox().setSelectedItem(null);
                }
            }
        }

        class BtnBookListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) view.getHotelComboBox2().getSelectedItem();
                Hotel hotel = model.getHotel(name);
                String customerName = view.getCustomerNameFieldInput();
                int checkInDate = Integer.parseInt((String) view.getCheckInDateBox().getSelectedItem());
                int checkOutDate = Integer.parseInt((String) view.getCheckOutDateBox().getSelectedItem());
                String roomType = (String) view.getRoomTypeBox().getSelectedItem();
                String couponCode = view.getCouponCodeFieldInput();

                if (!couponCode.equals("I_WORK_HERE") && !couponCode.equals("STAY4_GET1") && !couponCode.equals("PAYDAY")
                    && !couponCode.equals("0")) {
                view.displayMessage("Invalid coupon code!");
                return;
                } 
                else if (couponCode.equals("STAY4_GET1") && checkOutDate - checkInDate < 5) {
                    JOptionPane.showMessageDialog(null,
                            "Coupon code (STAY4_GET1) could only be used for reservations with 5 days or more!");
                    return;
                } 
                else if (couponCode.equals("PAYDAY")) {
                    boolean validPayday = false;
                    for (int i = checkInDate; i < checkOutDate; i++) {
                        if ((i == 15 && checkOutDate != 15) || (i == 30 && checkOutDate != 30)) {
                            validPayday = true;
                            break;
                        }
                    }
                    if (!validPayday) {
                        JOptionPane.showMessageDialog(null,
                                "Coupon code (PAYDAY) could only be used for reservations covering 15 or 30 but not as checkout date!");
                        return;
                    }
                }
        
                model.addReservation(name, customerName, checkInDate, checkOutDate, roomType, couponCode);
                CustomerReservation reservation = hotel.getHotelReservation(customerName);
                view.displayMessage("Successfully made reservation for: \n" + "Customer name : " + reservation.getCustomerName() + "\n"
                + "Check-in date : " + reservation.getCheckInDate() + "\n" + "Check-out date : " + reservation.getCheckOutDate() + "\n"
                + "Room Type : " + reservation.getRoomInfo().getRoomType() + "\n" + "Total Price : " + reservation.getTotalPrice());
            }
        }

}
