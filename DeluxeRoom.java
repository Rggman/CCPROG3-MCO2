/**
 * This class represents a deluxe room in a hotel, with details such as room number
 * and price. It extends the Room class and includes additional functionality specific
 * to deluxe rooms.
 */
public class DeluxeRoom extends Room {

  private double price;
  /**
   * Constructs a DeluxeRoom object with the specified room number and price.
   * The price is adjusted by multiplying the given price by 1.25.
   *
   * @param number The number of the room.
   * @param price The base price of the room per night.
   */
  public DeluxeRoom(int number, double price) {
    super(number, price);
    this.price = price * 1.25;
  }


  /**
   * Returns the price of the room.
   *
   * @return The price of the room.
   */
  public double getPrice() {
    return this.price;
  }

  /**
   * Returns the type of the room, which is "Deluxe".
   *
   * @return A string indicating the type of the room.
   */
  public String getRoomType() {
    return "Deluxe";
  }

  /**
   * Sets the price of the room.
   *
   * @param price The new price of the room.
   */
  public void setPrice(double price) {
    this.price = price;
  }
}