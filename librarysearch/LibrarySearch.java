package librarysearch;

import java.util.ArrayList;
import java.util.Scanner;

/** LibrarySearch class
 *
 * @author Fei Song
 *
 * A class that adds, maintains, and searches for books and journals.
 *
 */

public class LibrarySearch {

	private ArrayList<Book> books;       // A list for maintaining books
	private ArrayList<Journal> journals; // A list for maintaining journals
	
	public static final String[] REFERENCE_TYPES = new String[]{"book", "journal", "b", "j"};

	/**
	 * Create an instance of LibrarySearch
	 */
	public LibrarySearch() {
		books = new ArrayList<Book>();
		journals = new ArrayList<Journal>();
	}

	/*
	 * Add a valid book
	 */
	private boolean addBook( Book book ) {
		for( int i = 0; i < books.size(); i++ ) 
			if( books.get(i).keyEquals(book) )
				return false;
		books.add( book );
		return true;
	}
	
	/*
	 * Add a valid journal
	 */
	private boolean addJournal( Journal journal ) {
		for( int i = 0; i < journals.size(); i++ ) 
			if( journals.get(i).keyEquals(journal) )
				return false;
		journals.add( journal );
		return true;
	}
	
	/** 
	 * Create a book or journal from the input and add it to the appropriate list
	 */
	public void add( Scanner input ) {
		String type;
		do {
			System.out.print( "Enter a reference type (book or journal)> " );
			type = input.nextLine();
		} while( !matchedKeyword(type, REFERENCE_TYPES) );
		
                String callNumber = "";
                do {
			System.out.print( "Enter a call number> " );
			callNumber = input.nextLine();
                } while( callNumber.equals("") );
		
		String authors = "";
		if( type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b") ) {
			System.out.print("Enter a list of authors separated by comma> ");
			authors = input.nextLine();
		}

		System.out.print("Enter a title> " );
		String title = input.nextLine();

		String publisher = "";
		if( type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b") ) {
			System.out.print("Enter a publisher> ");
			publisher = input.nextLine();
		}

		String organizer = "";
		if( type.equalsIgnoreCase("journal") || type.equalsIgnoreCase("j") ) {
			System.out.print("Enter an organizer> ");
			organizer = input.nextLine();
		}

		int year = 0;
		do {
			System.out.print("Enter a year (between 1000 and 9999)>");
			String yearValue = input.nextLine();
			year = yearValue.equals("") ? 0 : Integer.parseInt(yearValue);
		} while( year < 1000 || year > 9999 );
		
		boolean result = true;
		if( type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b") )
			result = addBook( new Book(callNumber, authors, title, publisher, year) );
		else
			result = addJournal( new Journal(callNumber, title, organizer, year) );
		if( !result ) 
			System.out.println( "Add failed: there is another reference with the same call number and year" );
	}

	/* 
	 * Check if a keyword is on a list of tokens
	 */
	private boolean matchedKeyword( String keyword, String[] tokens ) {
		for( int i = 0; i < tokens.length; i++ ) 
			if( keyword.equalsIgnoreCase(tokens[i]) )
				return true;
		return false;
	}

	/*
	 * Check if all keywords are in a string 
	 */
	private boolean matchedKeywords( String[] keywords, String title ) {
		String[] tokens = title.split( "[ ,\n]+" );
		for( int i = 0; i < keywords.length; i++ ) 
			if( !matchedKeyword(keywords[i], tokens) )
				return false;
		return true;
	}

	/*
	 * Search for all books that satisfy a search request
	 */
	private void searchBooks( String callNumber, String[] keywords, int startYear, int endYear ) {
		for( int i = 0; i < books.size(); i++ ) 
			if( (callNumber.equals("") || books.get(i).getCallNumber().equalsIgnoreCase(callNumber)) &&
			    (keywords == null || matchedKeywords(keywords, books.get(i).getTitle())) &&
			    (books.get(i).getYear() >= startYear && books.get(i).getYear() <= endYear) )
				System.out.println( books.get(i) ); 		
	}

	/*
	 * Search for all journals that satisfy a search request
	 */
	private void searchJournals( String callNumber, String[] keywords, int startYear, int endYear ) {
		for( int i = 0; i < journals.size(); i++ ) 
			if( (callNumber.equals("") || journals.get(i).getCallNumber().equalsIgnoreCase(callNumber)) &&
			    (keywords == null || matchedKeywords(keywords, journals.get(i).getTitle())) &&
			    (journals.get(i).getYear() >= startYear && journals.get(i).getYear() <= endYear) )
				System.out.println( journals.get(i) ); 		
	}

	/**
	 * Gather a search request and find the matched books and journals in the related list(s)
	 */ 
	public void search( Scanner input ) {
		
		System.out.print( "Enter a call number> " );
		String callNumber = input.nextLine();

		System.out.print( "Enter title keywords> " );
		String[] keywords = null;
		String line = input.nextLine();
		if( !line.equals("") )
			keywords = line.split( "[ ,\n]+" );

		int startYear = Integer.MIN_VALUE, endYear = Integer.MAX_VALUE;
		boolean valid;
		do {
			valid = true;
			System.out.print("Enter a time period (startYear-endYear)> ");
			line = input.nextLine();
			if( !line.equals("") ) {
				int hyphen = line.indexOf('-');
				if( hyphen < 0 ) 
					valid = false; 
				else {
					String startValue = line.substring(0, hyphen);
					int start = startValue.equals("") ? Integer.MIN_VALUE : Integer.parseInt(startValue);
					String endValue = line.substring(hyphen + 1, line.length());	
					int end = endValue.equals("") ? Integer.MAX_VALUE : Integer.parseInt(endValue);
					if( start > Integer.MIN_VALUE && (start < 1000 || start > 9999) ||
                                            end < Integer.MAX_VALUE && (end < 1000 || end > 9999) ) 
						valid = false;
					else {
						startYear = start;
						endYear = end;
					}
				}
			}
			if( !valid )
				System.out.println( "All year values should be between 1000 and 9999." );
		} while( !valid );

		// search for matched references
		System.out.println( "Matched references: " );
		searchBooks( callNumber, keywords, startYear, endYear );
		searchJournals( callNumber, keywords, startYear, endYear );
	}
	
	public static void main( String[] args ) {
		Scanner input = new Scanner( System.in );
		LibrarySearch library = new LibrarySearch();
		String command;
		do {
			System.out.print( "Enter add, search, or quit> " );
			command = input.nextLine();
			if( command.equalsIgnoreCase("add") || command.equalsIgnoreCase("a") )
				library.add( input );
			else if( command.equalsIgnoreCase("search") || command.equalsIgnoreCase("s") )
				library.search( input );			
		} while( !command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q") );
	}
}
