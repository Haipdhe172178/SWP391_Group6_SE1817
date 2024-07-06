/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author huyca
 */
public class Revenue {

    private int orderYear;
    private int orderMonth;
    private int orderDay;
    private float totalRevenue;

    public Revenue(float totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Revenue(int orderDay, float totalRevenue) {
        this.orderDay = orderDay;
        this.totalRevenue = totalRevenue;
    }

    public Revenue(int orderYear, int orderMonth, float totalRevenue) {
        this.orderYear = orderYear;
        this.orderMonth = orderMonth;
        this.totalRevenue = totalRevenue;
    }

    public Revenue(int orderYear, int orderMonth, int orderDay, float totalRevenue) {
        this.orderYear = orderYear;
        this.orderMonth = orderMonth;
        this.orderDay = orderDay;
        this.totalRevenue = totalRevenue;
    }

    public int getOrderYear() {
        return orderYear;
    }

    public void setOrderYear(int orderYear) {
        this.orderYear = orderYear;
    }

    public int getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(int orderMonth) {
        this.orderMonth = orderMonth;
    }

    public int getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(int orderDay) {
        this.orderDay = orderDay;
    }

    public float getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(float totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @Override
    public String toString() {
        return "Revenue{" + "orderYear=" + orderYear + ", orderMonth=" + orderMonth + ", orderDay=" + orderDay + ", totalRevenue=" + totalRevenue + '}';
    }
    
}
