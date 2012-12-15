import java.io.*;
import java.util.*;

/**
 * LibrarySearch class.
 * 
 * A class that adds, maintains, and searches for books and journals.
 * 
 * @author Fei Song
 * @author Junior Samaroo
 * 
 */
public class LibrarySearch {

	/** Master list containing references of books and journals. */
	private static ArrayList<Reference> items;
	/** HashMap of title of book/journal with associated values of the ArrayList index. */
	private static HashMap<String, ArrayList<Integer>> titleHashMap = new HashMap<String, ArrayList<Integer>>();
	/** Types of references. */
	public static final String[] REFERENCE_TYPES = new String[] { "book","journal", "b", "j" };

	/**
	 * Create an instance of LibrarySearch.
	 */
	public LibrarySearch() {
		items = new ArrayList<Reference>();
	}

	/**
	 * Checks validity of book before adding.
	 */
	private boolean addBook(Book book) {
		for (int i = 0; i < items.size(); i++)
			if (items.get(i).keyEquals(book))
				return false;
		items.add(book);
		return true;
	}

	/**
	 * Checks validity of journal before adding.
	 */
	private boolean addJournal(Journal journal) {
		for (int i = 0; i < items.size(); i++)
			if (items.get(i).keyEquals(journal))
				return false;
		items.add(journal);
		return true;
	}

	/**
	 * Creates a book or journal from the input and adds it to the master
	 * ArrayList "Reference".
	 */
	public void addRecord(Scanner input) {
		String type;
		do {
			System.out.print("Enter a reference type (book or journal)> ");
			type = input.nextLine();
		} while (!matchedKeyword(type, REFERENCE_TYPES));

		String callNumber = "";
		do {
			System.out.print("Enter a call number> ");
			callNumber = input.nextLine();
		} while (callNumber.equals(""));

		String authors = "";
		if (type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b")) {
			System.out.print("Enter a list of authors separated by comma> ");
			authors = input.nextLine();
		}

		System.out.print("Enter a title> ");
		String title = input.nextLine();

		String publisher = "";
		if (type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b")) {
			System.out.print("Enter a publisher> ");
			publisher = input.nextLine();
		}

		String organizer = "";
		if (type.equalsIgnoreCase("journal") || type.equalsIgnoreCase("j")) {
			System.out.print("Enter an organizer> ");
			organizer = input.nextLine();
		}

		int year = 0;
		do {
			System.out.print("Enter a year (between 1000 and 9999)>");
			String yearValue = input.nextLine();
			year = yearValue.equals("") ? 0 : Integer.parseInt(yearValue);
		} while (year < 1000 || year > 9999);

		boolean result = true;
		if (type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b")) {
			result = addBook(new Book(callNumber, authors, title, publisher,
					year));
			addToHashMap(title.toLowerCase().split("[ ,\n]+"));
		} else {
			result = addJournal(new Journal(callNumber, title, organizer, year));
			addToHashMap(title.toLowerCase().split("[ ,\n]+"));
		}
		if (!result)
			System.out.println("Add failed: there is another reference with the same call number and year");
	}

	/**
	 * Check if a keyword is on a list of tokens.
	 */
	private boolean matchedKeyword(String keyword, String[] tokens) {
		for (int i = 0; i < tokens.length; i++)
			if (keyword.equalsIgnoreCase(tokens[i]))
				return true;
		return false;
	}

	/**
	 * Check if all keywords are in a string.
	 */
	private boolean matchedKeywords(String[] keywords, String title) {
		String[] tokens = title.split("[ ,\n]+");
		for (int i = 0; i < keywords.length; i++)
			if (!matchedKeyword(keywords[i], tokens))
				return false;
		return true;
	}

	/**
	 * Search for a Book using the sequential method as in Assignment2.
	 * 
	 * If a search request does not contain a 'title' the sequential method is
	 * used for searching the master list.
	 */
	private void searchBooks(String callNumber, String[] keywords,int startYear, int endYear) {
		/* Loop to sequentially search master list 'Reference' */
		for (int i = 0; i < items.size(); i++)
			if ((callNumber.equals("") || items.get(i).getCallNumber()
					.equalsIgnoreCase(callNumber))
					&& (keywords == null || matchedKeywords(keywords, items
							.get(i).getTitle()))
					&& (items.get(i).getYear() >= startYear && items.get(i)
							.getYear() <= endYear)) {
				Reference ref = items.get(i);
				/* Checks if reference is of a 'Book' type */
				if (ref instanceof Book) {
					Book book = (Book) ref;
					System.out.println("--------------------------------------------");
					System.out.println(book.toString());
					System.out.println("--------------------------------------------");
				}

			}
	}

	/**
	 * Search for a Journal using the sequential method as in Assignment2.
	 * 
	 * If a search request does not contain a 'title' the sequential method is
	 * used for searching the master list.
	 * 
	 */
	private void searchJournals(String callNumber, String[] keywords,int startYear, int endYear) {
		/* Loop to sequentially search master list 'Reference' */
		for (int i = 0; i < items.size(); i++)
			if ((callNumber.equals("") || items.get(i).getCallNumber()
					.equalsIgnoreCase(callNumber))
					&& (keywords == null || matchedKeywords(keywords, items
							.get(i).getTitle()))
					&& (items.get(i).getYear() >= startYear && items.get(i)
							.getYear() <= endYear)) {
				Reference ref = items.get(i);
				/* Checks if reference is of a 'Journal' type */
				if (ref instanceof Journal) {
					Journal book = (Journal) ref;
					System.out.println("--------------------------------------------");
					System.out.println(book.toString());
					System.out.println("--------------------------------------------");
				}
			}
	}

	/**
	 * Gather a search request and find the matched books and journals in the
	 * master ArrayList 'items'.
	 * 
	 * This is a single method to perform a search request via the HashMap or
	 * sequentially as in Assignment2 or using both ways. If no 'title' is
	 * entered the sequential method is used else if a 'title' is entered, it is
	 * searched for via the HashMap. If a combination of 'title' and another
	 * field is entered, both methods are used, searching only their
	 * intersection.
	 * 
	 */
	public void searchRecords(Scanner input) {
		System.out.print("Enter a call number> ");
		String callNumber = input.nextLine();

		System.out.print("Enter title keywords> ");
		String[] keywords = null;
		String line = input.nextLine();
		if (!line.equals(""))
			keywords = line.split("[ ,\n]+");

		int startYear = Integer.MIN_VALUE, endYear = Integer.MAX_VALUE;
		boolean valid;
		do {
			valid = true;
			System.out.print("Enter a time period (startYear-endYear)> ");
			line = input.nextLine();
			if (!line.equals("")) {
				int hyphen = line.indexOf('-');
				if (hyphen < 0)
					valid = false;
				else {
					String startValue = line.substring(0, hyphen);
					int start = startValue.equals("") ? Integer.MIN_VALUE: Integer.parseInt(startValue);
					String endValue = line.substring(hyphen + 1, line.length());
					int end = endValue.equals("") ? Integer.MAX_VALUE : Integer.parseInt(endValue);
					if (start > Integer.MIN_VALUE
							&& (start < 1000 || start > 9999)
							|| end < Integer.MAX_VALUE
							&& (end < 1000 || end > 9999))
						valid = false;
					else {
						startYear = start;
						endYear = end;
					}
				}
			}
			if (!valid)
				System.out.println("All year values should be between 1000 and 9999.");
		} while (!valid); /* Loop to enter only a valid year range */

		/* search for matched references */
		System.out.println("Matched references: ");
		/* Performs sequential search if search query does not contain a 'title' */
		if (keywords == null) {
			searchBooks(callNumber, keywords, startYear, endYear);
			searchJournals(callNumber, keywords, startYear, endYear);
		} else
			/* Performs HashMap search for records if search query contains a 'title' */
			searchHashMap(keywords, callNumber, startYear, endYear);
	}

	/**
	 * Adds 'title' keywords to HashMap and the associated values.
	 * 
	 * If a 'title' keyword already exists, the values of the HashMap is just
	 * updated only.
	 */
	private void addToHashMap(String[] keywords) {
		for (String key : keywords) {
			if (!titleHashMap.containsKey(key)) {
				/* If title keyword does not exist in HashMap, add it */
				titleHashMap.put(key, new ArrayList<Integer>());
			}
			/* If title keywords exist in HashMap, just update its associated 'key-value' pair */
			titleHashMap.get(key).add(items.size() - 1);
		}
	}

	/**
	 * Searches HashMap for 'title' keywords and its associated values.
	 * 
	 * If a search request contains a 'title' its keywords are searched for via
	 * the HashMap. If other fields are requested ex:'callNumber', this field is
	 * searched for via the intersection of the matching 'values' of the 'title'.
	 * 
	 */
	private void searchHashMap(String[] searchKey, String callNumber,int startYear, int endYear) {
		/* Temporarily stores next 'values' for a title keyword */
		ArrayList<Integer> tempStore = new ArrayList<Integer>();
		/* Stores only the intersection 'values' of title keywords */
		Collection<Integer> intersection = new ArrayList<Integer>();
		/* Counts number of keywords found */
		int foundKeys = 0;

		/* Loop to iterate through title keywords and get key-value pair intersection */
		for (String key : searchKey) {
			if (titleHashMap.containsKey(key)) {
				foundKeys = foundKeys + 1;
				tempStore.addAll((titleHashMap.get(key))); /* Stores all found values */
				if (intersection.size() == 0) {
					intersection.addAll(titleHashMap.get(key));
				}
				intersection.retainAll(tempStore); /* Stores only common 'values' */
				tempStore.clear(); /* Clears temporary array*/
			}
		}

		/* Checks if all keywords were found */
		if(searchKey.length == foundKeys){
		/* Performs search of other fields via the reduced list of 'values' for matched record */
		for (Integer i : intersection) {
			if ((callNumber.equals("") || items.get(i.intValue()).getCallNumber().equalsIgnoreCase(callNumber))
					&& (items.get(i.intValue()).getYear() >= startYear && items.get(i.intValue()).getYear() <= endYear)) {
				System.out.println("--------------------------------------------");
				System.out.println(items.get(i.intValue())); /* Prints found records from master list 'Reference' */
				System.out.println("--------------------------------------------");
			}
		}
		}
	}

	/**
	 * Loads the existing references from a file at the start and saves all the
	 * references (including the existing and new references) to another file at
	 * the end of the program. Also has a command loop to add a record, search
	 * records or exit the program.
	 * 
	 * @param args Input file and Output file entered at command line.
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		LibrarySearch library = new LibrarySearch(); 
		String command; /* Stores command to perform (add, search, quit) */

		/* Checks if input and output files are entered at command line */
		try {
			if (args[0] == null || args[1] == null) {
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("No input/ouput file specified at command line!");
			System.out.println("Program ended. Please run program again with an input and output file.");
			System.exit(0);
		}

		/* Loads existing references from a file at start of program */
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream(args[0]));
			String type;
			while (inputStream.hasNextLine()) {
				type = inputStream.nextLine();
				if (type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b")) {
					String callNumber = inputStream.nextLine();
					String authors = inputStream.nextLine();
					String title = inputStream.nextLine();
					String publisher = inputStream.nextLine();
					int year = inputStream.nextInt();
					library.addBook(new Book(callNumber, authors, title, publisher, year));
					library.addToHashMap(title.toLowerCase().split("[ ,\n]+")); /* Adds loaded reference from file to HashMap */
				} else if (type.equalsIgnoreCase("journal") || type.equalsIgnoreCase("j")) {
					String callNumber = inputStream.nextLine();
					String title = inputStream.nextLine();
					String organizer = inputStream.nextLine();
					int year = inputStream.nextInt();
					library.addJournal(new Journal(callNumber, title, organizer, year));
					library.addToHashMap(title.toLowerCase().split("[ ,\n]+")); /* Adds loaded reference from file to HashMap */
				}
			}
			inputStream.close(); /* Closes file */
			System.out.println("Input file loaded successfully!!");
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found. No books/journals loaded!");
		}

		/* Command Loop of program. Can add record, search records or quit program. */
		do {
			System.out.print("Enter now  add, search, or quit> ");
			command = input.nextLine();
			if (command.equalsIgnoreCase("add") || command.equalsIgnoreCase("a"))
				library.addRecord(input);
			else if (command.equalsIgnoreCase("search") || command.equalsIgnoreCase("s"))
				library.searchRecords(input);
			else if (command.equalsIgnoreCase("quit")) {
				
				PrintWriter outputStream = null;
				try {
					outputStream = new PrintWriter(new FileOutputStream(args[1]));
				} catch (FileNotFoundException e) {
					System.out.println("Error storing data to file! File not found!");
					System.exit(0);
				}
				/* Loop to add all records to specified output file. */
				for (int i = 0; i < items.size(); i++) {
					outputStream.println(items.get(i));
				}
				outputStream.close(); /* Closes file ensuring data is written to the file. */
			}
			
		} while (!command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q"));
	}
}
