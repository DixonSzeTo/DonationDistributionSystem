package control;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dixon
 */

import adt.List;
import adt.ListInterface;
import entity.Donation;
import entity.Donee;
import entity.DoneeType;

public class DoneeUtility {
    private static ListInterface<Donee> doneeList = new List<>();

    public static void initialize(ListInterface<Donee> list) {
        doneeList = list;
    }
   
    public static int getNextDoneeId() {
    ListInterface<Donee> donees = getAllDonees();
    int maxId = 0;
    
    // Find the highest current ID
    for (int i = 0; i < donees.getCount(); i++) {
        Donee donee = donees.getDetails(i);
        String id = donee.getId();
        int currentIdNumber = Integer.parseInt(id.substring(1)); // Extract number from ID (e.g., D5 -> 5)
        if (currentIdNumber > maxId) {
            maxId = currentIdNumber;
        }
    }
    
    // Return the next ID number
    return maxId + 1;
}

    // Add a new Donee
    public static void addNewDonee(String id, String name, DoneeType type, ListInterface<Donation> donations) {
        Donee newDonee = new Donee(id, name, type, donations);
        doneeList.add(newDonee);
        
    }

    // Search for a Donee by ID
    
    
    public static Donee searchDonee(String id) {
        for (int i = 1; i <= doneeList.getCount(); i++) {
            Donee donee = doneeList.getDetails(i);
            if (donee.getId().equals(id)) {
                return donee;
            }
        }
        return null;
    }

    // Remove a Donee by ID
    public static boolean removeDonee(String id) {
        for (int i = 0; i < doneeList.getCount(); i++) {
            Donee donee = doneeList.getDetails(i);
            if (donee.getId().equals(id)) {
                doneeList.remove(i+1);
                return true;
            }
        }
        return false;
    }
    
    // Update Donee information
    public static boolean updateDonee(String id, String newName, DoneeType newType, ListInterface<Donation> newDonations) {
    int position = findDoneePosition(id);
    if (position == -1) {
        return false; // Donee with the given ID not found
    }
    Donee updatedDonee = new Donee(id, newName, newType, newDonations);
    return doneeList.update(position, updatedDonee);
}

    // Find the position of a Donee by ID
    private static int findDoneePosition(String id) {
    for (int i = 0; i < doneeList.getCount(); i++) {
        Donee donee = doneeList.getDetails(i);
        if (donee.getId().equals(id)) {
            return i; // Return position if ID matches
        }
    }
    return -1; // ID not found
}
   
    public static String generateDoneeReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 1; i <= doneeList.getCount(); i++) {
            Donee donee = doneeList.getDetails(i);
            report.append(donee.toString()).append("\n"); // Assuming Donee has a proper toString() method
        }
        return report.toString();
    }
    
    // New filter method
    public static ListInterface<Donee> filterDonees(String criteria) {
        ListInterface<Donee> filteredList = new List<>();
        for (int i = 1; i <= doneeList.getCount(); i++) {
            Donee donee = doneeList.getDetails(i);
            if (donee.getName().contains(criteria) || donee.getType().name().contains(criteria.toUpperCase())) {
                filteredList.add(donee);
            }
        }
        return filteredList;
    }
    

    
 public static ListInterface<Donee> filterDoneesByName(String name) {
    ListInterface<Donee> filteredList = new List<>();
    for (int i = 1; i <= getAllDonees().getCount(); i++) {
        Donee donee = getAllDonees().getDetails(i);
        if (donee.getName().equalsIgnoreCase(name)) {
            filteredList.add(donee);
        }
    }
    return filteredList;
} 
 public static ListInterface<Donee> filterDoneesByType(DoneeType type) {
    ListInterface<Donee> filteredList = new List<>();
    for (int i = 1; i <= getAllDonees().getCount(); i++) {
        Donee donee = getAllDonees().getDetails(i);
        if (donee.getType() == type) {
            filteredList.add(donee);
        }
    }
    return filteredList;
}  
    public static ListInterface<Donee> getAllDonees() {
    return doneeList;
}
 

    // Display donee list
    public static void displayDoneeList() {
        if (doneeList.isEmpty()) {
            System.out.println("Donee list is empty.");
        } else {
            doneeList.display();
        }
    }
    
  // Validate ID
    public static boolean isValidId(String id) {
        // Ensure ID is non-null and non-empty
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        // Check if ID exists in the donee list
        for (int i = 0; i < doneeList.getCount(); i++) {
            Donee donee = doneeList.getDetails(i);
            if (donee.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    // Validate Name
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // Validate Donation Amount
    public static boolean isValidDonationAmount(double amount) {
        return amount > 0;
    }

    // Validate Date Format (yyyy-MM-dd)
    public static boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

   }