package qlnhahangtieccuoitest;


import com.mycompany.conf.JdbcUtils;
import com.mycompany.services.FoodService;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class FoodTest {
    private static Connection conn;
    private static FoodService food = new FoodService();
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
    @CsvSource({"Salad,false","Bia,true","Gà ta,false","Trà sữa,true","Dâu tây,false"})
    public void test_KiemTaTonTai(String name, boolean expected) throws SQLException{
        Assertions.assertEquals(expected, food.KiemTaTonTai(name));
    }
    
//    @Test
//    public void getFoodListTest(String kw) throws SQLException{
//        boolean flag = false;
//        List<Food> list = food.getFood(kw);
//        for (Food f : list){
//            if (f.getFoodName().equals("Salad")){
//                flag = true;
//                break;
//            }                
//        }
//        Assertions.assertTrue(flag);
//    }
}
