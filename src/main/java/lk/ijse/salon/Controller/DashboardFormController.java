package lk.ijse.salon.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DashboardFormController {
    @FXML
    private ImageView imgDashImage;

    public void initialize(){
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
