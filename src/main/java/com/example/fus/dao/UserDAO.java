package com.example.fus.dao;


import com.example.fus.dto.UserDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class UserDAO {

    public UserDTO login(String memberId, String passwd) {
        UserDTO userDTO = null;
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

            String sql = "SELECT * FROM `user` WHERE `memberId` = ? AND `passwd` = ?";
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.setString(2, passwd);
            @Cleanup ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                userDTO = UserDTO.builder().
                        memberId(rs.getString("memberId"))
                        .passwd(rs.getString("passwd"))
                        .name(rs.getString("name"))
                        .birthDate(rs.getString("birthDate"))
                        .zipCode(rs.getString("zipCode"))
                        .address01(rs.getString("address01"))
                        .address02(rs.getString("address02"))
                        .phone(rs.getString("phone"))
                        .findQuestion(rs.getString("findQuestion"))
                        .findAnswer(rs.getString("findAnswer"))
                        .addDate(rs.getString("addDate"))
                        .build();
            } else {
                log.info("로그인 정보가 없습니다");
            }
            log.info("로그인 성공");
            log.info("로그인한 UserDTO" + userDTO);
            return userDTO;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDTO;
    }

}
