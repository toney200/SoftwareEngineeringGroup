import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Contains all the necessary functionality to provide a command line interface to the user. Handles switching of user
 * interaction menus that link to functionality within external classes.
 */
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
                            publicationRouting();
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
        Customer.closeSQLConnection();
        Publication.closeSQLConnector();
        System.exit(130);
    }



//  *** CUSTOMER METHODS ***


    /**
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
                System.out.println("1. Create a new customer    " +
                        "2. Edit a customer profile     " +
                        "3. View a customer profile     " +
                        "99. Exit to previous selection");

                userSelect = sc.nextInt();
                switch (userSelect) {
                    case 1:
                        Customer newCustomer = new Customer();
                        createCustomer(newCustomer);
                        break;
                    case 2:
                        updateCustomer();
                        break;
                    case 3:
                        readCustomer();
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


    /**
     * @param newCustomer
     * Takes in all the member variables of a Customer object via user prompt and returns when finished.
     */
    static void createCustomer(Customer newCustomer){
    	try{
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
	
	           if (Customer.createCustomerInDB(newCustomer)){
	               System.out.println("New customer successfully created");
	               return;
	            }
	            else if (!Customer.createCustomerInDB(newCustomer)){
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


    /**
     * Prompts the user for input that may be used as a database query to find and display Customers that match the
     * given input.
     */
    private static void readCustomer(){
        ArrayList<Customer> customers;
        int userSelect = 0;
        String firstName;
        String lastName;

        System.out.println("Please enter the first name of the customer: ");
        firstName = sc.next();
        System.out.println("Please enter the last name of the customer: ");
        lastName = sc.next();

        customers = Customer.searchCustomerByName(firstName, lastName);
        System.out.println("The following results match your search: ");
        for(Customer customer : customers){
            System.out.println(customer.toString() + "\n");
        }
    }


    /**
     * Calls Customer.searchCustomerInDB() for a list of customers based on user selection. User is prompted to select
     * customer details they would like to change and these are passed to Customer for update within the database
     */
    private static void updateCustomer(){
        ArrayList<Customer> customerList;
        String firstName;
        String lastName;
        boolean affirmSelection = false;
        int userSelect = 0;

        // User provides the first and last name of a customer to query database for matches
        System.out.println("Enter the first and last name of the customer you wish to change details for: \n");
        firstName = sc.next();
        lastName = sc.next();
        customerList = Customer.searchCustomerByName(firstName, lastName);

        // Lists all the returned customer objects based on provided first and last name
        for(Customer customer : customerList){
            System.out.println(customerList.indexOf(customer) + 1);
            System.out.println(customer.toString() + "\n");
        }

        // User selects the customer to be updated
        System.out.println("Select the customer you would like to change the details for by typing the number above them");
        userSelect = sc.nextInt();
        if (userSelect < 1 || userSelect > customerList.size()){
            System.out.println("Invalid input.");
            return;
        }
        Customer customerSelected = customerList.get(userSelect - 1);

        /*
         * Takes the Customer selected by the user and presents the option to update all or certain attributes.
         * Leaves details unchanged if a null value is entered.
         */
        userSelect = 0;
        System.out.println("Change any of the following attributes. Press Enter to leave an attribute as is");
        while (!affirmSelection) {
            try {
                System.out.println("First name: ");
                String firstNameInput = sc.nextLine();
                if (!firstNameInput.isEmpty()) {
                    customerSelected.setFirstName(firstNameInput);
                }

                System.out.println("Last name: ");
                String lastNameInput = sc.nextLine();
                if (!lastNameInput.isEmpty()) {
                    customerSelected.setLastName(lastNameInput);
                }

                System.out.println("Phone number: ");
                String phoneNumberInput = sc.nextLine();
                if (!phoneNumberInput.isEmpty()) {
                    customerSelected.setPhoneNo(phoneNumberInput);
                }

                System.out.println("Customer address: ");
                String addressInput = sc.nextLine();
                if (!addressInput.isEmpty()) {
                    customerSelected.setAddress(addressInput);
                }

                System.out.println("Customer eircode: ");
                String eircodeInput = sc.nextLine();
                if (!eircodeInput.isBlank()) {
                    customerSelected.setEircode(eircodeInput.trim());
                }

                System.out.println("Delivery Area ID: ");
                String deliveryAreaIdInput = sc.nextLine();
                if (!deliveryAreaIdInput.isEmpty()) {
                    int deliveryAreaId = Integer.parseInt(deliveryAreaIdInput);
                    customerSelected.setDeliveryAreaId(deliveryAreaId);
                }

                System.out.println("Confirm changes? [y/n]");
                if (sc.next().toLowerCase().charAt(0) == 'y') {
                    affirmSelection = true;
                    if(Customer.updateCustomerInDB(customerSelected)){
                        System.out.println("Customer successfully updated");
                    }
                    else {
                        System.out.println("Unable to update customer profile. Please try again.");
                        return;
                    }
                }

            } catch (Exception e) {
                System.out.println("Error encountered when changing customer information. Returning to previous menu.");
                return;
            }
        }
        return;
    }





//  *** DELIVERY AREA METHODS ***


    /**
     * Directs the user to differing functions involved in the creation and manipulation of delivery area related
     * features.
     */
    private static void deliveryAreaRouting(){
        int userSelect = 0;
        boolean validInput = false;

        while(!validInput) { // Loops until the Scanner receives a valid input.
            try {

                System.out.println("Select from the following options: \n");
                System.out.println("1. Create a new delivery area    " +
                        "2. Edit a delivery area     " +
                        "3. View an existing delivery area     " +
                        "99. Exit to previous selection");

                userSelect = sc.nextInt();
                switch (userSelect) {
                    case 1:
                        validInput = true;
                        createDeliveryArea();
                        return;
                    case 2:
                        validInput = true;
                        return;
                    case 3:
                        validInput = true;
                        readDeliveryArea();
                        return;
                    case 99:
                        return;
                    default: // loops again
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number from the aforementioned options!");
                sc.nextLine();
                validInput = false;
            }
        }
    }


    /**
     * Creates an instance of the DeliveryArea class and populates its private member variables with user-defined values
     */
    static void createDeliveryArea(){
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


    /**
     * Prints the associated delivery area according to user entered values.
     */
    private static void readDeliveryArea(){
        int deliveryAreaID = 0;
        boolean validInput = false;

        while(!validInput) {
            try {
                System.out.println("Enter the ID number of the delivery area: ");
                deliveryAreaID = sc.nextInt();
                System.out.println(DeliveryArea.readDeliveryAreaFromDB(deliveryAreaID).toString());
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Value entered is invalid. Please try again.");
            }
        }
        return;
    }


    /**
     *
     */
    private static void updateDeliveryArea() {
        int userSelect = 0;
    }



//  *** ORDER METHODS ***


    /**
     * Directs the user to the desired functionality related to orders
     */
    private static void orderRouting(){
        int userSelect = 0;
        boolean validInput = false;

        while(!validInput) {
            System.out.println("Select from the following options: \n");
            System.out.println("1. Create new order     " +
                    "2. Place a scheduled order on hold     " +
                    "99. Exit to previous selection");

            userSelect = sc.nextInt();
            switch (userSelect) {

            }
        }
    }



//  *** PUBLICATION METHODS ***

    static void publicationRouting() {
        int userSelect = 0;
        boolean validInput = false;

        while(!validInput) { // Loops until the Scanner receives a valid input.
            try {
                validInput = true;

                System.out.println("Select from the following options: \n");
                System.out.println("1. Create a new publication    " +
                        "2. Find a publication         " +
                		"3. Edit a publication         " +
                        "99. Exit to previous selection");

                userSelect = sc.nextInt();
                switch (userSelect) {
                    case 1:
                    	Publication publication = new Publication();
                    	publicationCreation(publication);
                        return;
                    case 2:
                        retrievePublication();
                        break;
                    case 3:
                        updatePublication();
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

    /**
     * @param newPublication
     * Takes a Publication object and inserts data into the member variables (user-defined). Sends object
     * of the class to the MySQLConnector class to be passed to SQL database.
     */
    static void publicationCreation(Publication newPublication){
        // @todo remove direct link from CLI to MySQLConnector
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

    public static void updatePublication() {
        ArrayList<Publication> publicationList;
        String pubName;
        String pubAuthor;
        boolean validInput = false;
        boolean confirmSeletion = false;
        int userSelect = 0;
        //Loops until valid input is entered.
        while(!validInput) {
            try{
                //Enter name of publication
                System.out.println("Enter name of publication: ");
                sc.nextLine();
                pubName = sc.nextLine();

                if(pubName.isEmpty()){
                    System.out.println("Publication name cannot be empty.");

                } else if (pubName.equals("99")) {
                    return;
                }
                //Search for publication in database.
                publicationList = Publication.searchPublicationInDB(pubName);
                //If publication is not in list displays message.
                if(publicationList.isEmpty()){
                    System.out.println("Publication not found.");
                    return;
                }
                else{
                    //Displays details of each publication found
                    for(Publication p : publicationList){
                        System.out.println(publicationList.indexOf(p) + 1);
                        System.out.println(p.toString());
                    }
                }

                System.out.println("Select using the numbers displayed, which publication you would like to update: ");
                userSelect = sc.nextInt();

                  if(userSelect < 1 || userSelect > publicationList.size()){
                    System.out.println("Please enter a valid number from the following options!");
                    return;
                }

                Publication publicationSelected = publicationList.get(userSelect - 1);

                //
                System.out.println("Change desired attributes of publication slected. If you do not want to change publication attribute " +
                        "please press enter.");
                while (!confirmSeletion){
                    try{
                        System.out.println("Enter name of publication: ");
                        sc.nextLine();
                        String pubNameInput = sc.nextLine();
                        if(!pubNameInput.isEmpty()){
                            publicationSelected.setPubName(pubNameInput);
                        }
                        else{
                            System.out.println("Publication attribute staying the same.");
                        }

                        System.out.println("Enter publication author: ");
                        //sc.nextLine();
                        String pubAuthorInput = sc.nextLine();
                        if(!pubAuthorInput.isEmpty()){
                            publicationSelected.setPubAuthor(pubAuthorInput);
                        }
                        else{
                            System.out.println("Publication attribute staying the same.");
                        }

                        System.out.println("Enter publication type: ");
                        String pubTypeInput = sc.nextLine();
                        if(!pubTypeInput.isEmpty()){
                            publicationSelected.setPubType(pubTypeInput);
                        }
                        else{
                            System.out.println("Publication attribute staying the same.");
                        }

                        System.out.println("Enter publication frequency: ");
                        String pubFrequencyInput = sc.nextLine();
                        if(!pubFrequencyInput.isEmpty()){
                            publicationSelected.setPubFrequency(pubFrequencyInput);
                        }

                        System.out.println("Enter publication price: ");
                        String pubCostInput = sc.next();
                        if(!pubCostInput.isEmpty()){
                            double pubCost = Double.parseDouble(pubCostInput);
                            publicationSelected.setPubCost(pubCost);
                        }
                        else{
                            System.out.println("Publication attribute staying the same.");
                        }

                        System.out.println("Confirm changes: [y/n] ");
                        if(sc.next().equals("y")){
                            confirmSeletion = true;

                            if(Publication.updatePublicationInDB(publicationSelected)){
                                System.out.println("Publication successfully updated.");
                            }
                            else{
                                System.out.println("Unable to update publication.");
                            }
                        }


                    }
                    catch(Exception e){
                        System.out.println("Error!! Publication could not be updated please try again!");
                        return;
                    }
                }

            }
            catch (InputMismatchException e) {
                System.out.println("Please try again");
            }

        }
    }
/**
* Retrieve information for publications when title is entered.
*/
    public static void retrievePublication() {
        ArrayList<Publication> publicationList;
        String pubName;
        String pubAuthor;
        boolean validInput = false;
        //Loops until valid input is entered.
        while(!validInput) {
            try{
                //Enter name of publication
                System.out.println("Enter name of publication: ");
                sc.nextLine();
                pubName = sc.nextLine();

                if(pubName.isEmpty()){
                    System.out.println("Publication name cannot be empty.");

                } else if (pubName.equals("99")) {
                    return;
                }
                //Search for publication in database.
                publicationList = Publication.searchPublicationInDB(pubName);
                //If publication is not in list displays message.
                if(publicationList.isEmpty()){
                    System.out.println("Publication not found.");
                }
                else{
                    //Displays details of each publication found
                    for(Publication p : publicationList){
                        System.out.println(p.toString());
                        validInput = true;
                    }
                }

            }
            catch (InputMismatchException e) {
                System.out.println("Please try again");
            }
        }


    }


//  *** MAIN ***

    /**
     * Calls a new object of the CLI(Command Line Interface) class
     */
    public static void main(String[] args) {
        System.out.println("Welcome...");
        try {
            new CLI();
        }
        catch(Exception e){
            // we should never be here
            System.err.println("Oops! We ran into an unknown issue! Please restart the application.");
            e.printStackTrace();
        }
    }

}
// END
