import java.util.ArrayList;

public class HotelReservationSystemModel {

    private ArrayList<Hotel> hotels;

    public HotelReservationSystemModel() {
        this.hotels = new ArrayList<>();
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
}
