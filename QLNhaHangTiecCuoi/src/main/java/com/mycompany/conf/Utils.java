/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conf;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        return null;
    }

    
}
