/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class DichVuServices {
    public List<Services> getServices(String kw) throws SQLException{
        List<Services> list = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM services";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE serviceName LIKE CONCAT('%', ?, '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Services s = new Services(rs.getInt("serviceID"), rs.getString("serviceName"), rs.getDouble("unitPrice"));
                list.add(s);
            }
        }        
        return list;
    }
    
    public List<Services> getServicesFromOrderDetails(int id) throws SQLException{
        List<Services> list = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM servicelist where id = ? group by serviceid";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Services s = new Services(rs.getInt("serviceID"), rs.getString("serviceName"), rs.getDouble("unitPrice"));
                list.add(s);
            }
        }        
        return list;
    }
    
    public void AddService(Services s) throws SQLException{        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "INSERT INTO services (serviceName, unitPrice) "
                + "VALUES (?, ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, s.getServiceName());
            stm.setDouble(2, s.getUnitPrice());
            stm.executeUpdate();
        }
    }
    public void UpdateService(int serviceID, String serviceName, double servicePrice) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "UPDATE services SET serviceName = ?, unitPrice = ? "
                    + "WHERE (serviceID = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, serviceName);
            stm.setDouble(2, servicePrice);
            stm.setInt(3, serviceID);
            stm.executeUpdate();
        }
    }
    public void DeleteService(int serviceID) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "DELETE FROM services WHERE (serviceID = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, serviceID);
            stm.executeUpdate();
        }
    }
    public boolean kiemTraTonTai(String serName) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT COUNT(*) FROM services WHERE serviceName = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, serName);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String kq = rs.getString(1);
                if (kq.equals("0"))
                    return true;
            }
            return false;
        }
    }
}
