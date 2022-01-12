/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.services.CustomerServices;
import com.mycompany.services.EmployeeService;
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
import javafx.scene.control.ComboBox;
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

    @FXML private TextField sdt;
    @FXML private TextField mk;
    @FXML private Label lbMess;
    @FXML private ComboBox<String> cbLoaiDN;
    public static int cusID = 0;
    public static int emID = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCombobox();
    }    
    
    @FXML
    private void DangNhapBtr(ActionEvent event) throws SQLException, IOException{
        String user  = this.sdt.getText();
        String pass = this.mk.getText();
        String cate = this.cbLoaiDN.getValue();
        if (cate.equals("Khách hàng")){
            CustomerServices cus = new CustomerServices();
            boolean kt = cus.KiemTraDangNhap(user, pass);
            if (kt){
                cusID = cus.getCustomerID(user);
                emID = 0;
//                Utils.getBox("Đăng Nhập thành công", Alert.AlertType.INFORMATION).show();
                //qua scene đặt tiệc
                Parent root = FXMLLoader.load(getClass().getResource("ManHinhChon.fxml"));
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                lbMess.setText("Sai số điện thoại hoặc mật khẩu");
                this.sdt.setStyle("-fx-border-color: red;");
                this.mk.setStyle("-fx-border-color: red;");
            }
        }
        else{
            EmployeeService em = new EmployeeService();
            boolean kt = em.KiemTraDangNhap(user, pass);
            if (kt){
                emID = em.GetEmployeeID(user);
                cusID = -1;
//                Utils.getBox("Đăng Nhập thành công", Alert.AlertType.INFORMATION).show();
                //qua scene Quản lý
                Parent root = FXMLLoader.load(getClass().getResource("ManHinhChonNV.fxml"));
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                lbMess.setText("Sai số điện thoại hoặc mật khẩu");
                this.sdt.setStyle("-fx-border-color: red;");
                this.mk.setStyle("-fx-border-color: red;");
            }
        }
    }
    @FXML
    private void DangKyBtr(ActionEvent event) throws IOException{
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
    
    private void initCombobox(){
        this.cbLoaiDN.getItems().add("Khách hàng");
        this.cbLoaiDN.getItems().add("Nhân viên");
        this.cbLoaiDN.setValue("Khách hàng");
    }
    
}
