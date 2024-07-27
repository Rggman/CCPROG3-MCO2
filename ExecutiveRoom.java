public class ExecutiveRoom extends Room {

  private double price;

  public ExecutiveRoom(int number, double price) {
    super(number, price);
    this.price = price * 1.35;
  }

  public double getPrice() {
    return this.price;
  }
  
  public String getRoomType() {
    return "Executive";
  }

  public void setPrice(double price) {
    this.price = price;
  }
}