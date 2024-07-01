/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

public class ShippingAddress {
    private int addressID;
    private int accID;
    private String address;
    private String phoneNumber;
    private int isDefault;

    public ShippingAddress() {
    }

    public ShippingAddress(int addressID, int accID, String address, String phoneNumber, int isDefault) {
        this.addressID = addressID;
        this.accID = accID;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isDefault = isDefault;
    }

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

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    
    
}
