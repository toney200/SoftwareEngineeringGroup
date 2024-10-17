package Publications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Publications {
	
	//Class Variables
	  private double pubCost;
	  private String pubName;
	  private String pubType;
	  private String pubAuthor;
	  private String pubFrequency;
	  private int pubID;
	  

	
	  
	  //Getter and Setters
	public double getPubCost() {
		return this.pubCost;
	}

	public void setPubCost(double pubCost) {
		if(validatePublicationCost(pubCost) == true) {
			this.pubCost = pubCost;
		}
		
	}

	public String getPubName() {
		return this.pubName;
	}

	public void setPubName(String pubName) {
		if(validatePublicationName(pubName) == true) {
			this.pubName = pubName;
		}
	}

	public String getPubType() {
		return this.pubType;
	}

	public void setPubType(String pubType) {
		if(validatePublicationType(pubType) == true) {
			this.pubType = pubType;
		}
		
	}

	public String getPubAuthor() {
		return this.pubAuthor;
	}

	public void setPubAuthor(String pubAuthor) {
		if(validatePublicationAuthor(pubAuthor) == true) {
			this.pubAuthor = pubAuthor;
		}
		
	}

	public String getPubFrequency() {
		return this.pubFrequency;
	}

	public void setPubFrequency(String pubFrequency) {
		if(validatePublicationFrequency(pubFrequency) == true) {
			this.pubFrequency = pubFrequency;
		}
		
	}
	
	public int getPublicationID() {
		return this.pubID;
	}
	
	//Validation Methods

	
	boolean validatePublicationName(String pubName){
		
		if(pubName != null) {
			if(pubName.length() >= 2 && pubName.length() <= 50) {
				return true;
			}
		} 
		return false;
		
		
		
	}
	
	boolean validatePublicationType(String pubType) {
		if(pubType != null) {
			if(pubType.contains("Newspaper") || pubType.contains("Magazine") || pubType.contains("Book")) {
				return true;
			}
		}
		return false;
			
	}
	
	boolean validatePublicationAuthor(String pubAuthor) {
		if(pubAuthor == null) {
			return false;
		}
		
		if(pubAuthor.length() >= 2 && pubAuthor.length() <= 50) {
			return true;	
		}
		return false;
	}
	
	boolean validatePublicationFrequency(String pubFrequency) {
		return true;
	}
	
	boolean validatePublicationCost(double pubCost) {
		return true;
	}
	
}
