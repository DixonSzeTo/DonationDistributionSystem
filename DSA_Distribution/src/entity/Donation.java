/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayQueue;
import adt.List;
import adt.ListInterface;
import adt.QueueInterface;
import boundary.DonationUI;
import java.time.LocalDate;
import utility.DonationMsg;

/**
 *
 * @author Asus
 */
public class Donation extends Donor {

    public QueueInterface<Food> foodQueue;
    private static int MAX_QTY = 10;
    private static int currentIndex;
    private ListInterface<Cash> cashList;
    private String item;
    private ListInterface<Donation> donationList;
    private LocalDate donationDate;
    DonationUI select = new DonationUI();

    public Donation() {
    }

    public Donation(QueueInterface<Food> foodQueue, ListInterface<Cash> cashList, LocalDate donationDate, String donorID) {
        super(donorID);
        this.foodQueue = foodQueue;
        this.cashList = cashList;
        this.donationDate = LocalDate.now();
    }

    

    //Main interface
    public void setMain() {
        int choice = select.getDonationAction();
        if (choice < 1 || choice > 4) {
            DonationMsg.displayInvalidSelection();
            setMain();
        }
                Food newFood = new Food();
                Cash newCash = new Cash();
        switch (choice) {
            case 1:
                newFood.foodMain();
                break;

            case 2:
                newCash.cashMain();
                break;

            case 3:
                //show all
                newFood.displayQueue();
                newCash.displayList();
                break;

            case 4:
                //report
                break;
            default:
                break;
        }

    }

    /*public void setItem() { //what to donate
        System.out.println("test getItem");
        int choice = select.getDonorChoice();

        switch (choice) {

            case 1:
                item = "Cash";
                Cash cash = new Cash();
                cash.setAmount();
                cashList.add(cash);
                break;
            case 2:
                item = "Food ";
                Food food = new Food();
                food.foodMain();
                currentIndex++;
                break;

            case 3:
            //something happens

        }

    }


    public void donationRemove() {
        //display all
        //let user select
        //
        //continue until press 0
    }

    public void donationSearch() {

    }

    public void donationUpdate() {

    }
*/
        //System.out.println(foodQueue[lastIndex]);
        //if ( <= ) {
        //    displayAll(n - 1)
        //}
    //}
}


