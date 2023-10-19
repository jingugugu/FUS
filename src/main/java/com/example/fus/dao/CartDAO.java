package com.example.fus.dao;
import com.example.fus.dto.CartDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CartDAO {

    // 장바구니 목록
    public List<CartDTO> selectAll(String memberId) throws SQLException {
        String sql = "SELECT * FROM cart WHERE memberId = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        List<CartDTO> cartDTOList = new ArrayList<>();

        while(resultSet.next()){
            CartDTO cartDTO = CartDTO.builder()
                    .productId(resultSet.getInt("productId"))
                    .memberId(resultSet.getString("memberId"))
                    .productName(resultSet.getString("productName"))
                    .count(resultSet.getInt("count"))
                    .price(resultSet.getInt("price"))
                    .fileName(resultSet.getString("fileName"))
                    .addDate(resultSet.getString("addDate"))
                    .build();
            cartDTOList.add(cartDTO);
        }
        return cartDTOList;
    }

    // 장바구니 추가
    public void insertCart(CartDTO cartDTO) throws SQLException {

        String sql = "INSERT INTO cart (productId, memberId, productName, count, price, fileName, addDate) VALUES (?, ?, ?, ?, ?, ?, now())";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cartDTO.getProductId());
        preparedStatement.setString(2, cartDTO.getMemberId());
        preparedStatement.setString(3, cartDTO.getProductName());
        preparedStatement.setInt(4, cartDTO.getCount());
        preparedStatement.setInt(5, cartDTO.getPrice());
        preparedStatement.setString(6, cartDTO.getFileName());
        preparedStatement.executeUpdate();
    }

    // 전부 삭제
    public void allRemoveCart(String memberId) throws SQLException {
        String sql = "DELETE FROM cart WHERE memberId = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberId);
        preparedStatement.executeUpdate();
    }

    // 체크 삭제
    public void checkedRemoveCart(String memberId, String[] checkId) throws SQLException {
        for (String productId : checkId) {
            try {
                String sql = "DELETE FROM cart WHERE memberId = ? AND productId = ?";
                @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
                @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, memberId);
                preparedStatement.setString(2, productId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                log.info(e.getMessage());
                log.info("체크삭제 cartDao 에러");
            }
        }
    }

    // 개별 삭제
    public void oneRemoveCart(String memberId, String productId) throws SQLException {
        try {
            String sql = "DELETE FROM cart WHERE memberId = ? AND productId = ?";
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId);
            preparedStatement.setString(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.info(e.getMessage());
            log.info("개별삭제 cartDao 에러");
        }

    }

    public List<CartDTO> orderCart(String memberId, String[] checkId) throws SQLException {
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (String productId : checkId) {
            try {
                String sql = "SELECT * FROM cart WHERE memberId = ? AND productId = ?";
                @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
                @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, memberId);
                preparedStatement.setString(2, productId);
                @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    CartDTO cartDTO = CartDTO.builder()
                            .num(resultSet.getInt("num"))
                            .productId(resultSet.getInt("productId"))
                            .memberId(resultSet.getString("memberId"))
                            .productName(resultSet.getString("productName"))
                            .count(resultSet.getInt("count"))
                            .price(resultSet.getInt("price"))
                            .fileName(resultSet.getString("fileName"))
                            .addDate(resultSet.getString("addDate"))
                            .build();
                    cartDTOList.add(cartDTO);
                }
            } catch (Exception e) {
                log.info(e.getMessage());
                log.info("장바구니에서 상품 불러오기 에러");
            }
        }
        return cartDTOList;
    }


    // 장바구니 전체 주문
    public List<CartDTO> allOrderCart(String memberId) throws SQLException {
        List<CartDTO> cartDTOList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cart WHERE memberId = ?;";
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CartDTO cartDTO = CartDTO.builder()
                        .num(resultSet.getInt("num"))
                        .productId(resultSet.getInt("productId"))
                        .memberId(resultSet.getString("memberId"))
                        .productName(resultSet.getString("productName"))
                        .count(resultSet.getInt("count"))
                        .price(resultSet.getInt("price"))
                        .fileName(resultSet.getString("fileName"))
                        .addDate(resultSet.getString("addDate"))
                        .build();
                cartDTOList.add(cartDTO);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("장바구니에서 전체상품 불러오기 에러");
        }
        return cartDTOList;
    }







}
