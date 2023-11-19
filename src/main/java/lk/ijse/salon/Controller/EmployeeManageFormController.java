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

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String E_id = txtEmployeeid.getText();
        var model = new EmployeeModel();

        try{
            var EmployeeModel = new EmployeeModel();
            EmployeeDto dto = model.searchEmployee(E_id);
            if(dto != null) {
                boolean isDeleted = model.deleteEmployee(E_id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Schedule Delete Succesfull!!!").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Schedule Not Found!!!").show();
            }
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    private boolean validateEmployee (){
        String E_id = txtEmployeeid.getText();
        boolean sidValidation = Pattern.compile("[S][0-9]{3,}").matcher(E_id).matches();
        if (!sidValidation) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee ID").show();
            txtEmployeeid.requestFocus();
            return false;
        }

        String fName = txtFname.getText();
        boolean fNameValidation = Pattern.compile("[A-Za-z .]{3,}").matcher(fName).matches();
        if (!fNameValidation) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Name").show();
            txtFname.requestFocus();
            return false;
        }

        String lName = txtLname.getText();
        boolean lNameValidation = Pattern.compile("[A-Za-z .]{3,}").matcher(lName).matches();
        if (!lNameValidation) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Name").show();
            txtLname.requestFocus();
            return false;
        }

        String emailText = txtEmail.getText();
        boolean isEmployeeEmailValidated = Pattern.matches("[A-Za-z0-9@.]{3,}", emailText);
        if (!isEmployeeEmailValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee email").show();
            return false;
        }

        String mobileText = txtMobile.getText();
        boolean isEmployeeMobileValidated = Pattern.matches("[0-9]{10}", mobileText);
        if (!isEmployeeMobileValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee mobile").show();
            return false;
        }

        String nicText = txtMobile.getText();
        boolean isNicValidated = Pattern.matches("[0-9]{10}", nicText);
        if (!isNicValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee nic").show();
            return false;
        }

        String rank = txtRank.getText();
        boolean isEmprankValidated = Pattern.matches("[A-Za-z]{3,}", rank);
        if (!isEmprankValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Rank").show();
            return false;
        }

        String userName = txtUsername.getText();
        boolean isEmployeeIdValidated = Pattern.matches("[E][0-9]{3,}", userName);
        if (!isEmployeeIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid user id").show();
            return false;
        }

        String password = txtPassword.getText();
        boolean isEmpPasswordValidated = Pattern.matches("[A-Za-z]{3,}", password);
        if (!isEmpPasswordValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee password").show();
            return false;
        }
        return true;
    }
}
