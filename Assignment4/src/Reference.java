/**
 * Reference Class.
 * 
 * This class is a super class that contains Book and Journal as its subclasses.
 * It contains methods common to both the Book and Journal subclasses.
 * 
 * @author Junior Samaroo
 * @author Fei Song
 * 
 */
public class Reference {
	/** CallNumber of Book/Journal */
	public String callNumber;
	/** Title of Book/Journal */
	public String title;
	/** Year of Book/Journal */
	public int year;

	/**
	 * Creates an empty reference object.
	 */
	public Reference() {
		callNumber = "";
		title = "";
	}

	/**
	 * Creates a new reference object with the following specified parameters.
	 * 
	 * @param callNumber CallNumber of book/journal
	 * @param title Title of book/journal
	 * @param year Year of book/journal
	 */
	public Reference(String callNumber, String title, int year) {
		this.callNumber = callNumber;
		this.title = title;
		this.year = year;
	}

	/**
	 * A static method for validating if the information for a book/journal is
	 * valid.
	 */
	public static boolean consistent(String callNumber, int year) {
		return !callNumber.equals("") && year >= 1000 & year <= 9999;
	}

	/**
	 * Set a new value for callNumber.
	 */
	public void setCallNumber(String callNumber) {
		if (callNumber.equals("")) {
			System.out.println("Empty value for a call number");
			System.exit(0);
		} else
			this.callNumber = callNumber;
	}

	/**
	 * Set a new value for title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set a new value for year.
	 */
	public void setYear(int year) {
		if (year < 1000 || year > 9999) {
			System.out.println("Invalid value for year: " + year);
			System.exit(0);
		} else
			this.year = year;
	}

	/**
	 * Get the value of call number.
	 */
	public String getCallNumber() {
		return callNumber;
	}

	/**
	 * Get the value of title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get the value of year.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Check for equality of two keys.
	 */
	public boolean keyEquals(Object otherObject) {
		/* Correct equals method */
		if (otherObject == null) {
			return false;
		} else if (otherObject.getClass() != this.getClass()) {
			return false;
		}

		Reference otherActivity = (Reference) otherObject;
		return ((title.equalsIgnoreCase(otherActivity.title))
				&& (callNumber.equals(otherActivity.callNumber)) && (year == otherActivity.year));
	}
}
