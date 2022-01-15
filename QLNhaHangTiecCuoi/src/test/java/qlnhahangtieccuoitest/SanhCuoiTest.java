package qlnhahangtieccuoitest;


import com.mycompany.conf.JdbcUtils;
import com.mycompany.services.SanhCuoiService;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class SanhCuoiTest {
    private static Connection conn;
    private static SanhCuoiService sc = new SanhCuoiService();
    @BeforeAll
    public static void beforeAll() throws SQLException{
        conn = JdbcUtils.getConn();
    }
    @AfterAll
    public static void AfterAll() throws SQLException{
        if (conn != null)
            conn.close();
    }
    
    @ParameterizedTest
    @CsvSource({"Gia Linh,false","Obera,true","Vu Quy,false"})
    public void test_KiemTaTonTai(String name, boolean expected) throws SQLException{
        Assertions.assertEquals(expected, sc.kiemTraTonTai(name));
    }
}
