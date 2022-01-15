import com.mycompany.pojo.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.mycompany.conf.JdbcUtils;
import com.mycompany.services.OrdersService;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */

@RunWith(Suite.class)
@SuiteClasses({CategoryTest.class, test_CustomerServices.class
        ,test_DichVuServices.class, test_EmployeeService.class
        ,test_FoodService.class,test_OrdersService.class,test_SanhCuoiService.class})

public class TestAll {
        
        @BeforeAll
        public static void beforeAll() throws SQLException{
            conn = JdbcUtils.getConn();
        }
    @AfterAll
    public static void AfterAll() throws SQLException{
        if (conn != null)
            conn.close();

}