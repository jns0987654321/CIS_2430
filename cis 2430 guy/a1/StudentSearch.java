/* Import The Required Classes For Use */
import java.util.*;

/**
 * Implements a simple student record database that allows the user to <b>add</b>,
 * <b>list</b> and <b>search</b> the records.
 *
 * Since the Records are stored in an ArrayList there are no limitations on the
 * number of elements that can be added as long as there is enough memory.
 *
 * @author Zeeshan Qureshi
 */
public class StudentSearch {
  /* Stores all the student records */
  static ArrayList<String> Students = new ArrayList<String>();

  /**
   * Checks whether the provided name is valid. A name is considered valid
   * if it atleast has a first and last name with optional middle names
   *
   * @param name The name to be balidated
   * @return True if the name is a valid one.
   */
  public static boolean isValidName(String name) {
    if (name.split(" ").length < 2) {
      return false;
    }

    return true;
  }

  /**
   * Creates a search key from the provided record.
   * The key is formulated by concatenating the first name
   * to the last name and then converting everything to lowercase
   *
   * @param studentRecord The record to create the key from
   * @return The created key
   */
  public static String createKey(String studentRecord) {
    /*
     * Extract the name from the record by first splitting by ',', get
     * taking the first element and then splitting by ' ' to
     * get individual components of the name
     */
    String[] name = studentRecord.split(",")[0].split(" ");

    /* Concatenate the first name and last name and convert to lowercase*/
    return name[0].toLowerCase().concat(name[name.length - 1].toLowerCase());
  }

  public static void main(String[] args) {
    Scanner kbInput = new Scanner(System.in);
    String input;
    char choice = 'm';

    do {
      System.out.println("\nOptions:");
      System.out.println("  a: Add");
      System.out.println("  s: Search");
      System.out.println("  l: List");
      System.out.println("  q: Quit");
      System.out.print("\nEnter Choice: ");

      /* Take the input from the user and process any exceptions */
      try {
        choice = kbInput.nextLine().toLowerCase().charAt(0);
      } catch (StringIndexOutOfBoundsException e) {
        System.out.println("You Need to Enter Something!!");
        continue;
      }

      switch (choice) {
        case 'a':
          System.out.print("Enter a comma separated list of fields with the name being the first one\n : ");
          input = kbInput.nextLine();
          /* If the entered name is not valid then don't add the record */
          if (!isValidName(input.split(",")[0])) {
            System.out.println("ERROR!! Student Should Atleast Have First and Last Name");
          } else {
            Students.add(input);
          }
          break;

        case 'l':
          if(Students.size() == 0){
            System.err.println("\nDatabase is empty!!\n");
            break;
          }
          
          System.out.println("\nListing all students:\n");
          for (int i = 0; i < Students.size(); i++) {
            System.out.println(Students.get(i));
          }
          break;

        case 's':
          System.out.print("Enter name to search : ");
          input = kbInput.nextLine();
          /* If the entered name is not valid then don't search the database */
          if (!isValidName(input)) {
            System.out.println("ERROR!! Student Should Atleast Have First and Last Name");
            break;
          }

          /* Create the search key from the input */
          String searchKey = createKey(input);

          int count = 0;
          System.out.println("\nMatched Records:");
          for (int i = 0; i < Students.size(); i++) {
            if (searchKey.equals(createKey(Students.get(i)))) {
              count++;
              System.out.println(Students.get(i));
            }
          }

          /* If no records found then display a message */
          if (count == 0) {
            System.out.println("Sorry!! No Records Matched.");
          }
          break;

        case 'q':
          break;

        default:
          System.out.println("Wrong Choice!! Enter Again");
      }
    } while (choice != 'q');
  }
}
