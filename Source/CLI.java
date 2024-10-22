import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {
    static Scanner sc = new Scanner(System.in);
 
    
    CLI() throws Exception{
        
    	int userSelect = 0;
        boolean validInput = false;
        boolean finished = false;

        System.out.println("Select from the following options:\n");
        System.out.println("1. Customer Features    " +
                "2. Order Functions     " +
                "3. Publication Management    " +
                "4. Delivery Area Management    " +
                "5. Staff Management" +
                "9. Exit Application");
        while(!validInput) {
            try {
                userSelect = sc.nextInt();
                switch (userSelect) {
                    case 1:
                        validInput = true;
                        customerRouting();
                        break;
                    case 3:
                    	validInput = true;
                    	Publication newPub = new Publication();
                    	publicationCreation(newPub);
                    	break;
                    case 9:
                        validInput = true;
                        finished = true;
                        System.out.println("Application shutting down...")
                    default:
                        System.out.println("Unexpected input. Try again.");
                        sc.nextLine();

                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option!");
            }
        }
        if(!finished) {
            try {
                new CLI();
            } catch(Exception e){
            System.out.println("Oops! We found a big issue. Please restart the application.");
            }
        }
    }

    /*
    The customer routing method is accessed from main when selecting "Customer Features" in the opening console window.
    The method will route the user to the next customer-related function based on their inputs here.
     */
    static void customerRouting(){
        
    	int userSelect = 0;
        boolean validInput = false;

        System.out.println("Select from the following options: \n");
        System.out.println("1. Create a new customer profile    " +
                "2. View a customer profile" +
                "99. Exit to Start");

        while(!validInput) { // Loops until the Scanner receives a valid input.
            try {
                userSelect = sc.nextInt();
                validInput = true;

                switch (userSelect) {
                    case 1:
                        Customer newCustomer = new Customer();
                        customerCreation(newCustomer);
                        return;
                    case 2:
                    case 3:
                    default:
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number from the aforementioned options!");
                sc.nextLine();
            }
        }
    }


    /*
    This method is called from the customerRouting() method when the user opts to create a new customer profile
    within the system. The method takes in all the member variables of a Customer object via user prompt and returns to
    the 'main' screen when finished.
     */
    static void customerCreation(Customer newCustomer){
    	try{
    		MySQLConnector sql = new MySQLConnector();
    	
	    	boolean validInput = false;
	
	        while(!validInput){
	            try {
	                System.out.println("Enter first name: ");
	                newCustomer.setFirstName(sc.next());
	                System.out.println("Enter last name: ");
	                newCustomer.setLastName(sc.next());
	                System.out.println("Enter phone number: ");
	                newCustomer.setPhoneNo(sc.next());
	                sc.nextLine();
	                System.out.println("Enter customer address: ");
	                newCustomer.setAddress(sc.nextLine());
	                System.out.println("Enter customer eircode: ");
	                newCustomer.setEircode(sc.next());
	                System.out.println("Enter delivery area ID: ");
	                newCustomer.setDeliveryAreaId(sc.nextInt());
	                validInput = true;
	            } catch (Exception e) {
	                System.out.println("Error encountered when inserting customer information. Please try again and mind your syntax");
	                validInput = false;
	            }
	
	           if (newCustomer.sendCustomerToDB(newCustomer) == true){
	               System.out.println("New customer successfully created");
	               return;
	            }
	            else if (newCustomer.sendCustomerToDB(newCustomer) == false){
	                System.out.println("Unable to create new customer profile. Please try again.");
	                return;
	            }
	
	        }
	       }
        catch(Exception e) {
        	e.printStackTrace();
        }


        
    }

    public static void main(String[] args) {
        System.out.println("Welcome...");
        try {
            new CLI();
        }
        catch(Exception e){
            System.out.println("Oops! We found a big issue. Please restart the application.");
        }
    }


    static void publicationCreation(Publication newPublication){
    	try {
    		MySQLConnector sql = new MySQLConnector();
    		boolean validInput = false;
    		
    		while(!validInput){

                try{
                System.out.println("Enter publication price: ");
                newPublication.setPubCost(sc.nextDouble());
		sc.nextLine();
                System.out.println("Enter publication name: ");
                newPublication.setPubName(sc.nextLine());

                System.out.println("Enter publication type: ");
                newPublication.setPubType(sc.next());
		sc.nextLine();
                System.out.println("Enter publication author: ");
                newPublication.setPubAuthor(sc.nextLine());

                System.out.println("Enter publication frequency: ");
                newPublication.setPubFrequency(sc.next());

                validInput = true;
                }
                catch(Exception e){
                    System.out.println("Error encountered when trying to enter publication information!!! Please try again");
                    validInput = false;
                }

                

           }
            if (sql.insertPublicationDetails(newPublication) == true) {
            	System.out.println("New publication successfully entered.");
            	return;
            }
            else if (sql.insertPublicationDetails(newPublication) == false) {
            	System.out.println("Unable to create new publication item. Please try again.");
            	return;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        

        


    }


}
