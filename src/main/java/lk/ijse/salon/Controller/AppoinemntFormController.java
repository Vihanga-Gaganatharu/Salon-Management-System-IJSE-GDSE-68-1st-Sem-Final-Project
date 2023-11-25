package lk.ijse.salon.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.salon.dto.*;
import lk.ijse.salon.dto.tm.BookingTm;
import lk.ijse.salon.model.AppoinmentModel;
import lk.ijse.salon.model.CustomerModel;
import lk.ijse.salon.model.EmployeeModel;
import lk.ijse.salon.model.ServiceModel;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AppoinemntFormController implements Initializable {

    public ComboBox cmdCusid;
    public TextField txtCustomerName;
    public ComboBox cmbServiceId;
    public TextField txtServiceType;
    public TextField txtServicePrice;
    public TableColumn colcusId;
    public TableColumn colServieceId;
    public TableColumn colCustomerName;
    public TableColumn colServiceType;
    public TableColumn colServicePrice;
    public TableView tbl;
    public ComboBox cmbEmp;
    public TextField txtEmpName;

    ObservableList<BookingTm> list = FXCollections.observableArrayList();


    public void btnApooinmentOnAction(ActionEvent actionEvent) {
    }

    public void btnsaveAppoin(ActionEvent actionEvent) {
        BookingTm bookingTm = new BookingTm();
        bookingTm.setType(txtServiceType.getText());
        bookingTm.setName(txtCustomerName.getText());
        bookingTm.setPrice(txtServicePrice.getText());
        bookingTm.setCusId((String) cmdCusid.getValue());
        bookingTm.setSId((String) cmbServiceId.getValue());
        list.add(bookingTm);
        tbl.refresh();
    }

    public void bynBookingAppo(ActionEvent actionEvent) throws SQLException {
        AppoinmentDto  dto=new AppoinmentDto();
        dto.setB_id(nextId());
        dto.setTime(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        dto.setDate(new SimpleDateFormat("yyyy-M-dd").format(new Date()));
        dto.setEmp_id((String) cmbEmp.getValue());
        dto.setC_id((String) cmdCusid.getValue());

        try {
            boolean b = AppoinmentModel.plaseOrder(dto, list);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"yes hutto").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private String nextId() throws SQLException {
        return AppoinmentModel.getNext();
    }

    public void btnCancel(ActionEvent actionEvent) {

    }


    private void loadAllCustomer() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> customerDtos = CustomerModel.loadAllCustomer();
            for (CustomerDto dto : customerDtos) {
                obList.add(dto.getC_id());
            }
            cmdCusid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadAllService() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ServiceDto> serList = ServiceModel.loadAllService();

            for (ServiceDto serviceDto : serList) {
                obList.add(serviceDto.getService_id());
            }
            cmbServiceId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbCusIdOnAction(ActionEvent event) {
        String id = String.valueOf(cmdCusid.getValue());
        try {
            CustomerDto dto = CustomerModel.findCustomerById(id);
            txtCustomerName.setText(dto.getFirst_name());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbServiceIdOnAction(ActionEvent event) {
        String id = String.valueOf(cmbServiceId.getValue());
        try {
            ServiceDto dto = ServiceModel.findServesById(id);
            txtServicePrice.setText(dto.getPrice());
            txtServiceType.setText(dto.getService_type());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colcusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colServieceId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colServiceType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colServicePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tbl.setItems(list);

        loadAllCustomer();
        loadAllService();
        loadAllEmployee();
    }
    private void loadAllEmployee() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        EmployeeModel employeeModel = new EmployeeModel();
        try {
            List<EmployeeDto> list = employeeModel.getAllEmployees();
            for (EmployeeDto dto:list){
                obList.add(dto.getEmp_id());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cmbEmp.setItems(obList);
    }

    public void btnEmpIdOnAction(ActionEvent actionEvent) {
        EmployeeModel model=new EmployeeModel();
        try {
            EmployeeDto dto = model.searchEmployee((String) cmbEmp.getValue());
            txtEmpName.setText(dto.getFirst_name()+" "+dto.getLast_name());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
