/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Food;
import com.mycompany.pojo.OrderDetails;
import com.mycompany.pojo.Orders;
import com.mycompany.pojo.Services;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.FoodService;
import com.mycompany.services.OrderDetailsService;
import com.mycompany.services.OrdersService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class HoaDonController implements Initializable {
    @FXML TableView<Orders> tbOrder;
    @FXML TableView<Food> tbFood;
    @FXML TableView<Services> tbService;
    @FXML Text sanhCuoiName;
    @FXML Text ca;
    @FXML Text tongTien;
    @FXML Text tienCanThanhToan;
    @FXML Text soLuongBan;
    @FXML Text tinhTrang;
    @FXML Button btThanhToan;
    @FXML DatePicker ngayToChuc;
    
            
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
        LoadTableView();
        //LoadTableViewFood();
        //LoadTableViewService();
        try {
            LoadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void LoadTableView(){
        TableColumn colOrderID = new TableColumn("Mã đơn hàng");
        colOrderID.setCellValueFactory(new PropertyValueFactory("orderID"));
        colOrderID.setPrefWidth(150);
        
        TableColumn colCustomerID = new TableColumn("mã khách hàng");
        colCustomerID.setCellValueFactory(new PropertyValueFactory("customerID"));
        colCustomerID.setPrefWidth(150);
        
        TableColumn colOrderdate = new TableColumn("Ngày đặt");
        colOrderdate.setCellValueFactory(new PropertyValueFactory("OrderDate"));
        colOrderdate.setPrefWidth(200);
        
        this.tbOrder.getColumns().addAll(colOrderID, colCustomerID, colOrderdate);
    }
    
    private void LoadTableData() throws SQLException{
        OrdersService or = new OrdersService();
        int id = DangNhapController.cusID;
        int cusID = or.getCustomerIDFromOrder(id);
        this.tbOrder.setItems(FXCollections.observableArrayList(or.getOrders(id)));
    }
    @FXML
    private void handleClickTableViewOrders(MouseEvent click) throws SQLException, ParseException{
        Orders order = tbOrder.getSelectionModel().getSelectedItem();
        if (order != null){
        //Load Food
            tbFood.getColumns().clear();
            tbService.getColumns().clear();
            LoadTableViewFood();
            LoadTableDataFood(order.getOrderID());
            //Load Service
            LoadTableViewService();
            LoadTableDataService(order.getOrderID());
            //Load Tên sảnh cưới và chí tiết hoá đơn        
            OrderDetailsService orSer = new OrderDetailsService();
            OrderDetails orDe = orSer.getSCFromOrderDetails(order.getOrderID());
            this.soLuongBan.setText(String.valueOf(orDe.getSoBan()));
            this.ca.setText(orDe.getRentalPeriod());
            this.tongTien.setText(String.valueOf(orDe.getUnitPrice()));            
            this.sanhCuoiName.setText(orDe.getSanhcuoiName());
            //LocalDate local = java.sql.Date(orDe.getPartyDay().getTime()).toLocalDate();
            this.ngayToChuc.setValue(orDe.getPartyDay().toLocalDate());
            //paid = 1 => Toàn bộ
            if (order.getPaid() == 1){
                this.tienCanThanhToan.setText("0");
                this.tinhTrang.setText("ĐÃ THANH TOÁN");
                this.btThanhToan.setDisable(true);
                this.btThanhToan.setVisible(false);

            }
            else{
                Double t = orDe.getUnitPrice() * 0.9;
                this.tienCanThanhToan.setText(String.valueOf(t));
                this.tinhTrang.setText("CHƯA THANH TOÁN");
                if (btThanhToan.getText().equals("Thanh toán")){
                    String d1 = this.ngayToChuc.getValue().toString();
                    String d2 = LocalDate.now().toString();
                    if (Utils.CompareTwoDates(d1, d2) > 0){                        
                        this.btThanhToan.setDisable(false);
                        this.btThanhToan.setVisible(true);
                    }
                    else{
                        this.btThanhToan.setDisable(true);
                        this.btThanhToan.setVisible(false);
                    }
                }
                else{
                    String d1 = this.ngayToChuc.getValue().toString();
                    String d2 = LocalDate.now().toString();
                    if (Utils.CompareTwoDates(d1, d2) <= 0){                        
                        this.btThanhToan.setDisable(false);
                        this.btThanhToan.setVisible(true);
                    }
                    else{
                        this.btThanhToan.setDisable(true);
                        this.btThanhToan.setVisible(false);
                    }
                }
            }
        }
    }
    
    private void LoadTableViewFood(){
        TableColumn colID = new TableColumn("ID");
        colID.setCellValueFactory(new PropertyValueFactory("foodID"));
        colID.setPrefWidth(50);
        TableColumn colName = new TableColumn("Tên món ăn");
        colName.setCellValueFactory(new PropertyValueFactory("foodName"));
        colName.setPrefWidth(150);
        
        TableColumn colPrice = new TableColumn("Giá món ăn");
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPrice.setPrefWidth(100);
        
        TableColumn colNote = new TableColumn("Ghi chú");
        colNote.setCellValueFactory(new PropertyValueFactory("notes"));
        colNote.setPrefWidth(100);
        
        this.tbFood.getColumns().addAll(colID, colName, colPrice, colNote);
    }
    private void LoadTableDataFood(int id) throws SQLException{
        FoodService d = new FoodService();
        this.tbFood.setItems(FXCollections.observableArrayList(d.getFoodFromOrderDetails(id)));
    }
    
    private void LoadTableViewService(){        
        TableColumn colName = new TableColumn("Tên dịch vụ");
        colName.setCellValueFactory(new PropertyValueFactory("serviceName"));
        colName.setPrefWidth(150);
        
        TableColumn colPrice = new TableColumn("Giá dịch vụ");
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPrice.setPrefWidth(150);
        
        this.tbService.getColumns().addAll(colName, colPrice);
    }
    
    private void LoadTableDataService(int id) throws SQLException{
        DichVuServices dv = new DichVuServices();
        this.tbService.setItems(FXCollections.observableArrayList(dv.getServicesFromOrderDetails(id)));
    }
    
    public void BtrQuayLai(ActionEvent event) throws IOException{
        if (DangNhapController.cusID > 0){
            Parent root = FXMLLoader.load(getClass().getResource("ManHinhChon.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Parent root = FXMLLoader.load(getClass().getResource("ManHinhChonNV.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
       
    }
    public void BtrThanhToan(ActionEvent event) throws IOException, SQLException{
        Orders order = tbOrder.getSelectionModel().getSelectedItem();
        if (btThanhToan.getText().equals("Thanh toán")){            
            OrdersService orSer = new OrdersService();
            orSer.UpdatePaid(order.getOrderID());
            this.tinhTrang.setText("Thanh toán thành công");
            tbOrder.getColumns().clear();
            LoadTableData();
            LoadTableView();
            this.btThanhToan.setDisable(true);
            this.btThanhToan.setVisible(false);
        }
        else{
            OrderDetailsService orDetailsSer = new OrderDetailsService();
            orDetailsSer.DeleteOrderDetails(order.getOrderID());
            OrdersService orSer = new OrdersService();
            orSer.DeleteOrder(order.getOrderID());
            this.tinhTrang.setText("Huỷ Đơn hàng thành công");
            tbFood.getItems().clear();
            tbService.getItems().clear();
            tbOrder.getItems().remove(order);
            tbOrder.getColumns().clear();
            LoadTableData();
            LoadTableView();
            this.btThanhToan.setDisable(true);
            this.btThanhToan.setVisible(false);
        }
    }
    
    public void init(){
        if (DangNhapController.cusID == -1){
            this.btThanhToan.setText("Huỷ đơn hàng");
        }
        else this.btThanhToan.setText("Thanh toán");
        this.btThanhToan.setDisable(true);
        this.btThanhToan.setVisible(false);
    }
    
}
