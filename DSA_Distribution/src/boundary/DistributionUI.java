package boundary;

import java.util.Scanner;

/**
 *
 * @author youch
 */
public class DistributionUI {

    Scanner scanner = new Scanner(System.in);

    public int getDonationDChoice() {
        System.out.println("\nDonation Distribution Subsystem: ");
        System.out.println("1. Add");
        System.out.println("2. Update");
        System.out.println("3. Remove");
        System.out.println("4. Track Distributed Items");
        System.out.println("5. Track Distributed Items By DoneeID");
        System.out.print("Enter choice(0 - back): ");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }
}
