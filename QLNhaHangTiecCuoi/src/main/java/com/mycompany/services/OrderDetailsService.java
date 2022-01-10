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
    public List<Integer> getSCIDList(Date partyDay) throws SQLException{
        List<Integer> SCList = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM orderdetails where partyday = ? ";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, partyDay);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int sc = rs.getInt("SanhCuoiID");
                SCList.add(sc);
            }
            return SCList;
        }
    }
    
    public void AddOrderDetails(OrderDetails orDe) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "INSERT INTO orderdetails (OrderID, FoodID, SanhCuoiID, ServiceID, PartyDay, soBan, UnitPrice) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, orDe.getOrderID());
            stm.setInt(2, orDe.getFoodID());
            stm.setInt(3, orDe.getSanhCuoiID());
            stm.setInt(4, orDe.getServiceID());
            stm.setDate(5, orDe.getPartyDay());
            stm.setInt(6, orDe.getSoBan());
            stm.setDouble(7, orDe.getUnitPrice());
            stm.executeUpdate();
        }
    }
    
}
