import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a library search program which allows the user to enter and
 * search for library information such as books and journals.
 * 
 * @author Junior Samaroo
 * @version 1.0
 * @since 2010/10/18
 */
public class Library {
	/** Menu selection # */
	private static int choice = 0;
	/** Creates new object of class Scanner */
	public Scanner keyboard = new Scanner(System.in);
	/** Creates new arraylist to store books */
	public ArrayList<String> books = new ArrayList<String>();
	/** Creates new arraylist to store journals */
	public ArrayList<String> journals = new ArrayList<String>();
	/** Creates string to get rid of unnecessary end of line characters */
	public static String junk;
	
	/**
	 * Method that checks correct choice of Menu.
	 * 
	 * This Method takes the user input for menu selection and checks
	 * whether it is a valid input, displaying appropriate error messages.
	 */
	public static void correctChoice(Library lib){
		try { /* check for correct input */
			choice = lib.keyboard.nextInt();
			if (choice != 1 && choice != 2 && choice != 3) { /* Loop to check for appropriate input */
				System.out.println("Error: Please enter 1, 2 or 3!! Try Again!");
				choice = 0;
			}
		} catch (java.util.InputMismatchException e) {
			System.out.println("Error: Please enter 1, 2 or 3!! Try Again!");
		}
		junk = lib.keyboard.nextLine(); /* gets rid of newline character */
	}
	
	/**
	 * Method that checks if library is empty.
	 * 
	 * This Method simply checks if the library is empty i.e. if no journal
	 * or book exists in the library.
	 */
	public static boolean isEmpty(Library lib){
		if(lib.books.size() == 0 && lib.journals.size() == 0){
			System.out.println("Library is empty! Please add a book/journal!");
			return true;}
		else
			return false;
	}

	/**
	 * Main method of "Library" class.
	 * 
	 * Contains the Menu Loop in which the user can select to either Add or
	 * Search records or exit the program by calling the appropriate Methods.
	 */
	public static void main(String[] args) {
		
		Library myLib = new Library();
		
		while (choice == 0) {
			if (choice == 0) {
				System.out.println("Enter number of choice: ");
				System.out.println("1: Add");
				System.out.println("2: Search");
				System.out.println("3: Quit");
				correctChoice(myLib); /* Calls Method to check for correct menu choice */
			}

			if (choice == 1) {
				String entry;
				/* Loop to select either book or journal to add */
				do{
				System.out.println("Please type either 'book' or 'journal' without quotes to add corresponding entry:");
				entry = myLib.keyboard.nextLine();
				}while(!entry.equalsIgnoreCase("book") && !entry.equalsIgnoreCase("journal"));
				
				if(entry.equalsIgnoreCase("book")){
				Book newBook = new Book();							/* Creates new book object */
				newBook.setCallNumber(myLib); 						/* Calls Method to enter call number for book */
				newBook.setAuthor(myLib); 							/* Calls Method to enter authors for book */
				newBook.setTitle(myLib); 							/* Calls Method to enter title for book */
				newBook.setPublisher(myLib); 						/* Calls Method to enter publisher for book */
				newBook.setYear(myLib); 							/* Calls Method to enter year for book */
				newBook.addBook(myLib); 							/* Calls Method to add book to library */
				}

				if (entry.equalsIgnoreCase("journal")){
				Journal newJournal = new Journal(); 				/* Creates new journal object */
				newJournal.setCallNumber(myLib); 					/* Calls Method to enter call number for journal */
				newJournal.setTitle(myLib); 						/* Calls Method to enter title for journal */
				newJournal.setOrganization(myLib);					/* Calls Method to enter organization for journal */
				newJournal.setYear(myLib);							/* Calls Method to enter year for journal */
				newJournal.addJournal(myLib);						/* Calls Method to add journal to library */
				}
				
				choice = 0;
			}

			if (choice == 2) {
				LibrarySearch newSearch = new LibrarySearch(); 		/* Creates new LibrarySearch object to search library */
				newSearch.setCallNumber(myLib); 					/* Calls Method to enter call number for query */
				newSearch.setTitle(myLib); 							/* Calls Method to enter title for query */
				newSearch.setYear(myLib); 							/* Calls Method to enter year range for query */
				newSearch.searchBook(myLib); 						/* Calls Method to enter books for match */
				newSearch.searchJournal(myLib); 					/* Calls Method to search journals for match */
				isEmpty(myLib);										/* Calls Method to check if library is empty */
				
				choice = 0;
			}

			if (choice == 3) {
				System.out.println("Application Exiting.......");
				System.exit(0); /* Exits program */
			}
		}

	}

}
