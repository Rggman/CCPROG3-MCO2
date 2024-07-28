import java.util.ArrayList;

/**
 * This class represents a reservation made by a customer for a specific room,
 * with details such as customer name, check-in and check-out dates, room
 * information,
 * and total price calculated based on the duration of stay and room price.
 */
public class CustomerReservation {

    private Hotel hotel;
    private String customerName;
    private int checkInDate;
    private int checkOutDate;
    private Room roomInfo;
    private double totalPrice;
    private String couponCode;
    private float[] datePercent;

    /**
     * Constructs a CustomerReservation object with the provided details.
     *
     * @param customerName The name of the customer making the reservation.
     * @param checkInDate  The date when the customer will check into the room (1 to
     *                     31).
     * @param checkOutDate The date when the customer will check out from the room
     *                     (1 to 31).
     * @param roomInfo     The Room object containing details of the reserved room.
     */
    public CustomerReservation(String customerName, int checkInDate, int checkOutDate, Room roomInfo,
            String couponCode, Hotel hotel) {
        this.customerName = customerName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomInfo = roomInfo;
        this.couponCode = couponCode;
        this.datePercent = new float[31];

        for (int i = 0; i < 31; i++) {
            this.datePercent[i] = hotel.getPercent(i + 1);
        }
    }

    public double calculateTotalPrice() {
        double calculatedTotalPrice = 0;
        for(int i = checkInDate; i < checkOutDate; i++) {
            calculatedTotalPrice += roomInfo.getPrice() * datePercent[i - 1];
        }

        switch (couponCode) {
            case "I_WORK_HERE" -> calculatedTotalPrice= calculatedTotalPrice * 0.9;
            case "STAY4_GET1" -> calculatedTotalPrice = calculatedTotalPrice - roomInfo.getPrice();
            case "PAYDAY" -> calculatedTotalPrice = calculatedTotalPrice * 0.93;
            case null, default -> calculatedTotalPrice = calculatedTotalPrice;
        }
        this.totalPrice = calculatedTotalPrice;
        return totalPrice;
    }

    /**
     * Retrieves the name of the customer who made the reservation.
     *
     * @return The name of the customer.
     */
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * Retrieves the check-in date of the reservation.
     *
     * @return The check-in date (1 to 31).
     */
    public int getCheckInDate() {
        return this.checkInDate;
    }

    /**
     * Retrieves the check-out date of the reservation.
     *
     * @return The check-out date (1 to 31).
     */
    public int getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Retrieves the Room object associated with this reservation.
     *
     * @return The Room object containing details of the reserved room.
     */
    public Room getRoomInfo() {
        return roomInfo;
    }

    public double getTotalPrice() {
        totalPrice = calculateTotalPrice();
        return totalPrice;
    }

    public void setDatePercent(int date, float percent) {
        this.datePercent[date - 1] = percent;
    }
}
