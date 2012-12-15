package bicycle;

/**
 * @author adnan
 *
 */
public abstract class Bicycle {

  //instance variables
  private int speed;
  private String make;

  //constructor
  public Bicycle(int _speed, String _make) {
    this.speed = _speed;
    this.make = _make;
  }

  /**
   *
   * Set the make of the bicycle
   *
   * @param _speed An int signifying the new speed
   */
  public void setSpeed(int _speed) {
    this.speed = _speed;
  }

  /**
   *
   * Set the make of the bicycle
   *
   * @param _make A string signifying the make of the bike
   */
  public void setMake(String _make) {
    this.make = _make;
  }

  /**
   *
   * Get the make of the bicycle
   *
   * @return speed The current speed int.
   */
  public int getSpeed() {
    return this.speed;
  }

  /**
   *
   * Get the make of the bicycle
   *
   * @return make The current make string
   */
  public String getMake() {
    return this.make;
  }

  /**
   *
   * Get the price of the bicycle
   *
   * @return price The current double price
   */
  public abstract double getPrice();

  /**
   *
   * Set the price of the bicycle
   *
   * @param _price The new double price
   */
  public abstract void setPrice(double _price);

  /**
   *
   * The equals method. Compares speed, make and price
   *
   * @return isEqual True if Bicycles are equal
   */
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }
    if (this.getClass() != otherObject.getClass()) {
      return false;
    }

    Bicycle other = (Bicycle) otherObject;
    boolean isEqual = false;

    if (this.make.equals(other.make) && this.speed == other.speed && this.getPrice() == other.getPrice()) {
      isEqual = true;
    }

    return isEqual;

  }

  /**
   *
   * The toString method
   *
   * @return returnStr The String representation of the Bicycle
   */
  public String toString() {
    String returnStr = "";

    returnStr = "Bicycle: make: " + this.make + ", speed: " + this.speed + ", price = " + this.getPrice();

    return returnStr;
  }
}
