/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author Lenovo
 */
public class OrderDetails {
    private int OrderID;
    private int FoodID;
    private int SanhCuoiID;
    private int ServiceID;
    private double UnitPrice;
    private double Discount;
    private int paid;

    public OrderDetails() {
    }

    public OrderDetails(int OrderID, int FoodID, int SanhCuoiID, int ServiceID, double UnitPrice, double Discount, int paid) {
        this.OrderID = OrderID;
        this.FoodID = FoodID;
        this.SanhCuoiID = SanhCuoiID;
        this.ServiceID = ServiceID;
        this.UnitPrice = UnitPrice;
        this.Discount = Discount;
        this.paid = paid;
    }
    
    

    /**
     * @return the OrderID
     */
    public int getOrderID() {
        return OrderID;
    }

    /**
     * @param OrderID the OrderID to set
     */
    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    /**
     * @return the FoodID
     */
    public int getFoodID() {
        return FoodID;
    }

    /**
     * @param FoodID the FoodID to set
     */
    public void setFoodID(int FoodID) {
        this.FoodID = FoodID;
    }

    /**
     * @return the SanhCuoiID
     */
    public int getSanhCuoiID() {
        return SanhCuoiID;
    }

    /**
     * @param SanhCuoiID the SanhCuoiID to set
     */
    public void setSanhCuoiID(int SanhCuoiID) {
        this.SanhCuoiID = SanhCuoiID;
    }

    /**
     * @return the ServiceID
     */
    public int getServiceID() {
        return ServiceID;
    }

    /**
     * @param ServiceID the ServiceID to set
     */
    public void setServiceID(int ServiceID) {
        this.ServiceID = ServiceID;
    }

    /**
     * @return the UnitPrice
     */
    public double getUnitPrice() {
        return UnitPrice;
    }

    /**
     * @param UnitPrice the UnitPrice to set
     */
    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    /**
     * @return the Discount
     */
    public double getDiscount() {
        return Discount;
    }

    /**
     * @param Discount the Discount to set
     */
    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    /**
     * @return the paid
     */
    public int getPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(int paid) {
        this.paid = paid;
    }
}
