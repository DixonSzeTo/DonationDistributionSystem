package control;

import utility.Message;
import adt.List;
import adt.ListInterface;
import adt.QueueInterface;
import boundary.DistributionUI;
import boundary.DoneeInitialize;
import boundary.DoneeUI;
import boundary.systemUI;
import entity.Distribution;
import entity.DistributionInitializer;
import entity.DonationInitializer;
import entity.Donee;
import entity.Food;

/**
 *
 * @author youch
 */
public class testing {

    public static void main(String[] args) {
        ListInterface<Distribution> list = new List<>();
        DistributionInitializer disIni = new DistributionInitializer();
        list = disIni.initializeDistr();
        
        DoneeInitialize doneeInitialize = new DoneeInitialize();
        DonationInitializer donationInitialize = new DonationInitializer();

        ListInterface<Donee> doneeList = doneeInitialize.initializeDonees();
        DoneeUtility.initialize(doneeList);

        
        DistributionCtrl distributeCtrl = new DistributionCtrl();
        DistributionUI distributeUI = new DistributionUI();

        systemUI sUI = new systemUI();
        DoneeUI doneeUI = new DoneeUI();

        int choice = 0;
        do {
            choice = sUI.getSystemChoice();

            switch (choice) {
                case 0:
                    Message.displayExitMessage();
                    break;
                case 1:
                    break;
                case 2:
                    // Start the user interface
                    doneeUI.start();
                    break;
                case 3:
                    break;
                case 4:
                    int option = 0;
                    do {
                        option = distributeUI.getDonationDChoice();
                        switch (option) {
                            case 0:
                                Message.displayExitMessage();
                                break;
                            case 1:
                                distributeCtrl.addNewDistribution();
                                break;
                            case 2:
                                distributeCtrl.updateDistribution();
                                break;
                            case 3:
                                distributeCtrl.removeDistributionById();
                                break;
                            case 4:
                                distributeCtrl.viewAllDistributions();
                                break;
                            case 5:
                                distributeCtrl.viewDistributionsByDonee();
                                break;
                            default:
                                Message.displayInvalidChoiceMessage();

                                break;
                        }

                    } while (option != 0);

                default:
                    Message.displayInvalidChoiceMessage();
            }

        } while (choice != 0);
    }
}
