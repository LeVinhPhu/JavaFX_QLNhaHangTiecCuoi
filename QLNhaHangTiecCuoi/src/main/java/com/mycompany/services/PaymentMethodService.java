/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.PaymentMethods;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class PaymentMethodService {
    public List<PaymentMethods> getPaymentMethods() throws SQLException{
        List<PaymentMethods> list = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM paymentmethods");
            while (rs.next()){
                PaymentMethods p = new PaymentMethods(rs.getInt("paymentID"), rs.getString("paymentname"), rs.getString("notes"));
                list.add(p);
            }
        }
        return list;
    }
}
