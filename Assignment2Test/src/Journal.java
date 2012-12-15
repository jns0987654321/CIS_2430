/**
 * Journal class that that adds a new journal.
 * 
 * This class performs menu choice #2 "Add' when the user enters 'journal' for the
 * addition of a new journal to the library. It sets all the required fields,
 * checks if a duplicate exists and finally adds it.
 */
public class Journal {
	/** Call number of journal */
	private String callNumber;
	/** Title of journal */
	private String title;
	/** Organization of journal */
	private String organization;
	/** Year of journal */
	private String year;
	/** Year of journal as integer for comparison purposes */
	private int yearInt;

	/**
	 * Method sets the call number for the journal.
	 * 
	 * This Method sets the call number of the journal to be added. 
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
	 * Method sets the title for the journal.
	 * 
	 * This Method sets the title of the journal to be added. 
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
	 * Method sets the organization name for the journal.
	 * 
	 * This Method sets the organization of the journal to be added. 
	 * This field is not required and can be left blank.
	 */
	public void setOrganization(Library lib) {
		System.out.println("Enter organization: ");
		this.organization = lib.keyboard.nextLine();
	}

	/**
	 * Method sets the year of the journal.
	 * 
	 * This Method sets the year of the journal to be added. 
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
	 * Method adds the journal to the arraylist.
	 * 
	 * This Method adds the journal to the journal arraylist. Before addition
	 * it checks to see if exists using the 'exists' Method.
	 */
	public void addJournal(Library lib) {
		if (!exists(callNumber, year, lib)) {
			lib.journals.add(toString()); /* Calls toString Method to add journal contents to journal */
		} else {
			System.out.println("Error: Cannot add journal! Record already exists!!");
		}
	}

	/**
	 * Method checks for duplicate entry.
	 * 
	 * This Method checks for a duplicate entry of the journal to be added. If it
	 * exists it returns true else it returns false and works together with the
	 * 'addJournal' Method.
	 */
	public boolean exists(String callNumber, String year, Library lib) {

		int foundCallNumber = 0; /* flag for a found call number */
		int foundYear = 0; /* flag for a found year */
		
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

		if (foundCallNumber == 1 && foundYear == 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Method returns the string to add to journal arraylist.
	 * 
	 * This Method adds to the journal arraylist the corresponding string elements.
	 * This Method works together with the 'addJournal' Method.
	 */
	public String toString() {
		return this.callNumber + "\n" + this.title + "\n" + this.organization
				+ "\n" + this.yearInt + "\n";
	}

}
