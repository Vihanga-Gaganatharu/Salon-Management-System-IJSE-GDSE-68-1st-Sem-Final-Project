package lk.ijse.salon.model;

import javafx.scene.control.Alert;
import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {

    public boolean addEmployee(String Username , String Password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT username, password FROM employee WHERE username = ?";

        PreparedStatement pstm =connection.prepareStatement(sql);
        pstm.setString(1,Username);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            if (Username.equals(resultSet.getString(1))){
                if (Password.equals(resultSet.getString(2))){
                    return true;
                }
                else {
                    new Alert(Alert.AlertType.INFORMATION,"password is not correct").show();
                }
            }
            else {
                new Alert(Alert.AlertType.INFORMATION,"username is not correct").show();
            }
        }
        return false;
    }

    public boolean signupEmployee(EmployeeDto dto) throws SQLException {
        Connection  connection = DbConnection.getInstance().getConnection();

        String  sql = "insert into employee values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, dto.getEmp_id());
        statement.setString(2, dto.getFirst_name());
        statement.setString(2, dto.getLast_name());
        statement.setString(2, dto.getEmail());
        statement.setString(2, dto.getPhone_number());
        statement.setString(2, dto.getNic());
        statement.setString(2, dto.getJob_rank());
        statement.setString(2, dto.getUsername());
        statement.setString(2, dto.getPassword());

        return statement.executeUpdate() > 0;
    }
}

