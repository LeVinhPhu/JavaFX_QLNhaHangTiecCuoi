/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Lenovo
 */
public class OrdersService {
    public void AddOrder(Orders order) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "INSERT INTO orders (CustomerID, OrderDate, paid, paymentID) "
                    + " VALUES (?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, order.getCustomerID());
            stm.setDate(2, order.getOrderDate());
            stm.setInt(3, order.getPaid());
            stm.setInt(4, order.getPaymentID());
            stm.executeUpdate();
        }
    }
    
    public int getTheLargestOrderID() throws SQLException{
        int orderID = 0;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT MAX(OrderID) FROM orders";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                orderID = rs.getInt(1);
            }
        }
        return orderID;
    }
}
