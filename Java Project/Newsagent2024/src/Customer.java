import java.util.regex.Pattern;

public class Customer {
    
    /*
     * Member variables outlining basic customer information
     */
    private String firstName;
    private String address;
    private String eircode;
    private String phoneNo;
    private int id;

    // The variables below inform the format of the eicode and phone numbers when being added by the User
    private static final String EIRCODE_REGEX = "^[A-Za-z0-9]{3}[A-Za-z0-9]{4}$";
    private static final Pattern EIRCODE_PATTERN = Pattern.compile(EIRCODE_REGEX);
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

    }

    public void setLastName(String name){

    }

    public void setPhoneNo(String phoneNumber){

    }

    public void setAddress(String address){

    }

    public void setEircode(String eircode){

    }

    protected boolean validateFirstName(String firstName){
        return false;
    }

    protected boolean validateLastName(String lastName){
        return false;
    }

    protected boolean validateAddress(String address){
        return false;
    }

    protected boolean validateEircode(String eircode){
        return false;
    }
    protected boolean validatePhoneNo(String phoneNo){
        return false;
    }

}
