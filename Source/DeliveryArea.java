
public class DeliveryArea {

    //Class Variables
    private int deliveryAreaID;
    private String deliveryAreaName;

    public DeliveryArea() {

    }
        //Getters and setters
    public int getDeliveryAreaID() {
        return this.deliveryAreaID;
    }

    public String getDeliveryAreaName() {
        return this.deliveryAreaName;
    }

    //setter for deliveryAreaName with validation
    public void setDeliveryAreaName(String deliveryAreaName) {
        if(validateDeliveryAreaName(deliveryAreaName) == true) {
            this.deliveryAreaName = deliveryAreaName;
        }
    }


    //validation methods

    boolean validateDeliveryAreaName(String deliveryAreaName){
        if(deliveryAreaName != null) {
            if(deliveryAreaName.length() >= 2 && deliveryAreaName.length() <= 50) {
                return true;
            }
        }
        return false;
    }

    boolean createDeliveryAreaInDB(DeliveryArea da){
        try {
            MySQLConnector sql = new MySQLConnector();
            return sql.insertDeliveryAreaDetails(da);
        } catch(Exception e) {
            System.out.println("Error inserting delivery area into database.");
            return false;
        }
    }
}