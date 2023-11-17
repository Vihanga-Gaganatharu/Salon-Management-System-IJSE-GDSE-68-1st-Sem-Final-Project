package lk.ijse.salon.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainFormController {

    @FXML
    private Pane bodypane;

    @FXML
    private AnchorPane dashboard;

    public void initialize(){
        bodypane.getChildren().clear();
        try {
            bodypane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void btnAppoinmentOnAction(ActionEvent event) {
        bodypane.getChildren().clear();
        try {
            bodypane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/AppoinemntForm.fxml")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        bodypane.getChildren().clear();
        try {
            bodypane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/CustomerForm.fxml")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        bodypane.getChildren().clear();
        try {
            bodypane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        bodypane.getChildren().clear();
        try {
            bodypane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/EmployeeForm.fxml")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void btnProductOnAction(ActionEvent event) {
        bodypane.getChildren().clear();
        try {
            bodypane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/ProductForm.fxml")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void btnReportOnAction(ActionEvent event) {
        bodypane.getChildren().clear();
        try {
            bodypane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/ReportsForm.fxml")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void btnServiceOnAction(ActionEvent event) {
        bodypane.getChildren().clear();
        try {
            bodypane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/ServiceForm.fxml")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void btnSignoutOnAction(ActionEvent event) {

        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/LoginPageForm.fxml"))));
            stage.show();

            Stage stage1 = (Stage) dashboard.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
