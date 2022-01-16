package qlnhahangtieccuoitest;


import com.mycompany.conf.Utils;
import com.mycompany.qlnhahangtieccuoi.DangKyController;
import java.text.ParseException;
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
    @CsvSource({"Giao%^* Giao%&$ ,GiaoGiao", "123Nguyen *^Van Quoc$%,123NguyenVanQuoc", "+-*Van vo<>?, Vanvo"})
    public void ClearSpecialCharTest(String s, String expected) {
        Assertions.assertEquals(expected, Utils.clearSpecialChar(s));
    }
    
    @ParameterizedTest
    @CsvSource({"Giao,'',0386634020,123456, 123456,19","Giao,'','',123456, 123456,19","'','','',123456, 123456,19"})
    public void MessTestTrue(String ho, String ten, String sdt, String mk, String mk2, int expected) {
        int actual = Utils.Mess(ho,ten,sdt,mk,mk2).length();
        Assertions.assertEquals(expected, actual);
    }
    
    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/CSVFileTest/TestThongTinDangKy.csv", numLinesToSkip = 0)
    public void MessTestNotNull(String ho, String ten, String sdt, String mk, String mk2, String expected) {
        Assertions.assertEquals(expected,Utils.Mess(ho,ten,sdt,mk,mk2));    
    }
    
    @ParameterizedTest
    @CsvSource({"123456,47","12345qa,47","quocQQQ,47","Q0168456q,0"})
    public void checkPassword(String str, int expected){
        int actual = Utils.checkPassword(str).length();
        Assertions.assertEquals(expected, actual);
    }
    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/CSVFileTest/TestGetNextWeek.csv", numLinesToSkip = 0)
    public void testNextWeek(String strDate, int d, int m, int y, int expected){
        d = Integer.parseInt(strDate.substring(8));
        m = Integer.parseInt(strDate.substring(5, 7));
        y = Integer.parseInt(strDate.substring(0, 4));
        d += 7;
        int sn = Utils.sntd(m, y);
        if (d > sn){
            m++;
            if (m > 12){
                y++;
                m = 1;
            }
            d -= sn;
        }
        int actual = d;
        Assertions.assertEquals(expected, actual);
    }
    @ParameterizedTest
    @CsvSource({"2,2020,29","2,2021,28","4,2000,30","12,2030,31"})
    public void testSntd(int m, int y, int expected){
        Assertions.assertEquals(expected, Utils.sntd(m, y));
    }
    
    @ParameterizedTest
    @CsvSource({"2002-02-01, 2001-12-31,1","2019-07-07,2019-10-30,-1","2022-01-16,2022-01-16,0"})
    public void testCompareTwoDates(String d1, String d2, int expected) throws ParseException{
        Assertions.assertEquals(expected, Utils.CompareTwoDates(d1, d2));
    }
}
    
