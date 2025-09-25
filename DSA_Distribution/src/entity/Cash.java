/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.List;
import adt.ListInterface;
import boundary.DonationUI;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Cash extends Donor {

    private ListInterface<Cash> cashList = new List<Cash>();
    private LocalDate donationDate = LocalDate.now();
    private double amount;
    Scanner scanner = new Scanner(System.in);
    DonationUI select = new DonationUI();

    public Cash() {
    }

    public Cash(double amount, LocalDate donationDate) {
        this.amount = amount;
        donationDate = LocalDate.now();
    }

    public void cashMain() {

        switch (select.getCashMain()) {
            case 1:
                Cash cash = new Cash();
                cash.setAmount(cash);
                cashMain();
                break;
            case 2:
                //remove
                removeCash();
                cashList.display();
                cashMain();
                break;
            case 3:
                searchCash();
                break;
            case 4:
                //update
                updateCash();
                break;
            case 5:
                //list all
                displayList();
                break;
            case 6:
            //generate report
            default:
                break;
        };
    }

    public void setAmount(Cash cash) {

        do {
            System.out.print("How much are you donating? \nRM ");
            cash.amount = scanner.nextDouble();

            if (amount < 1 || amount > 9999999) { //need a better way
                System.out.println("Error, try again");
            }

        } while (amount < 1 || amount > 9999999);

        System.out.printf("RM %.2f", amount);

        cashList.add(cash);
    }

    private void removeCash() {
        cashList.display();
        int i;
        do {
            i = select.getRemoveUI();
        } while (i > cashList.getCount() || i < 1);

        cashList.remove(i + 1);
        System.out.println("Record number " + select + " has been removed.");

    }

    private void searchCash() {
        System.out.print("Enter amount: ");
        double filter = scanner.nextDouble();
        for (int a = 0; a < cashList.getCount(); a++) {
            Cash search = cashList.getDetails(a);
            if (search.getAmount() == filter) {
                {
                    System.out.println(search);

                }
            }
        }
    }

    private void updateCash() {
        cashList.display();  // Display all donors
        System.out.print("Enter record number: ");
        int num = scanner.nextInt();

        Cash select = cashList.remove(num);
        select.setAmount(select);
        cashList.add(select);

    }
    
    public void displayList(){
        cashList.display();
    }

    public double getAmount() {
        return amount;
    }

}
