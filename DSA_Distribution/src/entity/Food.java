/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayQueue;
import adt.ListInterface;
import adt.QueueInterface;
import boundary.DonationUI;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import utility.DonationMsg;

/**
 *
 * @author Asus
 */
public class Food extends Donor {

    private static QueueInterface<Food> foodQueue = new ArrayQueue<>();
    private static int MAX_QTY = 100;
    private LocalDate donationDate = LocalDate.now();
    DonationInitializer donationIni = new DonationInitializer();

    private static int nextID = 1000;
    private String dfID;
    private LocalDate expiry;
    private String type;
    private int qty;
    Scanner scanner = new Scanner(System.in);
    DonationUI select = new DonationUI();

    //no args constrctor    
    public Food() {
        foodQueue = donationIni.InitializeFood();
    }


    //Full args constructor
    public Food(LocalDate expiry, String type, int qty, String donorID, LocalDate donationDate) {
        super(donorID);
        this.dfID = generateDonationID();
        this.expiry = expiry;
        this.type = type;
        this.qty = qty;
        donationDate = LocalDate.now();
    }

    public Food(String dfID, LocalDate expiry, String type, int qty, String donorID, LocalDate donationDate) {
        super(donorID);
        this.dfID = dfID;
        this.expiry = expiry;
        this.type = type;
        this.qty = qty;
        donationDate = LocalDate.now();
    }

    //Empty constructor
    public Food(String donorID, String dfID, LocalDate donationDate) {
        super(donorID);
        this.dfID = generateDonationID();

    }

    private String generateDonationID() {
        return "DF" + (++nextID);
    }

    public String getDfID() {
        return dfID;
    }

    public void setDfID(String dfID) {
        this.dfID = dfID;
    }

    public void foodMain() {
        switch (select.getFoodMain()) {
            case 1:
                Food food = new Food(getDonorID(), getDfID(), LocalDate.now());
                food.addFood(food);
                foodQueue.enqueue(food);
                foodMain();
                break;
            case 2:
                //remove
                removeDonation();
                foodMain();
                break;
            case 3:
                //search
                searchDonation();
                displayNext();
                foodMain();
                break;
            case 4:
                //update
                updateDonation(select.getUpdateUI());
                foodMain();
                break;
            case 5:
                //list all
                displayQueue();
                foodMain();
                break;
            case 6:
                //generate report
                generateReport();
                foodMain();
            default:
                break;
        }
    }

    public void addFood(Food food) {
        setFoodType(food);
        setExpiry(food);
    }

    public void removeDonation() {
        displayQueueZip();
        int rm = select.getRemoveUI();
        for (int i = 0; i < foodQueue.size(); i++) {
            if (i == rm) {
                Food rmFood = foodQueue.dequeue();
                System.out.println(rmFood);
            } else {
                foodQueue.enqueue(foodQueue.dequeue());
            }
        }
    }

    public void searchDonation() {
        if (!foodQueue.isEmpty()) {
            System.out.println("What are yu searching for? ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (foodQueue.getFront().getType().equals("Preserved")) {

                    }
            }
        } else {
            System.out.println("The food queue is empty.");
        }
    }

    public void displayNext() {
        if (!foodQueue.isEmpty()) {
            Food nextFood = foodQueue.getFront();
            System.out.println("Next food item: " + nextFood.getType() + ", Expiry: " + nextFood.getExpiry());
        } else {
            System.out.println("The food queue is empty.");
        }
    }

    public void displayAll() {
        if (!foodQueue.isEmpty()) {
            System.out.println("Listing all food items in the queue:");
            displayQueue(); // Assuming displayQueue() is a method that lists all items
        } else {
            System.out.println("The food queue is empty.");
        }
    }

    public void displayQueue() {
        if (foodQueue.isEmpty()) {
            DonationMsg.displayEmpty();
        } else {
            System.out.println("Queue contents:\n");

            for (int i = 0; i < foodQueue.size(); i++) {
                Food currentFood = foodQueue.dequeue(); // Dequeue the item
                System.out.println("  ID: " + currentFood.getDfID());
                System.out.println("  Type: " + currentFood.getType());
                System.out.println("  Expiry Date: " + currentFood.getExpiry());
                System.out.println("  Quantity: " + currentFood.getQty() + "\n");
                foodQueue.enqueue(currentFood); // Enqueue it back to maintain the queue
            }
        }
    }

    public void displayQueueZip() {
        if (foodQueue.isEmpty()) {
            DonationMsg.displayEmpty();
        } else {
            System.out.println("Queue contents:");
            System.out.println("Item\t|Type\t|Exp Date");
            for (int i = 0; i < foodQueue.size(); i++) {
                Food currentFood = foodQueue.dequeue(); // Dequeue the item
                System.out.println((i + 1));
                System.out.print("\t|" + currentFood.getType());
                System.out.print("\t|" + currentFood.getExpiry());
                foodQueue.enqueue(currentFood); // Enqueue it back to maintain the queue
            }
        }
    }

    public void updateDonation(int index) {
        for (int i = 0; i < foodQueue.size(); i++) {
            if (i == index) {
                Food firstFood = foodQueue.dequeue(); // Remove the item to be updated
                System.out.println("Updating food item: " + firstFood.getType());
                setFoodType(firstFood); // Update food type
                setExpiry(firstFood); // Update expiry date
                foodQueue.enqueue(firstFood); // Re-enqueue the updated item
            } else {
                foodQueue.enqueue(foodQueue.dequeue());
            }
        }
    }

    public void generateReport() {
        int preserveTtl = 0, othersTtl = 0;
        if (foodQueue.isEmpty()) {
            DonationMsg.displayEmpty();
        } else {
            System.out.println("Summary report:");

            for (int i = 1; i < foodQueue.size(); i++) {
                if ("Preserved".equals(foodQueue.getFront().getQty())) {
                    preserveTtl++;
                } else {
                    othersTtl++;
                }
            }
            System.out.println("Total Preserved Food Item Donation: " + preserveTtl);
            System.out.println("Other Food Total donation: " + othersTtl);
            System.out.println("Total donations: " + preserveTtl + othersTtl);
        }
    }

    //Sub functions
    //-------------------------------------------------------------
    public void setFoodQty(Food food) {
        food.qty = select.getFoodQty();

    }

    public void setFoodType(Food food) {
        switch (select.getFoodType()) {
            case 1:
                food.type = "Preserved";
                setFoodQty(food);
                break;
            case 2:
                System.out.println("What did you prepare? ");
                food.type = scanner.nextLine();
                break;
            case 3:
                System.out.println("Exit");
                foodMain();
                break;
            default:
                System.out.println("Invalid Selection. ");

        }
    }

    public void setExpiry(Food food) {
        int date, month, year;

        while (true) {
            System.out.println("When is the expiry date? ");
            System.out.print("Day: ");
            date = scanner.nextInt();
            System.out.print("Month: ");
            month = scanner.nextInt();
            System.out.print("Year: ");
            year = scanner.nextInt();

            if (chkDateValidity(date, month, year)) {
                if (chkDate(date, month, year)) {
                    DonationMsg.displayExpiryMsg();
                    //Add exit button
                } else {
                    food.expiry = LocalDate.of(year, month, date);
                    break;
                }
            }

        }
    }

    //Checks and Validations--------------------------------
    //-------------------------------------------------------
    public boolean chkDate(int date, int month, int year) {
        String dateStr = String.format("%04d-%02d-%02d", year, month, date);
        try {
            LocalDate exdate = LocalDate.parse(dateStr);
            if (exdate.isBefore(donationDate)) {
                DonationMsg.displayExpiryMsg();
                return true;
            }
        } catch (DateTimeParseException e) {
            DonationMsg.displayInvalidDate();
        }
        return false;
    }

    public boolean chkDateValidity(int date, int month, int year) {
        boolean valid = true;
        if (month < 1 || month > 12) {
            DonationMsg.displayInvalidDate();
            valid = false;
        } else if (date > 31 || date < 1) {
            DonationMsg.displayInvalidDate();
            valid = false;
        } else {
            try {
                // This will throw an exception if the date is not valid
                LocalDate.of(year, month, date);
            } catch (DateTimeParseException | IllegalArgumentException e) {
                DonationMsg.displayInvalidDate();
                valid = false;
            }

        }
        return valid;
    }

    public int getYear() {
        return donationDate.getYear();
    }

    public int getMonth() {
        return donationDate.getMonthValue();
    }

    public int getDate() {
        return donationDate.getDayOfMonth();
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public String getType() {
        return type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ID:" + dfID + "Food\nDate donated: " + donationDate + "\nExpiry date: " + expiry + "\nFood type: " + type;
    }

}
