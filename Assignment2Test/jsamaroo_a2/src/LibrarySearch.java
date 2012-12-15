/**
 * Library searching class.
 * 
 * This class performs menu choice #2 "Search". It takes the user input
 * and searches both book and journal arraylists for a match. If all
 * fields are left blank it prints the entire library.
 */
public class LibrarySearch {
	/** Call number to be searched for */
	private String callNumber;
	/** Title to be searched for */
	private String title;
	/** Year to be searched for */
	private String year;
	/** Initial year range value */
	private int yearInitial;
	/** Final year range value */
	private int yearFinal;
	
	/**
	 * Method sets the call number for query.
	 * 
	 * This Method sets the call number of the book/journal to be searched
	 * for. This field can be left empty and is not required.
	 */
	public void setCallNumber(Library lib) {
		System.out.println("Enter call number: ");
		this.callNumber = lib.keyboard.nextLine();
	}

	/**
	 * Method sets the title for query.
	 * 
	 * This Method sets the title of the book/journal to be searched
	 * for. This field can be left empty and is not required.
	 */
	public void setTitle(Library lib) {
		System.out.println("Enter title: ");
		this.title = lib.keyboard.nextLine();
	}

	/**
	 * Method sets the year range for query.
	 * 
	 * This Method sets the year range of the book/journal to be searched
	 * for. This field can be left empty and is not required. Year range must
	 * be in the form 'XXXX-XXXX' without quotes where XXXX represents a 4
	 * digit number between 1000-9999. If 'XXXX-' is entered it returns all
	 * years XXXX and greater and if '-XXXX' is entered it returns all
	 * years XXXX and less between 1000-9999.
	 */
	public void setYear(Library lib) {
		do {
			try {
				do {
					System.out.println("Enter year range 'XXXX-XXXX' without quotes  where XXXX represents a 4 digit number: ");
					this.year = lib.keyboard.nextLine();
					if (this.year.length() == 0) { /* Loop if the field is left blank */
						yearInitial = 1000;
						yearFinal = 9999;
					}
					break;
				} while (this.year.indexOf("-") == -1);

				String[] years = this.year.split("-");
				if (this.year.indexOf("-") == 0) { /* Loop for if only a final range is given */
					System.out.println(years[0] + " " + years[1]);
					yearFinal = Integer.parseInt(years[1]);
					yearInitial = 1000;
					System.out.println(yearInitial + " " + yearFinal);
				}

				if (this.year.indexOf("-") >= 0) { /* Loop for if only a initial range is given */
					yearFinal = Integer.parseInt(years[0]);
					try {
						yearFinal = Integer.parseInt(years[1]);
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {
						yearFinal = 9999;
					}
				}

				try {
					yearInitial = Integer.parseInt(years[0]);
				} catch (NumberFormatException e1) {
					yearInitial = 1000;
				}

				/* Loop that checks range of years */
				if (yearInitial < 1000 || yearInitial > 9999 
						|| yearFinal < 1000 || yearFinal > 9999) {
					System.out.println("Error: Must enter a number between 1000 and 9999! Try Again!!");
				}

			} catch (NumberFormatException e) {
				System.out.println("Error: Must enter a number between 1000 and 9999! Try Again!!");
			}

		} while (yearInitial < 1000 || yearInitial > 9999 || yearFinal < 1000
				|| yearFinal > 9999);
	}
	
	/**
	 * Method searches book arraylist.
	 * 
	 * This Method searches the book arraylist for a match of the search
	 * parameters. If a match is found it is printed to the screen.
	 */
	public void searchBook(Library lib) {

		for (int j = 0; j < lib.books.size(); j++) {
			int foundCallNumber = 0;
			int foundTitle = 0;
			int foundYear = 0;
			String[] temp = lib.books.get(j).split("\n");
			for (int i = 0; i < temp.length; i++) {

				/* Checks if call number is a match */
				if (temp[0].contains(this.callNumber)) {
					foundCallNumber = 1;
				}

				/* Checks if title is a match */
				String[] query = this.title.split(" ");
				int match = 0;
				for (int k = 0; k < query.length; k++) {
					if (temp[2].contains(query[k]))
						match += 1;
				}

				if (match == query.length)
					foundTitle = 1;

				/* Checks if year is within range */
				int yearInt = Integer.parseInt(temp[4]);
				if (yearInt >= this.yearInitial && yearInt <= this.yearFinal) {
					foundYear = 1;
				}
			}

			if (foundCallNumber == 1 && foundTitle == 1 && foundYear == 1) {
				System.out.println("_________________________________________________");
				System.out.println("Found Book: ");
				System.out.println(lib.books.get(j));
				System.out.println("_________________________________________________");
			}else
				System.out.println("Record not found!!!");
		}
	}

	/**
	 * Method searches journal arraylist.
	 * 
	 * This Method searches the journal arraylist for a match of the search
	 * parameters. If a match is found it is printed to the screen.
	 */
	public void searchJournal(Library lib) {
		for (int j = 0; j < lib.journals.size(); j++) {
			int foundCallNumber = 0;
			int foundTitle = 0;
			int foundYear = 0;
			String[] temp = lib.journals.get(j).split("\n");
			for (int i = 0; i < temp.length; i++) {

				/* Checks if call number is a match */
				if (temp[0].contains(this.callNumber)) {
					foundCallNumber = 1;
				}

				/* Checks if title is a match */
				String[] query = this.title.split(" ");
				int match = 0;
				for (int k = 0; k < query.length; k++) {
					if (temp[1].contains(query[k]))
						match += 1;
				}
				if (match == query.length)
					foundTitle = 1;

				/* Checks if year is within range */
				int yearInt = Integer.parseInt(temp[3]);
				if (yearInt >= this.yearInitial && yearInt <= this.yearFinal) {
					foundYear = 1;
				}
			}

			if (foundCallNumber == 1 && foundTitle == 1 && foundYear == 1) {
				System.out.println("_________________________________________________");
				System.out.println("Found Journal: ");
				System.out.println(lib.journals.get(j));
				System.out.println("_________________________________________________");
			}
			else
				System.out.println("Record not found!!!");
		}
	}
	
}
