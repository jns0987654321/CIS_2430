package librarysearch;

/** Book class
 *
 * @author Fei Song
 *
 * A class for representing and comparing books.
 *
 */

public class Book {
	private String callNumber;  // call number of a book
	private String authors;     // one or multiple authors separated by commas
	private String title;       // title of a book
	private String publisher;   // publisher of a book
	private int year;           // publication year of a book
	
	/**
	 * Create a book with all the required fields
	 */
	public Book(String callNumber, String authors, String title, String publisher, int year) {
		if( consistent(callNumber, year) ) {
			this.callNumber = callNumber;
			this.authors = authors;
			this.title = title;
			this.publisher = publisher;
			this.year = year;
		} else {			
			System.out.println("Invalid values for creating a book");
			System.exit(0);
		}
	}
	
	/**
	 * Create a book with only callNumber and year
	 */
	public Book(String callNumber, int year) {
		this(callNumber, "", "", "", year);
	}
	
	/**
	 * Create a copy of a book
	 */
	public Book(Book other) {
		if (other == null) {
			System.out.println("null value for copying a book");
			System.exit(0);
		} else {
			callNumber = other.callNumber;
			authors = other.authors;
			title = other.title;
			publisher = other.publisher;
			year = other.year;
		}
	}
	
	/**
	 * A static method for validating if the information for a book is valid
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
	 * Set a new value for authors
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/**
	 * Set a new value for title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set a new value for publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
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
	 * Get the value of authors
	 */
	public String getAuthors() {
		return authors;
	}
	
	/**
	 * Get the value of title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Get the value of publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * Get the value of year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Check for the equality of two books
	 */
	public boolean equals(Book other) {
		if (other == null)
			return false;
		else 
			return callNumber.equalsIgnoreCase(other.callNumber) &&
			       authors.equalsIgnoreCase(other.authors) &&
			       title.equalsIgnoreCase(other.title) &&
			       publisher.equalsIgnoreCase(other.publisher) &&
			       year == other.year;
	}

	/**
	 * Check for equality of two keys
	 */
	public boolean keyEquals(Book other) {
		if( other == null )
		 	return false;
		else
			return callNumber.equalsIgnoreCase(other.callNumber) &&
			       year == other.year;
	}
	
	/**
	 * Show the content of a book in a string
	 */
	public String toString() {
		return "Book: " + callNumber + "\n" +
                        authors + "\n" +
			title + "\n" +
			publisher + ", " + year;
	}
	
	public static void main(String[] args) {
                Book aBook = new Book( "QA76.73.J38S265", "Walter Savitch, Kenrich Mock", "Absolute Java", 
                                       "Addison-Wesley", 2009 );
		System.out.println(aBook);
	}
}
