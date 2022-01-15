package qlnhahangtieccuoitest;


import com.mycompany.conf.Utils;
import com.mycompany.qlnhahangtieccuoi.DangKyController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvFileSource;

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
    @CsvSource({"Giao           Giao,Giao Giao", " Nguyen Van   Quoc,Nguyen Van Quoc"})
    public void removeWhitespaceTest(String s, String expected) {
        Assertions.assertEquals(expected, Utils.removeWhitespace(s));
    }

    //Ký tự khoảng trắng không phải là ký tự đặc biệt
    @ParameterizedTest
    @CsvSource({"Giao%^* Giao%&$ ,GiaoGiao", "Nguyen *^Van Quoc$%,NguyenVanQuoc", "+-*Van vo<>?, Vanvo"})
    public void ClearSpecialCharTest(String s, String expected) {
        Assertions.assertEquals(expected, Utils.clearSpecialChar(s));
    }
    @ParameterizedTest
    @CsvSource({",Giao,0386634020,123456, 123456,Không được để trống", ",,,12345, true,Không được để trống"})
    public void MessTestTrue(String ho, String ten, String sdt, String mk, String mk2, String expected) {
        Assertions.assertEquals(Utils.Mess(ho,ten,sdt,mk,mk2), expected);
    }
    @ParameterizedTest
    @CsvSource({"Le,Vinh Phu,03856283755,123456, 123456", "Nguyen,Vu,0904716392,123456, 123456"})
    public void MessTestNull(String ho, String ten, String sdt, String mk, String mk2) {
        Assertions.assertNull(Utils.Mess(ho,ten,sdt,mk,mk2));    
    }
    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/CSVFileTest/TestThongTinDangKy.csv", numLinesToSkip = 0)
    public void MessTestNotNull(String ho, String ten, String sdt, String mk, String mk2, String expected) {
        Assertions.assertEquals(expected,Utils.Mess(ho,ten,sdt,mk,mk2));    
    }
}
    
