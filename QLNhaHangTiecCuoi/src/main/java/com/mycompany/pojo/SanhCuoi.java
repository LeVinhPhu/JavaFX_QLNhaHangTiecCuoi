/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;


/**
 *
 * @author Lenovo
 */
public class SanhCuoi {
    private int sanhCuoiID;
    private String sanhCuoiName;
    private int soBanToiDa;
    private double unitPrice;
    private String notes;

    public SanhCuoi() {
    }

    public SanhCuoi(int scID, String scName, int soBanToiDa, double unitPrice, String notes) {
        this.sanhCuoiID = scID;
        this.sanhCuoiName = scName;
        this.soBanToiDa = soBanToiDa;
        this.unitPrice = unitPrice;
        this.notes = notes;
    }
    
    public SanhCuoi(String scName, int soBanToiDa, double unitPrice, String notes) {
        this.sanhCuoiName = scName;
        this.soBanToiDa = soBanToiDa;
        this.unitPrice = unitPrice;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return this.sanhCuoiName; //To change body of generated methods, choose Tools | Templates.
    }
    

    /**
     * @return the scID
     */
    public int getSanhCuoiID() {
        return sanhCuoiID;
    }

    /**
     * @param scID the scID to set
     */
    public void setSanhCuoiID(int scID) {
        this.sanhCuoiID = scID;
    }

    /**
     * @return the scName
     */
    public String getSanhCuoiName() {
        return sanhCuoiName;
    }

    /**
     * @param scName the scName to set
     */
    public void setSanhCuoiName(String scName) {
        this.sanhCuoiName = scName;
    }

    /**
     * @return the soBanToiDa
     */
    public int getSoBanToiDa() {
        return soBanToiDa;
    }

    /**
     * @param soBanToiDa the soBanToiDa to set
     */
    public void setSoBanToiDa(int soBanToiDa) {
        this.soBanToiDa = soBanToiDa;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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
