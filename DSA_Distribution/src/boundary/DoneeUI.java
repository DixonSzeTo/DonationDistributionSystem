package boundary;

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
import control.DoneeUtility;
import entity.Donation;
import entity.Donee;
import entity.DoneeType;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DoneeUI {

    private static final Scanner scanner = new Scanner(System.in);
    private  ListInterface<Donee> list = new List<>();
    DoneeInitialize doneeIni = new DoneeInitialize();

    public DoneeUI() {
        list = doneeIni.initializeDonees();
    }

    public void start() {
        DoneeUtility.initialize(new DoneeInitialize().initializeDonees());
        while (true) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    displayDoneeList();
                    break;
                case 2:
                    addDonee();
                    break;
                case 3:
                    searchDonee();
                    break;
                case 4:
                    removeDonee();
                    break;
                case 5:
                    updateDonee();
                    break;
                case 6:
                    listDoneeWithAllDonations();
                    break;
                case 7:
                    filterDonees();
                    break;
                case 8:
                    generateReport();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- Donee Management System ---");
        System.out.println("1. Display Donee List");
        System.out.println("2. Add Donee");
        System.out.println("3. Search Donee");
        System.out.println("4. Remove Donee");
        System.out.println("5. Update Donee");
        System.out.println("6. List Donee with All Donations");
        System.out.println("7. Filter Donees");
        System.out.println("8. Generate Report");
        System.out.println("9. Exit");
        System.out.print("\nEnter your choice: ");
    }

    private int getUserChoice() {
        int choice = -1;
        boolean validChoice = false;

        while (!validChoice) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (choice >= 1 && choice <= 9) {
                    validChoice = true;
                } else {
                    System.out.println("\n[ Invalid choice. Please enter a number between 1 and 9 ] ");
                    System.out.print(" Enter your choice: ");
                }
            } catch (InputMismatchException e) {
                System.out.println(" \n[ Invalid input.Please enter a numeric value ] ");
                System.out.print("Enter your choice: ");

                scanner.next();
            }
        }

        return choice;
    }

    public void displayDoneeList() {
        System.out.println("\n------------------------ Donee List ------------------------");
        DoneeUtility.displayDoneeList();
    }

    public void refreshDoneeList(ListInterface<Donee> updatedList) {
        this.list = updatedList;
        displayDoneeList();
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    private void addDonee() {
        int nextIdNumber = DoneeUtility.getNextDoneeId();
        String id = "D" + nextIdNumber;

        System.out.print("Enter Donee name: ");
        String name = scanner.nextLine();
        while (!isValidName(name)) {
            System.out.print(" \n [ Invalid name. Name cannot contain digits or special characters ] \n\n Please enter a valid Donee name: ");
            name = scanner.nextLine();
        }

        DoneeType type = null;
        while (type == null) {
            System.out.print("Enter Donee type (I for INDIVIDUAL, O for ORGANIZATION, F for FAMILY): ");
            type = getDoneeType(scanner.nextLine());
            if (type == null) {
                System.out.println(" \n [ Invalid type. Please enter I for INDIVIDUAL, O for ORGANIZATION, F for FAMILY ] ");
                System.out.print("\nEnter Donee type (I for INDIVIDUAL, O for ORGANIZATION, F for FAMILY): ");
            }
        }

        ListInterface<Donation> donations = new List<>();
        DoneeUtility.addNewDonee(id, name, type, donations);
        System.out.println(" \n Donee added successfully with ID: " + id + "!!");
    }

    private void searchDonee() {
        System.out.println("\n--- Search Donee ---");
        String id;
        Donee donee = null;

        while (true) {
            System.out.print("Enter Donee ID: ");
            id = scanner.nextLine();

            if (DoneeUtility.isValidId(id)) {
                donee = DoneeUtility.searchDonee(id);
                if (donee != null) {
                    break;
                } else {
                    System.out.println(" \n || Donee not found. Please try again || ");
                }
            } else {
                System.out.println(" \n [ Invalid ID format. Please enter a valid Donee ID ] ");
                System.out.print("\nEnter Donee ID: ");
            }
        }

        System.out.println(" \n Donee found: " + donee.getName() + ", Type: " + donee.getType() + " !!");
        System.out.println(" **************************************************************************");
    }

    private void removeDonee() {
        while (true) {
            displayDoneeList();
            System.out.print("Enter Donee ID to remove: ");
            String id = scanner.nextLine().trim();

            // Validate Donee ID
            while (!DoneeUtility.isValidId(id)) {
                System.out.print("\n[ Invalid ID ]\n\nPlease enter a valid Donee ID ");
                System.out.print("Enter Donee ID to remove: ");
                id = scanner.nextLine().trim();
            }

            // Confirm removal
            System.out.print("Are you sure you want to remove this donee? (Y/N): ");
            String confirmation = scanner.nextLine().trim().toUpperCase();

            if (confirmation.equals("Y")) {
                if (DoneeUtility.removeDonee(id)) {
                    System.out.println("\nDonee removed successfully!");
                    System.out.println("\nDonee list after removal:");
                    System.out.println("=========================");
                    displayDoneeList();
                } else {
                    System.out.println("\n|| Donee not found ||");
                }

                // Continue removing or exit
                System.out.print("\nDo you want to remove another donee? (Y/N): ");
                String continueRemoving = scanner.nextLine().trim().toUpperCase();
                if (!continueRemoving.equals("Y")) {
                    System.out.println("\nReturning to main menu...");
                    break;
                }
            } else if (confirmation.equals("N")) {
                System.out.println("\nReturning to main menu...");
                break;
            } else {
                System.out.println("\n[ Invalid input. Please enter 'Y' for yes or 'N' for no ]");
            }
        }
    }

    private void updateDonee() {
        while (true) {
            displayDoneeList();
            System.out.print("Enter Donee ID to update: ");
            String id = scanner.nextLine().trim();

            // Validate Donee ID
            while (!DoneeUtility.isValidId(id)) {
                System.out.print("\n[ Invalid ID , Please enter a valid Donee ID ] ");
                System.out.print("\n Enter Donee ID to update: ");
                id = scanner.nextLine().trim();
            }

            // Confirm update
            System.out.print("\nAre you sure you want to update this donee? (Y/N): ");
            String confirmation = scanner.nextLine().trim().toUpperCase();

            if (confirmation.equals("Y")) {
                // Get new name
                System.out.print("Enter new Donee name: ");
                String newName = scanner.nextLine().trim();
                while (!isValidName(newName)) {
                    System.out.print("\n[ Invalid name , Please enter a valid Donee name ] ");
                    System.out.print("Enter new Donee name: ");
                    newName = scanner.nextLine().trim();
                }

                // Get new type
                System.out.print("Enter new Donee type (I for INDIVIDUAL, O for ORGANIZATION, F for FAMILY): ");
                DoneeType newType = getDoneeType(scanner.nextLine().trim());
                while (newType == null) {
                    System.out.print("\n[ Invalid type , Please enter I for INDIVIDUAL, O for ORGANIZATION, F for FAMILY ]  ");
                    System.out.print("\nEnter new Donee type (I for INDIVIDUAL, O for ORGANIZATION, F for FAMILY): ");
                    newType = getDoneeType(scanner.nextLine().trim());
                }

                // Initialize new donations list (if needed)
                ListInterface<Donation> newDonations = new List<>();

                // Perform the update
                if (DoneeUtility.updateDonee(id, newName, newType, newDonations)) {
                    System.out.println("\nDonee updated successfully!");
                    System.out.println("\nDonee list after update:");
                    System.out.println("==========================");
                    displayDoneeList();
                } else {
                    System.out.println("\n|| Donee not found or update failed ||");
                }

                // Continue updating or exit
                System.out.print("\nDo you want to update another donee? (Y/N): ");
                String continueUpdating = scanner.nextLine().trim().toUpperCase();
                if (!continueUpdating.equals("Y")) {
                    System.out.println("\nReturning to main menu...");
                    break;
                }
            } else if (confirmation.equals("N")) {
                System.out.println("\nReturning to main menu...");
                break;
            } else {
                System.out.println("\n[ Invalid input , Please enter 'Y' for yes or 'N' for no ]");
            }
        }

    }

    private void listDoneeWithAllDonations() {
        System.out.println("\n--- List Donee with All Donations ---");
        System.out.print("Enter Donee ID: ");
        String doneeId = scanner.nextLine();
        Donee donee = DoneeUtility.searchDonee(doneeId);

        if (donee != null) {
            System.out.println("\n Donee: " + donee.getName() + ", Type: " + donee.getType());
            ListInterface<Donation> donations = donee.getDonations();
            if (donations.isEmpty()) {
                System.out.println("\n || No donations have been made by this donee || ");
            } else {

                for (int i = 1; i <= donations.getCount(); i++) {
                    Donation donation = donations.getDetails(i);
                    System.out.println(donation);
                }
            }
        } else {
            System.out.println("\nDonee not found.");
        }
    }

    private void filterDonees() {
        System.out.println("Choose filter criteria:");
        System.out.println("1. Filter by Name");
        System.out.println("2. Filter by ID");
        System.out.println("3. Filter by Type");
        System.out.print("Enter your choice: ");

        int filterChoice = -1;
        while (filterChoice < 1 || filterChoice > 3) {
            try {
                filterChoice = scanner.nextInt();
                scanner.nextLine();
                if (filterChoice < 1 || filterChoice > 3) {
                    System.out.println("\n[ Invalid choice , Please enter 1, 2, or 3 ] ");
                    System.out.print("\nEnter your choice: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n [Invalid input. Please enter a number] ");
                System.out.print("\nEnter your choice: ");
                scanner.next();
            }
        }

        ListInterface<Donee> filteredList = new List<>();
        switch (filterChoice) {
            case 1:
                System.out.print("Enter name to filter by: ");
                String name = scanner.nextLine();
                filteredList = DoneeUtility.filterDoneesByName(name);
                break;
            case 2:
                System.out.print("Enter ID to filter by: ");
                String id = scanner.nextLine();
                Donee donee = DoneeUtility.searchDonee(id);
                if (donee != null) {
                    filteredList.add(donee);
                }
                break;
            case 3:
                System.out.print("Enter Donee type (I for INDIVIDUAL, O for ORGANIZATION, F for FAMILY): ");
                DoneeType type = getDoneeType(scanner.nextLine());
                if (type != null) {
                    filteredList = DoneeUtility.filterDoneesByType(type);
                }
                break;
        }

        if (filteredList.isEmpty()) {
            System.out.println("\n ||  No donees found matching the criteria || ");
        } else {
            System.out.println("\n Filtered Donee List:");
            System.out.println("=====================");
            for (int i = 1; i <= filteredList.getCount(); i++) {
                System.out.println(filteredList.getDetails(i));
            }
        }
    }

    private void generateReport() {
        StringBuilder report = new StringBuilder("Donee Report:\n");
        System.out.println("==================");

        // Fetch all donees
        ListInterface<Donee> allDonees = DoneeUtility.getAllDonees();

        // Loop through each donee
        for (int i = 1; i <= allDonees.getCount(); i++) {
            Donee donee = allDonees.getDetails(i);

            report.append(String.format("Donee ID: %s\n", donee.getId()));
            report.append(String.format("Name: %s\n", donee.getName()));
            report.append(String.format("Type: %s\n", donee.getType()));

            // Fetch donations for the donee
            ListInterface<Donation> donations = donee.getDonations();
            if (donations.isEmpty()) {
                report.append("No donations recorded.\n");
            } else {

                for (int j = 1; j <= donations.getCount(); j++) {
                    Donation donation = donations.getDetails(j);
                    report.append(donation.toString()).append("\n");
                }
            }
            report.append("\n"); // Add a blank line for better readability between donees
        }

        System.out.println(report.toString());
    }

    private DoneeType getDoneeType(String typeInput) {
        switch (typeInput.toUpperCase()) {
            case "I":
                return DoneeType.INDIVIDUAL;
            case "O":
                return DoneeType.ORGANIZATION;
            case "F":
                return DoneeType.FAMILY;
            default:
                return null; // Return null for invalid input
        }
    }
}
