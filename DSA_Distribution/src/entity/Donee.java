package entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Dixon
 */
import adt.ListInterface;

public class Donee {

    private String id;
    private String name;
    private DoneeType type;
    private final ListInterface<Donation> donations;

    public Donee(String id, String name, DoneeType type, ListInterface<Donation> donations) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.donations = donations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoneeType getType() {
        return type;
    }

    public void setType(DoneeType type) {
        this.type = type;
    }

    public ListInterface<Donation> getDonations() {
        return donations;
    }

    public void addDonation(Donation donation) {
        this.donations.add(donation);
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %-10s Name: %-16s Type: %-15s",
                id, name, type
        );
    }

}
