package com.example.fus.dao;

import com.example.fus.dto.ProductDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProductDAO {

    //상품 등록
    public void insertProduct(ProductDTO product) throws Exception{
        String sql = "insert into product values (?,?,?,?,?,?,?,now())";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, product.getProductId());
        preparedStatement.setString(2, product.getProductName());
        preparedStatement.setString(3, product.getCategory());
        preparedStatement.setString(4, product.getDescription());
        preparedStatement.setInt(5, product.getPrice());
        preparedStatement.setInt(6, product.getUnitsInStock());
        preparedStatement.setString(7, product.getFileName());
        preparedStatement.executeUpdate();
    }

    //전체 출력
    public List<ProductDTO> selectAll() throws Exception{
        String sql = "select * from product order by `addDate` desc";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<ProductDTO> list = new ArrayList<>();
        while(resultSet.next()){
            ProductDTO product = ProductDTO.builder()
                    .productId(resultSet.getInt("productId"))
                    .productName(resultSet.getString("productName"))
                    .category(resultSet.getString("category"))
                    .description(resultSet.getString("description"))
                    .price(resultSet.getInt("price"))
                    .unitsInStock(resultSet.getInt("unitsInStock"))
                    .fileName(resultSet.getString("fileName"))
                    .addDate(String.valueOf(resultSet.getDate("addDate").toLocalDate()))
                    .build();
            list.add(product);
        }
        return list;
    }
    // 선택한 상품의 정보를 불러오는 메서드
    public ProductDTO selectProduct(String productId) throws SQLException{
        log.info("selectProduct ...");

        ProductDTO product = null;
        String sql = "select * from product where productId = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, productId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            product = ProductDTO.builder()
                    .productId(resultSet.getInt("productId"))
                    .productName(resultSet.getString("productName"))
                    .category(resultSet.getString("category"))
                    .description(resultSet.getString("description"))
                    .price(resultSet.getInt("price"))
                    .unitsInStock(resultSet.getInt("unitsInStock"))
                    .fileName(resultSet.getString("fileName"))
                    .addDate(String.valueOf(resultSet.getDate("addDate").toLocalDate()))
                    .build();
        }
        return product;
    }
}
