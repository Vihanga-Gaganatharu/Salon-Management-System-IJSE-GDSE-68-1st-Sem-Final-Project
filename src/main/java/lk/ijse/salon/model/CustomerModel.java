package lk.ijse.salon.model;

import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.CustomerDto;
import lk.ijse.salon.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public static List<CustomerDto> loadAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "select * from customers";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<CustomerDto> cusList  = new ArrayList<>();

        while (resultSet.next()){
            cusList.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return cusList;
    }

    public static List<CustomerDto> search() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "select * from customers";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<CustomerDto> cusList  = new ArrayList<>();

        while (resultSet.next()){
            cusList.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return cusList;

    }

    public static CustomerDto findCustomerById(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "select * from customers where c_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1,id);

        ResultSet resultSet = statement.executeQuery();

        CustomerDto customerDto=null;
        if (resultSet.next()){
             customerDto = new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return customerDto;
    }

    public CustomerDto searchCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "Select * from customers where c_id = ?";
        var pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        CustomerDto dto= null;

        if (resultSet.next()){
            dto = new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return dto;
    }
}
