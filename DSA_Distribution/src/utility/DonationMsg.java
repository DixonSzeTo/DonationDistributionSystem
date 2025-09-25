/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Asus
 */
public class DonationMsg {

    //Donation class
    public static void displayMainSelection() {
        System.out.println("Welcome to Donation Mangement, how can we help? ");
        System.out.println("1. Food donation \n2. Cash Donation \n3. Generate donation summary report");
        //System.out.println("4. Update a donation \n5. List all donations \n6. Generate donation summary report");
        System.out.println("4. Exit");
        System.out.print("Selection: ");
    }

    public static void displaySelection() {
        System.out.println("1. Add a new donation \n2. Remove a donation \n3. Search for a donation");
        System.out.println("4. Update a donation \n5. List all donations \n6. Generate donation summary report\n");
        System.out.print("Seletion: ");
    }

    //-----------------------------------------------
    //Food class
    //-----------------------------------------------
    public static void displayFoodType() {

        System.out.println("What type of food is donated?");
        System.out.print("1. Canned/Boxed\n2. Freshly prepared\n3. Exit\nSelection: ");
    }

    public static void displayQty(){
        System.out.print("Enter Quantity: ");
    }
    public static void displayExpiryMsg() {
        System.out.println("Item has expired, please try again");
    }

    public static void displayInvalidDate() {
        System.out.println("Invalid date, try again");
    }

    //-------------------------------------------------
    //Misc 
    //-------------------------------------------------
    public static void displayError() {
        System.out.println("Error, try again");
    }

    public static void displayInvalidSelection() {
        System.out.println("Invalid selection, try again");
    }

    public static void displayEmpty() {
        System.out.println("The queue is empty. ");
    }

}
