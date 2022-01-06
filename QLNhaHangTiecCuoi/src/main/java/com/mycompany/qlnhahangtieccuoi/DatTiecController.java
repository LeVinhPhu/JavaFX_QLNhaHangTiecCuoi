/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.pojo.Food;
import com.mycompany.pojo.SanhCuoi;
import com.mycompany.pojo.Services;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.FoodService;
import com.mycompany.services.SanhCuoiService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DatTiecController implements Initializable {
    
    @FXML private TableView<Food> tbFood;
    @FXML TableView<Services> tbService;
    @FXML TableView<SanhCuoi> tbSanhCuoi;
    @FXML TextField txtKeywordFood;
    @FXML TextField txtKeywordService;
    @FXML TextField txtKeywordSanhCuoi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableViewService();
        LoadTableViewFood();
        LoadTableViewSanhCuoi();
        try {
            LoadTableDataService(null);
            LoadTableDataFood(null);
            LoadTableDataSanhCuoi(null);
        } catch (SQLException ex) {
            Logger.getLogger(QLDichVuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtKeywordService.textProperty().addListener((evt)-> {
            try {
                this.LoadTableDataService(this.txtKeywordService.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QLDichVuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.txtKeywordFood.textProperty().addListener((evt)-> {
            try {
                this.LoadTableDataFood(this.txtKeywordFood.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QLMonAnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.txtKeywordSanhCuoi.textProperty().addListener((evt)-> {
            try {
                this.LoadTableDataSanhCuoi(this.txtKeywordSanhCuoi.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QLDichVuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
     private void LoadTableViewFood(){
        TableColumn colName = new TableColumn("Tên món ăn");
        colName.setCellValueFactory(new PropertyValueFactory("foodName"));
        colName.setPrefWidth(250);
        
        TableColumn colPrice = new TableColumn("Giá món ăn");
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPrice.setPrefWidth(100);
        
        TableColumn colNote = new TableColumn("Ghi chú");
        colNote.setCellValueFactory(new PropertyValueFactory("notes"));
        colNote.setPrefWidth(300);
        
        TableColumn colSelect = new TableColumn("Chọn");
        colSelect.setCellValueFactory(new PropertyValueFactory("select"));
        colSelect.setPrefWidth(100);
        
        this.tbFood.getColumns().addAll(colName, colPrice, colNote, colSelect);
    }
    
    private void LoadTableDataFood(String kw) throws SQLException{
        FoodService d = new FoodService();
        this.tbFood.setItems(FXCollections.observableArrayList(d.getFood(kw)));
    }
    
    private void LoadTableViewService(){        
        TableColumn colName = new TableColumn("Tên dịch vụ");
        colName.setCellValueFactory(new PropertyValueFactory("serviceName"));
        colName.setPrefWidth(350);
        
        TableColumn colPrice = new TableColumn("Giá dịch vụ");
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPrice.setPrefWidth(150);
        
        TableColumn colSelect = new TableColumn("Chọn");
        colSelect.setCellValueFactory(new PropertyValueFactory("select"));
        colSelect.setPrefWidth(100);
        
        this.tbService.getColumns().addAll(colName, colPrice, colSelect);
    }
    
    private void LoadTableDataService(String kw) throws SQLException{
        DichVuServices s = new DichVuServices();
        this.tbService.setItems(FXCollections.observableArrayList(s.getServices(kw)));
    }
    
    private void LoadTableViewSanhCuoi(){
        
        TableColumn colName = new TableColumn("Tên Sảnh cưới");
        colName.setCellValueFactory(new PropertyValueFactory("sanhCuoiName"));
        colName.setPrefWidth(200);
        
        TableColumn colNum = new TableColumn("Số bàn tối đa");
        colNum.setCellValueFactory(new PropertyValueFactory("soBanToiDa"));
        colNum.setPrefWidth(100);
        
        TableColumn colPrice = new TableColumn("Giá sảnh cưới");
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPrice.setPrefWidth(100);
        
        TableColumn colNote = new TableColumn("Ghi chú");
        colNote.setCellValueFactory(new PropertyValueFactory("notes"));
        colNote.setPrefWidth(300);
        
        TableColumn colSelect = new TableColumn("Chọn");
        colSelect.setCellValueFactory(new PropertyValueFactory("select"));
        colSelect.setPrefWidth(100);
        
        this.tbSanhCuoi.getColumns().addAll(colName, colNum, colPrice, colNote, colSelect);
    }
    
    private void LoadTableDataSanhCuoi(String kw) throws SQLException{
        SanhCuoiService s = new SanhCuoiService();
        this.tbSanhCuoi.setItems(FXCollections.observableArrayList(s.getSanhCuoiList(kw)));
    }
    
}
