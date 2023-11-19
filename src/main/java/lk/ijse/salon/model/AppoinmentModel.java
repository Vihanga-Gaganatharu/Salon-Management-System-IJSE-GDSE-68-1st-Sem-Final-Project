package lk.ijse.salon.model;

import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.AppoinmentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentModel {
    public List<AppoinmentDto> loadAllShedules() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM booking";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<AppoinmentDto> ScheduleList = new ArrayList<>();

        while (resultSet.next()) {
            ScheduleList.add(new AppoinmentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return ScheduleList;
    }

    public boolean saveAppoinment(AppoinmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into booking values (?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, dto.getB_id());
        statement.setString(2, dto.getTime());
        statement.setString(3, dto.getDate());
        statement.setString(4, dto.getService());
        statement.setString(5, dto.getEmp_id());
        statement.setString(6, dto.getC_id());


        boolean isSaved = statement.executeUpdate() > 0;
        return isSaved;
    }

}
