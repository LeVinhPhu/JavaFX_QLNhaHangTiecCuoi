/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlnhahangtieccuoi;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Categories;
import com.mycompany.pojo.Food;
import com.mycompany.services.CategoryService;
import com.mycompany.services.FoodService;
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
public class QLMonAnController implements Initializable {
    
    @FXML private ComboBox<Categories> cbCategories;
    @FXML private TableView<Food> tbFood;
    @FXML TextField txtFoodName;
    @FXML TextField txtFoodPrice;
    @FXML TextField txtNotes;
    @FXML TextField txtKeyword;
    @FXML Label lbMess;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableView();
        CategoryService cate = new CategoryService();
        try {
            this.cbCategories.setItems(FXCollections.observableList(cate.getCategories()));
            //this.cbCategories.setValue();
            LoadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(QLMonAnController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtKeyword.textProperty().addListener((evt)-> {
            try {
                this.LoadTableData(this.txtKeyword.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QLMonAnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void LoadTableView(){
        TableColumn colID = new TableColumn("ID");
        colID.setCellValueFactory(new PropertyValueFactory("foodID"));
        colID.setPrefWidth(50);
        TableColumn colName = new TableColumn("Tên món ăn");
        colName.setCellValueFactory(new PropertyValueFactory("foodName"));
        colName.setPrefWidth(250);
        
        TableColumn colPrice = new TableColumn("Giá món ăn");
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPrice.setPrefWidth(100);
        
        TableColumn colNote = new TableColumn("Ghi chú");
        colNote.setCellValueFactory(new PropertyValueFactory("notes"));
        colNote.setPrefWidth(300);
        
        this.tbFood.getColumns().addAll(colID, colName, colPrice, colNote);
    }
    
    private void LoadTableData(String kw) throws SQLException{
        FoodService d = new FoodService();
        this.tbFood.setItems(FXCollections.observableArrayList(d.getFood(kw)));
    }
    
    @FXML
    private void handleClickTableView(MouseEvent click) throws SQLException{
        Food food = tbFood.getSelectionModel().getSelectedItem();
        if (food != null){
            this.txtFoodName.setText(food.getFoodName());
            this.txtFoodPrice.setText(String.valueOf(food.getUnitPrice()));
            this.txtNotes.setText(food.getNotes());
            int cateID = food.getCategotyID();
            CategoryService cateService = new CategoryService();
            Categories cate = cateService.getCategory(food.getCategotyID());
            this.cbCategories.setValue(cate);
        }
    }
    @FXML
    private void BtrAddFood(ActionEvent event) throws SQLException{
        try{
            String foodName = this.txtFoodName.getText();
            String notes = this.txtNotes.getText();
            double price = Double.parseDouble(this.txtFoodPrice.getText());
            int cateID = getCategotyID();
            Food food = new Food(foodName, price, cateID, notes);
            FoodService f = new FoodService();
            boolean kt = f.KiemTaTonTai(foodName);
            if (kt){
                f.AddFood(food);
                LoadTableData(null);
                Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                init();
            }
            else
                lbMess.setText("Món ăn đã tồn tại");
        }catch(NullPointerException ex){
            lbMess.setText("Bạn phải điền đủ các cột dữ liệu");
        }catch (NumberFormatException ex2){
            lbMess.setText("Ô đơn giá phải nhập số");
        }
        
    }
    @FXML
    private void BtrUpdateFood(ActionEvent event) throws SQLException{
        Food f = this.tbFood.getSelectionModel().getSelectedItem();
        if (f != null){
            try{
                int foodID = f.getFoodID();
                String foodName = this.txtFoodName.getText();
                String notes = this.txtNotes.getText();
                double price = Double.parseDouble(this.txtFoodPrice.getText());
                int cateID = getCategotyID();
                FoodService foodSer = new FoodService();
                foodSer.UpdateFood(foodID, foodName, price, cateID, notes);
                LoadTableData(null);
                Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                init();
            }catch (NullPointerException ex){
                lbMess.setText("Bạn phải điền đủ các cột dữ liệu");
            }catch (NumberFormatException ex2){
                lbMess.setText("Ô đơn giá phải nhập số");
            }             
        }
        else
            lbMess.setText("Chưa chọn đối tượng để sửa");
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
            init();
        }
        else
            lbMess.setText("Chưa chọn đối tượng để xoá");
    }
    
    private int getCategotyID(){
        Categories cate = this.cbCategories.getSelectionModel().getSelectedItem();
        int cateID = cate.getCategoryID();
        return cateID;
    }
    private void init(){
        this.txtFoodName.setText(null);
        this.txtKeyword.setText(null);
        this.txtFoodPrice.setText("0");
        this.txtNotes.setText(null);
        this.lbMess.setText(null);
    }
    @FXML
    private void restrictNumbersOnly(KeyEvent keyEvent) {
        this.txtFoodPrice.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            txtFoodPrice.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }
}
