public class DeluxeRoom extends Room {

  private double price;

  public DeluxeRoom(int number, double price) {
    super(number, price);
    this.price = price * 1.25;
  }

  public double getPrice() {
    return this.price;
  }

  public String getRoomType() {
    return "Deluxe";
  }
  
  public void setPrice(double price) {
    this.price = price;
  }
}