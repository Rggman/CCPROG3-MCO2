import java.util.ArrayList;

/**
 * The HotelReservationSystemModel class manages a collection of hotels, allowing
 * operations such as creating, retrieving, and removing hotels, as well as managing
 * reservations.
 */
public class HotelReservationSystemModel {

    private ArrayList<Hotel> hotels;


    /**
     * Constructs a HotelReservationSystemModel object with an empty list of hotels.
     */
    public HotelReservationSystemModel() {
        this.hotels = new ArrayList<>();
    }


    /**
     * Retrieves the list of hotels managed by the system.
     *
     * @return The list of hotels.
     */
    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    /**
     * Creates and adds a new hotel to the system.
     *
     * @param hotelName         The name of the hotel.
     * @param basePrice         The base price for each room in the hotel.
     * @param numOfStandardRooms The number of standard rooms in the hotel.
     * @param numOfDeluxeRooms   The number of deluxe rooms in the hotel.
     * @param numOfExecutiveRooms The number of executive rooms in the hotel.
     */
    public void createHotel(String hotelName, double basePrice, int numOfStandardRooms,
                            int numOfDeluxeRooms, int numOfExecutiveRooms) {
        Hotel hotel = new Hotel(hotelName, basePrice, numOfStandardRooms, numOfDeluxeRooms,
                numOfExecutiveRooms);
        hotels.add(hotel);
    }

    /**
     * Checks if a hotel name is unique within the system.
     *
     * @param hotelName The name of the hotel to check.
     * @return true if the hotel name is unique; false otherwise.
     */
    public boolean isHotelNameUnique(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelName().equalsIgnoreCase(hotelName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves a hotel by its name.
     *
     * @param hotelName The name of the hotel to retrieve.
     * @return The Hotel object if found; null otherwise.
     */
    public Hotel getHotel(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelName().equalsIgnoreCase(hotelName)) {
                return hotel;
            }
        }
        return null;
    }

    /**
     * Removes a hotel from the system by its name.
     *
     * @param hotelName The name of the hotel to remove.
     */
    public void removeHotel(String hotelName) {
        hotels.removeIf(hotel -> hotel.getHotelName().equalsIgnoreCase(hotelName));
    }

    /**
     * Adds a reservation to a specified hotel.
     *
     * @param hotelName     The name of the hotel where the reservation is to be made.
     * @param customerName  The name of the customer making the reservation.
     * @param checkInDate   The check-in date for the reservation.
     * @param checkOutDate  The check-out date for the reservation.
     * @param roomType      The type of room to reserve ("Standard", "Deluxe", "Executive").
     * @param couponCode    The coupon code to apply to the reservation (can be null).
     */
    public void addReservation(String hotelName, String customerName, int checkInDate, int checkOutDate, String roomType, String couponCode) {
        Hotel hotel = getHotel(hotelName);
        if (hotel != null) {
            Room availableRoom = hotel.findAvailableRoom(checkInDate, checkOutDate, roomType);
            if (availableRoom != null) {
                availableRoom.setIsReserved(checkInDate, checkOutDate, true);
                CustomerReservation reservation = new CustomerReservation(customerName, checkInDate, checkOutDate, availableRoom, couponCode , hotel);
                hotel.getHotelReservations().add(reservation);
            }
        }
    }
}