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

    // 로그인
    public UserDTO login(String memberId, String passwd) {
        UserDTO userDTO = null;
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

            String sql = "SELECT * FROM `user` WHERE `memberId` = ? AND `passwd` = ?";
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.setString(2, passwd);
            @Cleanup ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
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

    // 회원가입
    public Integer register(UserDTO userDTO) {
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

            String sql = "INSERT INTO `user` (`memberId`, `passwd`, `name`, `birthDate`, `zipCode`, `address01`," +
                    " `address02`, `phone`, `findQuestion`, `findAnswer`, `addDate`) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userDTO.getMemberId());
            pstmt.setString(2, userDTO.getPasswd());
            pstmt.setString(3, userDTO.getName());
            pstmt.setString(4, userDTO.getBirthDate());
            pstmt.setString(5, userDTO.getZipCode());
            pstmt.setString(6, userDTO.getAddress01());
            pstmt.setString(7, userDTO.getAddress02());
            pstmt.setString(8, userDTO.getPhone());
            pstmt.setString(9, userDTO.getFindQuestion());
            pstmt.setString(10, userDTO.getFindAnswer());
            log.info("UserDAO : register() 성공");
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("UserDAO : register() 실패");
        }
        return 0;
    }

    // 회원탈퇴
    public Integer removeUser(String memberId) {
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

            String sql = "DELETE FROM `user` WHERE `memberId` = ?";
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            if (pstmt.executeUpdate() == 1) {
                log.info("회원 탈퇴를 성공적으로 처리하였습니다");
                return 1;
            } else {
                log.info("회원 탈퇴를 실패했습니다");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 회원 정보 수정
    public Integer editUser(UserDTO userDTO) {
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

            String sql = "UPDATE `user` SET `passwd` = ?, `name` = ?, `zipCode` = ?, `address01` = ?, `address02` = ?, " +
                    "`phone` = ?, `findQuestion` = ?, `findAnswer` = ? WHERE `memberId` = ? ";
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userDTO.getPasswd());
            pstmt.setString(2, userDTO.getName());
            pstmt.setString(3, userDTO.getZipCode());
            pstmt.setString(4, userDTO.getAddress01());
            pstmt.setString(5, userDTO.getAddress02());
            pstmt.setString(6, userDTO.getPhone());
            pstmt.setString(7, userDTO.getFindQuestion());
            pstmt.setString(8, userDTO.getFindAnswer());
            pstmt.setString(9, userDTO.getMemberId());
            log.info("UserDAO : editUser 성공적으로 수정");
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("UserDAO : editUser 중 에러 발생");
        }
        return 0;
    }

    // 비밀번호 찾기
    public Integer findPassword(String memberId, String findQuestion, String findAnswer) {
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

            String sql = "SELECT `passwd` FROM `user` WHERE `memberId` = ? AND " +
                    "`findQuestion` = ? AND `findAnswer` = ?";
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.setString(2, findQuestion);
            pstmt.setString(3, findAnswer);
            @Cleanup ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return 2;
            } else {
                log.info("UserDAO : findPassword, 해당하는 유저는 없음");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("UserDAO : findPassword 중 에러 발생");
        }
        return 0;
    }

    // 비밀번호 재설정
    public Integer resetPassword(String memberId, String passwd) {
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

            String sql = "UPDATE `user` SET `passwd` = ? WHERE `memberId` = ? ";
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, passwd);
            pstmt.setString(2, memberId);
            log.info("UserDAO : resetPassword 성공");
            return pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            log.error("UserDAO : resetPassword 중 에러 발생");
        }
        return 0;
    }

}
