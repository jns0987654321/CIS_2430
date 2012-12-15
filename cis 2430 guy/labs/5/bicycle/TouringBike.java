package bicycle;

public class TouringBike extends Bicycle{
  private double price;

  public TouringBike(int _speed, String _make, double _price){
    super(_speed, _make);
    this.price = _price;
  }

  public double getPrice(){
    return this.price;
  }

  public void setPrice(double _price){
    this.price = _price;
  }
}