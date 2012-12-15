package dayplanner;

import java.util.*;

/**
 * Derived from class Activity
 *
 * @author Zeeshan Qureshi
 */
public class HomeActivity extends Activity{
  public HomeActivity(){
    super();
  }

  public HomeActivity(String title, Date startTime, Date endTime, String comment){
    super(title, startTime, endTime, comment);
  }
}