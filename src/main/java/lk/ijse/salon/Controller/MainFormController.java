package lk.ijse.salon.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainFormController {

    @FXML
    private Pane bodypane;

    @FXML
    private AnchorPane dashboard;

    @FXML
    private Label txtDate;

    @FXML
    private Label txtTime;



    public void initialize(){
        bodypane.getChildren().clear();
        try {
            bodypane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        loadTime();
        loadData();

    }

    public String timeNow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
        //System.out.println(dateFormat.format(new Date()));
        return dateFormat.format(new Date()) ;
    }

    private void loadTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        txtDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {

            txtTime.setText(timeNow());
            // lblTime.setText(LocalDateTime.now().format(formatter));

        }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void loadData() {

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
