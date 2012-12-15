package dayplanner;

import java.util.*;

/**
 * Represents any Activity other than Home and School Activity
 *
 * @author Zeeshan Qureshi
 */
public class OtherActivity extends Activity {

  private String location;

  public OtherActivity() {
    super();

    location = "";
  }

  /**
   * Creates an Other Activity with the Specified Parameters
   *
   * @param title  Activity Title
   * @param startTime  Start Time
   * @param endTime  End Time
   * @param comment  Comment
   * @param location  Activity Location
   */
  public OtherActivity(String title, Date startTime, Date endTime, String comment, String location) {
    super(title, startTime, endTime, comment);

    this.location = location;
  }

  /**
   * Set the Activity Location to the Specified One
   *
   * @param location New Location
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * Return the Current Location of the Activity
   *
   * @return Activity Location
   */
  public String getLocation() {
    return location;
  }

  /**
   * Return the String Representation of the current Activity
   *
   * @return String Representatino of Activity
   */
  public String toString() {
    return ("Title: " + getTitle() + "\nTime: " + dateFormatter.format(getStartTime()) + " to " + dateFormatter.format(getEndTime()) + "\nLocation: " + getLocation() + "\nComment: " + getComment()) + "\n";
  }

  /**
   * Check if the Specified Object is equal to the current one
   *
   * @param otherObject The Object to Test for Equivalence
   * @return true if equal
   */
  public boolean equals(Object otherObject) {
    if (otherObject == null) {
      return false;
    } else if (otherObject.getClass() != this.getClass()) {
      return false;
    }

    OtherActivity otherActivity = (OtherActivity) otherObject;
    return (super.equals(otherActivity) && location.equalsIgnoreCase(otherActivity.location));
  }
}
