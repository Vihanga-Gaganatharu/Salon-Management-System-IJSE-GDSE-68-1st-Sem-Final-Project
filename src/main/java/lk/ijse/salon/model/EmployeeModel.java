package lk.ijse.salon.model;

import javafx.scene.control.Alert;
import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    public boolean addEmployee(String Username, String Password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT username, password FROM employee WHERE username = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Username);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            if (Username.equals(resultSet.getString(1))) {
                if (Password.equals(resultSet.getString(2))) {
                    return true;
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "password is not correct").show();
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "username is not correct").show();
            }
        }
        return false;
    }

    public boolean signupEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into employee values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, dto.getEmp_id());
        statement.setString(2, dto.getFirst_name());
        statement.setString(3, dto.getLast_name());
        statement.setString(4, dto.getEmail());
        statement.setString(5, dto.getPhone_number());
        statement.setString(6, dto.getNic());
        statement.setString(7, dto.getJob_rank());
        statement.setString(8, dto.getUsername());
        statement.setString(9, dto.getPassword());

        return statement.executeUpdate() > 0;
    }

    public List<EmployeeDto> getAllEmployees() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement("SELECT * FROM employee").executeQuery();
        List<EmployeeDto> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)

            ));
        }
        return list;
    }

    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into employee values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, dto.getEmp_id());
        statement.setString(2, dto.getFirst_name());
        statement.setString(3, dto.getLast_name());
        statement.setString(4, dto.getEmail());
        statement.setString(5, dto.getPhone_number());
        statement.setString(6, dto.getNic());
        statement.setString(7, dto.getJob_rank());
        statement.setString(8, dto.getUsername());
        statement.setString(9, dto.getPassword());

        boolean isSaved = statement.executeUpdate() > 0;
        return isSaved;
    }

    public List<EmployeeDto> loadAllEmployee() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<EmployeeDto> ScheduleList = new ArrayList<>();

        while (resultSet.next()) {
            ScheduleList.add(new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }
        return ScheduleList;
    }

    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE employee SET first_name = ?, last_name = ?, email = ?, phone_number = ?, nic = ?, job_Rank = ?, username =?, password = ? WHERE emp_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, dto.getFirst_name());
        statement.setString(2, dto.getLast_name());
        statement.setString(3, dto.getEmail());
        statement.setString(4, dto.getPhone_number());
        statement.setString(5, dto.getNic());
        statement.setString(6, dto.getJob_rank());
        statement.setString(7, dto.getUsername());
        statement.setString(8, dto.getPassword());
        statement.setString(9, dto.getEmp_id());

        return statement.executeUpdate() > 0;
    }
/*
    public EmployeeDto searchEmployee(String eId) {

    }

    public boolean deleteEmployee(String eId) {
    }*/
}

