package lk.ijse.salon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launcher extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception{
        Parent parent = FXMLLoader.load(this.getClass().getResource("/View/LoginPageForm.fxml"));
        Scene scene = new Scene(parent);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Mona Beauty Salon");
        stage.getIcons().add(new Image("D:\\Salon_mona_Mange\\src\\main\\resources\\asstes\\Rectangle 1396.png"));
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}