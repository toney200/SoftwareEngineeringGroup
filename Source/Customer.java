import java.util.ArrayList;

public class Customer {
    
    /*
     * Member variables outlining basic customer information
     */
    private String firstName;   // the first name of a customer
    private String lastName;    // the last name of a customer
    private String address;     // the address of a given customer
    private String eircode;     // the eircode/postal code tied to the given customer
    private String phoneNo;     // the contact number of the customer
    private int deliveryAreaID; // each customer falls within a delivery area defined by the user
    private int id;             // customer ID is a unique identifier that differentiates customers in the database

    // The variables below inform the format of the eicode and phone numbers when being added by the User
    private static final String EIRCODE_REGEX = "^[A-Za-z0-9]{3}[A-Za-z0-9]{4}$";
    private static final String PHONE_REGEX = "^08[3679][0-9]{7}$";

    private static MySQLConnector sqlConnector;

    public Customer(){
        if(sqlConnector == null){
            instantiateSQLInstance();
        }
    }

    public Customer(int id, String firstName, String lastName, String phoneNo, String address, String eircode, int deliveryAreaID){
        if(sqlConnector == null){
            instantiateSQLInstance();
        }

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.eircode = eircode;
        this.phoneNo = phoneNo;
        this.deliveryAreaID = deliveryAreaID;
    }

    /**
     * Creates a single instance of the SQL Connector class for the Customer class to make exclusive use of.
     */
    private static void instantiateSQLInstance() {
        try {
            sqlConnector = new MySQLConnector();
        }
        catch(Exception e){
            System.err.println("Error occurred linking application to database. Ref Customer.instantiateSQLInstance() method.");
        }
    }


    public static void closeSQLConnection(){
        if(sqlConnector != null){
            sqlConnector.closeDB();
        }
        sqlConnector = null;
    }

    /**
     * @param customer
     * customer object to be inserted into database.
     * @return boolean value representing sql insertion success.
     */
    public static boolean createCustomerInDB(Customer customer){
        return sqlConnector.insertCustomerDetails(customer);
    }

    /**
     * @param firstname customer first name as entered by the user
     * @param lastname customer last name as entered by the user
     * @return the list of Customer objects that contain either parameter as attributes
     */
    public static ArrayList<Customer> searchCustomerByName(String firstname, String lastname){
        if (sqlConnector == null) {
            instantiateSQLInstance();
        }
        return sqlConnector.searchCustomerByName(firstname, lastname);
    }

    /**
     * Takes a Customer object and updates the relevant table entry in the database
     * @param customer
     * @return the success/failure of the SQL update attempt
     */
    public static boolean updateCustomerInDB(Customer customer){
        return sqlConnector.updateCustomerDetails(customer);
    }

    
    public int getID(){
        return this.id;
    }

    /**
    Returns the firstName member variable of th instance of the class this function is called on.
     */
    public String getFirstName(){
        return this.firstName;
    }


    /**
    Returns the lastName member variable of th instance of the class this function is called on.
     */
    public String getLastName() {
    	return this.lastName;
    }


    /**
    * Returns the address member variable of th instance of the class this function is called on.
     */
    public String getAddress(){
        return this.address;
    }


    /**
    Returns the eircode member variable of th instance of the class this function is called on.
     */
    public String getEircode(){
        return this.eircode;
    }


    /**
    Returns the phoneNo member variable of th instance of the class this function is called on.
     */
    public String getPhoneNo(){
      return this.phoneNo;

    }
    
    public int getDeliveryAreaId() {
    	return this.deliveryAreaID;
    }

    public void setDeliveryAreaId(int id) {
    	this.deliveryAreaID = id;
    }

    public void setFirstName(String name) throws IllegalArgumentException{
        if (validateFirstName(name) == true){
            this.firstName = name;
        }
        else{
            throw new IllegalArgumentException();
        }
    }


    public void setLastName(String name) throws IllegalArgumentException{
        if (validateLastName(name) == true){
            this.lastName = name;
        }
        else {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Sets the phone number member variable of an instance of Customer
     * @param phoneNumber the Customer phone number
     * @throws IllegalArgumentException
     */
    public void setPhoneNo(String phoneNumber) throws IllegalArgumentException{
        if(validatePhoneNo(phoneNumber)){
            this.phoneNo = phoneNumber;
        }
        else {
            throw new IllegalArgumentException();
        }
        
    }


    public void setAddress(String address){
    	this.address = address;
    }


    public void setEircode(String eircode) throws IllegalArgumentException{
        if (validateEircode(eircode)){
            this.eircode = eircode;
        }
        else {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Checks if the entered string is less than 2 or greater than 20 in length. Returns true if value falls within this range 
     * and does not contain numbers
     */
    protected boolean validateFirstName(String firstName){
        if (firstName != null){
        // Checks if the passed in string contains a number
            for (char c : firstName.toCharArray()) {
                if (Character.isDigit(c)) {
                    return false;
                }
            }

            if (firstName.length() > 20 || firstName.length() < 2){
                return false;
            }

            return true;
        }
        
        return false;
    }


    /**
    * Check if a passed in string is greater than 2 characters and less than 21. Returns false if value falls out of expected range
    * or if the string contains digit values. 
    */
    protected boolean validateLastName(String lastName){
        if (lastName != null){
            for (char c : lastName.toCharArray()){
                if (Character.isDigit(c)){
                    return false;
                }
            }
            if (lastName.length() > 20 || lastName.length() < 2){
                return false;
            }
            else
                return true;
        }
        
        return false;
    }

    /**
     * Checks if an entered address is within the character limits of 2 and 100 characters(inclusive). Returns true when string is
     * within this boundary
     */
    protected boolean validateAddress(String address){
        if (address != null){
            if(address.length() < 2 || address.length() > 100){
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if entered string matches the 7-character format of a typical Eircode, e.g. D08VF8H. Returns false if the length of the 
     * string is greater or less than 7 or does not match the usual format.
     */
    protected boolean validateEircode(String eircode){
        if (eircode != null){
            if(eircode.length() == 7){
                return eircode.matches(EIRCODE_REGEX);
            }
            return false;
        }
        
        return false;
    }

    /**
     * Checks a phone number to ensure the following:
     *      Length exactly 10 digits;
     *      Begins with the digits of one of 4 mobile network operators, i.e. 083, 086, 087, 089;
     *      Does not contain letters or symbols
     */
    protected boolean validatePhoneNo(String phoneNo){
        if (phoneNo != null){
            if (phoneNo.length() == 10){
                if (phoneNo.matches(PHONE_REGEX)){
                    return true;
                }
                return false;
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * @return Formatted Customer object listing private member variables.
     */
    @Override
    public String toString() {
        return "ID: " + this.id +
                "\nName: " + this.firstName + " " + this.lastName +
                "\nPhone No. : " + this.phoneNo +
                "\nAddress: " + this.address + ", " + this.eircode +
                "\nDelivery Area: " + this.deliveryAreaID;
    }

}
