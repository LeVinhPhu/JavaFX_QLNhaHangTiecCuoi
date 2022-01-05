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
    private int scID;
    private String scName;
    private int soBanToiDa;
    private double unitPrice;
    private String notes;

    public SanhCuoi() {
    }

    public SanhCuoi(int scID, String scName, int soBanToiDa, double unitPrice, String notes) {
        this.scID = scID;
        this.scName = scName;
        this.soBanToiDa = soBanToiDa;
        this.unitPrice = unitPrice;
        this.notes = notes;
    }
    
    public SanhCuoi(String scName, int soBanToiDa, double unitPrice, String notes) {
        this.scName = scName;
        this.soBanToiDa = soBanToiDa;
        this.unitPrice = unitPrice;
        this.notes = notes;
    }

    /**
     * @return the scID
     */
    public int getScID() {
        return scID;
    }

    /**
     * @param scID the scID to set
     */
    public void setScID(int scID) {
        this.scID = scID;
    }

    /**
     * @return the scName
     */
    public String getScName() {
        return scName;
    }

    /**
     * @param scName the scName to set
     */
    public void setScName(String scName) {
        this.scName = scName;
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
