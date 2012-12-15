import java.util.*;
import Bicycle.*;

class BicycleStore {

  private static ArrayList<Bicycle> bikeList = new ArrayList<Bicycle>();

  public static void main(String[] args) {
    Scanner kbInput = new Scanner(System.in);

    String make, choice;
    int speed, type;
    double price;

    Bicycle toAdd = new MountainBike(0, " ", 0);

    do {
      System.out.print("Enter Make: ");
      make = kbInput.nextLine();

      System.out.print("Enter Speed: ");
      speed = kbInput.nextInt();
      kbInput.nextLine();

      System.out.print("Enter Price: ");
      price = kbInput.nextDouble();
      kbInput.nextLine();

      System.out.print("Enter type (Mountain = 0, Cruiser = 1, Racing = 2, Touring = 3): ");
      type = kbInput.nextInt();
      kbInput.nextLine();

      switch(type){
        case 0:
          toAdd = new MountainBike(speed, make, price);
          break;

        case 1:
          toAdd = new CruiserBike(speed, make, price);
          break;

        case 2:
          toAdd = new RacingBike(speed, make, price);
          break;

        case 3:
          toAdd = new TouringBike(speed, make, price);
          break;
      }

      if(!bikeList.contains(toAdd))
        bikeList.add(toAdd);
      else
        System.out.println("Bicycle not Unique");

      System.out.print("Add More(y or n): ");
      choice = kbInput.nextLine();

      if (choice.equals("n")) {
        break;
      }
    } while (true);

    System.out.println("Listing All Bikes:: ");
    for (int i = 0; i < bikeList.size(); i++) {
      System.out.println(bikeList.get(i));
    }
  }
}