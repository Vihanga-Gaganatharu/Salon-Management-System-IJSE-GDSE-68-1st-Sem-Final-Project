package lk.ijse.salon.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.salon.dto.AppoinmentDto;
import lk.ijse.salon.dto.EmployeeDto;
import lk.ijse.salon.model.AppoinmentModel;
import lk.ijse.salon.model.EmployeeModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class AppoinemntManageFormController {

    @FXML
    private TextField txtAppoinemntId;

    @FXML
    private ComboBox<?> txtCustomerid;

    @FXML
    private DatePicker txtDate;

    @FXML
    private ComboBox<?> txtEmployeeid;
    @FXML
    private TextField txtService;

    @FXML
    private TextField txtTime;

    @FXML
    private Button txtsearch;

    public void initialize() {
        //loadCustomerIds();
        loadEmployeeIds();
    }

    private void loadEmployeeIds() {
        try {
            List<EmployeeDto> list = new EmployeeModel().getAllEmployees();
            ObservableList oblist = FXCollections.observableArrayList();
            for (EmployeeDto dto : list) {
                oblist.add(dto.getEmp_id());
            }
            txtEmployeeid.setItems(oblist);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    private void loadCustomerIds() {
//        try {
//            List<> list=new EmployeeModel().getAllEmployees();
//            ObservableList oblist= FXCollections.observableArrayList();
//            for(EmployeeDto dto : list){
//                oblist.add(dto.getEmp_id());
//            }
//            txtEmployeeid.setItems(oblist);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtAppoinemntId.setText("");
        txtTime.setText("");
        txtDate.setValue(LocalDate.parse(""));
        txtService.setText("");
    }

    @FXML
    void btnCustomerid(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmployeeid(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String A_id = txtAppoinemntId.getText();
        String time = txtTime.getText();
        String date = String.valueOf(txtDate.getValue());
        String service = txtService.getText();

        boolean isAppoinmentValidated = validateAppoinment();
        if (!isAppoinmentValidated) {
            return;
        }

        var dto = new AppoinmentDto();
        var model = new AppoinmentModel();

        try {
            boolean isSaved = model.saveAppoinment(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Apppoinment Added Succesfull").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    public boolean validateAppoinment() {
        String Aid = txtAppoinemntId.getText();
        boolean AidValidation = Pattern.compile("[A][0-9]{3,}").matcher(Aid).matches();
        if (!AidValidation) {
            new Alert(Alert.AlertType.ERROR, "Invalid Appinment ID").show();
            txtAppoinemntId.requestFocus();
            return false;
        }

        String timeText = txtTime.getText();
        boolean timeValidation = Pattern.compile("[0-9]{2}[:][0-9]{2}").matcher(timeText).matches();
        if (!timeValidation) {
            new Alert(Alert.AlertType.ERROR, "Invalid Time").show();
            txtTime.requestFocus();
            return false;
        }

        String dateText = txtDate.getValue().toString();
        boolean dateValidation = Pattern.compile("[0-9]{4}[-][0-9]{2}[-][0-9]{2}").matcher(dateText).matches();
        if (!dateValidation) {
            new Alert(Alert.AlertType.ERROR, "Invalid Date").show();
            txtDate.requestFocus();
            return false;
        }

        String serviceText = txtService.getText();
        boolean serviceValidation = Pattern.compile("[A-Za-z .]{3,}").matcher(serviceText).matches();
        if (serviceValidation) {
            new Alert(Alert.AlertType.ERROR, "Invalid Service").show();
            txtService.requestFocus();
            return false;
        }

        return true;
    }
}
