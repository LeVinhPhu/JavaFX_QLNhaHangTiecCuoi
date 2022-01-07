/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.pojo.Food;
import com.mycompany.pojo.PaymentMethods;
import com.mycompany.pojo.SanhCuoi;
import com.mycompany.pojo.Services;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.FoodService;
import com.mycompany.services.PaymentMethodService;
import com.mycompany.services.SanhCuoiService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DatTiecController implements Initializable {
    
    @FXML private TableView<Food> tbFood;
    @FXML private TableView<Services> tbService;
    @FXML private TextField txtKeywordFood;
    @FXML private TextField txtKeywordService;
    @FXML private TextField txtSoBan;
    @FXML private ComboBox<SanhCuoi> cbSanhCuoi;
    @FXML private ComboBox<PaymentMethods> cbPhuongThucTT;
    @FXML private Text tongTien; 
    @FXML private Text donGiaBan; 
    @FXML private Text slBanTD; 
    @FXML private Text tienThanhToan; 
    @FXML private DatePicker dayParty;
    private double totalCost;
    private double totalPrepayment;
    private int soBan = 0;
    private double price;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableViewService();
        LoadTableViewFood();
        SanhCuoiService sc = new SanhCuoiService();
        PaymentMethodService p = new PaymentMethodService();
        try {
            LoadTableDataService(null);
            LoadTableDataFood(null);
            this.cbSanhCuoi.setItems(FXCollections.observableList(sc.getSanhCuoiList(null)));
            this.cbPhuongThucTT.setItems(FXCollections.observableList(p.getPaymentMethods()));
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
        colSelect.setId("disa");
        //colSelect.setStyle(string);
        
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
    
    @FXML
    private void handleClickTableViewService(MouseEvent click){
        Services service = this.tbService.getSelectionModel().getSelectedItem();
        if (service != null){
            if (service.getSelect().isSelected())                
                service.getSelect().setSelected(false);  //Chuyển trạng thái của checkbox                            
            else
                service.getSelect().setSelected(true); //Chuyển trạng thái của checkbox
        }
        if (service.getSelect().isSelected()){
            this.totalCost += service.getUnitPrice();
            this.tongTien.setText(String.valueOf(totalCost));
        }
        else{
            this.totalCost -= service.getUnitPrice();
            if (totalCost > 0)
                this.tongTien.setText(String.valueOf(totalCost));
            else this.tongTien.setText(String.valueOf(0));
        }
    }
    @FXML
    private void handleClickTableViewFood(MouseEvent click){
        Food food = this.tbFood.getSelectionModel().getSelectedItem();
        if (food != null){
            if (food.getSelect().isSelected())                
                food.getSelect().setSelected(false);  //Chuyển trạng thái của checkbox                            
            else
                food.getSelect().setSelected(true); //Chuyển trạng thái của checkbox
        }
        if (food.getSelect().isSelected()){
            this.totalCost += food.getUnitPrice();
            this.tongTien.setText(String.valueOf(totalCost));
        }
        else{
            this.totalCost -= food.getUnitPrice();
            if (totalCost > 0)
                this.tongTien.setText(String.valueOf(totalCost));
            else this.tongTien.setText(String.valueOf(0));
        }
    }
    
    @FXML
    private void BtdLuu(ActionEvent event) throws SQLException{
        List<Services> listService = new ArrayList<>();
        DichVuServices dv = new DichVuServices();
        listService = dv.getServices(null);
        for (Services s : listService){
            if (s.getSelect().isSelected()){
                this.totalCost += s.getUnitPrice();                
            }
        }
        List<Food> listFood = new ArrayList<>();
        FoodService food = new FoodService();
        listFood = food.getFood(null);
        for (Food f : listFood){
            if (f.getSelect().isSelected()){
                this.totalCost += f.getUnitPrice();                
            }
        }
        this.tongTien.setText(String.valueOf(totalCost));
    }
    
    @FXML
    private void BtrHuy(ActionEvent event){
        
    }
    @FXML
    private void BthThanhToan (ActionEvent event){
        
    }
    
    private void getSanhCuoiID(){
        SanhCuoi sc = this.cbSanhCuoi.getSelectionModel().getSelectedItem();
        if (sc != null){
            this.donGiaBan.setText(String.valueOf(sc.getUnitPrice()));
            this.slBanTD.setText(String.valueOf(sc.getSoBanToiDa()));
        }
    }
    @FXML
    private void EventComboBox(ActionEvent event){
        getSanhCuoiID();
        this.price = Double.parseDouble(this.donGiaBan.getText());
    }
    
    @FXML
    private void restrictNumbersOnly(KeyEvent keyEvent) {
        this.txtSoBan.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            txtSoBan.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }
    @FXML
    private void TextChanged(KeyEvent keyEvent){
        this.txtSoBan.textProperty().addListener((evt)->{
            this.soBan = Integer.getInteger(this.txtSoBan.getText());
            this.totalCost += soBan * price;
            this.tongTien.setText(String.valueOf(totalCost));
        });
    }

}
