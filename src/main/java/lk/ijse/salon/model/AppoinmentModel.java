package lk.ijse.salon.model;

import javafx.collections.ObservableList;
import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.AppoinmentDto;
import lk.ijse.salon.dto.tm.BookingTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentModel {
    public static boolean plaseOrder(AppoinmentDto dto, ObservableList<BookingTm> list) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (save(dto) && saveDetails(list, dto)) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    private static boolean saveDetails(ObservableList<BookingTm> list, AppoinmentDto dto) throws SQLException {
        for (BookingTm tm : list) {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO bookingdetails VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, tm.getSId());
            statement.setObject(2, dto.getB_id());

            if (!(statement.executeUpdate() > 0)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(AppoinmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO booking values (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, dto.getB_id());
        statement.setObject(2, dto.getDate());
        statement.setObject(3, dto.getTime());
        statement.setObject(4, dto.getEmp_id());
        statement.setObject(5, dto.getC_id());

        return statement.executeUpdate() > 0;
    }

    public static String getNext() throws SQLException {
        List<AppoinmentDto> appoinmentDtos = loadAllShedules();
        if (appoinmentDtos.isEmpty())return "B001";

        String id = null;
        for (AppoinmentDto dto : appoinmentDtos) {
            id = dto.getB_id();
        }
        int index = Integer.parseInt(id.split("B00")[1]);
        index++;
        return "B00" + index;
    }

    public static List<AppoinmentDto> loadAllShedules() throws SQLException {
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
                    resultSet.getString(5)));

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
        statement.setString(4, dto.getEmp_id());
        statement.setString(5, dto.getC_id());


        boolean isSaved = statement.executeUpdate() > 0;
        return isSaved;
    }

    public int getAllAppoinemts() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement("SELECT count(*) from booking").executeQuery();
        int count = 0;
        while (resultSet.next()){
            count+=resultSet.getInt(1);
        }
        return count;


    }
}
