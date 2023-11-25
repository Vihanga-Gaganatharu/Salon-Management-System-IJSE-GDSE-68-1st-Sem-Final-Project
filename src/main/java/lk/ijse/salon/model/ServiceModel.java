package lk.ijse.salon.model;

import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.ServiceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceModel {
    public static List<ServiceDto> loadAllService() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "select * from service";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<ServiceDto> serviceList = new ArrayList<>();

        while (resultSet.next()){
            serviceList.add(new ServiceDto(
               resultSet.getString(1),
               resultSet.getString(2),
               resultSet.getString(3),
               resultSet.getString(4),
               resultSet.getString(5)
            ));
        }
        return serviceList;
    }

    public static List<ServiceDto> search () throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "select * from service";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<ServiceDto> serviceList = new ArrayList<>();

        while (resultSet.next()){
            serviceList.add(new ServiceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return serviceList;
    }

    public static ServiceDto findServesById(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "select * from service where Service_Id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1,id);

        ResultSet resultSet = statement.executeQuery();

        ServiceDto serviceDto =null;
        if (resultSet.next()){
             serviceDto = new ServiceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return serviceDto;
    }

    public boolean updateService(ServiceDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = " UPDATE service SET Service_Id = ?, price = ?, service_type = ?, Description= ?,Service_Name = ? WHERE Service_Id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, dto.getService_id());
        statement.setString(1, dto.getService_name());
        statement.setString(1, dto.getPrice());
        statement.setString(1, dto.getDescription());
        statement.setString(1, dto.getService_type());

        boolean isSaved = statement.executeUpdate() > 0;
        return isSaved;

    }


    public boolean saveService(ServiceDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into service values (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, dto.getService_id());
        statement.setString(2, dto.getPrice());
        statement.setString(3, dto.getService_type());
        statement.setString(4, dto.getDescription());
        statement.setString(5, dto.getService_name());

        boolean isSaved = statement.executeUpdate() > 0;


        return isSaved;
    }
}
