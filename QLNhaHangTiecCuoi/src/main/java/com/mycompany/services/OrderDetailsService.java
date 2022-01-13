/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.OrderDetails;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Lenovo
 */
public class OrderDetailsService {
    public List<Integer> getSCIDList(Date partyDay, String RentalPeriod) throws SQLException{
        List<Integer> SCList = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT SanhCuoiID FROM orderdetails where partyday = ? AND RentalPeriod = ? ";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, partyDay);
            stm.setString(2, RentalPeriod);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("SanhCuoiID");
                SCList.add(sc);
            }
            return SCList;
        }
    }
    
    public OrderDetails getSCFromOrderDetails(int id) throws SQLException{
        OrderDetails oD = null;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM quanlydattiec.hoadon where orderID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                oD = new OrderDetails(rs.getInt("orderID"), rs.getString("sanhcuoiname"), 
                        rs.getDate("PartyDay"), rs.getString("RentalPeriod"), rs.getInt("soBan"), rs.getDouble("UnitPrice"));
            }
            return oD;
        }
    }
    
    public void AddOrderDetails(OrderDetails orDe) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "INSERT INTO orderdetails (OrderID, FoodID, SanhCuoiID, ServiceID, PartyDay, RentalPeriod, soBan, UnitPrice) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, orDe.getOrderID());
            stm.setInt(2, orDe.getFoodID());
            stm.setInt(3, orDe.getSanhCuoiID());
            stm.setInt(4, orDe.getServiceID());
            stm.setDate(5, orDe.getPartyDay());
            stm.setString(6, orDe.getRentalPeriod());
            stm.setInt(7, orDe.getSoBan());
            stm.setDouble(8, orDe.getUnitPrice());
            stm.executeUpdate();
        }
    }
    public void DeleteOrderDetails(int orderID) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "DELETE FROM orderdetails WHERE (orderID = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, orderID);
            stm.executeUpdate();
        }
    }
    
}
