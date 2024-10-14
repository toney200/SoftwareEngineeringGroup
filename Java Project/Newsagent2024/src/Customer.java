import java.util.regex.Pattern;

public class Customer {
    
    /*
     * Member variables outlining basic customer information
     */
    private String firstName;
    private String lastName;
    private String address;
    private String eircode;
    private String phoneNo;
    private int id;

    // The variables below inform the format of the eicode and phone numbers when being added by the User
    private static final String EIRCODE_REGEX = "^[A-Za-z0-9]{3}[A-Za-z0-9]{4}$";
    //private static final Pattern EIRCODE_PATTERN = Pattern.compile(EIRCODE_REGEX);
    private static final String PHONE_REGEX = "^08[3679][0-9]{7}$";

    public Customer(){
        
    }


    public int getID(){
        return this.getID();
    }


    public String getName(){
        return null;
    }


    public String getAddress(){
        return null;
    }


    public String getEircode(){
        return null;
    }


    public String getPhoneNo(){
        return null;
    }


    public void setFirstName(String name){
        if (validateFirstName(name) == true){
            this.firstName = name;
        }
    }


    public void setLastName(String name){
        if (validateLastName(name) == true){
            this.lastName = name;
        }
    }

    
    public void setPhoneNo(String phoneNumber){
        if(validatePhoneNo(phoneNumber) == true){
            this.phoneNo = phoneNo;
        }
    }


    public void setAddress(String address){

    }


    public void setEircode(String eircode){
        if (validateEircode(eircode)){
            this.eircode = eircode;
        }
        else {
            // TODO: 
        }
    }


    /*
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


    /*  
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

    /*
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

    /*
     * Checks if entered string matches the 7-character format of a typical Eircode, e.g. D08VF8H. Returns false if the length of the 
     * string is greater or less than 7 or does not match the usual format.
     */
    protected boolean validateEircode(String eircode){
        if (eircode != null){
            if(eircode.length() == 7){
                if (eircode.matches(EIRCODE_REGEX)){
                    return true;
                }
                return false;
            }
            return false;
        }
        
        return false;
    }

    /*
     * Checks a phone number to ensure the following:
     * -> Length exactly 10 digits
     * -> Begins with the digits of one of 4 mobile network operators, i.e. 083, 086, 087, 089
     * -> Does not contain letters or symbols
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

}
