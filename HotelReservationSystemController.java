import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * The HotelReservationSystemController class is responsible for handling user interactions
 * with the HotelReservationSystemView and managing the HotelReservationSystemModel.
 * It listens for user actions and updates the view or model accordingly.
 */
public class HotelReservationSystemController {

    private HotelReservationSystemView view;
    private HotelReservationSystemModel model;

    /**
     * Constructs a HotelReservationSystemController with the specified view and model.
     *
     * @param view the view component of the system
     * @param model the model component of the system
     */
    public HotelReservationSystemController(HotelReservationSystemView view, HotelReservationSystemModel model) {
        this.view = view;
        this.model = model;

        this.view.addBtnCreateHotelListener(new BtnCreateHotelListener());
        this.view.addBtnViewHotelListener(new BtnViewHotelListener());
        this.view.addBtnManageHotelListener(new BtnManageHotelListener());
        this.view.addBtnSimulateBookingListener(new BtnSimulateBookingListener());
    }

    /**
     * Handles the action of the "Create Hotel" button.
     */
    class BtnCreateHotelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.displayCreateHotelForm();
            view.removeListeners(view.getBtnCreateSubmit());
            view.addBtnCreateSubmitListener(new BtnCreateSubmitListener());
            view.clearViewHotelForm();
        }
    }

    /**
     * Handles the submission of the "Create Hotel" form.
     */
    class BtnCreateSubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = view.getHotelNameInput();
                String standardRoomsInput = view.getNumberOfStandardRoomsInput();
                String deluxeRoomsInput = view.getNumberOfDeluxeRoomsInput();
                String executiveRoomsInput = view.getNumberOfExecutiveRoomsInput();
                String basePriceInput = view.getBasePriceInput().getText();

                // Check if any input fields are empty
                if (name.isEmpty() || standardRoomsInput.isEmpty() || deluxeRoomsInput.isEmpty() || executiveRoomsInput.isEmpty()) {
                    view.displayMessage("Please fill in all fields");
                    return;
                }

                if (basePriceInput.isEmpty())
                    basePriceInput = "1299";

                int numOfStandardRooms = Integer.parseInt(standardRoomsInput);
                int numOfDeluxeRooms = Integer.parseInt(deluxeRoomsInput);
                int numOfExecutiveRooms = Integer.parseInt(executiveRoomsInput);
                double basePrice = Double.parseDouble(basePriceInput);

                if (!model.isHotelNameUnique(name)) {
                    view.displayMessage("Hotel " + name + " already exists");
                    return;
                }
                if (numOfStandardRooms > 50 || numOfDeluxeRooms > 50 || numOfExecutiveRooms > 50 ||
                        (numOfStandardRooms + numOfDeluxeRooms + numOfExecutiveRooms) > 50) {
                    view.displayMessage("Maximum number of total rooms is 50");
                    return;
                }
                if (basePrice < 100) {
                    view.displayMessage("Base price of hotel should be greater than 100");
                    return;
                }
                if (basePriceInput.isEmpty()) {
                    basePrice = 1299;
                }


                model.createHotel(name, basePrice, numOfStandardRooms, numOfDeluxeRooms, numOfExecutiveRooms);
                view.displayMessage("Hotel " + name + " has been successfully created\nBase price : " + basePrice + "\n"
                        + "Standard rooms : " + numOfStandardRooms + "\n" + "Deluxe rooms : " + numOfDeluxeRooms + "\n" +
                        "Executive rooms : " + numOfExecutiveRooms);
                view.clearCreateHotelForm();
            } catch (NumberFormatException ex) {
                view.displayMessage("Please enter valid numbers for rooms and prices");
            }
        }
    }


    /**
     * Handles the action of the "View Hotel" button.
     */
    class BtnViewHotelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.getHotels().isEmpty()) {
                view.displayMessage("No available hotels");
                return;
            }
            view.displayViewHotel();

            view.getHotelComboBox3().removeAllItems();
            for (Hotel hotel : model.getHotels())
                view.getHotelComboBox3().addItem(hotel.getHotelName());
            view.getHotelComboBox3().setSelectedItem(null);

            view.removeListeners(view.getBtnView());
            view.addBtnViewListener(new BtnViewListener());
            view.removeListeners(view.getBtnLowLevelInfo());
            view.addBtnLowLevelInfoListener(new BtnLowLevelInfoListener());
            view.clearViewHotelForm();
        }
    }

    /**
     * Handles the action of the "View Details" button.
     */
    class BtnViewListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getHotelComboBox3().getSelectedItem() == null) {
                view.displayMessage("Choose a hotel first");
                return;
            }

            String name = (String) view.getHotelComboBox3().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            view.getNameLabel().setText("Hotel Name : " + name);
            view.getNumOfRoomsLabel().setText("Number of Rooms : " + hotel.getHotelNumOfRooms());
            view.getNumOfReservationsLabel().setText("Number of Reservations : " + hotel.getHotelReservations().size());
            view.getEstimatedEarningsLabel().setText("Estimated Earnings: " + hotel.getEstimatedEarnings());
            view.getUnreservedRoomsLabel().setText("Number of Unreserved Rooms: " + hotel.getNumOfUnreservedRooms());
        }
    }

    /**
     * Handles the action of the "More Information" button for low-level details.
     */
    class BtnLowLevelInfoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getHotelComboBox3().getSelectedItem() == null) {
                view.displayMessage("Choose a hotel first");
                return;
            }
            String name = (String)view.getHotelComboBox3().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            view.displayLowLevelInfo();

            // Clear all combo box to avoid duplicates
            view.getDateBox2().removeAllItems();
            view.getRoomNumberBox().removeAllItems();
            view.getReservationsBox2().removeAllItems();

            // Populate dateBox2
            for (int i = 1; i <= 31; i++)
                view.getDateBox2().addItem(Integer.toString(i));
            // Populate roomNumberBox
            for (Room room : hotel.getRooms()) {
                view.getRoomNumberBox().addItem(Integer.toString(room.getNumber()));
            }
            for (CustomerReservation r : hotel.getHotelReservations()) {
                view.getReservationsBox2().addItem(r.getCustomerName());
            }
            view.getDateBox2().setSelectedItem(null);
            view.getRoomNumberBox().setSelectedItem(null);
            view.getReservationsBox2().setSelectedItem(null);

            view.removeListeners(view.getBtnCheckAvailability());
            view.removeListeners(view.getBtnRoomInfo());
            view.removeListeners(view.getBtnReservationInfo());
            view.addBtnCheckAvailabilityListener(new BtnCheckAvailabilityListener());
            view.addBtnRoomInfoListener(new BtnRoomInfoListener());
            view.addBtnReservationInfoListener(new BtnReservationInfoListener());
            view.clearLowLevelForm();
        }
    }

    /**
     * Handles the action of the "Check Availability" button.
     */
    class BtnCheckAvailabilityListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getDateBox2().getSelectedItem() == null) {
                view.displayMessage("Choose a date first");
                return;
            }

            String name = (String)view.getHotelComboBox3().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            // Clear info
            view.clearInfo();
            // Set info to display
            int date = Integer.parseInt((String) view.getDateBox2().getSelectedItem());
            int bookedRooms = hotel.countReservations(date);
            int availableRooms = hotel.countAvailableRooms(date);
            view.getLowLevelLabel1().setText("Total number of booked rooms ---- " + bookedRooms);
            view.getLowLevelLabel2().setText("Total number of available rooms -- " + availableRooms);
        }
    }

    /**
     * Handles the action of the "Check Room Info" button.
     */
    class BtnRoomInfoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getRoomNumberBox().getSelectedItem() == null) {
                view.displayMessage("Choose a room number first");
                return;
            }


            String name = (String)view.getHotelComboBox3().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            // Set info to display
            Room room = hotel.getRooms().get(Integer.parseInt((String) view.getRoomNumberBox().getSelectedItem()) - 1);
            // Clear info
            view.clearInfo();
            view.getLowLevelLabel1().setText("Room Number : " + room.getNumber());
            view.getLowLevelLabel2().setText("Room Price : " + room.getPrice());
            view.getLowLevelLabel3().setText("Room Availability : ");
            StringBuilder infoB1 = new StringBuilder();
            StringBuilder infoB2 = new StringBuilder();
            StringBuilder infoB3 = new StringBuilder();
            StringBuilder infoB4 = new StringBuilder();

            for (int i = 1; i <= 8; i++) {
                if (room.getIsReserved(i))
                    infoB1.append(" X ");
                else
                    infoB1.append(i).append(" ");
            }
            for (int i = 9; i <= 16; i++) {
                if (room.getIsReserved(i))
                    infoB2.append(" X ");
                else
                    infoB2.append(i).append(" ");
            }
            for (int i = 17; i <= 24; i++) {
                if (room.getIsReserved(i))
                    infoB3.append(" X ");
                else
                    infoB3.append(i).append(" ");
            }
            for (int i = 17; i <= 24; i++) {
                if (room.getIsReserved(i))
                    infoB4.append(" X ");
                else
                    infoB4.append(i).append(" ");
            }
            view.getLowLevelLabel4().setText(infoB1.toString());
            view.getLowLevelLabel5().setText(infoB2.toString());
            view.getLowLevelLabel6().setText(infoB3.toString());
            view.getLowLevelLabel7().setText(infoB4.toString());
        }
    }

    /**
     * Handles the action of the "Check Reservation Info" button.
     */
    class BtnReservationInfoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getReservationsBox2().getSelectedItem() == null) {
                view.displayMessage("Choose a reservation first");
                return;
            }

            String name = (String)view.getHotelComboBox3().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            // Set info to display
            String customerName = (String) view.getReservationsBox2().getSelectedItem();
            CustomerReservation reservation = hotel.getHotelReservation(customerName);
            // Clear info
            view.clearInfo();
            view.getLowLevelLabel1().setText("Customer Name: " + reservation.getCustomerName());
            view.getLowLevelLabel2().setText("Check-in Date: " + reservation.getCheckInDate());
            view.getLowLevelLabel3().setText("Check-out Date: " + reservation.getCheckOutDate());
            view.getLowLevelLabel4().setText("Room Number: " + reservation.getRoomInfo().getNumber());
            view.getLowLevelLabel5().setText("Room Price: " + reservation.getRoomInfo().getPrice());
            view.getLowLevelLabel6().setText("Total Price: " + reservation.getTotalPrice());
        }
    }

    /**
     * Handles the action of the "Manage Hotel" button.
     */
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

            view.removeListeners(view.getBtnAddRooms());
            view.removeListeners(view.getBtnRemoveRooms());
            view.removeListeners(view.getBtnChangeName());
            view.removeListeners(view.getBtnChangePrice());
            view.removeListeners(view.getBtnRemoveReservation());
            view.removeListeners(view.getBtnRemoveAllReservation());
            view.removeListeners(view.getBtnDelete());
            view.removeListeners(view.getBtnDatePrice());
            view.populateRoomsComboBox(new PopulateRoomsBox());
            view.addBtnAddRoomsListener(new BtnAddRoomsListener());
            view.addBtnRemoveRoomsListener(new BtnRemoveRoomsListener());
            view.addBtnChangeNameListener(new BtnChangeNameListener());
            view.addBtnChangePriceListener(new BtnChangePriceListener());
            view.populateReservationsComboBox(new PopulateReservationsBox());
            view.addBtnRemoveReservationListener(new BtnRemoveReservationListener());
            view.addBtnRemoveAllReservationListener(new BtnRemoveAllReservationListener());
            view.addBtnDeleteListener(new BtnDeleteListener());
            view.addBtnDatePriceListener(new BtnDatePriceListener());
            view.clearViewHotelForm();
        }
    }

    /**
     * Populates the room boxes
     */
    class PopulateRoomsBox implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String name = (String) view.getHotelComboBox().getSelectedItem();
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

    /**
     * Populates the reservation boxes
     */
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

    /**
     * Handles the action of the "Add Rooms" button.
     */
    class BtnAddRoomsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getHotelComboBox().getSelectedItem() == null) {
                view.displayMessage("Choose a hotel first");
                return;
            }
            if (view.getTypeOfRoomBox().getSelectedItem() == null) {
                view.displayMessage("Choose a type of room first");
                return;
            }
            if (view.getAddRoomBox().getSelectedItem() == null) {
                view.displayMessage("Choose the number of rooms to add first");
                return;
            }

            String name = (String) view.getHotelComboBox().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            hotel.addRooms(Integer.parseInt((String) view.getAddRoomBox().getSelectedItem()), (String) view.getTypeOfRoomBox().getSelectedItem());
            view.displayMessage("Successfully added " + (String)view.getAddRoomBox().getSelectedItem() + " rooms");
            view.clearManageHotelForm();
        }
    }

    /**
     * Handles the action of the "Remove Rooms" button.
     */
    class BtnRemoveRoomsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getHotelComboBox().getSelectedItem() == null) {
                view.displayMessage("Choose a hotel first");
                return;
            }
            if (view.getTypeOfRoomBox().getSelectedItem() == null) {
                view.displayMessage("Choose a type of room first");
                return;
            }
            if (view.getRemoveRoomBox().getSelectedItem() == null) {
                view.displayMessage("Choose the number of rooms to add first");
                return;
            }

            String name = (String)view.getHotelComboBox().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            hotel.removeRooms(Integer.parseInt((String) view.getRemoveRoomBox().getSelectedItem()),
                    (String) view.getTypeOfRoomBox().getSelectedItem());
            view.displayMessage("Successfully removed " + (String)view.getRemoveRoomBox().getSelectedItem() + " rooms");
            view.clearManageHotelForm();
        }
    }

    /**
     * Handles the action of the "Change Name" button.
     */
    class BtnChangeNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getHotelComboBox().getSelectedItem() == null) {
                view.displayMessage("Choose a hotel first");
                return;
            }
            if (view.getChangedNameInput().getText().isEmpty()) {
                view.displayMessage("Input a new name first");
                return;
            }

            String name = (String)view.getHotelComboBox().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            if (!model.isHotelNameUnique(view.getChangedNameInput().getText())) {
                view.displayMessage("Hotel " + view.getChangedNameInput() + " already exists");
                return;
            }
            view.displayMessage("Hotel " + hotel.getHotelName() + " has been changed to " + view.getChangedNameInput());
            hotel.setHotelName(view.getChangedNameInput().getText());
            view.clearManageHotelForm();
        }
    }

    /**
     * Handles the action of the "Change Price" button.
     */
    class BtnChangePriceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getHotelComboBox().getSelectedItem() == null) {
                view.displayMessage("Choose a hotel first");
                return;
            }
            if (view.getChangedPriceInput().getText().isEmpty()) {
                view.displayMessage("Input a new price first");
                return;
            }

            String name = (String)view.getHotelComboBox().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            try {
                if (Double.parseDouble(view.getChangedPriceInput().getText()) < 100) {
                    view.displayMessage("Base price of hotel should be greater than 100");
                    return;
                }
                hotel.setRoomPrice(Double.parseDouble(view.getChangedPriceInput().getText()));
                view.displayMessage("Hotel " + hotel.getHotelName() + " base price is now " + view.getChangedPriceInput());
                view.clearManageHotelForm();
            }
            catch (NumberFormatException ex) {
                view.displayMessage("Please enter valid number for price");
                System.out.print(ex);
            }
        }
    }

    /**
     * Handles the action of the "Delete Hotel" button.
     */
    class BtnDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getHotelComboBox().getSelectedItem() == null) {
                view.displayMessage("Choose a hotel first");
                return;
            }

            String name = (String)view.getHotelComboBox().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            if (hotel.getHotelReservations().size() != 0) {
                view.displayMessage("There are still reservations!\nHotel cannot be deleted");
                return;
            }
            model.removeHotel(name);
            JOptionPane.showMessageDialog(null, "Hotel " + name + " successfully deleted!");
            view.clearManageHotelForm();
        }
    }

    /**
     * Handles the action of the "Change Date Price" button.
     */
    class BtnDatePriceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getHotelComboBox().getSelectedItem() == null) {
                view.displayMessage("Choose a hotel first");
                return;
            }

            if (view.getDatesBox().getSelectedItem() == null) {
                view.displayMessage("Choose a date first");
                return;
            }

            if (view.getDatesPercentBox().getSelectedItem() == null) {
                view.displayMessage("Choose a percentage first");
                return;
            }

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
            view.clearManageHotelForm();
        }
    }

    /**
     * Handles the action of the "Remove Reservation" button.
     */
    class BtnRemoveReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getHotelComboBox().getSelectedItem() == null) {
                view.displayMessage("Choose a hotel first");
                return;
            }
            if (view.getHotelComboBox().getSelectedItem() == null) {
                view.displayMessage("Choose reservation first");
                return;
            }

            String name = (String) view.getHotelComboBox().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            hotel.removeReservation((String) view.getReservationsBox().getSelectedItem());
            view.displayMessage("Reservation for " + (String) view.getReservationsBox().getSelectedItem() + "successfully removed");
            view.clearManageHotelForm();
        }
    }

    /**
     * Handles the action of the "Remove All Reservation" button.
     */
    class BtnRemoveAllReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getHotelComboBox().getSelectedItem() == null) {
                view.displayMessage("Choose a hotel first");
                return;
            }

            String name = (String)view.getHotelComboBox().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            hotel.clearReservations();
            view.displayMessage("All reservations for " + name + " have been removed");
            view.clearManageHotelForm();
        }
    }

    /**
     * Handles the action of the "Simulate Booking" button.
     */
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

            view.removeListeners(view.getBtnBookListener());
            view.populateRoomTypeBox(new PopulateRoomTypeBox());
            view.populateCheckInDateBox(new PopulateCheckInDateBox());
            view.populateCheckOutDateBox(new PopulateCheckOutDateBox());
            view.addBtnBookListener(new BtnBookListener());
            view.clearViewHotelForm();
        }
    }

    /**
     * Populate the roomType box
     */
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

    /**
     * Populate the checkInDate box
     */
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

    /**
     * Populate the checkOutDate box
     */
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

    /**
     * Listener for the book button
     */
    class BtnBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = (String) view.getHotelComboBox2().getSelectedItem();
            Hotel hotel = model.getHotel(name);
            String customerName = view.getCustomerNameFieldInput().getText();
            int checkInDate = Integer.parseInt((String) view.getCheckInDateBox().getSelectedItem());
            int checkOutDate = Integer.parseInt((String) view.getCheckOutDateBox().getSelectedItem());
            String roomType = (String) view.getRoomTypeBox().getSelectedItem();
            String couponCode = view.getCouponCodeFieldInput().getText();

            if (name.isEmpty()) {
                view.displayMessage("Choose a hotel first");
                return;
            }
            if (customerName.isEmpty()) {
                view.displayMessage("Input a customer name first");
                return;
            }
            if (view.getCheckInDateBox().getSelectedItem() == null) {
                view.displayMessage("Choose a check-in date first");
                return;
            }
            if (view.getCheckOutDateBox().getSelectedItem() == null) {
                view.displayMessage("Choose a check-in date first");
                return;
            }
            if (view.getCouponCodeFieldInput().getText().isEmpty()) {
                couponCode = "0";
            }

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
            view.clearSimulateBookingForm();
        }
    }

}