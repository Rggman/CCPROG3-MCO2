import java.util.ArrayList;

/**
 * The Hotel class represents a hotel with rooms and reservations.
 */
public class Hotel {

    private final int MAX_ROOMS; // Maximum number of rooms this hotel can accommodate

    private String hotelName; // Name of the hotel
    private ArrayList<Room> rooms; // List of rooms in the hotel
    private int numOfRooms; // Current number of rooms in the hotel
    private ArrayList<CustomerReservation> reservations; // List of reservations made in the hotel
    private float[] percent;

    /**
     * Constructs a new Hotel object with the specified name, number of rooms, and
     * base price.
     * Initializes rooms and reservations.
     *
     * @param name       The name of the hotel.
     * @param basePrice  The base price for each room.
     */
    public Hotel(String name, double basePrice, int numOfStandardRooms, int numOfDeluxeRooms, int numOfExecutiveRooms) {
        this.MAX_ROOMS = 50;
        this.hotelName = name;
        this.numOfRooms = numOfStandardRooms + numOfDeluxeRooms + numOfExecutiveRooms;
        this.rooms = new ArrayList<>();
        this.percent = new float[31];

        int roomNumber = 1;

        for (int i = 0; i < numOfStandardRooms; i++) {
            this.rooms.add(new StandardRoom(roomNumber++, basePrice));
        }

        for (int i = 0; i < numOfDeluxeRooms; i++) {
            this.rooms.add(new DeluxeRoom(roomNumber++, basePrice));
        }

        for (int i = 0; i < numOfExecutiveRooms; i++) {
            this.rooms.add(new ExecutiveRoom(roomNumber++, basePrice));
        }

        this.reservations = new ArrayList<>();

        for (int i = 0; i < 31; i++)
            percent[i] = 1.00f;
    }


    /**
     * Gets the name of the hotel.
     *
     * @return The name of the hotel.
     */
    public String getHotelName() {
        return this.hotelName;
    }

    /**
     * Gets the list of rooms in the hotel.
     *
     * @return The list of rooms.
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Gets the current number of rooms in the hotel.
     *
     * @return The number of rooms in the hotel.
     */
    public int getHotelNumOfRooms() {
        return numOfRooms;
    }

    /**
     * Gets the list of reservations made in the hotel.
     *
     * @return The list of reservations.
     */
    public ArrayList<CustomerReservation> getHotelReservations() {
        return reservations;
    }

    public CustomerReservation getHotelReservation(String name) {
        for (CustomerReservation r : reservations) {
            if (r.getCustomerName().equals(name))
                return r;
        }
        return null;
    }

    /**
     * Counts the number of currently unreserved rooms in the hotel.
     *
     * @return Number of unreserved rooms.
     */
    public int getNumOfUnreservedRooms() {
        int count = 0;
        for (Room room : rooms) {
            if (room.getDaysReserved() == 0)
                count++;
        }
        return count;
    }

    public int getNumOfStandardRooms () {
        int count = 0;
        for (Room room : rooms) {
            if (room.getRoomType().equals("Standard"))
                count++;
        }
        return count;
    }

    public int getNumOfDeluxeRooms () {
        int count = 0;
        for (Room room : rooms) {
            if (room.getRoomType().equals("Deluxe"))
                count++;
        }
        return count;
    }

    public int getNumOfExecutiveRooms () {
        int count = 0;
        for (Room room : rooms) {
            if (room.getRoomType().equals("Executive"))
                count++;
        }
        return count;
    }

    /**
     * Calculates the estimated earnings from all reservations in the hotel.
     *
     * @return Estimated earnings based on reservations made.
     */
    public double getEstimatedEarnings() {
        double totalEarnings = 0;
        for (CustomerReservation reservation : reservations) {
            totalEarnings += reservation.getTotalPrice();
        }
        return totalEarnings;
    }

    public float getPercent(int date) {
        return percent[date - 1];
    }

    /**
     * Sets the name of the hotel.
     *
     * @param hotelName The new name of the hotel.
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setRoomPrice(double basePrice) {
        for (Room room : rooms) {
            room.setPrice(basePrice);
        }
    }

    public void setPercent(int date, float newPercent) {
        percent[date - 1] = newPercent;
    }

    /**
     * Adds a specified number of rooms to the hotel.
     *
     * @param howMany Number of rooms to add.
     */
    public void addRooms(int howMany, String type) {
        for (int i = numOfRooms + 1; i <= numOfRooms + howMany; i++) {
            if (type.equals("Standard"))
                rooms.add(new StandardRoom(i, rooms.getFirst().getBasePrice()));
            else if (type.equals("Deluxe"))
                rooms.add(new DeluxeRoom(i, rooms.getFirst().getBasePrice()));
            else
                rooms.add(new ExecutiveRoom(i, rooms.getFirst().getBasePrice()));
        }
        numOfRooms += howMany;
    }

    /**
     * Removes a specified number of rooms from the hotel if they are unreserved.
     *
     * @param howMany Number of rooms to remove.
     */
    public void removeRooms(int howMany, String type) {
        for (int i = 0; i < rooms.size(); i++){
            if (rooms.get(i).getRoomType() == type) {
                rooms.remove(i);
            }
        }

        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).setNumber(i + 1);
            numOfRooms = i;
        }
    }

    /**
     * Clears all reservations in the hotel.
     */
    public void clearReservations() {
        reservations.clear();
        for (Room room : rooms) {
            room.setIsReserved(1, 31, false);
        }
    }

    public void removeReservation(String name) {
        for (int i = 0; i < reservations.size(); i++) {
            if (name.equals(reservations.get(i).getCustomerName())) {
                reservations.get(i).getRoomInfo().setIsReserved(reservations.get(i).getCheckInDate(), reservations.get(i).getCheckOutDate(),false);
                reservations.remove(i);
            }
        }
    }

    /**
     * Counts the number of reservations for a specific date across all rooms.
     *
     * @param selectDate The date to count reservations for.
     * @return Number of reservations for the specified date.
     */
    public int countReservations(int selectDate) {
        int count = 0;
        for (Room room : rooms) {
            if (room.getIsReserved(selectDate)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of available rooms for a specific date across all rooms.
     *
     * @param selectDate The date to count available rooms for.
     * @return Number of available rooms for the specified date.
     */
    public int countAvailableRooms(int selectDate) {
        return numOfRooms - countReservations(selectDate);
    }

    /**
     * Finds an available room for a specified check-in and check-out date range.
     *
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return A Room object if an available room is found; otherwise, null.
     */
    public Room findAvailableRoom(int checkInDate, int checkOutDate, String roomType) {
        for (Room room : rooms) {
            boolean isAvailable = true;
            for (int i = checkInDate; i < checkOutDate; i++) {
                if (room.getIsReserved(i)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable && room.getRoomType() == roomType) {
                return room;
            }
        }
        return null;
    }
}