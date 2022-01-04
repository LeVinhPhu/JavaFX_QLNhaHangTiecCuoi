/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.conf.Utils;
import com.mycompany.services.CustomerServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DangNhapController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML 
    private TextField sdt;
    @FXML 
    private TextField mk;
    
    public void DangNhapBtr(ActionEvent event) throws SQLException{
        String user  = this.sdt.getText();
        String pass = this.mk.getText();
        //Kiểm tra user có tồn tại ko
        CustomerServices cus = new CustomerServices();
        boolean kt = cus.KiemTraDangNhap(user, pass);
        if (kt)
            Utils.getBox("Đăng Nhập thành công", Alert.AlertType.INFORMATION).show();
        else
            Utils.getBox("Sai số điện thoại hoặc mật khẩu", Alert.AlertType.WARNING).show();
    }
}
