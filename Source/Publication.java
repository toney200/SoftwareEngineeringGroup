
/*
 * This class represents Publications, the details include: cost, name, type, author, frequency and id number.
 * It includes validation methods to ensure data integrity. 
 */
public class Publication {
	
	//Class Variables
	  private double pubCost;
	  private String pubName;
	  private String pubType;
	  private String pubAuthor;
	  private String pubFrequency;
	  private int pubID;
	  
	  public static MySQLConnector mySQLConnector;


	    public Publication(){
	
		}

	public Publication(int pubID, String pubName, String pubType, String pubAuthor, String pubFrequency, double pubCost) {

		this.pubID = pubID;
		this.pubName = pubName;
		this.pubType = pubType;
		this.pubAuthor = pubAuthor;
		this.pubFrequency = pubFrequency;
		this.pubCost = pubCost;

	}
	//Searches for publications within the database when a name is entered.
	public static ArrayList<Publication> searchPublicationInDB(String pubName){
		//Ensures SQL instance is initiated properly.
		instantiateSQLInstance();
		//Return search results
		return mySQLConnector.searchPublication(pubName);
	}

	public static boolean updatePublicationInDB(Publication p){
		return mySQLConnector.updatePublication(p);
	}
	//Initializes instance of SQL connector if none is found
	private static void instantiateSQLInstance() {
		try {
			if(mySQLConnector == null){
				mySQLConnector = new MySQLConnector();
			}
		} catch (Exception e) {
			System.err.println("Error occured linking application to database. Ref instantiateSQLInstance() method.");
		}
	}
	
	public Publication(int id, String name, String author, String type, String frequency, double cost) {
		pubID = id;
		pubName = name;
		pubAuthor = author;
		pubType = type;
		pubFrequency = frequency;
		pubCost = cost;
	}
	
	  //Getter and Setters
	public int getPubID() {
		return this.pubID;
	}
	  

	public double getPubCost() {
		return this.pubCost;
	}
	//Setter for pubCost with validation
	public void setPubCost(double pubCost) {
		if(validatePublicationCost(pubCost) == true) {
			this.pubCost = pubCost;
		}
		
		
	}

	public String getPubName() {
		return this.pubName;
	}
	//Setter for pubName with validation
	public void setPubName(String pubName) {
			if(validatePublicationName(pubName) == true) {
				this.pubName = pubName;
			}
			
	

	}

	public String getPubType() {
		return this.pubType;
	}
	//Setter for pubType with validation
	public void setPubType(String pubType) {
		if(validatePublicationType(pubType) == true) {
			this.pubType = pubType;
		}
		
	}

	public String getPubAuthor() {
		return this.pubAuthor;
	}
	//Setter for pubAuthor with validation
	public void setPubAuthor(String pubAuthor) {
		if(validatePublicationAuthor(pubAuthor) == true) {
			this.pubAuthor = pubAuthor;
		}
		
	}

	public String getPubFrequency() {
		return this.pubFrequency;
	}
	//Setter for pubFrequency with validation
	public void setPubFrequency(String pubFrequency) {
		if(validatePublicationFrequency(pubFrequency) == true) {
			this.pubFrequency = pubFrequency;
		}
		
	}
	
	//Validation Methods

	/*
	 * Validates the publications name.
	 * @param pubName, name of publication.
	 * return true if valid; otherwise false.
	 * 
	 */
	boolean validatePublicationName(String pubName){
		//checks if null and within range (2 - 50)
		if(pubName != null) {
			if(pubName.length() >= 2 && pubName.length() <= 50) {
				return true;
			}
		} 		
		return false;
	}
	
	/*
	 * Validates the publications type.
	 * @param pubType, type of publication.
	 * return true if valid; otherwise false.
	 * 
	 */
	boolean validatePublicationType(String pubType) {
		//checks if null and only contains the 3 strings given.
		if(pubType != null) {
			if(pubType.contains("Newspaper") || pubType.contains("Magazine") || pubType.contains("Book")) {
				return true;
			}
		}
		return false;
			
	}
	
	/*
	 * Validates the publications author.
	 * @param pubAuthor, author of publication.
	 * return true if valid; otherwise false.
	 */
	boolean validatePublicationAuthor(String pubAuthor) {
		//checks if null, within range (2 - 50) and if there is a number in  the string.
		if(pubAuthor != null) {
			if(pubAuthor.length() >= 2 && pubAuthor.length() <= 50) {
				//Loop through pubAuthor to check if a number is present
				for(char c : pubAuthor.toCharArray()) {
					if(Character.isDigit(c)) {
						return false;
					}
				}
				return true;	
			}
		}	
		return false;
	}
	
	/*
	 * Validates the publications frequency.
	 * @param pubFrequency, frequency of publication.
	 * return true if valid; otherwise false.
	 * 
	 */
	boolean validatePublicationFrequency(String pubFrequency) {
		//checks if null and contains 3 strings given.
		if(pubFrequency != null) {
			if(pubFrequency.contains("Daily") || pubFrequency.contains("Weekly") || pubFrequency.contains("Monthly") || pubFrequency.contains("Once")){
				return true;
			}
		}
		return false;
		
	}
	
	/*
	 * Validates the publications cost.
	 * @param pubCost, cost of publication.
	 * return true if valid; otherwise false.
	 * 
	 */
	boolean validatePublicationCost(double pubCost) {
		//checks if integer is positive and within range(1.00 - 75.00)
		if(pubCost != 0 && pubCost > 0.99 && pubCost < 75.01) {
			if(pubCost >= 1.00 && pubCost <= 75.00) {
				return true;
			}
		}
		return false;
	}
	
}
