package lk.ijse.salon.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.salon.dto.CustomerDto;
import lk.ijse.salon.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerManageFormController {

    @FXML
    private ComboBox<String> cmb_gender;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCus_name;

    @FXML
    private TextField txt_Address;

    @FXML
    private TextField txt_Nic;

    @FXML
    private TextField txt_Phone;

    @FXML
    void btnClearOn(ActionEvent event) {
        txtCusId.setText("");
        txtCus_name.setText("");
        txt_Nic.setText("");
        txt_Address.setText("");
        cmb_gender.setValue("");

    }

    @FXML
    void btnDeleteOn(ActionEvent event) {

    }

    @FXML
    void btnSaveOn(ActionEvent event) {
        String c_Id = txtCusId.getText();
        String c_Name = txtCus_name.getText();
        String c_Nic = txt_Nic.getText();
        String c_Address = txt_Address.getText();
        String c_Phone = txt_Phone.getText();
        String gender = (String) cmb_gender.getValue();

//        boolean isEmployeeValidate = validateEmployee();
//        if (!isEmployeeValidate) {
//            return;
//        }

        var dto = new CustomerDto(c_Id,c_Name,c_Nic,c_Address,c_Phone,gender);
        var model = new CustomerModel();

        try {
            boolean isSaved = model.(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added Succesfull").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSearchOn(ActionEvent event) {

    }

    @FXML
    void btnUpdateOn(ActionEvent event) {

    }

    public void initialize (URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("fe-male");
        list.add("male");
        cmb_gender.setItems(list);
    }

}
