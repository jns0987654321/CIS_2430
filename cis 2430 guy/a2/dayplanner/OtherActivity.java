package dayplanner;

import java.util.*;

/**
 * Derived from class Activity and added extra field location
 *
 * @author Zeeshan Qureshi
 */
public class OtherActivity extends Activity{
  private String location;

  public OtherActivity(){
    super();

    location = "";
  }

  public OtherActivity(String title, Date startTime, Date endTime, String comment, String location){
    super(title, startTime, endTime, comment);
    
    this.location = location;
  }

  public void setLocation(String location){
    this.location = location;
  }

  public String getLocation(){
    return location;
  }

  public String toString(){
    return ("Title: " + getTitle() + "\nTime: " + dateFormatter.format(getStartTime()) + " to " + dateFormatter.format(getEndTime()) + "\nLocation: " + getLocation() + "\nComment: " + getComment()) + "\n";
  }

  public boolean equals(Object otherObject){
    if(otherObject == null)
      return false;
    else if(otherObject.getClass() != this.getClass())
      return false;

    OtherActivity otherActivity = (OtherActivity) otherObject;
    return (super.equals(otherActivity) && location.equalsIgnoreCase(otherActivity.location));
  }
}