package boundary;

import adt.ListInterface;
import java.util.Scanner;
import entity.Donee;

/**
 *
 * @author youch
 */
public class systemUI {

    private Scanner scanner = new Scanner(System.in);
    public ListInterface<Donee> dlist;
    DoneeInitialize dInilist = new DoneeInitialize();

    public systemUI() {
        dlist = dInilist.initializeDonees(); // Initialize doneelist with donees from DoneeInit
    }

    public int getSystemChoice() {
        System.out.println("Welcome to Donation Management System");
        System.out.println("\nSubSystem: ");
        System.out.println("1. Donor");
        System.out.println("2. Donee");
        System.out.println("3. Donation");
        System.out.println("4. Donation Distribution");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return option;
    }


}
