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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
        initFood();
        initSanhCuoi();
        LoadTableView();
        CategoryService cate = new CategoryService();
        try {
            this.cbCategories.setItems(FXCollections.observableList(cate.getCategories()));
            this.cbCategories.setValue(cate.getCategory(1));
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
    
    public void BtrQuayLai(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ManHinhChonNV.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //Dịch vụ
    @FXML
    private void BtrAddService(ActionEvent event) throws SQLException{
        try{
            String serviceName = Utils.removeWhitespace(txtServiceName.getText());
            double unitprice = Double.parseDouble(txtServicePrice.getText());
            Services s = new Services(serviceName, unitprice);
            DichVuServices dv = new DichVuServices();
            if (unitprice == 0.0){
                lbMessService.setText("Đơn giá phải lớn hơn không");
            }
            else if (serviceName == null || serviceName.equals("")){
                lbMessService.setText("Tên không đc để trống");
            }
            else if (dv.kiemTraTonTai(serviceName)){
                dv.AddService(s);
                LoadTableData(null);
                Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                initService();
            }
            else
                lbMessService.setText("dịch vụ đã tồn tại");
        }catch (NumberFormatException ex2){
            lbMessService.setText("Ô đơn giá phải nhập số");
        }
    }
    @FXML
    private void BtrUpdateService(ActionEvent event) throws SQLException{
        Services service = tbService.getSelectionModel().getSelectedItem();
        if (service != null){
            try{
            int serID = service.getServiceID();
            String serviceName = Utils.removeWhitespace(txtServiceName.getText());
            double unitprice = Double.parseDouble(txtServicePrice.getText());
            if (unitprice == 0.0){
                lbMessService.setText("Đơn giá phải lớn hơn không");
            }
            else if (serviceName == null || serviceName.equals("")){
                lbMessService.setText("Tên không đc để trống");
            }
            else{
                DichVuServices dv = new DichVuServices();
                dv.UpdateService(serID, serviceName, unitprice);
                LoadTableData(null);      
                Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                initService();          
            }
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
        this.lbMessService.setText("");
        this.txtKeywordService.setText("");
        this.txtServiceName.setText("");
        this.txtServicePrice.setText("0");
    }
    
    //Món ăn
    @FXML
    private void BtrAddFood(ActionEvent event) throws SQLException{
        try{
            String foodName = Utils.removeWhitespace(this.txtFoodName.getText());
            String notes = Utils.removeWhitespace(this.txtNotesFood.getText());
            double price = Double.parseDouble(this.txtFoodPrice.getText());
            
            if (price == 0.0){
                lbMessFood.setText("Đơn giá phải lớn hơn không");
            }
            else if (foodName == null || foodName.equals("")){
                lbMessFood.setText("Tên không đc để trống");
            }
            else{
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
                    lbMessFood.setText("Món ăn đã tồn tại");
            }
        }catch (NumberFormatException ex2){
            lbMessFood.setText("Ô đơn giá phải nhập số");
        }
        
    }
    @FXML
    private void BtrUpdateFood(ActionEvent event) throws SQLException{
        Food f = this.tbFood.getSelectionModel().getSelectedItem();
        if (f != null){
            try{
                int foodID = f.getFoodID();
                String foodName = Utils.removeWhitespace(this.txtFoodName.getText());
                String notes = Utils.removeWhitespace(this.txtNotesFood.getText());
                double price = Double.parseDouble(this.txtFoodPrice.getText());
                
                if (price == 0.0){
                lbMessFood.setText("Đơn giá phải lớn hơn không");
                }
                else if (foodName == null || foodName.equals("")){
                    lbMessFood.setText("Tên không đc để trống");
                }
                else{
                    int cateID = getCategotyID();
                    FoodService foodSer = new FoodService();
                    foodSer.UpdateFood(foodID, foodName, price, cateID, notes);
                    LoadTableData(null);
                    Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                    initFood();
                }
            }catch (NumberFormatException ex2){
                lbMessFood.setText("Ô đơn giá phải nhập số");
            }             
        }
        else
            lbMessFood.setText("Chưa chọn đối tượng để sửa");
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
            lbMessFood.setText("Chưa chọn đối tượng để xoá");
    }
    
    private int getCategotyID(){
        Categories cate = this.cbCategories.getSelectionModel().getSelectedItem();
        int cateID = cate.getCategoryID();
        return cateID;
    }
    private void initFood(){
        this.txtFoodName.setText("");
        this.txtKeywordFood.setText("");
        this.txtFoodPrice.setText("0");
        this.txtNotesFood.setText("");
        this.lbMessSC.setText("");
    }
    
    //Sảnh cưới
    @FXML
    private void BtrAddSanhCuoi(ActionEvent event) throws SQLException{
        try{
            String scName = Utils.removeWhitespace(this.txtSCName.getText());
            double unitprice = Double.parseDouble(this.txtSCPrice.getText());
            int sbtd = Integer.parseInt(this.txtSoBanToiDa.getText());
            String notes = Utils.removeWhitespace(this.txtNotesSanhCuoi.getText());
            if (unitprice == 0.0){
                lbMessSC.setText("Đơn giá phải lớn hơn không");
            }
            else if (scName == null || scName.equals("")){
                lbMessSC.setText("Tên không đc để trống");
            }
            else if (sbtd == 0){
                lbMessSC.setText("số lượng bàn phải lớn hơn không");
            }
            else{
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
            }
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
            String scName = Utils.removeWhitespace(this.txtSCName.getText());            
            int sbtd = Integer.parseInt(this.txtSoBanToiDa.getText()); 
            double unitprice = Double.parseDouble(this.txtSCPrice.getText());
            String notes = Utils.removeWhitespace(this.txtNotesSanhCuoi.getText());
            if (unitprice == 0.0){
                lbMessSC.setText("Đơn giá phải lớn hơn không");
            }
            else if (scName == null || scName.equals("")){
                lbMessSC.setText("Tên không đc để trống");
            }
            else if (sbtd == 0){
                lbMessSC.setText("số lượng bàn phải lớn hơn không");
            }
            else{
                SanhCuoiService scService = new SanhCuoiService();
                scService.UpdateSanhCuoi(scID, scName, sbtd, unitprice, notes);
                LoadTableData(null);      
                Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                initSanhCuoi();
            }
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
        this.lbMessSC.setText("");
        this.txtKeywordSanhCuoi.setText("");
        this.txtSoBanToiDa.setText("0");
        this.txtSCPrice.setText("0");
        this.txtNotesSanhCuoi.setText("");
        this.txtSCName.setText("");
    }
    
}

