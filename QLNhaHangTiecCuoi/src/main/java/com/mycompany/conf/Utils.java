/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conf;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.scene.control.Alert;

/**
 *
 * @author Lenovo
 */
public class Utils {
    
    public static Alert getBox (String content, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setContentText(content);
        
        return alert;
    }    
    public static String removeWhitespace(String txt){
        return txt.trim().replaceAll(" +", " ");        
    }
    public static String clearSpecialChar(String s){
        return s.replaceAll("[^\\w\\s]", "").replaceAll(" ", "");
        ///quoc+-*/<>?;l -> quoc
    }
    
    //trường hợp null đã có câu lệnh xử lý
    public static String Mess(String ho, String ten, String sdt, String mk, String mk2){
        if ("".equals(ho) || "".equals(ten) || "".equals(sdt))
                return ("Không được để trống");
            else if (sdt.length() < 10){
                return("Số điện thoại phải có ít nhất 10 số");
            }
            else if (mk.length() < 6){
                return("Mật khẩu phải trên 6 ký tự"); 
            }
            else if (mk.equals(mk2) == false){
                return("2 mật khẩu không giống nhau");
            }
        return "";
    }
    
    public static String checkPassword(String str){
        int upper = 0, lower = 0, number = 0, special = 0;

            for(int i = 0; i < str.length(); i++)
            {
                char ch = str.charAt(i);
                if (ch >= 'A' && ch <= 'Z')
                    upper++;
                else if (ch >= 'a' && ch <= 'z')
                    lower++;
                else if (ch >= '0' && ch <= '9')
                    number++;
                else
                    special++;
            }
        if (upper == 0 || number == 0 || lower == 0)
            return "Mật khẩu phải bao gồm chữ hoa, chữ thường và số";
        return "";        
    }
    
    public static boolean isValidDate(String dateStr, String dateFormat){
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    public static int sntd (int m, int y){
	switch (m)
	{
            case 2:
                    if (y % 400 == 0 || y % 4 == 0 && y % 100 != 0)
                        return 29;
                    return 28;
            case 4: case 6: case 9: case 11:
                    return 30;
            default:
                    return 31;
	}
}
    public static LocalDate getNextWeek(String strDate, int d, int m, int y){
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
        LocalDate date = LocalDate.of(y, m, d);
        return date;
    }
    
    public  static int CompareTwoDates(String partyDay, String dayNow) throws ParseException{
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdformat.parse(partyDay);
        Date d2 = sdformat.parse(dayNow);
        int result = d1.compareTo(d2);
        return result;
    }
    
}
