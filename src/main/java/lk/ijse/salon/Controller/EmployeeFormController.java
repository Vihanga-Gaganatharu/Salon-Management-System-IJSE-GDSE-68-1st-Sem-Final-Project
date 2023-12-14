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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.EmployeeDto;
import lk.ijse.salon.dto.tm.EmployeeTm;
import lk.ijse.salon.model.EmployeeModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {
        @FXML
        private TableView<EmployeeTm> tblEmployee;
        @FXML
        private TableColumn<?, ?> colEmail;

        @FXML
        private TableColumn<?, ?> colEmpid;

        @FXML
        private TableColumn<?, ?> colFname;

        @FXML
        private TableColumn<?, ?> colLname;

        @FXML
        private TableColumn<?, ?> colMobile;

        @FXML
        private TableColumn<?, ?> colNic;

        @FXML
        private TableColumn<?, ?> colRank;
        @FXML
        private TextField txtEmpIdSerarch;

        public void initialize() throws SQLException {
                loadAllEmployee();
                setCellValueFactory();
        }

        private void setCellValueFactory() {
                colEmpid.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
                colFname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
                colLname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
                colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
                colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
                colRank.setCellValueFactory(new PropertyValueFactory<>("job_rank"));
        }

        @FXML
        void btnManageEmpOnAction(ActionEvent event) {
                try {
                        URL resource = EmployeeManageFormController.class.getResource("/View/EmployeeManageForm.fxml");
                        Parent parent = FXMLLoader.load(resource);
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle("Employee Manage Form");
                        stage.setAlwaysOnTop(true);
                        stage.setScene(scene);
                        stage.show();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        void ReportbtnOnActhion() throws SQLException, JRException {
                InputStream resourceAsStream = getClass().getResourceAsStream("/Reports/saloonManagment.jrxml");
                JasperDesign load = JRXmlLoader.load(resourceAsStream);
                JRDesignQuery jrDesignQuery = new JRDesignQuery();
                jrDesignQuery.setText("SELECT * FROM employee WHERE emp_id = '" +txtEmpIdSerarch.getText()+"'");
                load.setQuery(jrDesignQuery);

                JasperReport jasperReport = JasperCompileManager.compileReport(load);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getInstance().getConnection());
                JasperViewer.viewReport(jasperPrint,false);
        }

        @FXML
        void btnReportsEmpOnAction(ActionEvent event) {
                try {
                        ReportbtnOnActhion();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                } catch (JRException e) {
                        throw new RuntimeException(e);
                }

        }

        private void loadAllEmployee() throws SQLException {
                var model = new EmployeeModel();

                ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

                try{
                        List<EmployeeDto> dtoList = model.getAllEmployees();

                        for(EmployeeDto dto : dtoList){
                                obList.add(new EmployeeTm(
                                        dto.getEmp_id(),
                                        dto.getFirst_name(),
                                        dto.getLast_name(),
                                        dto.getEmail(),
                                        dto.getPhone_number(),
                                        dto.getNic(),
                                        dto.getJob_rank(),
                                        dto.getUsername(),
                                        dto.getPassword()
                                ));
                        }
                        tblEmployee.setItems(obList);
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }

        }


        public void btnAttendanceOnaction(ActionEvent actionEvent) {
        }

        public void BtnSalaryOnaction(ActionEvent actionEvent) {
        }
}
