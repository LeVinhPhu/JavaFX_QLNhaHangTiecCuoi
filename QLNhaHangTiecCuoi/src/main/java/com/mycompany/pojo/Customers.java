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
public class Customers {
    private String phone;
    private String hoKH;
    private String tenKH;
    private Date ngaySinh;
    private String diaChi;
    private String matKhau;

    public Customers() {
    }

    public Customers(String phone, String hoKH, String tenKH, Date ngaySinh, String diaChi, String matKhau) {
        this.phone = phone;
        this.hoKH = hoKH;
        this.tenKH = tenKH;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
    }

    
    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the hoKH
     */
    public String getHoKH() {
        return hoKH;
    }

    /**
     * @param hoKH the hoKH to set
     */
    public void setHoKH(String hoKH) {
        this.hoKH = hoKH;
    }

    /**
     * @return the tenKH
     */
    public String getTenKH() {
        return tenKH;
    }

    /**
     * @param tenKH the tenKH to set
     */
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

}
