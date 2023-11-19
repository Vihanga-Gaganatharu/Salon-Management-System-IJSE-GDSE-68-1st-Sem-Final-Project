package lk.ijse.salon.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.salon.dto.AppoinmentDto;
import lk.ijse.salon.dto.tm.AppoinmentTm;
import lk.ijse.salon.model.AppoinmentModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class AppoinemntFormController {
        @FXML
        private TableView<AppoinmentTm> tblAppoinment;

        @FXML
        private TableColumn<?, ?> colAppid;

        @FXML
        private TableColumn<?, ?> colCusid;

        @FXML
        private TableColumn<?, ?> colDate;

        @FXML
        private TableColumn<?, ?> colEmpid;

        @FXML
        private TableColumn<?, ?> colService;

        @FXML
        private TableColumn<?, ?> colTime;


        public void initialize() throws SQLException {
                setCellValueFactory();
                loadAllShedules();
        }

        private void setCellValueFactory() {
                colAppid.setCellValueFactory(new PropertyValueFactory<>("b_id"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
                colEmpid.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
                colCusid.setCellValueFactory(new PropertyValueFactory<>("c_id"));
                colService.setCellValueFactory(new PropertyValueFactory<>("service"));
        }


        @FXML
        void btnApooinmentOnAction(ActionEvent event) {
                try {
                        URL resource = AppoinemntFormController.class.getResource("/View/EmployeeManageForm.fxml");
                        Parent parent = FXMLLoader.load(resource);
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle("Mona Beauty Salon");
                        stage.setAlwaysOnTop(true);
                        stage.setScene(scene);
                        stage.show();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private void loadAllShedules() throws SQLException {
                var model = new AppoinmentModel();

                ObservableList<AppoinmentTm> obList = FXCollections.observableArrayList();

                try{
                        List<AppoinmentDto> dtoList = model.loadAllShedules();

                        for(AppoinmentDto dto : dtoList){
                                obList.add(new AppoinmentTm(
                                        dto.getB_id(),
                                        dto.getDate(),
                                        dto.getTime(),
                                        dto.getEmp_id(),
                                        dto.getC_id(),
                                        dto.getService()
                                ));
                        }
                        tblAppoinment.setItems(obList);
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }

        }
}
