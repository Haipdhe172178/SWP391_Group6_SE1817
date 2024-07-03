package Models;

public class ShipAddress {
    private int addressID;
    private int accID;
    private String address;
    private String phoneNumber;
    private boolean isDefault;

    // Constructors
    public ShipAddress(int accID, String address, String phoneNumber, boolean isDefault) {
        this.accID = accID;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isDefault = isDefault;
    }

    public ShipAddress(int addressID, int accID, String address, String phoneNumber, boolean isDefault) {
        this.addressID = addressID;
        this.accID = accID;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isDefault = isDefault;
    }

    // Getters and Setters
    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "ShipAddress{" +
                "addressID=" + addressID +
                ", accID=" + accID +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
