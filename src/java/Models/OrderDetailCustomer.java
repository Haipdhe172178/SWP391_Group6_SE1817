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
    private int productId;
    private int quantity;
    private float unitPrice;
    private Product product;

    public OrderDetailCustomer() {
    }

    public OrderDetailCustomer(int orderCId, int productId, int quantity, float unitPrice) {
        this.orderCId = orderCId;
        this.productId = productId;
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
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
