package entity;

import adt.List;
import adt.ListInterface;
import boundary.DoneeInitialize;
import control.DoneeUtility;

public class DistributionInitializer {

    ListInterface<Distribution> dList = new List<>();
    DoneeInitialize doneeInitialize = new DoneeInitialize();

    ListInterface<Donee> doneeList = doneeInitialize.initializeDonees();

    public ListInterface<Distribution> initializeDistr() {
        DoneeUtility.initialize(doneeList);
        Donee donee1 = doneeList.getDetails(0); 
        Donee donee2 = doneeList.getDetails(1); 
        Donee donee3 = doneeList.getDetails(2); 

        dList.add(new Distribution(donee1, "DF1001", 2, "Pending"));
        dList.add(new Distribution(donee2, "DF1002", 5, "Pending"));
        dList.add(new Distribution(donee3, "DF1003", 3, "On Delivery"));

        return dList;
    }

}
