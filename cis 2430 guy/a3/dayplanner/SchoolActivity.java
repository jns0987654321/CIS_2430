package dayplanner;

import java.util.*;

/**
 * Representa a School Activity
 *
 * @author Zeeshan Qureshi
 */
public class SchoolActivity extends Activity {

  public SchoolActivity() {
    super();
  }

  /**
   * Creates a School Activity with the specified parameters
   *
   * @param title Activity Title
   * @param startTime Start Time
   * @param endTime Ending Time
   * @param comment Comment on Activity
   */
  public SchoolActivity(String title, Date startTime, Date endTime, String comment) {
    super(title, startTime, endTime, comment);
  }
}
