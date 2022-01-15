package qlnhahangtieccuoitest;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.conf.JdbcUtils;
import com.mycompany.services.CustomerServices;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
/**
 *
 * @author Lenovo
 */
public class CustomerTest {
    private static Connection conn;
    private static CustomerServices cus = new CustomerServices();
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
    @CsvSource({"0386634020,true","0906716392,false","0123654789,true","0906066616,false","0965350366,true"})
    public void TonTaiSDTTest(String n, boolean expected) throws SQLException{
        Assertions.assertEquals(expected, cus.TonTaiSDT(n));
    }

    @Test 
    public void getCustomerIDTest() throws SQLException{
        String n = "0386634020";
        int expected = 10;
        Assertions.assertEquals(expected, cus.getCustomerID(n));
    }
    
    @ParameterizedTest
    @CsvSource({"0367948625,123456, false","0362348624,123456,true","0362348623,123456,true","0377988821,147852,false",})
    public void KiemTraDangNhapTest(String sdt, String mk, boolean expected) throws SQLException{
        Assertions.assertEquals(expected, cus.KiemTraDangNhap(sdt, mk));
    }
}
