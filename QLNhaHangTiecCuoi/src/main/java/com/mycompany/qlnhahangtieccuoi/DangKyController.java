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
import java.util.Date;
import java.util.InputMismatchException;
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
        init();
    }    
    @FXML private TextField ho;
    @FXML private TextField ten;
    @FXML private TextField diaChi;
    @FXML private TextField sdt;
    @FXML private PasswordField matKhau;
    @FXML private PasswordField matKhau2;
    @FXML private DatePicker ns;
    @FXML private Label lbMess;
    
    public void DangKybtr(ActionEvent event) throws SQLException, ParseException{
        try{
            String hoKH = this.ho.getText();
            if (hoKH == null){
                this.ho.setStyle("-fx-border-color: red;");
            } else this.ho.setStyle("-fx-border-color: green;");
            String tenKH = this.ten.getText();
            if (tenKH == null){
                this.ten.setStyle("-fx-border-color: red;");
            } else this.ten.setStyle("-fx-border-color: green;");
            String SDT = this.sdt.getText();
            String dc = this.diaChi.getText();
            String pass = this.matKhau.getText();
            String pass2 = this.matKhau2.getText();
            double soDT = Double.parseDouble(SDT);
            if (SDT == null || SDT.length() >10){
                this.lbMess.setText("Số điện thoại không hợp lệ"); 
                this.sdt.setStyle("-fx-border-color: red;");
            }
            //ns.setValue(LocalDate.now());
            String ngay = this.ns.getValue().toString();
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaySinh = f.parse(ngay);
            java.sql.Date birthdate = new java.sql.Date(ngaySinh.getTime());
            CustomerServices kh = new CustomerServices();
            if (pass.equals(pass2) == false){
                this.lbMess.setText("2 mật khẩu không giống nhau"); 
                this.matKhau2.setStyle("-fx-border-color: red;");
                this.sdt.setStyle("-fx-border-color: black;");
            } 
            else if (kh.TonTaiSDT(SDT)){
                this.lbMess.setText("Số điện thoại đã tồn tại");
                this.sdt.setStyle("-fx-border-color: red;");
                this.matKhau2.setStyle("-fx-border-color: black;");
            }
            else{
                Customers k = new Customers(SDT, hoKH, tenKH, birthdate, dc, pass);
                kh.DangKyKhachHang(k);
                Utils.getBox("Đăng ký thành công", Alert.AlertType.INFORMATION).show();
            }  
        }catch (NullPointerException ex){
            this.lbMess.setText("Phải điền đầy đủ các trường dữ liệu");
        }catch (NumberFormatException ex2){
            this.lbMess.setText("Số điện thoại không hợp lệ");
            this.sdt.setStyle("-fx-border-color: red;");
        }
    }

    private void init(){
        this.ho.setText(null);
        this.ten.setText(null);
        this.matKhau.setText(null);
        this.matKhau2.setText(null);
        this.sdt.setText(null);
    }
    private void KiemTraSDT(){
        
    }
    
    
}
