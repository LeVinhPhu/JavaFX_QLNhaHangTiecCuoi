/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Categories;
import com.mycompany.pojo.Food;
import com.mycompany.pojo.SanhCuoi;
import com.mycompany.pojo.Services;
import com.mycompany.services.CategoryService;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.FoodService;
import com.mycompany.services.SanhCuoiService;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
public class QuanLyController implements Initializable {
    //QL Dịch Vụ
    @FXML TableView<Services> tbService;
    @FXML TextField txtKeywordService;
    @FXML TextField txtServiceName;
    @FXML TextField txtServicePrice;
    @FXML Label lbMessService;
    //QL món ăn
    @FXML private ComboBox<Categories> cbCategories;
    @FXML private TableView<Food> tbFood;
    @FXML TextField txtFoodName;
    @FXML TextField txtFoodPrice;
    @FXML TextField txtNotesFood;
    @FXML TextField txtKeywordFood;
    @FXML Label lbMessFood;
    //QL Sanh Cuoi
    @FXML TableView<SanhCuoi> tbSanhCuoi;
    @FXML TextField txtKeywordSanhCuoi;
    @FXML TextField txtSCName;
    @FXML TextField txtSCPrice;
    @FXML TextField txtSoBanToiDa;
    @FXML TextField txtNotesSanhCuoi;
    @FXML Label lbMessSC;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
        public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initService();
        LoadTableView();
        CategoryService cate = new CategoryService();
        try {
            this.cbCategories.setItems(FXCollections.observableList(cate.getCategories()));
            LoadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(QLDichVuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //dịch vụ
        this.txtKeywordService.textProperty().addListener((evt)-> {
            try {
                this.LoadTableData(this.txtKeywordService.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QLDichVuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //món ăn
        this.txtKeywordFood.textProperty().addListener((evt)-> {
            try {
                this.LoadTableData(this.txtKeywordFood.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QLDichVuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //sảnh cưới
        this.txtKeywordSanhCuoi.textProperty().addListener((evt)-> {
            try {
                this.LoadTableData(this.txtKeywordSanhCuoi.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QLDichVuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    //Hàm dùng chung
    private void LoadTableView(){      
        //QL Dịch vụ
        TableColumn colNameService = new TableColumn("Tên dịch vụ");
        colNameService.setCellValueFactory(new PropertyValueFactory("serviceName"));
        colNameService.setPrefWidth(350);
        
        TableColumn colPriceService = new TableColumn("Giá dịch vụ");
        colPriceService.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPriceService.setPrefWidth(150);
        
        this.tbService.getColumns().addAll(colNameService, colPriceService);
        
        //QL món ăn
        TableColumn colNameFood = new TableColumn("Tên món ăn");
        colNameFood.setCellValueFactory(new PropertyValueFactory("foodName"));
        colNameFood.setPrefWidth(250);
        
        TableColumn colPriceFood = new TableColumn("Giá món ăn");
        colPriceFood.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPriceFood.setPrefWidth(100);
        
        TableColumn colNoteFood = new TableColumn("Ghi chú");
        colNoteFood.setCellValueFactory(new PropertyValueFactory("notes"));
        colNoteFood.setPrefWidth(300);
        
        this.tbFood.getColumns().addAll(colNameFood, colPriceFood, colNoteFood);
        
        //QL Sảnh cưới
        TableColumn colNameSC = new TableColumn("Tên Sảnh cưới");
        colNameSC.setCellValueFactory(new PropertyValueFactory("sanhCuoiName"));
        colNameSC.setPrefWidth(200);
        
        TableColumn colNum = new TableColumn("Số bàn tối đa");
        colNum.setCellValueFactory(new PropertyValueFactory("soBanToiDa"));
        colNum.setPrefWidth(100);
        
        TableColumn colPriceSC = new TableColumn("Giá sảnh cưới");
        colPriceSC.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPriceSC.setPrefWidth(100);
        
        TableColumn colNoteSC = new TableColumn("Ghi chú");
        colNoteSC.setCellValueFactory(new PropertyValueFactory("notes"));
        colNoteSC.setPrefWidth(300);
        
        this.tbSanhCuoi.getColumns().addAll(colNameSC, colNum, colPriceSC, colNoteSC);
    }
    
    private void LoadTableData(String kw) throws SQLException{
        //Dịch vụ
        DichVuServices dv = new DichVuServices();
        this.tbService.setItems(FXCollections.observableArrayList(dv.getServices(kw)));
        //Món ăn
        FoodService food = new FoodService();
        this.tbFood.setItems(FXCollections.observableArrayList(food.getFood(kw)));
        //Sảnh cưới
        SanhCuoiService sc = new SanhCuoiService();
        this.tbSanhCuoi.setItems(FXCollections.observableArrayList(sc.getSanhCuoiList(kw)));
    }       
    @FXML
    private void handleClickTableView(MouseEvent click) throws SQLException{
        //Dịch vụ
        Services service = tbService.getSelectionModel().getSelectedItem();
        if (service != null){
            txtServiceName.setText(service.getServiceName());
            txtServicePrice.setText(String.valueOf(String.format("%.0f", service.getUnitPrice())));
        }
        //Món ăn
        Food food = tbFood.getSelectionModel().getSelectedItem();
        if (food != null){
            this.txtFoodName.setText(food.getFoodName());
            this.txtFoodPrice.setText(String.valueOf(String.format("%.0f", food.getUnitPrice())));
            this.txtNotesFood.setText(food.getNotes());
            int cateID = food.getCategotyID();
            CategoryService cateService = new CategoryService();
            Categories cate = cateService.getCategory(food.getCategotyID());
            this.cbCategories.setValue(cate);
        }
        //Sảnh cưới
        SanhCuoi sc = this.tbSanhCuoi.getSelectionModel().getSelectedItem();
        if (sc != null){
            this.txtSCName.setText(sc.getSanhCuoiName());
            this.txtSCPrice.setText(String.valueOf(String.format("%.0f", sc.getUnitPrice())));
            this.txtSoBanToiDa.setText(String.valueOf(sc.getSoBanToiDa()));
            this.txtNotesSanhCuoi.setText(sc.getNotes());
        }
    }
    @FXML
    private void restrictNumbersOnly(KeyEvent keyEvent) {
        this.txtServicePrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtServicePrice.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        this.txtFoodPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtFoodPrice.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
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
    
    //Dịch vụ
    @FXML
    private void BtrAddService(ActionEvent event) throws SQLException{
        try{
            String serviceName = txtServiceName.getText();
            double unitprice = Double.parseDouble(txtServicePrice.getText());
            Services s = new Services(serviceName, unitprice);
            DichVuServices dv = new DichVuServices();
            if (dv.kiemTraTonTai(serviceName)){
                dv.AddService(s);
                LoadTableData(null);
                Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                initService();
            }
            else
                lbMessService.setText("Món ăn đã tồn tại");
        }catch (NumberFormatException ex2){
            lbMessService.setText("Ô đơn giá phải nhập số");
        }catch(SQLIntegrityConstraintViolationException ex3){
            lbMessService.setText("Bạn phải điền đủ các ô dữ liệu");
        }
    }
    @FXML
    private void BtrUpdateService(ActionEvent event) throws SQLException{
        Services service = tbService.getSelectionModel().getSelectedItem();
        if (service != null){
            try{
            int serID = service.getServiceID();
            String serviceName = txtServiceName.getText();
            double unitprice = Double.parseDouble(txtServicePrice.getText());
            DichVuServices dv = new DichVuServices();
            dv.UpdateService(serID, serviceName, unitprice);
            LoadTableData(null);      
            Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
            initService();           
            }catch (NumberFormatException ex2){
                lbMessService.setText("Ô đơn giá phải nhập số");
            } 
        }
        else
            lbMessService.setText("Chưa chọn đối tượng để sửa");
    }
    @FXML
    private void BtrDeleteService(ActionEvent event) throws SQLException{
        Services service = tbService.getSelectionModel().getSelectedItem();
        if (service != null){
            int serID = service.getServiceID();
            DichVuServices dv = new DichVuServices();
            dv.DeleteService(serID);
            LoadTableData(null);
            Utils.getBox("Xoá thành công", Alert.AlertType.INFORMATION).show();
            initService();
        }
        else
            lbMessService.setText("Chưa chọn đối tượng để xoá");
    }
    
    private void initService(){
        this.lbMessService.setText(null);
        this.txtKeywordService.setText(null);
        this.txtServiceName.setText(null);
        this.txtServicePrice.setText("0");
    }
    
    //Món ăn
    @FXML
    private void BtrAddFood(ActionEvent event) throws SQLException{
        try{
            String foodName = this.txtFoodName.getText();
            String notes = this.txtNotesFood.getText();
            double price = Double.parseDouble(this.txtFoodPrice.getText());
            int cateID = getCategotyID();
            Food food = new Food(foodName, price, cateID, notes);
            FoodService f = new FoodService();
            boolean kt = f.KiemTaTonTai(foodName);
            if (kt){
                f.AddFood(food);
                LoadTableData(null);
                Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                initFood();
            }
            else
                lbMessSC.setText("Món ăn đã tồn tại");
        }catch(NullPointerException ex){
            lbMessSC.setText("Bạn phải điền đủ các cột dữ liệu");
        }catch (NumberFormatException ex2){
            lbMessSC.setText("Ô đơn giá phải nhập số");
        }
        
    }
    @FXML
    private void BtrUpdateFood(ActionEvent event) throws SQLException{
        Food f = this.tbFood.getSelectionModel().getSelectedItem();
        if (f != null){
            try{
                int foodID = f.getFoodID();
                String foodName = this.txtFoodName.getText();
                String notes = this.txtNotesFood.getText();
                double price = Double.parseDouble(this.txtFoodPrice.getText());
                int cateID = getCategotyID();
                FoodService foodSer = new FoodService();
                foodSer.UpdateFood(foodID, foodName, price, cateID, notes);
                LoadTableData(null);
                Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                initFood();
            }catch (NullPointerException ex){
                lbMessSC.setText("Bạn phải điền đủ các cột dữ liệu");
            }catch (NumberFormatException ex2){
                lbMessSC.setText("Ô đơn giá phải nhập số");
            }             
        }
        else
            lbMessSC.setText("Chưa chọn đối tượng để sửa");
    }
    @FXML
    private void BtrDeleteFood(ActionEvent event) throws SQLException{
        Food f = this.tbFood.getSelectionModel().getSelectedItem();
        if (f != null){
            int foodID = f.getFoodID();
            FoodService fdSer = new FoodService();
            fdSer.DeleteFood(foodID);
            LoadTableData(null);
            Utils.getBox("Xoá thành công", Alert.AlertType.INFORMATION).show();
            initFood();
        }
        else
            lbMessSC.setText("Chưa chọn đối tượng để xoá");
    }
    
    private int getCategotyID(){
        Categories cate = this.cbCategories.getSelectionModel().getSelectedItem();
        int cateID = cate.getCategoryID();
        return cateID;
    }
    private void initFood(){
        this.txtFoodName.setText(null);
        this.txtKeywordFood.setText(null);
        this.txtFoodPrice.setText("0");
        this.txtNotesFood.setText(null);
        this.lbMessSC.setText(null);
    }
    
    //Sảnh cưới
    @FXML
    private void BtrAddSanhCuoi(ActionEvent event) throws SQLException{
        try{
            String scName = this.txtSCName.getText();
            double unitprice = Double.parseDouble(this.txtSCPrice.getText());
            int sbtd = Integer.parseInt(this.txtSoBanToiDa.getText());
            String notes = this.txtNotesSanhCuoi.getText();
            
            SanhCuoi s = new SanhCuoi(scName, sbtd, unitprice, notes);
            SanhCuoiService sc = new SanhCuoiService();
            if (sc.kiemTraTonTai(scName)){
                sc.AddSanhCuoi(s);
                LoadTableData(null);
                Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                initSanhCuoi();
            }
            else
                lbMessSC.setText("Sảnh cưới đã tồn tại");
        }catch(NullPointerException ex){
            lbMessSC.setText("Bạn phải điền đủ các cột dữ liệu");
        }catch (NumberFormatException ex2){
                lbMessSC.setText("Bạn phải điền đủ các cột dữ liệu");
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
            String notes = this.txtNotesSanhCuoi.getText();
            SanhCuoiService scService = new SanhCuoiService();
            scService.UpdateSanhCuoi(scID, serviceName, sbtd, unitprice, notes);
            LoadTableData(null);      
            Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
            initSanhCuoi();
            }catch (NullPointerException ex){
                lbMessSC.setText("Bạn phải điền đủ các cột dữ liệu");
            }catch (NumberFormatException ex2){
                lbMessSC.setText("Bạn phải điền đủ các cột dữ liệu");
            } 
        }
        else
            lbMessSC.setText("Chưa chọn đối tượng để sửa");
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
            initSanhCuoi();
        }
        else
            lbMessSC.setText("Chưa chọn đối tượng để xoá");
    }
    
    private void initSanhCuoi(){
        this.lbMessSC.setText(null);
        this.txtKeywordSanhCuoi.setText("");
        this.txtSoBanToiDa.setText("0");
        this.txtSCPrice.setText("0");
        this.txtNotesSanhCuoi.setText("");
        this.txtSCName.setText("");
    }
}
