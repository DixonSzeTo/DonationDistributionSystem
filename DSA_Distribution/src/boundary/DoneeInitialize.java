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
import entity.Donation;
import entity.Donee;
import entity.DoneeType;

public class DoneeInitialize {

    private final ListInterface<Donee> list = new List<>();

    public ListInterface<Donee> initializeDonees() {
        // Creating lists of donations for each donee
        ListInterface<Donation> donations1 = new List<>();
        ListInterface<Donation> donations2 = new List<>();
        ListInterface<Donation> donations3 = new List<>();

        // Initializing Donee objects
        list.add(new Donee("D1", "Dixon", DoneeType.INDIVIDUAL, donations1));
        list.add(new Donee("D2", "Jenson", DoneeType.ORGANIZATION, donations2));
        list.add(new Donee("D3", "JunYi", DoneeType.FAMILY, donations3));
        list.add(new Donee("D4", "YouCheng", DoneeType.ORGANIZATION, new List<>())); // No initial donations

        return list;
    }
}
