package dayplanner;

/* Import The Required Classes For Use */
import java.util.*;
import java.text.*;

/**
 * Implement an abstract class activity that is the parent class of all activities
 *
 * @author Zeeshan Qureshi
 */
public abstract class Activity {
  private String title, comment;
  private Date startTime, endTime;
  static SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE MMM dd yyyy hh:mm a");

  /**
   * Create class with current time
   */
  public Activity() {
    title = "";
    comment = "";
    startTime = (new GregorianCalendar()).getTime();
    endTime = (new GregorianCalendar()).getTime();
  }

  /**
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
   * 
   * @param title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * 
   * @param comment
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
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
   * 
   * @return the Title
   */
  public String getTitle(){
    return title;
  }

  /**
   * 
   * @return The Comment
   */
  public String getComment(){
    return comment;
  }

  /**
   * 
   * @return The Start Time
   */
  public Date getStartTime(){
    return (Date)startTime.clone();
  }

  /**
   * 
   * @return The End Time
   */
  public Date getEndTime(){
    return (Date)endTime.clone();
  }

  /**
   *
   * @return String representation
   */
  public String toString(){
    return ("Title: " + title + "\nTime: " + dateFormatter.format(startTime) + " to " + dateFormatter.format(endTime) + "\nComment: " + comment + "\n");
  }

  /**
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