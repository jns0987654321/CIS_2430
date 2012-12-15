package dayplanner;

import java.util.*;
import java.text.*;

/**
 * The main class that implements the Day Planner Functionality
 *
 * @author Zeeshan Qureshi
 */
public class DayPlanner {

  /* Three ArrayLists to Store Activities */
  static ArrayList<HomeActivity> homeActivityList = new ArrayList<HomeActivity>();
  static ArrayList<SchoolActivity> schoolActivityList = new ArrayList<SchoolActivity>();
  static ArrayList<OtherActivity> otherActivityList = new ArrayList<OtherActivity>();

  /* ArrayList to Store Search Result */
  static ArrayList<Activity> searchResult = new ArrayList<Activity>();

  private static String mapClassToString(Activity someActivity) {
    HomeActivity homeActivity = new HomeActivity();
    SchoolActivity schoolActivity = new SchoolActivity();
    OtherActivity otherActivity = new OtherActivity();

    if (someActivity.getClass() == homeActivity.getClass()) {
      return "Home Activity";
    }
    if (someActivity.getClass() == schoolActivity.getClass()) {
      return "School Activity";
    }
    if (someActivity.getClass() == otherActivity.getClass()) {
      return "Other Activity";
    }

    return "Error!!";
  }

  public static void main(String[] args) {
    Scanner kbInput = new Scanner(System.in);
    SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    String title, location, comment, sTime, eTime, temp;
    String[] titleKeywords = {};
    Date startTime, endTime;
    char choice = 'y', type;

    int i, j;
    boolean noClash;

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

          switch (type) {
            case 'h':
              System.out.print("Enter Title: ");
              title = kbInput.nextLine();
              System.out.print("Enter Start Time in 24 Hour Format(dd/mm/yyyy hh:mm): ");
              try {
                startTime = dateParser.parse(kbInput.nextLine());
              } catch (java.text.ParseException e) {
                System.out.println("Invalid Date!!");
                break;
              }
              System.out.print("Enter End Time in 24 Hour Format(dd/mm/yyyy hh:mm): ");
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

              HomeActivity newHomeActivity = new HomeActivity(title, startTime, endTime, comment);
              i = 0;
              noClash = true;

              if ((homeActivityList.size() != 0) && (newHomeActivity.getStartTime().equals(homeActivityList.get(0).getStartTime()))) {
                noClash = false;
              }

              while ((i < homeActivityList.size()) && (newHomeActivity.getStartTime().after(homeActivityList.get(i).getStartTime()))) {
                if (((i + 1) < homeActivityList.size()) && newHomeActivity.getStartTime().equals(homeActivityList.get(i + 1).getStartTime())) {
                  noClash = false;
                }
                i++;
              }

              if (noClash) {
                homeActivityList.add(i, newHomeActivity);
              } else {
                System.out.println("Starting Times Clash!! Activity Not Added");
              }

              break;

            case 's':
              System.out.print("Enter Title: ");
              title = kbInput.nextLine();
              System.out.print("Enter Start Time in 24 Hour Format(dd/mm/yyyy hh:mm): ");
              try {
                startTime = dateParser.parse(kbInput.nextLine());
              } catch (java.text.ParseException e) {
                System.out.println("Invalid Date!!");
                break;
              }
              System.out.print("Enter End Time in 24 Hour Format(dd/mm/yyyy hh:mm): ");
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

              SchoolActivity newSchoolActivity = new SchoolActivity(title, startTime, endTime, comment);
              i = 0;
              noClash = true;

              if ((schoolActivityList.size() != 0) && (newSchoolActivity.getStartTime().equals(schoolActivityList.get(0).getStartTime()))) {
                noClash = false;
              }

              while ((i < schoolActivityList.size()) && (newSchoolActivity.getStartTime().after(schoolActivityList.get(i).getStartTime()))) {
                if (((i + 1) < schoolActivityList.size()) && newSchoolActivity.getStartTime().equals(schoolActivityList.get(i + 1).getStartTime())) {
                  noClash = false;
                }
                i++;
              }

              if (noClash) {
                schoolActivityList.add(i, newSchoolActivity);
              } else {
                System.out.println("Starting Times Clash!! Activity Not Added");
              }

              break;

            case 'o':
              System.out.print("Enter Title: ");
              title = kbInput.nextLine();
              System.out.print("Enter Start Time in 24 Hour Format(dd/mm/yyyy hh:mm): ");
              try {
                startTime = dateParser.parse(kbInput.nextLine());
              } catch (java.text.ParseException e) {
                System.out.println("Invalid Date!!");
                break;
              }
              System.out.print("Enter End Time in 24 Hour Format(dd/mm/yyyy hh:mm): ");
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

              System.out.print("Enter Location: ");
              location = kbInput.nextLine();

              System.out.print(("Enter Comment: "));
              comment = kbInput.nextLine();

              OtherActivity newOtherActivity = new OtherActivity(title, startTime, endTime, comment, location);
              i = 0;
              noClash = true;

              if ((otherActivityList.size() != 0) && (newOtherActivity.getStartTime().equals(otherActivityList.get(0).getStartTime()))) {
                noClash = false;
              }

              while ((i < otherActivityList.size()) && (newOtherActivity.getStartTime().after(otherActivityList.get(i).getStartTime()))) {
                if (((i + 1) < otherActivityList.size()) && newOtherActivity.getStartTime().equals(otherActivityList.get(i + 1).getStartTime())) {
                  noClash = false;
                }
                i++;
              }

              if (noClash) {
                otherActivityList.add(i, newOtherActivity);
              } else {
                System.out.println("Starting Times Clash!! Activity Not Added");
              }

              break;

            default:
              System.out.println("Wrong Choice!! Enter Again");
          }

          break;

        case 's':
          System.out.print("Enter Title Keywords: ");
          titleKeywords = kbInput.nextLine().toLowerCase().split(" ");

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

          searchResult.clear();
          searchResult.addAll(homeActivityList);
          searchResult.addAll(schoolActivityList);
          searchResult.addAll(otherActivityList);

          if (type != 'n') {
            switch (type) {
              case 'h':
                HomeActivity homeActivity = new HomeActivity();
                i = 0;
                while (i < searchResult.size()) {
                  if (searchResult.get(i).getClass() == homeActivity.getClass()) {
                    i++;
                  } else {
                    searchResult.remove(i);
                  }
                }
                break;

              case 's':
                SchoolActivity schoolActivity = new SchoolActivity();
                i = 0;
                while (i < searchResult.size()) {
                  if (searchResult.get(i).getClass() == schoolActivity.getClass()) {
                    i++;
                  } else {
                    searchResult.remove(i);
                  }
                }
                break;

              case 'o':
                OtherActivity otherActivity = new OtherActivity();
                i = 0;
                while (i < searchResult.size()) {
                  if (searchResult.get(i).getClass() == otherActivity.getClass()) {
                    i++;
                  } else {
                    searchResult.remove(i);
                  }
                }
                break;
            }
          }

          if (titleKeywords.length > 0) {
            i = 0;
            while (i < searchResult.size()) {
              boolean found = false;
              for (j = 0; (!found) && (j < titleKeywords.length); j++) {
                if (searchResult.get(i).getTitle().toLowerCase().contains(titleKeywords[j])) {
                  found = true;
                }
              }

              if (found) {
                i++;
              } else {
                searchResult.remove(i);
              }
            }
          }

          if ((startTime != null) && (endTime != null)) {
            i = 0;
            while (i < searchResult.size()) {
              if ((startTime.before(searchResult.get(i).getStartTime())) && (endTime.after(searchResult.get(i).getEndTime()))) {
                i++;
              } else {
                searchResult.remove(i);
              }
            }
          }

          System.out.println("\nSearch Result: \n");
          for (i = 0; i < searchResult.size(); i++) {
            System.out.println(searchResult.get(i) + "Type: " + mapClassToString(searchResult.get(i)) + "\n");
          }

          break;

        case 'l':
          System.out.println("\nHome Activities :\n");
          for (i = 0; i < homeActivityList.size(); i++) {
            System.out.println(homeActivityList.get(i));
          }

          System.out.println("\nSchool Activities :\n");
          for (i = 0; i < schoolActivityList.size(); i++) {
            System.out.println(schoolActivityList.get(i));
          }

          System.out.println("\nOther Activities :\n");
          for (i = 0; i < otherActivityList.size(); i++) {
            System.out.println(otherActivityList.get(i));
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