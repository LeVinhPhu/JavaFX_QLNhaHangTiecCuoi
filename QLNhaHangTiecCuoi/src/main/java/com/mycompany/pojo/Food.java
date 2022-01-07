/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Lenovo
 */
public class Food {
    private int foodID;
    private String foodName;
    private double unitPrice;
    private int categotyID;
    private String notes;
    private CheckBox select;

    public Food() {
    }

    public Food(int dishID, String dishName, double unitPrice, int categotyID, String notes) {
        this.foodID = dishID;
        this.foodName = dishName;
        this.unitPrice = unitPrice;
        this.categotyID = categotyID;
        this.notes = notes;
        this.select = new CheckBox();
        this.select.setDisable(true);
    }

    public Food(String dishName, double unitPrice, int categotyID, String notes) {
        this.foodName = dishName;
        this.unitPrice = unitPrice;
        this.categotyID = categotyID;
        this.notes = notes;
        this.select = new CheckBox();
    }
    /**
     * @return the dishID
     */
    public int getFoodID() {
        return foodID;
    }

    /**
     * @param dishID the dishID to set
     */
    public void setFoodID(int dishID) {
        this.foodID = dishID;
    }

    /**
     * @return the dishName
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * @param dishName the dishName to set
     */
    public void setFoodName(String dishName) {
        this.foodName = dishName;
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
     * @return the categotyID
     */
    public int getCategotyID() {
        return categotyID;
    }

    /**
     * @param categotyID the categotyID to set
     */
    public void setCategotyID(int categotyID) {
        this.categotyID = categotyID;
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
    
    public CheckBox getSelect(){
        return select;
    }
    
    public void setSelect(CheckBox select){
        this.select = select;
    }
}
