/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author Lenovo
 */
public class PaymentMethods {
    private int paymentID;
    private String paymentname;
    private String notes;

    public PaymentMethods() {
    }

    public PaymentMethods(int paymentID, String paymentname, String notes) {
        this.paymentID = paymentID;
        this.paymentname = paymentname;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return this.paymentname; //To change body of generated methods, choose Tools | Templates.
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

    /**
     * @return the paymentname
     */
    public String getPaymentname() {
        return paymentname;
    }

    /**
     * @param paymentname the paymentname to set
     */
    public void setPaymentname(String paymentname) {
        this.paymentname = paymentname;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
