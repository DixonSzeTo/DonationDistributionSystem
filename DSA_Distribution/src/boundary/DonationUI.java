/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;
import utility.DonationMsg;

/**
 *
 * @author Asus
 */
public class DonationUI {
    Scanner scanner = new Scanner(System.in);
    
    
    //Donation UI
    public int getDonationAction(){
        DonationMsg.displayMainSelection();
        System.out.print("Selection: ");
        int action = scanner.nextInt();
        if(action < 1 || action > 4){
            DonationMsg.displayError();
            getDonationAction();
        }
        return action;
    }

    
    //Food UI----------------
    public int getFoodType(){
        DonationMsg.displayFoodType();
        int choice = scanner.nextInt();
        if(choice < 1 || choice > 3){
            DonationMsg.displayError();
            getFoodType();
        }
        return choice;
    }
    
    public int getFoodMain(){
        System.out.println("""
                           
                           -------------------------
                           Food Donation Department.
                           -------------------------""");
        DonationMsg.displaySelection();
        System.out.print("Selection: ");
        int foodChoice = scanner.nextInt(); 
        if(foodChoice < 1 || foodChoice > 6){
            DonationMsg.displayError();
            getFoodMain();
        }
        return foodChoice;
    }
    
    public int getFoodQty(){
        DonationMsg.displayQty();
        int qty = scanner.nextInt();
        if(qty < 1){
            DonationMsg.displayError();
            getFoodQty();
        }
        return qty;
    }
    
    //CashUI
    public int getCashMain(){
        System.out.println("\nCash Donation Department.");
        DonationMsg.displaySelection();
        System.out.print("Selection: ");
        int cashChoice = scanner.nextInt(); 
        if(cashChoice < 1 || cashChoice > 3){
            DonationMsg.displayError();
            getCashMain();
        }
        return cashChoice;
    }
      
    
    //Function UI
    public int getRemoveUI(){
        System.out.println("Which record to remove? ");
        int removeChoice = scanner.nextInt();
        //Validation in class
        return removeChoice;
    }
    
    public int getUpdateUI(){
        System.out.println("Which record to update? ");
        int updateChoice = scanner.nextInt(); 
        //Validation in class
        return updateChoice;
    }
        
}
