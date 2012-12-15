/**
 * Book Class.
 * 
 * This class is a subclass of the super class "Reference". It contains methods specific to the book
 * class only.
 * 
 * @author Junior Samaroo
 * @author Fei Song
 *
 */
public class Book extends Reference{

	/** Authors of the book. */
	private String authors;
	/** Publisher of the book. */
	private String publisher;
	
	/**
	 * Creates an empty Book object.
	 */
	public Book(){
	super();
	authors = "";
	publisher = "";
	}
	
	/**
	 * Creates a new book with the following specified parameters.
	 * 
	 * @param callNumber CallNumber of book.
	 * @param authors Authors of book.
	 * @param title Title of book.
	 * @param publisher Publisher of book.
	 * @param year Year of book.
	 */
	public Book(String callNumber, String authors, String title, String publisher, int year){
		super(callNumber, title, year);
		this.authors = authors;
		this.publisher = publisher;
	}
	
	/**
	 * Set a new value for authors.
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	/**
	 * Set a new value for publisher.
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * Get the value of authors.
	 */
	public String getAuthors() {
		return authors;
	}
	
	/**
	 * Get the value of publisher.
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * Check for the equality of two books.
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
	 * Show the content of a book in a string.
	 */
	public String toString() {
		return "Book\n" + callNumber + "\n" +
                        authors + "\n" +
			title + "\n" +
			publisher + "\n" + year + "\n";
	}
}
