package entity;

public class Distribution {

    private Donee donee;
    private String items;
    private int quantity;
    private String status;

    public Distribution(Donee donee, String items, int quantity, String status) {
        this.donee = donee;
        this.items = items;
        this.quantity = quantity;
        this.status = status;
    }

    public Donee getDonee() {
        return donee;
    }

    public void setDonee(Donee donee) {
        this.donee = donee;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String itemsList = String.join(", ", items); // Add space after comma for readability

        return String.format(
                "%-10s%-20s%-30s%-10s%-15s\n" // Adjusted width for Items column
                + "%-10s%-20s%-30s%-10d%-15s",
                "Donee ID", "Donee Name", "Items", "Quantity", "Status",
                donee.getId(), donee.getName(), itemsList, quantity, status
        );
    }

}
