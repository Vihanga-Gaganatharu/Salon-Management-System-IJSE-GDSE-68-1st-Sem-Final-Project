package lk.ijse.salon.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class ServiceFormController {


        @FXML
        void btnManageServicesonAction(ActionEvent event) {
                try {
                        URL resource = ServiceFormController.class.getResource("/view/ServiceManageForm.fxml");
                        Parent parent = FXMLLoader.load(resource);
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle("Mona Beauty Salon");
                        //stage.setAlwaysOnTop(true);
                        stage.setScene(scene);
                        stage.show();
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }

}