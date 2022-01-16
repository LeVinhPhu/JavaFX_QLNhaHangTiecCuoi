package qlnhahangtieccuoitest;

    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    
import com.mycompany.conf.JdbcUtils;
import com.mycompany.services.CategoryService;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
    
    /**
     *
     * @author Lenovo
     */
    public class CategoryTest {
        
        private static Connection conn;
        private static CategoryService cate = new CategoryService();
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
        public void testGetCategory() throws SQLException{
            int n = 2;
            String expected = "Món chính";
            String actual = cate.getCategory(n).getCategoryName();
            Assertions.assertEquals(expected, actual);
        }         
    }
