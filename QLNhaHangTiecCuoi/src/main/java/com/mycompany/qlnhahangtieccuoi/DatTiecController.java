/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Food;
import com.mycompany.pojo.OrderDetails;
import com.mycompany.pojo.Orders;
import com.mycompany.pojo.PaymentMethods;
import com.mycompany.pojo.SanhCuoi;
import com.mycompany.pojo.Services;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.FoodService;
import com.mycompany.services.OrderDetailsService;
import com.mycompany.services.OrdersService;
import com.mycompany.services.PaymentMethodService;
import com.mycompany.services.SanhCuoiService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    @FXML private ComboBox<String> cbGioThue;
    @FXML private Text tongTien; 
    @FXML private Text donGiaBan; 
    @FXML private Text slBanTD; 
    @FXML private Text tienThanhToan; 
    @FXML private DatePicker dayParty;
    @FXML private Label lbMess;
    private double totalService;
    private double totalCost;
    private double totalFood;
    private int soBan;
    private List<Food> listFood;
    private List<Services> listService;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO;
        InitCbGioThue();
        DayLimit();
        this.txtSoBan.setText("0");
        listFood = new ArrayList<>();
        listService = new ArrayList<>();
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
                if (this.txtSoBan.getText().length() > 1){
                    String zero = this.txtSoBan.getText().substring(0, 1);
                    if ( zero.equals("0"))
                        this.txtSoBan.setText(this.txtSoBan.getText().substring(1, txtSoBan.getText().length()));
                }
                int tableNum = Integer.parseInt(this.txtSoBan.getText());
                if (tableNum > Integer.parseInt(this.slBanTD.getText())){
                    this.txtSoBan.setText(this.slBanTD.getText());
                    tableNum = Integer.parseInt(this.slBanTD.getText());;
                }                    
                totalCost = totalService + (tableNum * Double.parseDouble(this.donGiaBan.getText())) + (totalFood * Double.parseDouble(txtSoBan.getText()));;
                this.tongTien.setText(String.valueOf(String.format("%.0f", totalCost)));
                soBan = Integer.parseInt(this.txtSoBan.getText());
            } 
            else{
                this.txtSoBan.setText("0");
                this.tongTien.setText(String.valueOf(totalService + totalFood));
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
        try{
            Services service = this.tbService.getSelectionModel().getSelectedItem();
            double temp;
            if (service != null){
                if (service.getSelect().isSelected()){                
                    service.getSelect().setSelected(false);  //Chuyển trạng thái của checkbox  
                    listService.remove(service);
                }
                else{
                    service.getSelect().setSelected(true);
                    listService.add(service);
                }
            }            
            if (service.getSelect().isSelected()){
                this.totalService += service.getUnitPrice();
                temp = totalService + totalCost + (totalFood * Double.parseDouble(txtSoBan.getText()));
                this.tongTien.setText(String.valueOf(String.format("%.0f", temp)));
                getPaymentID();                  
            }
            else{                
                this.totalService -= service.getUnitPrice();
                if (totalService <= 0 && totalFood <= 0)
                    try{
                        totalCost = Integer.parseInt(this.txtSoBan.getText()) * Double.parseDouble(this.donGiaBan.getText());
                    }catch (NumberFormatException ex){
                        totalCost = 0;
                    }
                temp = totalService + totalCost + (totalFood * Double.parseDouble(txtSoBan.getText()));
                this.tongTien.setText(String.valueOf(String.format("%.0f", temp)));
                getPaymentID();
            }
        }catch (NullPointerException ex){}
    }
    @FXML
    private void handleClickTableViewFood(MouseEvent click){ //chưa xửa xong
        try{
            Food food = this.tbFood.getSelectionModel().getSelectedItem();
            double temp = 0;
            if (food != null){
                if (food.getSelect().isSelected())                
                    food.getSelect().setSelected(false);  //Chuyển trạng thái của checkbox                            
                else
                    food.getSelect().setSelected(true); //Chuyển trạng thái của checkbox
            }
            if (food.getSelect().isSelected()){
                //this.totalService += food.getUnitPrice();
                this.totalFood += food.getUnitPrice();
                temp = totalService + totalCost + (totalFood * Double.parseDouble(txtSoBan.getText()));
                this.tongTien.setText(String.valueOf(String.format("%.0f", temp)));
                getPaymentID();
                listFood.add(food);
            }
            else{
                listFood.remove(food);
                //this.totalService -= food.getUnitPrice();
                this.totalFood -= food.getUnitPrice();
                if (totalService <= 0 && totalFood <= 0)
                    try{
                        totalCost = Integer.parseInt(this.txtSoBan.getText()) * Double.parseDouble(this.donGiaBan.getText());
                    }catch (NumberFormatException ex){
                        totalCost = 0;
                    }
                temp = totalService + totalCost + (totalFood * Double.parseDouble(txtSoBan.getText()));
                this.tongTien.setText(String.valueOf(String.format("%.0f", temp)));
                getPaymentID();
            }
        }catch(NullPointerException ex){};
    }
    @FXML
    private void BtrQuayLai(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ManHinhChon.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void BtrHuy(ActionEvent event){
        init();
    }
    @FXML
    private void BtrThanhToan (ActionEvent event) throws ParseException, SQLException{
        //Cần xác định sảnh cưới, phương thức thanh toán
        if (this.cbSanhCuoi.getValue() == null)
            this.lbMess.setText("chưa chọn sảnh cưới");
        else if(this.txtSoBan.getText().equals("0")){
            this.lbMess.setText("số lượng bàn phải lớn hơn 0");
        }
        else if (cbPhuongThucTT.getValue() == null)
            this.lbMess.setText("Chưa chọn phương thức thanh toán");
        else if (listFood.size() < 1 || listService.size() < 1)
                    this.lbMess.setText("Phải chọn ít nhất 1 món ăn và 1 dịch vụ");
        else { 
            //Kiểm tra sảnh đã được đặt trước đó chưa
            int scID = getSanhCuoiID();
            String rentalPeriod = this.cbGioThue.getValue();
            String ngay = this.dayParty.getValue().toString();
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaySinh = f.parse(ngay);
            java.sql.Date ngayDT = new java.sql.Date(ngaySinh.getTime());
            OrderDetailsService oD = new OrderDetailsService();
            for (int id : oD.getSCIDList(ngayDT, rentalPeriod)){
                if (id == scID){
                    this.lbMess.setText("Sảnh cưới này không còn trống!");
                    scID = -1;
                    break;
                }
            }
            if (scID != -1){
                //Tạo hoá đơn
                //soBan = tableNum;
                int customerID = DangNhapController.cusID;
                int paymentID = getPaymentID();
                //lấy employeeID: 1 là quy định theo tháng, 2 là bỏ
                int paid = paymentID == 1 ? 1 : 0;
                Date ngayDD = Date.from(Instant.now());
                java.sql.Date orderDate = new java.sql.Date(ngayDD.getTime());
                Orders order = new Orders(customerID, orderDate, paid, paymentID);
                OrdersService orderSer = new OrdersService();
                orderSer.AddOrder(order);
                //lấy mã đơn đặt hàng
                int id = orderSer.getTheLargestOrderID();
                //Tạo chi tiết đơn hàng
                OrderDetailsService orderDetailsSer = new OrderDetailsService();
                //nếu có nhiều hơn 2 dịch vụ hoặc 2 món ăn thì thêm tiếp
                
                listFood.forEach(fo -> {
                    listService.forEach(s -> {
                    OrderDetails orderDetail = new OrderDetails(id, fo.getFoodID(), getSanhCuoiID(),
                            s.getServiceID(), ngayDT, rentalPeriod, soBan, Double.parseDouble(tongTien.getText()));
                        try {
                            orderDetailsSer.AddOrderDetails(orderDetail);
                        } catch (SQLException ex) {
                            Logger.getLogger(DatTiecController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                });
                init();
                this.lbMess.setText("Đặt tiệc thành công"); 
                Utils.getBox("Đặt tiệc thành công", Alert.AlertType.INFORMATION);
            }
        }
        
    }
    
    private int getSanhCuoiID(){
        int scID = 0;
        SanhCuoi sc = this.cbSanhCuoi.getSelectionModel().getSelectedItem();
        if (sc != null){
            this.donGiaBan.setText(String.valueOf(sc.getUnitPrice()));
            this.slBanTD.setText(String.valueOf(sc.getSoBanToiDa()));
            scID = sc.getSanhCuoiID();
        }
        return scID;
    }
    @FXML
    private void EventComboBox(ActionEvent event)   {
        getSanhCuoiID();
        getPaymentID();
    }
    
    private int getPaymentID(){
        int payID = 0;
        PaymentMethods pay = this.cbPhuongThucTT.getSelectionModel().getSelectedItem();
        if(this.cbSanhCuoi != null){
            if (pay != null){
                payID = pay.getPaymentID();
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
        return payID;
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
        this.totalService = 0;
        this.totalFood = 0;
        this.totalCost = 0;
        this.txtKeywordFood.setText("");
        this.txtKeywordService.setText("");
        this.donGiaBan.setText("0");
        this.slBanTD.setText("0");
        this.tongTien.setText("0");
        this.tienThanhToan.setText("0");
        this.cbPhuongThucTT.setValue(null);
        this.cbSanhCuoi.setValue(null);
        this.cbGioThue.setValue("Sáng");
        this.lbMess.setText("");
    }
    private void InitCbGioThue(){
        this.cbGioThue.getItems().add("Sáng");
        this.cbGioThue.getItems().add("Trưa");
        this.cbGioThue.getItems().add("Tối");
        this.cbGioThue.setValue("Sáng");
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
            String date = LocalDate.now().toString();
            int d = 0, m = 0, y = 0;
            dayNow.setValue(Utils.getNextWeek(date, d, m, y));
            dayParty.setValue(Utils.getNextWeek(date, d, m, y));
            return new MinDateCell(dayNow.valueProperty());
        });
    }
}
