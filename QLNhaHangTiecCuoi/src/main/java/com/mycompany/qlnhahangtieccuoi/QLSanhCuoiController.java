/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.SanhCuoi;
import com.mycompany.services.SanhCuoiService;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class QLSanhCuoiController implements Initializable {
    
    @FXML TableView<SanhCuoi> tbSanhCuoi;
    @FXML TextField txtKeyword;
    @FXML TextField txtSCName;
    @FXML TextField txtSCPrice;
    @FXML TextField txtSoBanToiDa;
    @FXML TextField txtNotes;
    @FXML Label lbMess;

    /**
     * Initializes the controller class.
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
        
        TableColumn colName = new TableColumn("Tên Sảnh cưới");
        colName.setCellValueFactory(new PropertyValueFactory("sanhCuoiName"));
        colName.setPrefWidth(200);
        
        TableColumn colNum = new TableColumn("Số bàn tối đa");
        colNum.setCellValueFactory(new PropertyValueFactory("soBanToiDa"));
        colNum.setPrefWidth(100);
        
        TableColumn colPrice = new TableColumn("Giá sảnh cưới");
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPrice.setPrefWidth(100);
        
        this.tbSanhCuoi.getColumns().addAll(colName, colNum, colPrice);
    }
    
    private void LoadTableData(String kw) throws SQLException{
        SanhCuoiService s = new SanhCuoiService();
        this.tbSanhCuoi.setItems(FXCollections.observableArrayList(s.getSanhCuoiList(kw)));
    }
    
    @FXML
    private void BtrAddSanhCuoi(ActionEvent event) throws SQLException{
        try{
            String scName = this.txtSCName.getText();
            double unitprice = Double.parseDouble(this.txtSCPrice.getText());
            int sbtd = Integer.parseInt(this.txtSoBanToiDa.getText());
            String notes = this.txtNotes.getText();
            
            SanhCuoi s = new SanhCuoi(scName, sbtd, unitprice, notes);
            SanhCuoiService sc = new SanhCuoiService();
            if (sc.kiemTraTonTai(scName)){
                sc.AddSanhCuoi(s);
                LoadTableData(null);
                Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                init();
            }
            else
                lbMess.setText("Sảnh cưới đã tồn tại");
        }catch(NullPointerException ex){
            lbMess.setText("Bạn phải điền đủ các cột dữ liệu");
        }catch (NumberFormatException ex2){
                lbMess.setText("Bạn phải điền đủ các cột dữ liệu");
        }
    }
    @FXML
    private void BtrUpdateSanhCuoi(ActionEvent event) throws SQLException{
        SanhCuoi sc = this.tbSanhCuoi.getSelectionModel().getSelectedItem();
        if (sc != null){
            try{
            int scID = sc.getSanhCuoiID();
            String serviceName = this.txtSCName.getText();            
            int sbtd = Integer.parseInt(this.txtSoBanToiDa.getText()); 
            double unitprice = Double.parseDouble(this.txtSCPrice.getText());
            String notes = this.txtNotes.getText();
            SanhCuoiService scService = new SanhCuoiService();
            scService.UpdateSanhCuoi(scID, serviceName, sbtd, unitprice, notes);
            LoadTableData(null);      
            Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
            init();
            }catch (NullPointerException ex){
                lbMess.setText("Bạn phải điền đủ các cột dữ liệu");
            }catch (NumberFormatException ex2){
                lbMess.setText("Bạn phải điền đủ các cột dữ liệu");
            } 
        }
        else
            lbMess.setText("Chưa chọn đối tượng để sửa");
    }
    @FXML
    private void BtrDeleteSanhCuoi(ActionEvent event) throws SQLException{
        SanhCuoi s = this.tbSanhCuoi.getSelectionModel().getSelectedItem();
        if (s != null){
            int scID = s.getSanhCuoiID();
            SanhCuoiService sc = new SanhCuoiService();
            sc.DeleteSanhCuoi(scID);
            LoadTableData(null);
            Utils.getBox("Xoá thành công", Alert.AlertType.INFORMATION).show();
            init();
        }
        else
            lbMess.setText("Chưa chọn đối tượng để xoá");
    }
    
    @FXML
    private void handleClickTableView(MouseEvent click){
        SanhCuoi sc = this.tbSanhCuoi.getSelectionModel().getSelectedItem();
        if (sc != null){
            this.txtSCName.setText(sc.getSanhCuoiName());
            this.txtSCPrice.setText(String.valueOf(sc.getUnitPrice()));
            this.txtSoBanToiDa.setText(String.valueOf(sc.getSoBanToiDa()));
            this.txtNotes.setText(sc.getNotes());
        }
    }
    private void init(){
        this.lbMess.setText(null);
        this.txtKeyword.setText("");
        this.txtSoBanToiDa.setText("0");
        this.txtSCPrice.setText("0");
        this.txtNotes.setText("");
        this.txtSCName.setText("");
    }
    
    @FXML
    private void restrictNumbersOnly(KeyEvent keyEvent) {
        this.txtSCPrice.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            txtSCPrice.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
        this.txtSoBanToiDa.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            txtSoBanToiDa.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
        
    }
}
