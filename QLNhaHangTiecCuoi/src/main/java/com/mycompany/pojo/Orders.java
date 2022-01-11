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
public class Orders {
    private int OrderID;
    private int CustomerID;
    private int EmployeeID;
    private Date OrderDate;
    private int paid;
    private int paymentID;

    public Orders() {
    }

    public Orders(int OrderID, int CustomerID, int EmployeeID, Date OrderDate, int paid, int paymentID) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.EmployeeID = EmployeeID;
        this.OrderDate = OrderDate;
        this.paid = paid;
        this.paymentID = paymentID;
    }

    public Orders(int CustomerID, int EmployeeID, Date OrderDate, int paid, int paymentID) {
        this.CustomerID = CustomerID;
        this.EmployeeID = EmployeeID;
        this.OrderDate = OrderDate;
        this.paid = paid;
        this.paymentID = paymentID;
    }
    
    public Orders(int CustomerID, Date OrderDate, int paid, int paymentID) {
        this.CustomerID = CustomerID;
        this.OrderDate = OrderDate;
        this.paid = paid;
        this.paymentID = paymentID;
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
     * @return the CustomerID
     */
    public int getCustomerID() {
        return CustomerID;
    }

    /**
     * @param CustomerID the CustomerID to set
     */
    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    /**
     * @return the EmployeeID
     */
    public int getEmployeeID() {
        return EmployeeID;
    }

    /**
     * @param EmployeeID the EmployeeID to set
     */
    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    /**
     * @return the OrderDate
     */
    public Date getOrderDate() {
        return OrderDate;
    }

    /**
     * @param OrderDate the OrderDate to set
     */
    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
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

    /**
     * @return the paymentID
     */
    public int getPaymentID() {
        return paymentID;
    }

    /**
     * @param paymentID the paymentID to set
     */
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
}
