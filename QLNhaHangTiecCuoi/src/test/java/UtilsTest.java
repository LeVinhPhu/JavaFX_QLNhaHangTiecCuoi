
import com.mycompany.conf.Utils;
import com.mycompany.qlnhahangtieccuoi.DangKyController;
import org.junit.jupiter.api.Assertions;
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
public class UtilsTest {
    private static DangKyController dk = new DangKyController();
    @ParameterizedTest
    @CsvSource({"Nguyen Van, Nguyen   Van","Nguyen Van,   Nguyen Van   ","Nguyen Van, Nguyen         Van    "})
    public void removeWhitespaceTest(String expected, String s){
        Assertions.assertEquals(expected, s);
    }
}
