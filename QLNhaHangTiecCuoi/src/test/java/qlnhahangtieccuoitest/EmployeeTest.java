package qlnhahangtieccuoitest;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.conf.JdbcUtils;
import com.mycompany.services.EmployeeService;
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
public class EmployeeTest {
    private static Connection conn;
    private static EmployeeService em = new EmployeeService();
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
    public void test_GetEmployeeID() throws SQLException{
        String n = "0377988821";
        int expected = 2;
        Assertions.assertEquals(expected, em.GetEmployeeID(n));
    }
    @ParameterizedTest
    @CsvSource({"0367948625,123456, true","0377988821,123456,true","0123654789,123456,false","0377988821,147852,false",})
    public void test_TonTaiSDT(String sdt, String mk, boolean expected) throws SQLException{
        Assertions.assertEquals(expected, em.KiemTraDangNhap(sdt, mk));
    }
}
