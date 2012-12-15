package Bicycle;

public class RacingBike extends Bicycle{
  private double price;

  public RacingBike(int _speed, String _make, double _price){
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