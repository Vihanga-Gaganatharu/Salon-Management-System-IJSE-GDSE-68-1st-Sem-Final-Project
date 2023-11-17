package lk.ijse.salon.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.salon.dto.EmployeeDto;
import lk.ijse.salon.model.EmployeeModel;

import java.sql.SQLException;

public class SignupTwoFormController {
    @FXML
    public TextField txtFirstName;

    @FXML
    public TextField txtEmployeeId;

    @FXML
    public TextField txtLastName;

    @FXML
    public TextField txtEmail;

    @FXML
    public TextField txtMobile;

    @FXML
    public TextField txtNic;

    @FXML
    public TextField txtRank;

    @FXML
    public void btnsignupOnAction(ActionEvent actionEvent) {
        String empId = txtEmployeeId.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String nic = txtNic.getText();
        String rank = txtRank.getText();
        String userName = SignupFormController.txtSignupusername.getText();
        String password = SignupFormController.txtSignUppassword.getText();

        if (empId.isEmpty() && firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() && mobile.isEmpty() && nic.isEmpty() && rank.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please fill out all fields").show();

        }

        var dto = new EmployeeDto(empId,firstName,lastName,email,mobile,nic,rank,userName,password);
        var model = new EmployeeModel();

        try {
            boolean isSaved = model.signupEmployee(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Register Succesfull").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    public void btnLoginOnAction(ActionEvent actionEvent) {
    }
}
