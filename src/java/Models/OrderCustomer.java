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
public class OrderCustomer {

    private List<OrderDetailCustomer> orderDetails;
    private Account account;
    private float totalPrice;
    private Date date;
    private StatusOrder status;
    public int paymentStatus;
    private ShippingAddress shipAddress;

    public OrderCustomer(List<OrderDetailCustomer> orderDetails, Account account, float totalPrice, Date date, StatusOrder status) {
        this.orderDetails = orderDetails;
        this.account = account;
        this.totalPrice = totalPrice;
        this.date = date;
        this.status = status;
    }
    public OrderCustomer() {
    }
    public ShippingAddress getShipaddress() {
        return shipAddress;
    }

    public void setShipaddress(ShippingAddress shipAddress) {
        this.shipAddress = shipAddress;
    }
    
    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public List<OrderDetailCustomer> getOrderDetails() {
        return orderDetails;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
}
