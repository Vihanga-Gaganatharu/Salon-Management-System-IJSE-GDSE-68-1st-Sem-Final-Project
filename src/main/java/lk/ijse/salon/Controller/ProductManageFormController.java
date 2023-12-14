package lk.ijse.salon.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.salon.dto.ProductDto;
import lk.ijse.salon.model.ProductModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProductManageFormController implements Initializable {
    public TextField txtProductid;
    public TextField txtProductname;
    public TextField txtQty;
    public TextField txtDescription;
    public TextField txtRank;
    public TextField txtPrice;
    public ComboBox<String > cmbType;
    public DatePicker txtDate;

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtProductid.getText();
        String name = txtProductname.getText();
        String qty = txtQty.getText();
        String description = txtDescription.getText();
        String price = txtPrice.getText();
        String type = cmbType.getValue();
        String date = String.valueOf(txtDate.getValue());

        var dto = new ProductDto(id,date,qty,price,type,description,name);
        var model = new ProductModel();

        try {
            boolean isSaved = model.saveProduct(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added Succesfull").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


        public void btnUpdateOnAction (ActionEvent actionEvent){
            String id = txtProductid.getText();
            String name = txtProductname.getText();
            String qty = txtQty.getText();
            String description = txtDescription.getText();
            String price = txtPrice.getText();
            String type = cmbType.toString();
            String date = String.valueOf(txtDate.getValue());

            var dto = new ProductDto(id, date, qty,price,type,description,name );
            var model = new ProductModel();

            try {
                boolean isUpdated = model.updateProduct(dto);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Product Update Succesfull!!!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }

        public void btnDeleteOnAction (ActionEvent actionEvent){
            String id = txtProductid.getText();

            var model = new ProductModel();

            try {
                var productModel = new ProductModel();
                ProductDto dto = model.searchProduct(id);
                if (dto != null) {
                    boolean isDeleted = productModel.deleteProduct(id);
                    if (isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Product delete succesfull").show();
                        btnClearOnAction(actionEvent);
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Product not found").show();
                    btnClearOnAction(actionEvent);
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        public void btnClearOnAction (ActionEvent actionEvent){
            txtProductid.setText("");
            txtProductname.setText("");
            txtQty.setText("");
            txtDescription.setText("");
            txtPrice.setText("");
        }

        public void btnSearchOnAction (ActionEvent actionEvent){
            String id = txtProductid.getText();

            var model = new ProductModel();
            try {
                ProductDto dto = model.searchProduct(id);

                if (dto != null) {
                    fillFields(dto);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Product not found");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        private void fillFields (ProductDto dto){
            txtProductid.setText(dto.getProduct_id());
            txtProductname.setText(dto.getProduct_name());
            txtQty.setText(dto.getQty());
            txtDescription.setText(dto.getDescription());
            txtPrice.setText(dto.getPrice());
            cmbType.setValue(dto.getType());
            txtDate.setValue(LocalDate.parse(dto.getGetDate()));
        }


        @Override
        public void initialize (URL location, ResourceBundle resources){
            ObservableList<String> list = FXCollections.observableArrayList();
            list.add("Rent Item");
            list.add("Buy Item");
            cmbType.setItems(list);
        }
    }
