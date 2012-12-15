package librarysearch;

/** Journal class
 *
 * @author Fei Song
 *
 * A class for representing and comparing journals.
 *
 */

public class Journal {
	private String callNumber;  // call number of a journal
	private String title;       // title of a journal
	private String organizer;   // organizer of a journal
	private int year;           // publication year of a journal
	
	/**
	 * Create a journal with all the required fields
	 */
	public Journal(String callNumber, String title, String organizer, int year) {
		if( consistent(callNumber, year) ) {
			this.callNumber = callNumber;
			this.title = title;
			this.organizer = organizer;
			this.year = year;
		} else {			
			System.out.println("Invalid values for creating a journal");
			System.exit(0);
		}
	}
	
	/**
	 * Create a journal with only callNumber and year
	 */
	public Journal(String callNumber, int year) {
		this(callNumber, "", "", year);
	}
	
	/**
	 * Create a copy of a journal
	 */
	public Journal(Journal other) {
		if (other == null) {
			System.out.println("null value for copying a journal");
			System.exit(0);
		} else {
			callNumber = other.callNumber;
			title = other.title;
			organizer = other.organizer;
			year = other.year;
		}
	}
	
	/**
	 * A static method for validating if the information for a journal is valid
	 */
	public static boolean consistent(String callNumber, int year) {
		return !callNumber.equals("") && year >= 1000 & year <= 9999;
	}

	/**
	 * Set a new value for callNumber
	 */
	public void setCallNumber(String callNumber) {
		if( callNumber.equals("") ) {
			System.out.println("Empty value for a call number");
			System.exit(0);
		} else
			this.callNumber = callNumber;
	}
	
	/**
	 * Set a new value for title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set a new value for organizer
	 */
	public void setPublisher(String organizer) {
		this.organizer = organizer;
	}

	/**
	 * Set a new value for year
	 */
	public void setYear(int year) {
		if( year <1000 || year > 9999 ) {
			System.out.println("Invalid value for year: " + year);
			System.exit(0);
		} else
			this.year = year;
	}
	
	/**
	 * Get the value of call number
	 */
	public String getCallNumber() {
		return callNumber;
	}
	
	/**
	 * Get the value of title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Get the value of organizer
	 */
	public String getOrganizer() {
		return organizer;
	}
	
	/**
	 * Get the value of year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Check for the equality of two journals
	 */
	public boolean equals(Journal other) {
		if (other == null)
			return false;
		else 
			return callNumber.equalsIgnoreCase(other.callNumber) &&
			       title.equalsIgnoreCase(other.title) &&
			       organizer.equalsIgnoreCase(other.organizer) &&
			       year == other.year;
	}
	
	/**
	 * Check for equality of two keys
	 */
	public boolean keyEquals(Journal other) {
		if( other == null )
		 	return false;
		else
			return callNumber.equalsIgnoreCase(other.callNumber) &&
			       year == other.year;
	}
	
	/**
	 * Show the content of a journal in a string
	 */
	public String toString() {
		return "Journal: " + callNumber + "\n" +
			title + "\n" +
			organizer + ", " + year;
	}
	
	public static void main(String[] args) {
                Journal aJournal = new Journal( "P98.C6116", "Computational Linguistics", 
                                       "Association for Computational Linguistics", 2008 );
		System.out.println(aJournal);
	}
}
