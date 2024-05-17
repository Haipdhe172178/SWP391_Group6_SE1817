/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author huyca
 */
public class OrderDetailCustomer {
    private int orderCId;
    private int ProductId;
    private int quantity;
    private float unitPrice;

    public OrderDetailCustomer() {
    }

    public OrderDetailCustomer(int orderCId, int ProductId, int quantity, float unitPrice) {
        this.orderCId = orderCId;
        this.ProductId = ProductId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getOrderCId() {
        return orderCId;
    }

    public void setOrderCId(int orderCId) {
        this.orderCId = orderCId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    
}
