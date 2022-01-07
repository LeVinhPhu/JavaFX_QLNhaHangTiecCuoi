/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.SanhCuoi;
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
public class SanhCuoiService {
    public List<SanhCuoi> getSanhCuoiList(String kw) throws SQLException{
        List<SanhCuoi> list = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM sanhcuoi ";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE SanhCuoiName LIKE CONCAT('%', ?, '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                SanhCuoi s = new SanhCuoi(rs.getInt("sanhCuoiID"), rs.getString("sanhCuoiName"), rs.getInt("soBanToiDa"), rs.getDouble("unitPrice"), rs.getString("notes"));
                list.add(s);
            }
        }        
        return list;
    }
    
    public void AddSanhCuoi(SanhCuoi s) throws SQLException{        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "INSERT INTO sanhcuoi (SanhCuoiName, SoBanToiDa, unitPrice, Notes) "
                    + " VALUES (?, ?, ?, ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, s.getSanhCuoiName());
            stm.setInt(2, s.getSoBanToiDa());
            stm.setDouble(3, s.getUnitPrice());
            stm.setString(4, s.getNotes());
            stm.executeUpdate();
        }
    }
    public void UpdateSanhCuoi(int scID, String scname, int sbtd, double unitPrice, String notes) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "UPDATE sanhcuoi SET SanhCuoiName = ?, SoBanToiDa = ?, "
                    + " unitPrice = ?, Notes = ? WHERE (SanhCuoiID = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, scname);
            stm.setInt(2, sbtd);
            stm.setDouble(3, unitPrice);
            stm.setString(4, notes);
            stm.setInt(5, scID);
            stm.executeUpdate();
        }
    }
    public void DeleteSanhCuoi(int scID) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "DELETE FROM sanhcuoi WHERE (SanhCuoiID = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, scID);
            stm.executeUpdate();
        }
    }
    public boolean kiemTraTonTai(String scName) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT COUNT(*) FROM sanhcuoi WHERE SanhCuoiName = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, scName);
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
