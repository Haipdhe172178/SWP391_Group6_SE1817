/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author huyca
 */
public class OrderGuest {
    private List<OrderDetailGuest> orderDetails;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private float totalPrice;
    private Date date;
    private Status status;

    public OrderGuest(List<OrderDetailGuest> orderDetails, String fullName, String email, String phoneNumber, String address, float totalPrice, Date date, Status status) {
        this.orderDetails = orderDetails;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.totalPrice = totalPrice;
        this.date = date;
        this.status = status;
    }

   

    public List<OrderDetailGuest> getOrderDetails() {
        return orderDetails;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status statusId) {
        this.status = status;
    }
}