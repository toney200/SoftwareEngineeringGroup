import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {
    static Scanner sc = new Scanner(System.in);
    static boolean wantsToClose = false;        // triggers the application to close when set to true via user input
    
    CLI() throws Exception{
        while (!wantsToClose) {
            int userSelect = 0; // directs the constructor to call the relevant routing method based on what the user changes this value to
            boolean validInput = false; // informs the while loop below that prompts the user to direct the system to a selected variable

            System.out.println("Select from the following options:\n");
            System.out.println("1. Customer Features    " +
                    "2. Order Functions     " +
                    "3. Publication Management    " +
                    "4. Delivery Area Management    " +
                    "5. Staff Management    " +
                    "0. Close application");
            while (!validInput) {
                try {
                    userSelect = sc.nextInt();
                    switch (userSelect) {
                        case 0:
                            wantsToClose = true;
                            break;
                        case 1:
                            validInput = true;
                            customerRouting();
                            break;
                        case 3:
                            validInput = true;
                            Publication newPub = new Publication();
                            publicationCreation(newPub);
                            break;
                        case 4:
                            validInput = true;
                            deliveryAreaRouting();
                            break;
                        default:
                            System.out.println("Unexpected input. Try again.");
                            sc.nextLine();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid option!");
                }
            }
        }
        System.exit(0);
    }



//  *** CUSTOMER METHODS ***


    /*
    The customer routing method is accessed from main when selecting "Customer Features" in the opening console window.
    The method will route the user to the next customer-related function based on their inputs here.
     */
    static void customerRouting(){
        
    	int userSelect = 0;
        boolean validInput = false;

        while(!validInput) { // Loops until the Scanner receives a valid input.
            try {
                validInput = true;

                System.out.println("Select from the following options: \n");
                System.out.println("1. Create a new customer profile    " +
                        "2. View a customer profile     " +
                        "99. Exit to previous selection");

                userSelect = sc.nextInt();
                switch (userSelect) {
                    case 1:
                        Customer newCustomer = new Customer();
                        customerCreation(newCustomer);
                        return;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 99:
                        return;
                    default:
                        break;
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
	            }
	
	           if (newCustomer.createCustomerInDB(newCustomer)){
	               System.out.println("New customer successfully created");
	               return;
	            }
	            else if (!newCustomer.createCustomerInDB(newCustomer)){
	                System.out.println("Unable to create new customer profile. Please try again.");
	                return;
	            }
	
	        }
	       }
        catch(Exception e) {
        	System.out.println("Unable to connect to database. Please restart your application and try again.");
            e.printStackTrace();
        }


        
    }



//*** DELIVERY AREA METHODS ***


    /*
    Intermediary function that directs the user to differing functions involved in the creation, modification or deletion
    of DeliveryArea entities within the underlying database
     */
    private static void deliveryAreaRouting(){
        int userSelect = 0;
        boolean validInput = false;

        while(!validInput) { // Loops until the Scanner receives a valid input.
            try {
                validInput = true;

                System.out.println("Select from the following options: \n");
                System.out.println("1. Create a new delivery area    " +
                        "2. View an existing delivery area     " +
                        "99. Exit to previous selection");

                userSelect = sc.nextInt();
                switch (userSelect) {
                    case 1:
                        deliveryAreaCreation();
                        return;
                    case 2:
                        return;
                    case 99:
                        return;
                    default:
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number from the aforementioned options!");
                sc.nextLine();
            }
        }
    }


    /*
    Creates an instance of the DeliveryArea class and populates its private member variables with user-defined values.
    Calls
     */
    static void deliveryAreaCreation(){
        DeliveryArea da = new DeliveryArea();
        boolean validInput = false;
        try {
            while(!validInput) {
                System.out.println("Enter the name of the delivery area: ");
                sc.nextLine();
                da.setDeliveryAreaName(sc.nextLine());
                validInput = true;
            }
        } catch (InputMismatchException ime){
            System.out.println("Unable to create new delivery area with given value. Please try again.");
        } catch(Exception e) {
            System.out.println("Unable to create new delivery area. Please try again.");
            e.printStackTrace();
        }

        if (da.createDeliveryAreaInDB(da)){
             System.out.println("New delivery area successfully created.");
        }
        else {
             System.out.println("Unable to create new delivery area. Please try again.");
        }
        return;
    }



// *** ORDER METHODS ***



// *** PUBLICATION METHODS ***



    /*
    Takes an object of the Publication class and inserts data into the member variables (user-defined). Sends object
    of the class to the MySQLConnector class to be passed to SQL database.
     */
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



// *** MAIN ***

    /*
    Calls a new object of the CLI(Command Line Interface) class. Catches unforseen exceptions and notifies the user
    before printing the details of where the exception took place.
     */
    public static void main(String[] args) {
        System.out.println("Welcome...");
        try {
            new CLI();
        }
        catch(Exception e){
            System.out.println("Oops! We ran into a big issue! Please restart the application.");
            e.printStackTrace();
        }
    }

}
// END
