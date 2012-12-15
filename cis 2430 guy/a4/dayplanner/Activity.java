package dayplanner;

/* Import The Required Classes For Use */
import java.util.*;
import java.text.*;

/**
 * Implements an Abstract Class Activity that serves as the 
 * base class for all other activites
 *
 * @author Zeeshan Qureshi
 */
public abstract class Activity {
  private String title, comment;
  private Date startTime, endTime;
  static SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE MMM dd yyyy hh:mm a");

  /**
   * Create Empty Object with Start Time and End Time equal to current time
   */
  public Activity() {
    title = "";
    comment = "";
    startTime = (new GregorianCalendar()).getTime();
    endTime = (new GregorianCalendar()).getTime();
  }

  /**
   * Creates an Activity with the specified parameters
   *
   * @param title Activity Title
   * @param startTime Start Time
   * @param endTime Ending Time
   * @param comment Comment on Activity
   */
  public Activity(String title, Date startTime, Date endTime, String comment) {
    this.title = title;
    this.startTime = (Date)startTime.clone();
    this.endTime = (Date)endTime.clone();
    this.comment = comment;
  }

  /**
   * Set the Title of the Activity to the Passed Title
   * 
   * @param title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Set the Comment of the Activity to the Passed Comment
   *
   * @param comment
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * Set the Start Time of the Activity to the Passed Start Time
   *
   * @param newStartTime
   * @return true if successful
   */
  public boolean setStartTime(Date newStartTime) {
    if (endTime.before(newStartTime)) {
      return false;
    } else {
      startTime = (Date)newStartTime.clone();
      
      return true;
    }
  }

  /**
   * Set the End Time of the Activity to the Passed Emd Time
   *
   * @param newEndTime
   * @return true if successful
   */
  public boolean setEndTime(Date newEndTime) {
    if (startTime.after(newEndTime)) {
      return false;
    } else {
      endTime = (Date)newEndTime.clone();

      return true;
    }
  }

  /**
   * Returns the current title of the activity
   *
   * @return the Title
   */
  public String getTitle(){
    return title;
  }

  /**
   * Returns the current comment of the activity
   *
   * @return The Comment
   */
  public String getComment(){
    return comment;
  }

  /**
   * Returns the current Start Time of the activity
   *
   * @return The Start Time
   */
  public Date getStartTime(){
    return (Date)startTime.clone();
  }

  /**
   * Returns the current End Time of the activity
   *
   * @return The End Time
   */
  public Date getEndTime(){
    return (Date)endTime.clone();
  }

  /**
   * Returns teh String Representation of the Activity with all
   * the details
   *
   * @return String representation
   */
  public String toString(){
    return ("Title: " + title + "\nTime: " + dateFormatter.format(startTime) + " to " + dateFormatter.format(endTime) + "\nComment: " + comment + "\n");
  }

  /**
   * Checkes whether the specified activity is equal to the
   * current activity
   * 
   * @param otherObject Object to be t ested with
   * @return true if equal
   */
  public boolean equals(Object otherObject){
    if (otherObject == null) {
      return false;
    } else if (otherObject.getClass() != this.getClass()) {
      return false;
    }

    Activity otherActivity = (Activity)otherObject;
    return ((title.equalsIgnoreCase(otherActivity.title)) && (startTime.equals(otherActivity.startTime)) && (endTime.equals(otherActivity.endTime)));
  }
}