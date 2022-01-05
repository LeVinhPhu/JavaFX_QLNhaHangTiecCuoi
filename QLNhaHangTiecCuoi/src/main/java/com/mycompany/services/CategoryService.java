/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Categories;
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
public class CategoryService {
    public List<Categories> getCategories() throws SQLException{
        List<Categories> list = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM categories");
            while (rs.next()){
                Categories c = new Categories(rs.getInt("categoryID"), rs.getString("categoryName"), rs.getString("Description"));
                list.add(c);
            }
        }
        return list;
    }
    public Categories getCategory(int cateID) throws SQLException{
        Categories cate = null;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM categories WHERE categoryID = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, cateID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                cate = new Categories(rs.getInt("categoryID"), rs.getString("categoryName"), rs.getString("Description"));
            }
        }
        return cate;
    }

}
