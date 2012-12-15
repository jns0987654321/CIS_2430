package librarysearch;

import java.util.Scanner;

/** LibrarySearchDemo class
 *
 * @author Fei Song
 *
 */

public class LibrarySearchDemo {
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
