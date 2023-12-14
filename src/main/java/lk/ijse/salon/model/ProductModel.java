package lk.ijse.salon.model;

import javafx.collections.FXCollections;
import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.ProductDto;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductModel {
    public boolean saveProduct(ProductDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into product values (?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, dto.getProduct_id());
        statement.setString(2, dto.getGetDate());
        statement.setString(3, dto.getQty());
        statement.setString(4, dto.getPrice());
        statement.setString(5, dto.getType());
        statement.setString(6, dto.getDescription());
        statement.setString(7, dto.getProduct_name());

        return statement.executeUpdate() > 0;
    }

    public ProductDto searchProduct(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "Select * from product where product_id = ?";
        var pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        ProductDto dto = null;

        if (resultSet.next()){
            dto = new ProductDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return dto;
    }

    public boolean updateProduct(ProductDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "update product set getDate=?, qty=?,price=?,itemType = ?, description = ?, product_name = ? where product_id = ?";
        var pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getProduct_name());
        pstm.setString(2, dto.getPrice());
        pstm.setString(3, dto.getGetDate());
        pstm.setString(4, dto.getQty());
        pstm.setString(5, dto.getType());
        pstm.setString(6, dto.getDescription());
        pstm.setString(7, dto.getProduct_id());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteProduct(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "delete from product where product_id = ?";
        var pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    @SneakyThrows
    public List<ProductDto> getAllProduct() {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM product";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<ProductDto> dto = FXCollections.observableArrayList();
        while (resultSet.next()){
            dto.add(new ProductDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7))
            );
        }
        return dto;
    }
}
