/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author Lenovo
 */
public class Services {
    private int serviceID;
    private String serviceName;
    private double unitPrice;
    
    public Services() {
    }

    public Services(int serviceID, String serviceName, double unitPrice) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.unitPrice = unitPrice;
    }
    public Services(String serviceName, double unitPrice) {
        this.serviceName = serviceName;
        this.unitPrice = unitPrice;
    }

    /**
     * @return the serviceID
     */
    public int getServiceID() {
        return serviceID;
    }

    /**
     * @param aServiceID the serviceID to set
     */
    public void setServiceID(int aServiceID) {
        serviceID = aServiceID;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
    
}
