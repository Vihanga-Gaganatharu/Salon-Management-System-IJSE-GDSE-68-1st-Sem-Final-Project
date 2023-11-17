package lk.ijse.salon.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.salon.dto.EmployeeDto;
import lk.ijse.salon.model.EmployeeModel;

import java.io.IOException;
import java.sql.SQLException;

public class SignupFormController {
    @FXML
    private AnchorPane Signup;

    @FXML
    private PasswordField txtSignUpconfirmpassword;

    @FXML
    public static PasswordField txtSignUppassword;

    @FXML
    public static TextField txtSignupusername;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/LoginPageForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) Signup.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login form");
        stage.centerOnScreen();
    }

    public void btnNextOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Username ekata awaa");
        String text = txtSignupusername.getText();
        System.out.println("password ekata awaa");

        String password = txtSignUppassword.getText();
        String confirmPw = txtSignUpconfirmpassword.getText();

        if (!text.isEmpty() && !password.isEmpty() && !confirmPw.isEmpty()) {
            if (password.equals(confirmPw)) {
                Parent root = FXMLLoader.load(this.getClass().getResource("/View/SignupTwoForm.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) Signup.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Second Signup form");
                stage.centerOnScreen();
            }else{
                System.out.println("Password is not match");
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Please fill out all field");
        }


    }
}
