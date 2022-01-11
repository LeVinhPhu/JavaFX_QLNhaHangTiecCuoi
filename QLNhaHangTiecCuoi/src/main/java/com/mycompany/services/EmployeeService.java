/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class EmployeeService {
    public boolean KiemTraDangNhap(String sdt, String mk) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT Password FROM employees WHERE PhoneEmployee = ?");
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
    public int GetEmployeeID(String sdt) throws SQLException{
        int id = 0;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT EmployeeID FROM employees WHERE PhoneEmployee = ?");
            stm.setString(1, sdt);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                id = rs.getInt("EmployeeID");                
            }    
            return id;
        }
    }
}
