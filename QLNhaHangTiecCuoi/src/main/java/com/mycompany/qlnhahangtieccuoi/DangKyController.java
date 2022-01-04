/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Customers;
import com.mycompany.services.CustomerServices;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DangKyController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML private TextField ho;
    @FXML private TextField ten;
    @FXML private TextField diaChi;
    @FXML private TextField sdt;
    @FXML private PasswordField matKhau;
    @FXML private PasswordField matKhau2;
    @FXML private DatePicker ns;
    @FXML private Label lb;
    
    public void demo(ActionEvent event){
        ns.setValue(LocalDate.now());
        String ngay = this.ns.getValue().toString();
        lb.setText(ngay);
    }
    public void DangKybtr(ActionEvent event) throws SQLException, ParseException{
        String hoKH = this.ho.getText();
        String tenKH = this.ten.getText();
        String SDT = this.sdt.getText();
        String dc = this.diaChi.getText();
        String pass = this.matKhau.getText();
        String pass2 = this.matKhau2.getText();
        ns.setValue(LocalDate.now());
        String ngay = this.ns.getValue().toString();
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySinh = f.parse(ngay);
        java.sql.Date sql = new java.sql.Date(ngaySinh.getTime()); 
        //KhachHangServices kh = new KhachHangServices();
        CustomerServices kh = new CustomerServices();
        if (pass.equals(pass2) == false)
            Utils.getBox("2 password không trùng nhau", Alert.AlertType.WARNING).show();        
        else if (kh.TonTaiSDT(SDT)){
            Utils.getBox("Số điện thoại đã tồn tại", Alert.AlertType.WARNING).show();
        }
        else{
            Customers k = new Customers(SDT, hoKH, tenKH, sql, dc, pass);
            kh.DangKyKhachHang(k);
            Utils.getBox("Đăng ký thành công", Alert.AlertType.INFORMATION).show();
        }  
    }
 
    public static Date asDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }
    
}
