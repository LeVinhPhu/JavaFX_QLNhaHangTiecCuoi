/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.sql.Date;

/**
 *
 * @author Lenovo
 */
public class OrderDetails {
    private int OrderID;
    private int FoodID;
    private int SanhCuoiID;
    private String sanhcuoiName;
    private int ServiceID;
    private Date PartyDay;
    private String RentalPeriod;
    private int soBan;
    private double UnitPrice;
    private double Discount;

    public OrderDetails() {
    }

    public OrderDetails(int OrderID, int FoodID, int SanhCuoiID, int ServiceID, Date PartyDay, String RentalPeriod, int soBan, double UnitPrice, double Discount) {
        this.OrderID = OrderID;
        this.FoodID = FoodID;
        this.SanhCuoiID = SanhCuoiID;
        this.ServiceID = ServiceID;
        this.PartyDay = PartyDay;
        this.RentalPeriod = RentalPeriod;
        this.soBan = soBan;
        this.UnitPrice = UnitPrice;
        this.Discount = Discount;
    }

    public OrderDetails(int OrderID, int FoodID, int SanhCuoiID, int ServiceID, Date PartyDay, String RentalPeriod, int soBan, double UnitPrice) {
        this.OrderID = OrderID;
        this.FoodID = FoodID;
        this.SanhCuoiID = SanhCuoiID;
        this.ServiceID = ServiceID;
        this.PartyDay = PartyDay;
        this.RentalPeriod = RentalPeriod;
        this.soBan = soBan;
        this.UnitPrice = UnitPrice;
    }
    public OrderDetails(int OrderID, int SanhCuoiID, Date PartyDay, String RentalPeriod, int soBan, double UnitPrice) {
        this.OrderID = OrderID;
        this.SanhCuoiID = SanhCuoiID;
        this.PartyDay = PartyDay;
        this.RentalPeriod = RentalPeriod;
        this.soBan = soBan;
        this.UnitPrice = UnitPrice;
    }
    
    public OrderDetails(int OrderID, String SanhCuoiName, Date PartyDay, String RentalPeriod, int soBan, double UnitPrice) {
        this.OrderID = OrderID;
        this.sanhcuoiName = SanhCuoiName;
        this.PartyDay = PartyDay;
        this.RentalPeriod = RentalPeriod;
        this.soBan = soBan;
        this.UnitPrice = UnitPrice;
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
     * @return the soBan
     */
    public int getSoBan() {
        return soBan;
    }

    /**
     * @param soBan the soBan to set
     */
    public void setSoBan(int soBan) {
        this.soBan = soBan;
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
     * @return the PartyDay
     */
    public Date getPartyDay() {
        return PartyDay;
    }

    /**
     * @param PartyDay the PartyDay to set
     */
    public void setPartyDay(Date PartyDay) {
        this.PartyDay = PartyDay;
    }

    /**
     * @return the RentalPeriod
     */
    public String getRentalPeriod() {
        return RentalPeriod;
    }

    /**
     * @param RentalPeriod the RentalPeriod to set
     */
    public void setRentalPeriod(String RentalPeriod) {
        this.RentalPeriod = RentalPeriod;
    }

    /**
     * @return the sanhcuoiName
     */
    public String getSanhcuoiName() {
        return sanhcuoiName;
    }

    /**
     * @param sanhcuoiName the sanhcuoiName to set
     */
    public void setSanhcuoiName(String sanhcuoiName) {
        this.sanhcuoiName = sanhcuoiName;
    }

    
}
