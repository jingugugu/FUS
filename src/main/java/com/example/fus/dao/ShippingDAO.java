package com.example.fus.dao;

import com.example.fus.dto.ShippingDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
