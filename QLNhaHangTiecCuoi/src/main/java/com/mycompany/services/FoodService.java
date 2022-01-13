/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Food;
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
public class FoodService {
    public List<Food> getFood(String kw) throws SQLException{
        List<Food> list = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM food ";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE FoodName LIKE CONCAT('%', ?, '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Food f = new Food(rs.getInt("foodID"), rs.getString("foodName"), 
                        rs.getDouble("unitPrice"), rs.getInt("categoryID"), rs.getString("notes"));
                list.add(f);
            }
        }
        return list;
    }  
    
    public List<Food> getFoodFromOrderDetails(int id) throws SQLException{
        List<Food> list = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM foodlist where id = ? group by foodid";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Food f = new Food(rs.getInt("foodID"), rs.getString("foodName"), 
                        rs.getDouble("unitPrice"), rs.getInt("categoryID"), rs.getString("notes"));
                list.add(f);
            }
        return list;
        }
    }
    public void AddFood(Food food) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "INSERT INTO food (FoodName, Unitprice, categoryID, Notes) "
                    + "VALUES (?, ?, ?, ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, food.getFoodName());
            stm.setDouble(2, food.getUnitPrice());
            stm.setInt(3, food.getCategotyID());
            stm.setString(4, food.getNotes());
            stm.executeUpdate();
        }
    }
    public void UpdateFood(int foodID, String foodName, double unitPrice, int cateID, String notes) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "UPDATE food SET FoodName = ?, Unitprice = ?, categoryID = ?, Notes = ?"
                    + " WHERE (FoodID = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, foodName);
            stm.setDouble(2, unitPrice);
            stm.setInt(3, cateID);
            stm.setString(4, notes);            
            stm.setInt(5, foodID);
            stm.executeUpdate();
        }
    }
    public void DeleteFood(int foodID) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "DELETE FROM food WHERE (foodID = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, foodID);
            stm.executeUpdate();
        }
    }
    public boolean KiemTaTonTai(String foodName) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT COUNT(*) FROM food WHERE FoodName = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, foodName);
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
