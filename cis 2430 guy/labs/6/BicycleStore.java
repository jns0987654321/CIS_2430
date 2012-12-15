
import java.io.*;
import java.util.*;
import bicycle.*;

/**
 *
 * @author Zeeshan Qureshi
 */
public class BicycleStore {

  String ordersFile = "orders.csv";
  HashMap<String, ArrayList<Bicycle>> ordersList = new HashMap<String, ArrayList<Bicycle>>();

  public boolean loadCatalouge() {
    Scanner input = new Scanner(System.in);

    try {
      input = new Scanner(new FileInputStream(System.getProperty("java.class.path").substring(0, System.getProperty("java.class.path").lastIndexOf(System.getProperty("file.separator")) + 1) + ordersFile));
    } catch (FileNotFoundException e) {
      System.out.println("Could not open the file " + System.getProperty("java.class.path").substring(0, System.getProperty("java.class.path").lastIndexOf(System.getProperty("file.separator")) +1)  + ordersFile + " for Reading");
      return false;
    }

    String[] bikeDetails;
    /* Initialize to keep compiler happy */
    Bicycle newBike = new MountainBike(0, "", 0);

    while (input.hasNextLine()) {
      bikeDetails = input.nextLine().split(",");

      switch (Integer.parseInt(bikeDetails[4])) {
        case 0:
          newBike = new MountainBike(Integer.parseInt(bikeDetails[2]), bikeDetails[1], Double.parseDouble(bikeDetails[3]));
        case 1:
          newBike = new CruiserBike(Integer.parseInt(bikeDetails[2]), bikeDetails[1], Double.parseDouble(bikeDetails[3]));
        case 2:
          newBike = new RacingBike(Integer.parseInt(bikeDetails[2]), bikeDetails[1], Double.parseDouble(bikeDetails[3]));
        case 3:
          newBike = new TouringBike(Integer.parseInt(bikeDetails[2]), bikeDetails[1], Double.parseDouble(bikeDetails[3]));
      }

      if (ordersList.containsKey(bikeDetails[0])) {
        ordersList.get(bikeDetails[0]).add(newBike);
      } else {
        ordersList.put(bikeDetails[0], new ArrayList<Bicycle>());
        ordersList.get(bikeDetails[0]).add(newBike);
      }
    }

    return true;
  }

  public static void main(String[] args) {
    BicycleStore myStore = new BicycleStore();
    /* Initialize scanner to keep compiler happy */
    Scanner input = new Scanner(System.in);

    myStore.loadCatalouge();

    input = new Scanner(System.in);
    String clientName = "";

    System.out.println("Welcome to the bike store!!");
    do {
      System.out.print("\nEnter client name: ");
      clientName = input.nextLine().trim();

      if (myStore.ordersList.containsKey(clientName)) {
        System.out.println("Printing catalouge for: " + clientName);
        for (Bicycle e : myStore.ordersList.get(clientName)) {
          System.out.println(e);
        }
      } else {
        System.out.println("No orders from " + clientName + "!!");
      }
    } while (!clientName.equals(""));
  }
}
