package dayplanner;

import java.util.*;

/**
 * Represents a Home Activity
 *
 * @author Zeeshan Qureshi
 */
public class HomeActivity extends Activity {

  public HomeActivity() {
    super();
  }

  /**
   * Creates a Home Activity with the specified parameters
   *
   * @param title Activity Title
   * @param startTime Start Time
   * @param endTime Ending Time
   * @param comment Comment on Activity
   */
  public HomeActivity(String title, Date startTime, Date endTime, String comment) {
    super(title, startTime, endTime, comment);
  }
}
