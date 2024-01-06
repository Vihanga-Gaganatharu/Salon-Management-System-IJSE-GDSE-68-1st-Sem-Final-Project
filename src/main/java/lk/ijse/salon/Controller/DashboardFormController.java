package lk.ijse.salon.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.salon.model.AppoinmentModel;
import lk.ijse.salon.model.CustomerModel;
import lk.ijse.salon.model.EmployeeModel;

import java.sql.SQLException;

public class DashboardFormController {
    public PieChart pieChart;
    @FXML
    private ImageView imgDashImage;

    @FXML
    private Label lblemployeecount;

    @FXML
    private Label labTotalAppoinemnts;

    @FXML
    private Label lblcustomercount;



    public void initialize(){

        try {
            int i = new EmployeeModel().getAllEmployee();
            labTotalAppoinemnts.setText(String.valueOf(i));
        } catch (SQLException e) {
            System.out.printf("", e.getMessage());

        }
        try {
            int i = new AppoinmentModel().getAllAppoinemts();
            lblemployeecount.setText(String.valueOf(i));
        } catch (SQLException e) {
            System.out.printf("", e.getMessage());

        }
        try {
            int i = new CustomerModel().getCustomerCount();
            lblcustomercount.setText(String.valueOf(i));
        } catch (SQLException e) {
            System.out.printf("", e.getMessage());

        }



        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList(
                new PieChart.Data("Nail Art", 10),
                new PieChart.Data("Colouring", 20),
                new PieChart.Data("Terapy", 30),
                new PieChart.Data("Haircut", 40),
                new PieChart.Data("Dressing", 50),
                new PieChart.Data("Makeup", 100)

        );
        pieChart.setData(observableList);
        new Thread(){
            @Override
            public void run() {
                try {
                    while (true) {
                        imgDashImage.setImage(new Image("/asstes/Leonardo_Diffusion_XL_salon_working_1.jpg"));
                        sleep(2000);
                        imgDashImage.setImage(new Image("/asstes/Leonardo_Diffusion_XL_salon_working_2.jpg"));
                        sleep(2000);
                        imgDashImage.setImage(new Image("/asstes/Leonardo_Diffusion_XL_salon_employees_2.jpg"));
                        sleep(2000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();
    }

}
