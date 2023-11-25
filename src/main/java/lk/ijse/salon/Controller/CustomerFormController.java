package lk.ijse.salon.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.CustomerDto;
import lk.ijse.salon.model.CustomerModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;

public class CustomerFormController {

        @FXML
        private TextField txtGenreportsCusId;





        @FXML
        void btnManageCustOnAction(ActionEvent event) {
                try {
                        URL resource = CustomerManageFormController.class.getResource("/View/CustomerManageForm.fxml");
                        Parent parent = FXMLLoader.load(resource);
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle("Mona Beauty Salon");
                       // stage.setAlwaysOnTop(true);
                        stage.setScene(scene);
                        stage.show();
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }

        @FXML
        void ReportsOnAction() throws SQLException, JRException {
                        InputStream resourceAsStream = getClass().getResourceAsStream("/Reports/customerReport.jrxml");
                        JasperDesign load = JRXmlLoader.load(resourceAsStream);
                        JRDesignQuery jrDesignQuery = new JRDesignQuery();
                        jrDesignQuery.setText("SELECT * FROM customers WHERE c_id = "+"\""+txtGenreportsCusId.getText()+"\"");
                        load.setQuery(jrDesignQuery);

                        JasperReport jasperReport = JasperCompileManager.compileReport(load);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getInstance().getConnection());
                        JasperViewer.viewReport(jasperPrint,false);
                }


        public void btnReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
                ReportsOnAction();
        }

        public void btnSearchCusdOnAction(ActionEvent actionEvent) {
                String id = txtGenreportsCusId.getText();

                var model = new CustomerModel();
                try {
                        CustomerDto dto = model.searchCustomer(id);

                        if(dto != null) {
                                fillFields(dto);
                        } else {
                                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
                        }
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
        }

        private void fillFields(CustomerDto dto) {
        }
}


