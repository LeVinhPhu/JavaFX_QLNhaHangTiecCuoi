/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.conf.Utils;
import com.mycompany.services.CustomerServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
    @FXML private TextField sdt;
    @FXML private TextField mk;
    @FXML private Label lbMess;
    public static int cusID = 5;
    
    public void DangNhapBtr(ActionEvent event) throws SQLException{
        String user  = this.sdt.getText();
        String pass = this.mk.getText();
        //Kiểm tra user có tồn tại ko
        CustomerServices cus = new CustomerServices();
        boolean kt = cus.KiemTraDangNhap(user, pass);
        if (kt){
            cusID = cus.getCustomerID(user);
            Utils.getBox("Đăng Nhập thành công", Alert.AlertType.INFORMATION).show();
            //qua scene đặt tiệc
        }
        else{
            lbMess.setText("Sai tên tài khoản hoặc mật khẩu");
            this.sdt.setStyle("-fx-border-color: red;");
            this.mk.setStyle("-fx-border-color: red;");
        }
    }
    public void DangKyBtr(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("DangKy.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    @FXML
    private void restrictNumbersOnly(KeyEvent keyEvent) {
        this.sdt.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            sdt.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }
}
