/**
 * Book class that that adds a new book.
 * 
 * This class performs menu choice #2 "Add' when the user enters 'book' for the
 * addition of a new book to the library. It sets all the required fields,
 * checks if a duplicate exists and finally adds it.
 */
public class Book {
	/** Call number of book */
	private String callNumber;
	/** Authors of book */
	private String authors;
	/** Title of book */
	private String title;
	/** Publisher of book */
	private String publisher;
	/** Year of book */
	private String year;
	/** Year of book as integer for comparison purposes */
	private int yearInt;

	/**
	 * Method sets the call number for the book.
	 * 
	 * This Method sets the call number of the book to be added. 
	 * This field is required and a value must be entered.
	 */
	public void setCallNumber(Library lib) {
		do {
			System.out.println("Enter call number: ");
			this.callNumber = lib.keyboard.nextLine();
			if (this.callNumber.length() == 0)
				System.out.println("Error: Must enter a value! Field is required!!");
		} while (this.callNumber.length() == 0);
	}

	/**
	 * Method sets the authors for the book.
	 * 
	 * This Method sets the authors of the book to be added. 
	 * This field is not required and can be left blank.
	 */
	public void setAuthor(Library lib) {
		System.out.println("Enter authors: ");
		this.authors = lib.keyboard.nextLine();
	}

	/**
	 * Method sets the title for the book.
	 * 
	 * This Method sets the title of the book to be added. 
	 * This field is required and a value must be entered.
	 */
	public void setTitle(Library lib) {
		do {
			System.out.println("Enter title: ");
			this.title = lib.keyboard.nextLine();
			if (this.title.length() == 0)
				System.out.println("Error: Must enter a value! Field is required!!");
		} while (this.title.length() == 0);
	}

	/**
	 * Method sets the publisher for the book.
	 * 
	 * This Method sets the publisher of the book to be added. 
	 * This field is not required and can be left blank.
	 */
	public void setPublisher(Library lib) {
		System.out.println("Enter publisher: ");
		this.publisher = lib.keyboard.nextLine();
	}

	/**
	 * Method sets the year of the book.
	 * 
	 * This Method sets the year of the book to be added. 
	 * This field is required and a value must be entered between 1000-9999.
	 */
	public void setYear(Library lib) {
		do {
			try {
				System.out.println("Enter year: ");
				this.year = lib.keyboard.nextLine();
				this.yearInt = Integer.parseInt(year);
				if (yearInt < 1000 || yearInt > 9999) {
					System.out.println("Error: Must enter a number between 1000 and 9999! Try Again!!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Must enter a number between 1000 and 9999! Try Again!!");
			}
		} while (yearInt < 1000 || yearInt > 9999);
	}

	/**
	 * Method adds the book to the arraylist.
	 * 
	 * This Method adds the book to the book arraylist. Before addition
	 * it checks to see if exists using the 'exists' Method.
	 */
	public void addBook(Library lib) {
		if (!exists(callNumber, year, lib)) {
			lib.books.add(toString());
		} else {
			System.out.println("Error: Cannot add book! Record already exists!!");
		}
	}

	/**
	 * Method checks for duplicate entry.
	 * 
	 * This Method checks for a duplicate entry of the book to be added. If it
	 * exists it returns true else it returns false and works together with the
	 * 'addBook' Method.
	 */
	public boolean exists(String callNumber, String year, Library lib) {

		int foundCallNumber = 0; /* flag for a found call number */
		int foundYear = 0; /* flag for a found year */
		
		/* Loop checks for journal existence */
		for (int j = 0; j < lib.books.size(); j++) {
			String[] temp = lib.books.get(j).split("\n");
			for (int i = 0; i < temp.length; i++) {
				if (temp[0].contains(this.callNumber)) {
					foundCallNumber = 1;
				}
				if (temp[4].contains(this.year)) {
					foundYear = 1;
				}
			}
		}
		/* Loop checks for journal existence */
		for (int j = 0; j < lib.journals.size(); j++) {
			String[] temp = lib.journals.get(j).split("\n");
			for (int i = 0; i < temp.length; i++) {

				if (temp[0].contains(this.callNumber)) {
					foundCallNumber = 1;
				}
				if (temp[3].contains(this.year)) {
					foundYear = 1;
				}
			}
		}
		if (foundCallNumber == 1 && foundYear == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method returns the string to add to book arraylist.
	 * 
	 * This Method adds to the book arraylist the corresponding string elements.
	 * This Method works together with the 'addBook' Method.
	 */
	public String toString() {
		return this.callNumber + "\n" + this.authors + "\n" + this.title + "\n"
				+ this.publisher + "\n" + this.yearInt + "\n";
	}
}
