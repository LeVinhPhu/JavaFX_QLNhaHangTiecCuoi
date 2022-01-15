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
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Orders> getOrders(int cusID) throws SQLException{
        List<Orders> list = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM orders ";
            if (cusID > 0)
                sql += " where CustomerID = ? ";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (cusID > 0)
                stm.setInt(1, cusID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Orders order = new Orders(rs.getInt("orderID"), rs.getInt("customerID"), 
                        rs.getInt("employeeID"), rs.getDate("orderdate"),rs.getInt("paid"), rs.getInt("paymentID"));
                list.add(order);
            }
        }
        return list;
    } 
    
    public int getCustomerIDFromOrder(int id) throws SQLException{
        int cusID = -1;
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT CustomerID FROM orders WHERE customerID = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                cusID = rs.getInt("CustomerID");                
            }    
            return cusID;
        }
    }
    
    public void UpdatePaid(int id) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "UPDATE orders SET paid = 1 WHERE (OrderID = ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        }
    }
    public void DeleteOrder(int orderID) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "DELETE FROM orders WHERE (orderID = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, orderID);
            stm.executeUpdate();
        }
    }
}
