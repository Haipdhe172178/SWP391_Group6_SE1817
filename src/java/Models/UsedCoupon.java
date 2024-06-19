/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author huyca
 */
public class UsedCoupon {
    private int codeId;
    private String codeName;
    private float discount;
    private String couponType;
    private int quantity;
    private int status;

    public UsedCoupon() {
    }

    public UsedCoupon(int codeId, String codeName, float discount, String couponType, int quantity, int status) {
        this.codeId = codeId;
        this.codeName = codeName;
        this.discount = discount;
        this.couponType = couponType;
        this.quantity = quantity;
        this.status = status;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

    
}
