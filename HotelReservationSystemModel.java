import java.util.ArrayList;

public class HotelReservationSystemModel {

    private ArrayList<Hotel> hotels;

    public HotelReservationSystemModel() {
        this.hotels = new ArrayList<>();
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public void createHotel(String hotelName, double basePrice, int numOfStandardRooms,
                            int numOfDeluxeRooms, int numOfExecutiveRooms) {
        Hotel hotel = new Hotel(hotelName, basePrice, numOfStandardRooms, numOfDeluxeRooms,
                numOfExecutiveRooms);
        hotels.add(hotel);
    }

    public boolean isHotelNameUnique(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelName().equalsIgnoreCase(hotelName)) {
                return false;
            }
        }
        return true;
    }

    public Hotel getHotel(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelName().equalsIgnoreCase(hotelName)) {
                return hotel;
            }
        }
        return null;
    }

    public void removeHotel(String hotelName) {
        hotels.removeIf(hotel -> hotel.getHotelName().equalsIgnoreCase(hotelName));
    }

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