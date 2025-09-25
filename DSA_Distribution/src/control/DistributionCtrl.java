package control;

import boundary.DoneeUI;
import adt.ArrayQueue;
import adt.List;
import adt.ListInterface;
import adt.QueueInterface;
import boundary.DoneeInitialize;
import entity.Distribution;
import entity.DistributionInitializer;
import entity.Donee;
import entity.DonationInitializer;
import entity.Food;
import java.util.Scanner;

public class DistributionCtrl {

    private final Scanner scanner = new Scanner(System.in);

    private ListInterface<Donee> dList;
    private DoneeUI doneeUI = new DoneeUI();
    private DoneeInitialize doneeIni = new DoneeInitialize();

    private ListInterface<Distribution> disList = new List<>();
    private DistributionInitializer disIni = new DistributionInitializer();

    private QueueInterface<Food> foodQueue = new ArrayQueue<>();
    private DonationInitializer donationIni = new DonationInitializer();

    public DistributionCtrl() {
        disList = disIni.initializeDistr();
        dList = doneeIni.initializeDonees();
        foodQueue = donationIni.InitializeFood();
    }

    public void addNewDistribution() {
        doneeUI.displayDoneeList();

        System.out.print("Enter Donee ID: "); //D4
        String id = scanner.nextLine().toUpperCase().trim();
        Donee foundDonee = getDoneeById(id);
        if (foundDonee != null) {
            // Display donee details
            System.out.println("\nSelected Donee:");
            System.out.println("Donee ID: " + foundDonee.getId() + "\nDonee Name: " + foundDonee.getName());
            System.out.println("Donation List:");
            displayFoodQueue();

            System.out.print("Enter Food ID: "); //DF...
            String foodID = scanner.nextLine().toUpperCase().trim();
            Food foundFood = findFoodById(foodID);
            if (foundFood == null) {
                System.out.println("Food with ID " + foodID + " not found.");
            }

            System.out.print("Enter Quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine();

            if (foundFood != null) {
                if (qty > foundFood.getQty()) {
                    System.out.println("\nQuantity of Food " + foundFood.getDfID() + " insufficient.");
                } else {
                    Distribution distribution = new Distribution(foundDonee, foundFood.getDfID(), qty, "Pending");
                    disList.add(distribution);
                    foundFood.setQty(foundFood.getQty() - qty);
                    updateFoodInQueue(foundFood);
                    System.out.println("Distribution Added Successfully");
                }
            }
        } else {
            System.out.println("Donee with ID " + id + " not found.");
        }
    }

    public void viewAllDistributions() {
        System.out.println("All Distributions:");
        for (int i = 0; i < disList.getCount(); i++) {
            Distribution distribution = disList.getDetails(i);
            System.out.println("Distribution to " + distribution.getDonee().getId()
                    + ", Food ID: " + distribution.getItems()
                    + ", Quantity: " + distribution.getQuantity()
                    + ", Status: " + distribution.getStatus());
        }
    }

    public void viewDistributionsByDonee() {
        System.out.print("Enter Donee ID: "); //D4
        String id = scanner.nextLine().toUpperCase().trim();
        System.out.println("Distributions for Donee ID: " + id);
        for (int i = 0; i < disList.getCount(); i++) {
            Distribution distribution = disList.getDetails(i);
            if (distribution.getDonee().getId().equals(id)) {
                System.out.println("Food ID: " + distribution.getItems()
                        + ", Quantity: " + distribution.getQuantity()
                        + ", Status: " + distribution.getStatus());
            }
        }
    }

    public void updateDistribution() {
        disList.display();
        Distribution foundDistribution = null;
        Food foundFood = null;

        System.out.print("Enter Donee ID: ");
        String id = scanner.nextLine().toUpperCase().trim();

        System.out.print("Enter Food ID: ");
        String fId = scanner.nextLine().toUpperCase().trim();
        int index = -1;
        for (int i = 0; i < disList.getCount(); i++) {
            Distribution distribution = disList.getDetails(i);
            if (distribution.getDonee().getId().equals(id) && distribution.getItems().equals(fId)) {
                foundDistribution = distribution;
                index = i;
                break;
            }
        }
        if (foundDistribution == null) {
            System.out.println("Distribution not found for Donee ID " + id + " and Food ID " + fId);
            return;
        }
        for (int i = 0; i < foodQueue.size(); i++) {
            Food food = foodQueue.get(i);
            if (food.getDfID().equals(fId)) {
                foundFood = food;
                break;
            }
        }
        if (foundFood == null) {
            System.out.println("Food with ID " + fId + " not found in inventory.");
            return;
        }
        System.out.print("Enter new Quantity: ");
        int newQty = scanner.nextInt();
        scanner.nextLine();
        String newStatus = selectStatus();
        int qtyDifference = newQty - foundDistribution.getQuantity();

        if (qtyDifference > 0 && foundFood.getQty() < qtyDifference) {
            System.out.println("Insufficient inventory for Food ID " + fId + " to increase quantity by " + qtyDifference);
            return;
        }
        foundDistribution.setQuantity(newQty);
        foundDistribution.setStatus(newStatus);
        foundFood.setQty(foundFood.getQty() - qtyDifference);
        updateFoodInQueue(foundFood);
        disList.update(index, foundDistribution);
        System.out.println("Distribution updated successfully.");
    }

    public void removeDistributionById() {
        System.out.print("Enter Donee ID: ");
        String doneeId = scanner.nextLine().toUpperCase().trim();

        System.out.print("Enter Food ID: ");
        String foodId = scanner.nextLine().toUpperCase().trim();

        boolean distributionFound = false;

        // Search for the distribution with matching Donee ID and Food ID
        for (int i = 0; i < disList.getCount(); i++) {
            Distribution distribution = disList.getDetails(i);
            if (distribution.getDonee().getId().equals(doneeId) && distribution.getItems().equals(foodId)) {
                // Remove the distribution from the list
                disList.remove(i);
                distributionFound = true;
                System.out.println("Distribution for Donee ID " + doneeId + " and Food ID " + foodId + " has been removed.");
                break;
            }
        }

        if (!distributionFound) {
            System.out.println("No matching distribution found for Donee ID " + doneeId + " and Food ID " + foodId + ".");
        }
    }
// Method to select a status from the predefined list

    private String selectStatus() {
        System.out.println("Select Status:");
        System.out.println("1. Pending");
        System.out.println("2. On Delivery");
        System.out.println("3. Canceled");
        System.out.println("4. Delivered");
        System.out.println("5. Transferred");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        switch (choice) {
            case 1:
                return "Pending";
            case 2:
                return "On Delivery";
            case 3:
                return "Canceled";
            case 4:
                return "Delivered";
            case 5:
                return "Transferred";
            default:
                System.out.println("Invalid choice. Defaulting to 'Pending'.");
                return "Pending";
        }
    }

    public Food findFoodById(String foodID) {
        Food foundFood = null;

        for (int i = 0; i < foodQueue.size(); i++) {
            Food food = foodQueue.get(i);
            if (food.getDfID().equals(foodID)) { // Check if the ID matches
                foundFood = food;
                break;
            }
        }
        return foundFood;
    }

    private void displayFoodQueue() {
        System.out.println("Food Queue:");
        for (int i = 0; i < foodQueue.size(); i++) {
            Food food = foodQueue.get(i);
            System.out.println("Food ID: " + food.getDfID() + ", Quantity: " + food.getQty());
        }
    }

    private void updateFoodInQueue(Food updatedFood) {
        for (int i = 0; i < foodQueue.size(); i++) {
            Food food = foodQueue.get(i);
            if (food.getDfID().equals(updatedFood.getDfID())) {
                // Update the existing Food object's quantity
                food.setQty(updatedFood.getQty());
                break;
            }
        }
    }

    public Donee getDoneeById(String id) {
        Donee foundDonee = null;
        for (int i = 0; i < dList.getCount(); i++) {
            Donee donee = dList.getDetails(i);
            if (donee != null && donee.getId().equals(id)) {
                foundDonee = donee;
                break;
            }
        }
        return foundDonee;

    }
}
