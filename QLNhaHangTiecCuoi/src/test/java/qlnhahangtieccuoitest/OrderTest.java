package qlnhahangtieccuoitest;


import com.mycompany.conf.JdbcUtils;
import com.mycompany.services.OrdersService;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class OrderTest {
    private static Connection conn;
    private static OrdersService or = new OrdersService();
    @BeforeAll
    public static void beforeAll() throws SQLException{
        conn = JdbcUtils.getConn();
    }
    @AfterAll
    public static void AfterAll() throws SQLException{
        if (conn != null)
            conn.close();
    }
    @Test 
    public void test_getTheLargestOrderID() throws SQLException{
        int expected = 32;
        Assertions.assertEquals(expected, or.getTheLargestOrderID());
    }

}
