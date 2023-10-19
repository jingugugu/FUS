package com.example.fus.dao;

import com.example.fus.dto.ReviewDTO;
import com.example.fus.dto.ShippingDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//주문 정보 등록
@Log4j2
public class ShippingDAO {

    public void insertShipping(List<ShippingDTO> shippings) throws Exception{
        for(ShippingDTO shipping: shippings) {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            String sql = "insert into shipping values (?,?,?,?,?,?,?,?,?,now())";
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, shipping.getMemberId());
            preparedStatement.setInt(2, shipping.getProductId());
            preparedStatement.setString(3, shipping.getProductName());
            preparedStatement.setInt(4, shipping.getCount());
            preparedStatement.setString(5, shipping.getPhone());
            preparedStatement.setString(6, shipping.getReceiverName());
            preparedStatement.setString(7, shipping.getReceiverZipCode());
            preparedStatement.setString(8, shipping.getReceiverAddress01());
            preparedStatement.setString(9, shipping.getReceiverAddress02());
            preparedStatement.executeUpdate();
        }
    }

    // 마이페이지 최근 구매내역 5개 가져오기
    public List<ShippingDTO> selectMemberShippings(String memberId) throws SQLException {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<ShippingDTO> shippingDTOList = new ArrayList<>();

        String sql = "SELECT * FROM shipping WHERE memberId = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            ShippingDTO shippingDTO = new ShippingDTO();
            shippingDTO.setMemberId(resultSet.getString("memberId"));
            shippingDTO.setProductId(resultSet.getInt("productId"));
            shippingDTO.setProductName(resultSet.getString("productName"));
            shippingDTO.setCount(resultSet.getInt("count"));
            shippingDTO.setPhone(resultSet.getString("phone"));
            shippingDTO.setReceiverName(resultSet.getString("receiverName"));
            shippingDTO.setReceiverZipCode(resultSet.getString("receiverZipCode"));
            shippingDTO.setReceiverAddress01(resultSet.getString("receiverAddress01"));
            shippingDTO.setReceiverAddress02(resultSet.getString("receiverAddress01"));
            shippingDTO.setOrderDate(resultSet.getString("orderDate"));
            shippingDTOList.add(shippingDTO);
        }
        return shippingDTOList;
    }
}
