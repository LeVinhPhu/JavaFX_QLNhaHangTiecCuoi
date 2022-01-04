/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Services;
import com.mycompany.services.DichVuServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class QLDichVuController implements Initializable {
    @FXML TableView<Services> tbService;
    @FXML TextField txtKeyword;
    @FXML TextField txtServiceName;
    @FXML TextField txtServicePrice;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
        public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableView();
        try {
            LoadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(QLDichVuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtKeyword.textProperty().addListener((evt)-> {
            try {
                this.LoadTableData(this.txtKeyword.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QLDichVuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    private void LoadTableView(){        
        TableColumn colName = new TableColumn("Tên dịch vụ");
        colName.setCellValueFactory(new PropertyValueFactory("serviceName"));
        colName.setPrefWidth(350);
        
        TableColumn colPrice = new TableColumn("Giá dịch vụ");
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPrice.setPrefWidth(150);
        
        this.tbService.getColumns().addAll(colName, colPrice);
    }
    
    private void LoadTableData(String kw) throws SQLException{
        DichVuServices s = new DichVuServices();
        this.tbService.setItems(FXCollections.observableArrayList(s.getServices(kw)));
    }
    
    private void BtrAddService(ActionEvent event) throws SQLException{
        String serviceName = txtServiceName.getText();
        double unitprice = Double.parseDouble(txtServicePrice.getText());
        Services s = new Services(serviceName, unitprice);
        DichVuServices dv = new DichVuServices();
        dv.AddService(s);
        LoadTableView();
        Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
    }
    private void BtrUpdateService(ActionEvent event) throws SQLException{
        Services service = tbService.getSelectionModel().getSelectedItem();
        int serID = service.getServiceID();
        String serviceName = txtServiceName.getText();
        double unitprice = Double.parseDouble(txtServicePrice.getText());
        DichVuServices dv = new DichVuServices();
        dv.UpdateService(serID, serviceName, unitprice);
        LoadTableView();
        Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
    }
    private void BtrDeleteService(ActionEvent event) throws SQLException{
        Services service = tbService.getSelectionModel().getSelectedItem();
        int serID = service.getServiceID();
        DichVuServices dv = new DichVuServices();
        dv.DeleteService(serID);
        LoadTableView();
        Utils.getBox("Xoá thành công", Alert.AlertType.INFORMATION).show();
    }
    
    @FXML
    private void handleClickTableView(MouseEvent click){
        Services service = tbService.getSelectionModel().getSelectedItem();
        if (service != null){
            txtServiceName.setText(service.getServiceName());
            txtServicePrice.setText(String.valueOf(service.getUnitPrice()));
        }
    }
}
