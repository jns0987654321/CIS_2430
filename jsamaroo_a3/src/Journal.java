/**
 * Journal Class.
 * 
 * This class is a subclass of the super class "Reference". It contains methods specific to the journal
 * class only.
 * 
 * @author Junior Samaroo
 * @author Fei Song
 *
 */
public class Journal extends Reference{

	/** Organizer of the journal. */
	private String organizer;
	
	/**
	 * Creates an empty Journal object.
	 */
	public Journal(){
		super();
		organizer = "";
		}
	
		/**
		 * Creates a new Journal object with the following specified parameters.	
		 * 
		 * @param callNumber CallNumber of journal.
		 * @param title Title of journal.
		 * @param organizer Organizer of journal.
		 * @param year Year of journal.
		 */
		public Journal(String callNumber, String title, String organizer, int year){
			super(callNumber, title, year);
			this.organizer = organizer;
		}
		
		/**
		 * Set a new value for organizer
		 */
		public void setOrganizer(String organizer) {
			this.organizer = organizer;
		}
		
		/**
		 * Get the value of organizer
		 */
		public String getOrganizer() {
			return organizer;
		}
		
		/**
		 * Check for the equality of two journals
		 */
		public boolean equals(Journal other) {
			if (other == null)
				return false;
			else 
				return callNumber.equalsIgnoreCase(other.callNumber) &&
				       title.equalsIgnoreCase(other.title) &&
				       organizer.equalsIgnoreCase(other.organizer) &&
				       year == other.year;
		}
		
		/**
		 * Show the content of a journal in a string
		 */
		public String toString() {
			return "Journal\n" + callNumber + "\n" +
				title + "\n" +
				organizer + "\n" + year + "\n";
		}
	
}
