package lk.ijse.salon.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.salon.dto.EmployeeDto;
import lk.ijse.salon.model.EmployeeModel;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class EmployeeManageFormController {
    public TextField txtEmployeeid;
    public TextField txtFname;
    public TextField txtLname;
    public TextField txtEmail;
    public TextField txtNic;
    public TextField txtMobile;
    public TextField txtRank;
    public TextField txtUsername;
    public TextField txtPassword;

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String eId = txtEmployeeid.getText();
        String fName = txtFname.getText();
        String lName = txtLname.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String nic = txtNic.getText();
        String rank  = txtRank.getText();
        String uName = txtUsername.getText();
        String password  = txtPassword.getText();

        boolean isEmployeeValidate = validateEmployee();
        if (!isEmployeeValidate) {
            return;
        }

        var dto = new EmployeeDto(eId,fName,lName,email,mobile,nic,rank,uName,password);
        var model = new EmployeeModel();

        try {
            boolean isSaved = model.saveEmployee(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added Succesfull").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String eId = txtEmployeeid.getText();
        String fName = txtFname.getText();
        String lName = txtLname.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String nic = txtNic.getText();
        String rank  = txtRank.getText();
        String uName = txtUsername.getText();
        String password  = txtPassword.getText();

        boolean isEmployeeValidate = validateEmployee();
        if (!isEmployeeValidate) {
            return;
        }

        var dto = new EmployeeDto(eId,fName,lName,email,mobile,nic,rank,uName,password);
        var model = new EmployeeModel();

        try {
            boolean isUpdated = model.updateEmployee(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Schedule Update Succesfull!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        String E_id = txtEmployeeid.getText();
        var model = new EmployeeModel();

        var EmployeeModel = new EmployeeModel();
        EmployeeDto dto = model.searchEmployee(E_id);
        if(dto != null) {
            boolean isDeleted = model.deleteEmployee(E_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Delete Succesfull!!!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Employee Not Found!!!").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtEmployeeid.setText("");
        txtFname.setText("");
        txtLname.setText("");
        txtEmail.setText("");
        txtMobile.setText("");
        txtNic.setText("");
        txtRank.setText("");
        txtUsername.setText("");
        txtPassword.setText("");

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String E_id = txtEmployeeid.getText();

        var model = new EmployeeModel();
        try {
            EmployeeDto dto = model.searchEmployee(E_id);

            if (dto != null){
                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.WARNING, "Employee not found");
            }
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(EmployeeDto dto) {
        txtEmployeeid.setText(dto.getEmp_id());
        txtFname.setText(dto.getFirst_name());
        txtLname.setText(dto.getLast_name());
        txtEmail.setText(dto.getEmail());
        txtMobile.setText(dto.getPhone_number());
        txtNic.setText(dto.getNic());
        txtRank.setText(dto.getJob_rank());
        txtUsername.setText(dto.getUsername());
        txtPassword.setText(dto.getPassword());
    }

    private boolean validateEmployee (){
        String E_id = txtEmployeeid.getText();
        boolean sidValidation = Pattern.compile("[E][0-9]{3,}").matcher(E_id).matches();
        if (!sidValidation) {
            txtEmployeeid.setStyle("-fx-border-color: red;");
            return false;

        }else {
            txtEmployeeid.setStyle("-fx-border-color: #08ff00;");
        }


        String fName = txtFname.getText();
        boolean fNameValidation = Pattern.compile("[A-Za-z .]{3,}").matcher(fName).matches();
        if (!fNameValidation) {
        txtFname.setStyle("-fx-border-color: red;");
        return false;

    }else {
        txtFname.setStyle("-fx-border-color: #08ff00;");
    }

        String lName = txtLname.getText();
        boolean lNameValidation = Pattern.compile("[A-Za-z .]{3,}").matcher(lName).matches();
        if (!lNameValidation) {
            txtLname.setStyle("-fx-border-color: red;");
            return false;

        }else {
            txtLname.setStyle("-fx-border-color: #08ff00;");
        }

        String emailText = txtEmail.getText();
        boolean isEmployeeEmailValidated = Pattern.matches("[A-Za-z0-9@.]{3,}", emailText);
        if (!isEmployeeEmailValidated) {
            txtEmail.setStyle("-fx-border-color: red;");
            return false;
        }else {
            txtEmail.setStyle("-fx-border-color: #08ff00;");
        }



        String mobileText = txtMobile.getText();
        boolean isEmployeeMobileValidated = Pattern.matches("[0-9]{10}", mobileText);
        if (!isEmployeeMobileValidated) {
//            new Alert(Alert.AlertType.ERROR, "Invalid Employee mobile").show();'
            txtMobile.setStyle("-fx-text-fill: red;");

            return false;
        }else {
            txtMobile.setStyle("-fx-text-fill: #56f648;");
        }


        String nicText = txtNic.getText();
        boolean isNicValidated = Pattern.matches("[0-9]{10}", nicText);
        if (!isNicValidated) {
            txtNic.setStyle("-fx-text-fill: red;");

            return false;
        }else {
            txtNic.setStyle("-fx-text-fill: #56f648;");
        }


        String rank = txtRank.getText();
        boolean isEmprankValidated = Pattern.matches("[A-Za-z]{3,}", rank);
        if (!isEmprankValidated) {
            txtRank.setStyle("-fx-text-fill: red;");

            return false;
        }else {
            txtRank.setStyle("-fx-text-fill: #56f648;");
        }

        String userName = txtUsername.getText();
        boolean isEmployeeIdValidated = Pattern.matches("[E][0-9]{3,}", userName);
        if (!isEmployeeIdValidated) {
            txtUsername.setStyle("-fx-text-fill: red;");

            return false;
        }else {
            txtUsername.setStyle("-fx-text-fill: #56f648;");
        }

        return true;
    }
}
