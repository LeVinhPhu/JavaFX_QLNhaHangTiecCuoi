/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Customers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class CustomerServices {
    public boolean KiemTraDangNhap(String sdt, String mk) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT Password FROM customers WHERE Phone = ?");
            stm.setString(1, sdt);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String pass = rs.getString("Password");
                if (mk.equals(pass)== true)
                    return true;
            }
            return false;
        }
    }
    
    public void DangKyKhachHang(Customers kh) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement("INSERT INTO quanlydattiec.customers "
                    + "(Phone, LastName, FirstName, Birthdate, Address, Password) "
                    + "VALUES (?, ?, ?, ?, ?, ?);");
            stm.setString(1, kh.getPhone());
            stm.setString(2, kh.getHoKH());
            stm.setString(3, kh.getTenKH());
            stm.setDate(4, kh.getNgaySinh());
            stm.setString(5, kh.getDiaChi());
            stm.setString(6, kh.getMatKhau());
            stm.executeUpdate();
            conn.commit();
        }
    }
    public boolean TonTaiSDT(String sdt) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM customers WHERE Phone = ?");
            stm.setString(1, sdt);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String kq = rs.getString(1);
                if (kq.equals("0"))
                    return false;
            }
            return true;              
        }
    }
}
