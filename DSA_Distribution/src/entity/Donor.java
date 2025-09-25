package entity;

public class Donor {

    // Extra information specific to Donor
    private String donorID;
    private static int nextID = 1000;
    private String name;
    private String contactNumber;
    private String emailAddress;
    private String address;
    private String donorType;

    public Donor() {

    }

    public Donor(String donorID) {
        this.donorID = donorID;
    }
    

    public Donor(String name, String contactNumber, String emailAddress, String address, String donorType) {
        this.donorID = generateDonorID();
        this.name = name;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.donorType = donorType;
    }

    public Donor(String donorID, String name, String contactNumber, String emailAddress, String address, String donorType) {
        this.donorID = donorID;
        this.name = name;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.donorType = donorType;
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Donor.nextID = nextID;
    }

    // Getter and setter methods for donorType
    public String getDonorType() {
        return donorType;
    }

    public void setDonorType(String donorType) {
        this.donorType = donorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getID() {
        return donorID;
    }

    public int compare(String donor) {
        return this.donorID.compareTo(donor);
    }

    private String generateDonorID() {
        return "DR" + (++nextID);
    }

    @Override
    public String toString() {
        return String.format(
                "%-10s%-12s%-18s%-25s%-30s%-8s", donorID, name, contactNumber, emailAddress, address, donorType);
    }

}
