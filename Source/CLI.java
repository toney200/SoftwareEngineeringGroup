import Publications.Publication;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {
    static Scanner sc = new Scanner(System.in);

    CLI() throws Exception{
        int userSelect = 0;
        boolean validInput = false;

        System.out.println("Select from the following options:\n");
        System.out.println("1. Customer Features    " +
                "2. Order Functions     " +
                "3. Delivery Area Management    " +
                "4. Staff Management");
        while(!validInput) {
            try {
                userSelect = sc.nextInt();
                switch (userSelect) {
                    case 1:
                        validInput = true;
                        customerRouting();
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

    /*
    The customer routing method is accessed from main when selecting "Customer Features" in the opening console window.
    The method will route the user to the next customer-related function based on their inputs here.
     */
    static void customerRouting(){
        sc = new Scanner(System.in);
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
        sc = new Scanner(System.in);
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


    static void publicationCustomer(Publication newPublication){
    
        boolean validInput = false;

        while(!validInput){

            try{
            System.out.println("Enter publication price: ");
            newPublication.setPubCost(sc.nextDouble());

            System.out.println("Enter publication name: ");
            newPublication.setPubName(sc.next());

            System.out.println("Enter publication type: ");
            newPublication.setPubType(sc.next());

            System.out.println("Enter publication author: ");
            newPublication.setPubAuthor(sc.next());

            System.out.println("Enter publication frequency: ");
            newPublication.setPubFrequency(sc.next());

            validInput = true;
            }
            catch(Exception e){
                System.out.println("Error encountered when trying to enter publication information!!! Please try again");
                validInput = false;
            }

       }


    }


}
