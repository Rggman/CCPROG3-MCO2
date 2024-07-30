/**
 * This class represents a standard room in a hotel, with details such as room number
 * and price. It extends the Room class and includes additional functionality
 * specific to standard rooms.
 */
public class StandardRoom extends Room {

  private double price;

  /**
   * Constructs a Standard Room object with the specified room number and price.
   *
   * @param number The number of the room.
   * @param price The price of the room per night.
   */
  public StandardRoom(int number, double price) {
    super(number, price);
    this.price = price;
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
   * Returns the type of the room, which is "Standard".
   *
   * @return A string indicating the type of the room.
   */
  public String getRoomType() {
    return "Standard";
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