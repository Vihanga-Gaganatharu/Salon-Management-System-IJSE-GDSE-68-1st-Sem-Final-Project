package lk.ijse.salon.model;

import javafx.scene.control.Alert;
import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.EmployeeDto;
import lk.ijse.salon.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    public boolean SearchEmployee(String Username, String Password) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT username, password FROM employee WHERE username = ?",Username);
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

//    public boolean signupEmployee(EmployeeDto dto) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "insert into employee values (?,?,?,?,?,?,?,?,?)";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        statement.setString(1, dto.getEmp_id());
//        statement.setString(2, dto.getFirst_name());
//        statement.setString(3, dto.getLast_name());
//        statement.setString(4, dto.getEmail());
//        statement.setString(5, dto.getPhone_number());
//        statement.setString(6, dto.getNic());
//        statement.setString(7, dto.getJob_rank());
//        statement.setString(8, dto.getUsername());
//        statement.setString(9, dto.getPassword());
//
//        return statement.executeUpdate() > 0;
//    }

    public List<EmployeeDto> getAllEmployees() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
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
         return SQLUtil.execute("insert into employee values (?,?,?,?,?,?,?,?,?)",dto.getEmp_id(),dto.getFirst_name(),dto.getLast_name(),dto.getEmail(),dto.getPhone_number(),dto.getNic(),dto.getJob_rank(),dto.getUsername(),dto.getPassword());

    }

    public boolean updateEmployee(EmployeeDto dto) throws SQLException {

        return SQLUtil.execute("UPDATE employee SET first_name = ?, last_name = ?, email = ?, phone_number = ?, nic = ?, job_Rank = ?, username =?, password = ? WHERE emp_id = ?",dto.getFirst_name(),dto.getLast_name(),dto.getEmail(),dto.getPhone_number(),dto.getNic(),dto.getJob_rank(),dto.getUsername(),dto.getPassword(),dto.getEmp_id());
    }

    public EmployeeDto searchEmployee(String eId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("Select * from employee where emp_id = ?",eId);
        EmployeeDto dto= null;

        if (resultSet.next()){
            dto = new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }
        return dto;
    }

    public boolean deleteEmployee(String eId) throws SQLException {
        return SQLUtil.execute("delete from employee where emp_id = ?",eId);
    }


    public int getAllEmployee() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) from employee");
        int count = 0;
        while (resultSet.next()){
            count+=resultSet.getInt(1);
        }
        return count;


    }
}

