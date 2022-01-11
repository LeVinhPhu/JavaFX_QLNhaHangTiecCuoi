/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Customers;
import com.mycompany.services.CustomerServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
        DayLimit();
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
            String tenKH = this.ten.getText();
            String SDT = this.sdt.getText();
            String dc = this.diaChi.getText();
            String pass = this.matKhau.getText();
            String pass2 = this.matKhau2.getText();
            
            String ngay = this.ns.getValue().toString();
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaySinh = f.parse(ngay);
            java.sql.Date birthdate = new java.sql.Date(ngaySinh.getTime());
            CustomerServices kh = new CustomerServices();
            if (SDT == null || SDT.length() < 10){
                this.lbMess.setText("Số điện thoại phải có ít nhất 10 số");
            }
            else if (pass.length() < 6){
                this.lbMess.setText("Mật khẩu phải trên 6 ký tự"); 
            }
            else if (pass.equals(pass2) == false){
                this.lbMess.setText("2 mật khẩu không giống nhau");
            } 
            else if (kh.TonTaiSDT(SDT)){
                this.lbMess.setText("Số điện thoại đã tồn tại");
            }
            else{
                Customers k = new Customers(SDT, hoKH, tenKH, birthdate, dc, pass);
                kh.DangKyKhachHang(k);
                Utils.getBox("Đăng ký thành công", Alert.AlertType.INFORMATION).show();
            }  
        }catch (NullPointerException ex){
            this.lbMess.setText("Phải điền đầy đủ các trường dữ liệu");
        }
    }
    public void BtrQuayLai(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void init(){
        this.ho.setText(null);
        this.ten.setText(null);
        this.matKhau.setText(null);
        this.matKhau2.setText(null);
        this.sdt.setText(null);
        this.ns.setValue(LocalDate.now());
    }
    @FXML
    private void restrictNumbersOnly(KeyEvent keyEvent) {
        this.sdt.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            sdt.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }
    
    private class MinDateCell extends DateCell {

        private ObjectProperty<LocalDate> date;

        private MinDateCell(ObjectProperty<LocalDate> date) {
            this.date = date;
        }

        @Override
        public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            if (item.isAfter(date.get())) {
                this.setDisable(true);
                setStyle("-fx-background-color: #7e7e7e;"); // I used a different coloring to see which are disabled.
            }
        }

    }
    
    private void DayLimit(){
        this.ns.setDayCellFactory(cf -> {
            DatePicker dayNow = new DatePicker();
            dayNow.setValue(LocalDate.now());
            return new MinDateCell(dayNow.valueProperty());
        });
    }
    
}
