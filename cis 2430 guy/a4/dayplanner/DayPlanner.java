package dayplanner;

import java.util.*;
import java.text.*;
import java.io.*;

/**
 * The main class that implements the Day Planner Functionality
 *
 * @author Zeeshan Qureshi
 */
public class DayPlanner {

  /* Three ArrayLists to Store Activities */
  static ArrayList<Activity> activityList = new ArrayList<Activity>();

  /* HashMap to Keyword Indices */
  static HashMap<String, ArrayList<Integer>> searchIndex = new HashMap<String, ArrayList<Integer>>();

  /* ArrayList to Store Search Result */
  static ArrayList<Activity> searchResult = new ArrayList<Activity>();

  /* Scanner to Scan Keyboard Input */
  static Scanner kbInput = new Scanner(System.in);
  /* Date Formatter to Parse Date Input from the Console */
  static SimpleDateFormat dateParser = new SimpleDateFormat("yyyy/MM/dd HH:mm");
  /*PrintWriter to Write to Output File */
  static PrintWriter fileOutput = null;
  /* Scanner to Read from Input File */
  static Scanner fileInput = null;

  /**
   * Return the name of the class of the passed activity
   * 
   * @param someActivity The Activity Who's Class is To Be Returned
   * @return The Name of the Class as A String
   */
  public static String mapClassToString(Activity someActivity) {
    if (someActivity instanceof HomeActivity) {
      return "Home";
    }
    if (someActivity instanceof SchoolActivity) {
      return "School";
    }
    if (someActivity instanceof OtherActivity) {
      return "Other";
    }

    return "Error!!";
  }

  /**
   * Searches for the position where the provided should be added
   * in the ordered list of Activities
   * 
   * @param newActivity
   * @return position where the Activity is to be added
   */
  public static int searchPosition(Activity newActivity) {
    int i = 0;
    boolean noClash = true;

    if ((activityList.size() != 0) && (newActivity.getStartTime().equals(activityList.get(0).getStartTime()))) {
      noClash = false;
    }

    while ((i < activityList.size()) && (newActivity.getStartTime().after(activityList.get(i).getStartTime()))) {
      if (((i + 1) < activityList.size()) && newActivity.getStartTime().equals(activityList.get(i + 1).getStartTime())) {
        noClash = false;
      }
      i++;
    }

    if (noClash) {
      return i;
    } else {
      return -1;
    }
  }

  /**
   * Update the Search index with the Specified Keywords which appear at the
   * position specified
   *
   * @param keywords The keywords to be Added
   * @param pos The position these keywords appear at
   */
  static void updateIndex(String[] keywords, int pos) {
    int i;

    /* Increment All Values >= pos by 1 */
    for (ArrayList<Integer> value : searchIndex.values()) {
      for (i = 0; i < value.size(); i++) {
        if (value.get(i) >= pos) {
          value.set(i, value.get(i) + 1);
        }
      }
    }

    /* Iterate through the keywords and add to HashMap */
    for (String k : keywords) {
      if (!searchIndex.containsKey(k)) {
        searchIndex.put(k, new ArrayList<Integer>());
      }

      searchIndex.get(k).add(pos);
    }
  }

  static void readFile(Scanner fileInput) {
    /* Variables to Store Comand-Line Input */
    String title, location, comment;
    /* To Store The Parsed Date from the Command-Line Input */
    Date startTime, endTime;
    char type;
    int pos;

    Activity newActivity = new HomeActivity();

    while (fileInput.hasNextLine()) {
      SimpleDateFormat fileDateParser = new SimpleDateFormat("yyyy, MM, dd, HH, mm");
      String[] line = fileInput.nextLine().split("[=\"]+");
      if (line[0].trim().equalsIgnoreCase("type")) {
        type = line[2].toLowerCase().trim().charAt(0);
        title = location = fileInput.nextLine().split("[=\"]+")[2].trim();

        try {
          startTime = fileDateParser.parse(fileInput.nextLine().split("[=\"]+")[2].trim());
        } catch (java.text.ParseException e) {
          System.out.println("Invalid Date in File!!");
          continue;
        }

        try {
          endTime = fileDateParser.parse(fileInput.nextLine().split("[=\"]+")[2].trim());
        } catch (java.text.ParseException e) {
          System.out.println("Invalid Date in File!!");
          continue;
        }

        if (endTime.before(startTime)) {
          System.out.println("End Time Cannot be Before Start Time!!\nSkipping Activity : " + title);
          continue;
        }

        if (type == 'o') {
          location = fileInput.nextLine().split("[=\"]+")[2].trim();
        }

        comment = fileInput.nextLine().split("[=\"]+")[2].trim();

        switch (type) {
          case 'h':
            newActivity = new HomeActivity(title, startTime, endTime, comment);

            break;

          case 's':
            newActivity = new SchoolActivity(title, startTime, endTime, comment);

            break;

          case 'o':
            newActivity = new OtherActivity(title, startTime, endTime, comment, location);

            break;

          default:
            System.out.println("Invalid Input Format in File!!!");
            continue;
        }

        pos = searchPosition(newActivity);
        if (pos == -1) {
          System.out.println("\nStarting Time Clashes!! Activity Not Added!!");
        } else {
          activityList.add(pos, newActivity);
          updateIndex(newActivity.getTitle().toLowerCase().split("[ ,\n]+"), pos);
        }
      }
    }
  }

  static void writeFile(PrintWriter fileOutput) {
    SimpleDateFormat fileDateFormatter = new SimpleDateFormat("yyyy, MM, dd, HH, mm");
    for (Activity e : activityList) {
      fileOutput.println("type = \"" + mapClassToString(e).toLowerCase() + "\"");
      fileOutput.println("title = \"" + e.getTitle() + "\"");
      fileOutput.println("start = \"" + fileDateFormatter.format(e.getStartTime()) + "\"");
      fileOutput.println("end = \"" + fileDateFormatter.format(e.getEndTime()) + "\"");
      if (e instanceof OtherActivity) {
        fileOutput.println("location = \"" + ((OtherActivity) e).getLocation() + "\"");
      }
      fileOutput.println("comment = \"" + e.getComment() + "\"");
      fileOutput.println();
    }

    fileOutput.flush();
  }

  static void performSearch(char type, String[] titleKeywords, Date startTime, Date endTime) {
    int i, j, pos;

    searchResult.clear();

    if ((titleKeywords.length > 0) && (!titleKeywords[0].trim().isEmpty())) {
      ArrayList<Integer> keywordSearch = new ArrayList<Integer>();
      if (searchIndex.containsKey(titleKeywords[0])) {
        keywordSearch.addAll(searchIndex.get(titleKeywords[0]));
      }

      for (String keyword : titleKeywords) {
        if (searchIndex.containsKey(keyword)) {
          keywordSearch.retainAll(searchIndex.get(keyword));
        } else {
          keywordSearch.clear();
          break;
        }
      }

      for (int index : keywordSearch) {
        searchResult.add(activityList.get(index));
      }
    } else {
      searchResult.addAll(activityList);
    }

    if (type != 'n') {
      switch (type) {
        case 'h':
          i = 0;
          while (i < searchResult.size()) {
            if (searchResult.get(i) instanceof HomeActivity) {
              i++;
            } else {
              searchResult.remove(i);
            }
          }
          break;

        case 's':
          i = 0;
          while (i < searchResult.size()) {
            if (searchResult.get(i) instanceof SchoolActivity) {
              i++;
            } else {
              searchResult.remove(i);
            }
          }
          break;

        case 'o':
          i = 0;
          while (i < searchResult.size()) {
            if (searchResult.get(i) instanceof OtherActivity) {
              i++;
            } else {
              searchResult.remove(i);
            }
          }
          break;
      }
    }

    if ((startTime != null) && (endTime != null)) {
      i = 0;
      while (i < searchResult.size()) {
        if ((startTime.before(searchResult.get(i).getStartTime()) || startTime.equals(searchResult.get(i).getStartTime())) && (endTime.after(searchResult.get(i).getEndTime()) || endTime.equals(searchResult.get(i).getEndTime()))) {
          i++;
        } else {
          searchResult.remove(i);
        }
      }
    }
  }

  public static void main(String[] args) {
    /* Variables to Store Comand-Line Input */
    String title, location, comment, sTime, eTime, temp;
    /* String to Store Keywords in the Title */
    String[] titleKeywords = {};
    /* To Store The Parsed Date from the Command-Line Input */
    Date startTime, endTime;
    char choice = 'y', type;

    int i, j, pos;

    /* To keep compiler happy */
    Activity newActivity = null;

    /* Parse the command-line arguments */
    for (i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-i")) {
        System.out.println("Input From File: " + args[i + 1]);
        try {
          fileInput = new Scanner(new FileInputStream(args[++i]));
        } catch (FileNotFoundException e) {
          System.out.println("Error Opening File For Input!!");
          System.exit(0);
        }
      } else if (args[i].equalsIgnoreCase("-o")) {
        System.out.println("Output To File: " + args[i + 1]);
        try {
          fileOutput = new PrintWriter(new FileOutputStream(args[++i]));
        } catch (FileNotFoundException e) {
          System.out.println("Error Opening File For Output!!");
          System.exit(0);
        }
      }
    }

    /* Parse the Activities from the file and add to the List */
    if (fileInput != null) {
      readFile(fileInput);
      fileInput.close();
    }

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
          System.out.print("Enter Activity Type (Home = h, School = s, Other = o): ");
          try {
            type = kbInput.nextLine().toLowerCase().charAt(0);
          } catch (StringIndexOutOfBoundsException e) {
            System.out.println("You Need to Enter Something!!");
            continue;
          }

          System.out.print("Enter Title: ");
          title = kbInput.nextLine();
          System.out.print("Enter Start Time in 24 Hour Format(yyyy/mm/dd hh:mm): ");
          try {
            startTime = dateParser.parse(kbInput.nextLine());
          } catch (java.text.ParseException e) {
            System.out.println("Invalid Date!!");
            break;
          }
          System.out.print("Enter End Time in 24 Hour Format(yyyy/mm/dd hh:mm): ");
          try {
            endTime = dateParser.parse(kbInput.nextLine());
          } catch (java.text.ParseException e) {
            System.out.println("Invalid Date!!");
            break;
          }

          if (endTime.before(startTime)) {
            System.out.println("End Time Cannot be Before Start Time!!");
            break;
          }

          System.out.print(("Enter Comment: "));
          comment = kbInput.nextLine();

          switch (type) {
            case 'h':
              newActivity = new HomeActivity(title, startTime, endTime, comment);

              break;

            case 's':
              newActivity = new SchoolActivity(title, startTime, endTime, comment);

              break;

            case 'o':
              System.out.print("Enter Location: ");
              location = kbInput.nextLine();

              newActivity = new OtherActivity(title, startTime, endTime, comment, location);

              break;

            default:
              System.out.println("Wrong Choice!! Enter Again");
          }

          pos = searchPosition(newActivity);
          if (pos == -1) {
            System.out.println("\nStarting Time Clashes!! Activity Not Added!!");
          } else {
            activityList.add(pos, newActivity);
            updateIndex(newActivity.getTitle().toLowerCase().split("[ ,\n]+"), pos);
          }

          break;

        case 's':
          System.out.print("Enter Title Keywords: ");
          titleKeywords = kbInput.nextLine().toLowerCase().split("[ ,\n]+");

          System.out.print("Enter Start Time: ");
          startTime = null;
          sTime = kbInput.nextLine();
          if (sTime.length() > 0) {
            try {
              startTime = dateParser.parse(sTime);
            } catch (java.text.ParseException e) {
              System.out.println("Invalid Date!!");
              break;
            }
          }

          System.out.print("Enter End Time: ");
          endTime = null;
          eTime = kbInput.nextLine();
          if (eTime.length() > 0) {
            try {
              endTime = dateParser.parse(eTime);
            } catch (java.text.ParseException e) {
              System.out.println("Invalid Date!!");
              break;
            }
          }

          System.out.print("Enter Activity Type (Home = h, School = s, Other = o): ");
          temp = kbInput.nextLine();
          if (temp.length() > 0) {
            type = temp.toLowerCase().charAt(0);
          } else {
            type = 'n';
          }

          performSearch(type, titleKeywords, startTime, endTime);

          System.out.println("\nSearch Result: \n");
          for (Activity result : searchResult) {
            System.out.println(result + "Type: " + mapClassToString(result) + " Activity\n");
          }

          break;

        case 'l':
          System.out.println("\nActivities :\n");
          for (Activity activity : activityList) {
            System.out.println(activity + "Type: " + mapClassToString(activity) + " Activity\n");
          }

          break;

        case 'q':
          if (fileOutput != null) {
            System.out.println("Outputting To File...");
            writeFile(fileOutput);
            fileOutput.close();
          }
          break;

        case 'i':
          System.out.println("Search Index: ");
          for (Map.Entry<String, ArrayList<Integer>> entry : searchIndex.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
          }
          break;

        default:
          System.out.println("Wrong Choice!! Enter Again");
      }
    } while (choice != 'q');
  }
}
