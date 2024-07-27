public class StandardRoom extends Room {

  private double price;

  public StandardRoom(int number, double price) {
    super(number, price);
    this.price = price;
  }

  public double getPrice() {
    return this.price;
  }
  
  public String getRoomType() {
    return "Standard";
  }

  public void setPrice(double price) {
    this.price = price;
  }
}