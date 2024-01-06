package lk.ijse.salon.model;

import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.ServiceDto;
import lk.ijse.salon.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceModel {
    public static List<ServiceDto> loadAllService() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from service");

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
        ResultSet resultSet = SQLUtil.execute( "select * from service");

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

        ResultSet resultSet = SQLUtil.execute("select * from service where Service_Id=?",id);

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

    public boolean deleteService(final String Service_ID) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE  FROM service WHERE Service_Id=?";
        PreparedStatement pstm= connection.prepareStatement(sql);
        pstm.setString(1,Service_ID);

        boolean isDeleted=pstm.executeUpdate()>0;
        return  isDeleted;
    }
}
