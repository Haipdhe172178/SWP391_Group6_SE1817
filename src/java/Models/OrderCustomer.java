/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author huyca
 */
public class OrderCustomer {
    private int orderCId;
    private int accountId;
    private float totalPrice;
    private Date date;
    private int statusId;

    public OrderCustomer() {
    }

    public OrderCustomer(int orderCId, int accountId, float totalPrice, Date date, int statusId) {
        this.orderCId = orderCId;
        this.accountId = accountId;
        this.totalPrice = totalPrice;
        this.date = date;
        this.statusId = statusId;
    }

    public int getOrderCId() {
        return orderCId;
    }

    public void setOrderCId(int orderCId) {
        this.orderCId = orderCId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
}
