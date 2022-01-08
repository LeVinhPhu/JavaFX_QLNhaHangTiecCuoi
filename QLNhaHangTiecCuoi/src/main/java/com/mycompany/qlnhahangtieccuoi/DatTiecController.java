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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
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
    private double totalFoodAndService;
    private double totalCost;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //this.dayParty.setMinDate(System.currentTimeMillis() - 1000);
        //this.dayParty.setDayCellFactory(cf -> new Min);
        DayLimit();
        this.txtSoBan.setText(null);
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
        this.txtSoBan.textProperty().addListener((evt)->{
            if (this.txtSoBan.getText().length() > 0){                
                int tableNum = Integer.parseInt(this.txtSoBan.getText());
                if (tableNum > Integer.parseInt(this.slBanTD.getText())){
                    this.txtSoBan.setText(this.slBanTD.getText());
                    tableNum = Integer.parseInt(this.slBanTD.getText());;
                }                    
                totalCost = totalFoodAndService + tableNum * Double.parseDouble(this.donGiaBan.getText());
                this.tongTien.setText(String.valueOf(String.format("%.0f", totalCost)));
            } 
            else{
                this.txtSoBan.setText("0");
                this.tongTien.setText(String.valueOf(totalFoodAndService));
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
    
    @FXML
    private void handleClickTableViewService(MouseEvent click){
        Services service = this.tbService.getSelectionModel().getSelectedItem();
        double temp;
        if (service != null){
            if (service.getSelect().isSelected())                
                service.getSelect().setSelected(false);  //Chuyển trạng thái của checkbox                            
            else
                service.getSelect().setSelected(true); //Chuyển trạng thái của checkbox
        }
        if (service.getSelect().isSelected()){
            this.totalFoodAndService += service.getUnitPrice();
            temp = totalFoodAndService + totalCost;
            this.tongTien.setText(String.valueOf(String.format("%.0f", temp)));
            getPaymentID();            
        }
        else{
            this.totalFoodAndService -= service.getUnitPrice();
            if (totalFoodAndService > 0)
                temp = totalCost - totalFoodAndService;
            else
                temp = Integer.parseInt(this.txtSoBan.getText()) * Double.parseDouble(this.donGiaBan.getText());
            this.tongTien.setText(String.valueOf(String.format("%.0f", temp)));
            getPaymentID();
        }
    }
    @FXML
    private void handleClickTableViewFood(MouseEvent click){ //chưa xửa xong
        Food food = this.tbFood.getSelectionModel().getSelectedItem();
        double temp = 0;
        if (food != null){
            if (food.getSelect().isSelected())                
                food.getSelect().setSelected(false);  //Chuyển trạng thái của checkbox                            
            else
                food.getSelect().setSelected(true); //Chuyển trạng thái của checkbox
        }
        if (food.getSelect().isSelected()){
            this.totalFoodAndService += food.getUnitPrice();
            temp = totalFoodAndService + totalCost;
            this.tongTien.setText(String.valueOf(String.format("%.0f", temp)));
            getPaymentID();
        }
        else{
            this.totalFoodAndService -= food.getUnitPrice();
            if (totalFoodAndService > 0){
                temp = totalCost - totalFoodAndService;
                totalCost = temp;
            }
            else
                totalCost = Integer.parseInt(this.txtSoBan.getText()) * Double.parseDouble(this.donGiaBan.getText());
            this.tongTien.setText(String.valueOf(String.format("%.0f", temp)));
            getPaymentID();
        }
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
            this.txtSoBan.setText(this.slBanTD.getText());
        }
    }
    @FXML
    private void EventComboBox(ActionEvent event){
        getSanhCuoiID();
    }
    
    private void getPaymentID(){
        PaymentMethods pay = this.cbPhuongThucTT.getSelectionModel().getSelectedItem();
        if(this.cbSanhCuoi != null){
            if (pay != null){
                int payID = pay.getPaymentID();
                if (payID == 1){
                    this.tienThanhToan.setText(this.tongTien.getText());
                }
                else{
                   double money = Double.parseDouble(this.tongTien.getText());
                   money *= 0.1;
                   this.tienThanhToan.setText(String.valueOf(String.format("%.0f", money)));
                }
            }
        }
    }
    @FXML
    private void EventTienThanhToan(ActionEvent event){
        getPaymentID();
    }
    
    @FXML
    private void restrictNumbersOnly(KeyEvent keyEvent) {
        this.txtSoBan.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            txtSoBan.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }
    
    private void init(){
        this.txtSoBan.setText("0");
        this.totalFoodAndService = 0;
        this.totalCost = 0;
        this.txtKeywordFood.setText(null);
        this.txtKeywordService.setText(null);
    }

    private class MinDateCell extends DateCell {

        private ObjectProperty<LocalDate> date;

        private MinDateCell(ObjectProperty<LocalDate> date) {
            this.date = date;
        }

        @Override
        public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            if (item.isBefore(date.get())) {
                this.setDisable(true);
                setStyle("-fx-background-color: #7e7e7e;"); // I used a different coloring to see which are disabled.
            }
        }

    }
    
    private void DayLimit(){
        this.dayParty.setDayCellFactory(cf -> {
            DatePicker dayNow = new DatePicker();
            dayNow.setValue(LocalDate.now());
            return new MinDateCell(dayNow.valueProperty());
        });
    }
}
