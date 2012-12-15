package dayplanner;

import java.util.*;

/**
 * Derived from class Activity
 *
 * @author Zeeshan Qureshi
 */
public class SchoolActivity extends Activity{
  public SchoolActivity(){
    super();
  }

  public SchoolActivity(String title, Date startTime, Date endTime, String comment){
    super(title, startTime, endTime, comment);
  }
}