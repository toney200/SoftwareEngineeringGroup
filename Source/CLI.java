import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Creates a command-line interface that utilises user prompts to facilitate access to the functionality of the
 * {@link Customer}, {@link DeliveryArea}, {@link Order} and {@link Publication} classes.
 */
public class CLI {
    static Scanner sc = new Scanner(System.in);
    static boolean wantsToClose = false;        // triggers the application to close when set to true via user input

    /**
     * Constructs a new {@code CLI} instance, which serves as the main access point for interacting with
     * the command-line interface (CLI) of the application. The constructor initializes the CLI and
     * handles routing to various available interfaces based on user input. The user can select different
     * actions by responding to prompts, and the constructor facilitates the navigation to
     * those methods by calling internal routing functions.
     *
     * <p>If an unforeseen exception occurs during initialization or routing, this constructor will throw
     * a generic {@link Exception}. This ensures that any unexpected issues that arise are communicated to
     * the caller for further handling.
     *
     * <p>Note: This constructor does not directly execute user-selected methods, but rather passes control
     * to other internal routing functions which then direct the flow to the corresponding method in the class.
     *
     * @throws Exception if an unexpected error occurs in the program
     */
    CLI() throws Exception{
        while (!wantsToClose) {
            int userSelect = 0; // directs the constructor to call the relevant routing method based on what the user changes this value to
            boolean validInput = false; // informs the while loop below that prompts the user to direct the system to a selected variable

            System.out.println("Select from the following options:\n");
            System.out.println("1. Customer Features\n" +
                    "2. Order Functions\n" +
                    "3. Publication Management\n" +
                    "4. Delivery Area Management\n" +
                    "5. Staff Management\n" +
                    "0. Close application");
            while (!validInput) {
                try {
                    userSelect = sc.nextInt();
                    switch (userSelect) {
                        case 0:
                            wantsToClose = true;
                            validInput = true;
                            break;
                        case 1:
                            validInput = true;
                            customerRouting();
                            break;
                        case 2:
                            validInput = true;
                            orderRouting();
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
        //DeliveryArea.closeSQLConnection();
        Order.closeSQLConnection();
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
                System.out.println("1. Create a new customer\n" +
                        "2. Edit a customer profile\n" +
                        "3. View a customer profile\n" +
                        "4. Delete a customer profile\n" +
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
                    case 4:
                        deleteCustomer();
                        break;
                    case 99:
                        return;
                    default:
                        validInput = false;
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
     * Prompts the user for a numeric value representing the Customer ID that is then passed to the system to be
     * deleted in the SQL database.
     */
    private static void deleteCustomer(){
        int customerID;

        System.out.println("Note: Deleting customers is permanent.");
        System.out.println("Enter customer ID to delete. Press any alphabetic key to return to last screen: ");

        try {
            customerID = sc.nextInt();
            Customer.deleteCustomerByID(customerID);
        }
        catch (InputMismatchException ime) {
            return;
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
                System.out.println("1. Create a new delivery area\n" +
                        "2. Edit a delivery area\n" +
                        "3. View an existing delivery area\n" +
                        "4. Delete an existing delivery area\n" +
                        "99. Exit to previous selection");

                userSelect = sc.nextInt();
                switch (userSelect) {
                    case 1:
                        validInput = true;
                        createDeliveryArea();
                        return;
                    case 2:
                        validInput = true;
                        updateDeliveryArea();
                        return;
                    case 3:
                        validInput = true;
                        readDeliveryArea();
                        return;
                    case 4:
                        validInput = true;
                        deleteDeliveryArea();
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
     * Creates an instance of the {@link DeliveryArea} class and populates its private member variables with user-defined values
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
     * Prompts the user for an integer representing the ID of an entry in the database and passes it to {@link DeliveryArea}
     * to be deleted.
     */
    private static void deleteDeliveryArea() {
        System.out.println("Enter the ID of the delivery area to delete: ");
        try {
            int deliveryAreaID = sc.nextInt();
            boolean success = DeliveryArea.deleteDeliveryAreaByID(deliveryAreaID);

            if (success) {
                System.out.println("Delivery area successfully deleted.");
            } else {
                System.out.println("Unable to delete delivery area. Please check the ID and try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid numeric delivery area ID.");
            sc.nextLine();
        }
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
     *  Prompts the user to direct a search query for a specific DeliveryArea entity within the database. Uses the
     *  attributes to populate a {@link DeliveryArea} object that may be edited and returned to the database.
     */
    private static void updateDeliveryArea() {
        DeliveryArea da = new DeliveryArea();
        int userSelect = 0;
        boolean validInput = false;
        boolean affirmUpdate = false;

        System.out.println("Select search criteria: ");
        System.out.println("1. Search by ID     "+
                "2. Show all");

        while(!validInput) {
            try{
                userSelect = sc.nextInt();
                switch (userSelect) {
                    case 1:
                        while(!affirmUpdate) {
                            System.out.println("Enter Delivery Area ID: ");
                            userSelect = sc.nextInt();
                            da = DeliveryArea.readDeliveryAreaFromDB(userSelect);

                            System.out.println("New Delivery Area name: ");
                            da.setDeliveryAreaName(sc.nextLine());
                            System.out.println("Confirm changes? [y/n]");
                            if (sc.next().toLowerCase().charAt(0) == 'y') {
                                affirmUpdate = true;
                                if (DeliveryArea.updateDeliveryAreaInDB(da)) {
                                    System.out.println("Delivery Area successfully updated.");
                                } else {
                                    System.out.println("Unable to update delivery area. Please try again.");
                                }
                            }
                            if(sc.next().toLowerCase().charAt(0) == 'n') {
                                System.out.println("Return to previous menu? [y/n]");
                                if (sc.next().toLowerCase().charAt(0) == 'y') {
                                    return;
                                }
                                else break;
                            }
                        }

                    case 2:
                        /*
                        * @todo Enable ability to print List generated from DB query and select 1 to update
                        *  ArrayList<DeliveryArea> deliveryAreas = DeliveryArea.methodToReturnDeliveryAreas();
                        * for(DeliveryArea d : deliveryAreas){
                        *   System.out.print(deliveryAreas.getIndexOf(d));
                        *   System.out.println(d.toString());
                        * }
                        *
                        * System.out.println("Type the number above the delivery area to select it.");
                        * userSelect = sc.nextInt();
                        * da = deliveryAreas.get
                        *
                         */
                    default:
                        break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Input error encountered. Please try again.");
            }
            catch(Exception e){
                System.out.println("Unknown Exception");
                return;
            }
        }
        return;
    }



//  *** ORDER METHODS ***


    /**
     * Directs the user to the desired functionality related to orders via user prompt.
     */
    private static void orderRouting(){
        int userSelect = 0;
        boolean validInput = false;

        while(!validInput) {
            System.out.println("Select from the following options: \n");
            System.out.println("1. Create new order\n" +
                    "2. Place a scheduled order on hold\n" +
                    "99. Exit to previous selection");

            userSelect = sc.nextInt();
            switch (userSelect) {
                case 1:
                    createOrder();
                    validInput = true;
                    break;
                case 2:
                    break;
                case 99:
                    return;
            }
        }
        return;
    }


    /**
     * Prompts the user for a data to populate an instance of {@link Order} before creating an additional database
     * entry with that data.
     */
    private static void createOrder(){
        boolean validInput = false;
        Order order = new Order();

        while(!validInput) {
            System.out.println("Please enter the order details below ");
            try{
            	long millis=System.currentTimeMillis();
            	System.out.println(new java.sql.Date(millis));
                order.setOrderDate(new java.sql.Date(millis));

                System.out.println("Enter Publication ID: ");
                order.setPublicationID(sc.nextInt());

                System.out.println("Enter the associated customer ID: ");
                order.setCustomerID(sc.nextInt());

                Order.createOrderInDB(order);
            }
            catch(InputMismatchException ime){
                System.out.println("Input error occurred. Please try again.");
            }
            catch(Exception e){
                System.out.println("There was an issue creating this order. Please try again.");
            }
        }

        return;
    }



//  *** PUBLICATION METHODS ***

    static void publicationRouting() {
        int userSelect = 0;
        boolean validInput = false;

        while(!validInput) { // Loops until the Scanner receives a valid input.
            try {
                validInput = true;

                System.out.println("Select from the following options: \n");
                System.out.println("1. Create a new publication\n" +
                        "2. Find a publication\n" +
                		"3. Edit a publication\n" +
                        "4. Delete a publication\n" +
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
                    case 4:
                        deletePublication();
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


    private static void deletePublication(){
        int publicationID;
        System.out.println("Note: deleting stock items is permanent.");
        try{
            System.out.println("Enter publication ID to delete. Press any alphabetic key to return");
            publicationID = sc.nextInt();
            Publication.deletePublicationByID(publicationID);
        }
        catch (InputMismatchException ime){
            return;
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
