/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author huyca
 */
public class OrderDetailGuest {
    private int orderGId;
    private int productId;
    private int quantity;
    private float unitPrice;

    public OrderDetailGuest() {
    }

    public OrderDetailGuest(int orderGId, int productId, int quantity, float unitPrice) {
        this.orderGId = orderGId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getOrderGId() {
        return orderGId;
    }

    public void setOrderGId(int orderGId) {
        this.orderGId = orderGId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
