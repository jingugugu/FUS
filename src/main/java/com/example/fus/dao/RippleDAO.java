package com.example.fus.dao;

import com.example.fus.dto.BoardDTO;
import com.example.fus.dto.RippleDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class RippleDAO {

    //댓글 등록
    public int insertRipple(RippleDTO rippleDTO) throws Exception{
        String sql = "INSERT INTO ripple (`boardNum`, `memberId`, `name`, `content`,`insertDate`, `ip`)VALUES(?, ?, ?, ?, now(), ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, rippleDTO.getBoardNum());
        preparedStatement.setString(2, rippleDTO.getMemberId());
        preparedStatement.setString(3, rippleDTO.getName());
        preparedStatement.setString(4, rippleDTO.getContent());
        preparedStatement.setString(5, rippleDTO.getIp());


        return preparedStatement.executeUpdate();
    }

    //댓글 목록 출력
    public List<RippleDTO> selectRipples(int boardNum) throws Exception{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<RippleDTO> rippleDTOS = new ArrayList<>();

        String sql = "SELECT * FROM `ripple` WHERE `boardNum` = ? ";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, boardNum);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            RippleDTO rippleDTO = RippleDTO.builder()
                    .boardNum(resultSet.getInt("boardNum"))
                    .rippleId(resultSet.getInt("rippleId"))
                    .memberId(resultSet.getString("memberId"))
                    .name(resultSet.getString("name"))
                    .content(resultSet.getString("content"))
                    .insertDate(resultSet.getString("insertDate"))
                    .ip(resultSet.getString("ip")).build();
            rippleDTOS.add(rippleDTO);
        }
        return rippleDTOS;
    }

    //댓글 삭제 메소드
    public boolean deleteRipple(int rippleId) throws Exception{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "DELETE FROM `ripple` WHERE `rippleId` = ? ";

        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, rippleId);

        return preparedStatement.executeUpdate() == 1;
    }

    // 마이페이지 리플 5개 가져오기
    public List<RippleDTO> selectMemberRipples(String memberId) throws SQLException {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<RippleDTO> rippleDTOS = new ArrayList<>();

        String sql = "SELECT * FROM ripple WHERE memberId = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            RippleDTO rippleDTO = RippleDTO.builder()
                    .boardNum(resultSet.getInt("boardNum"))
                    .rippleId(resultSet.getInt("rippleId"))
                    .memberId(resultSet.getString("memberId"))
                    .name(resultSet.getString("name"))
                    .content(resultSet.getString("content"))
                    .insertDate(resultSet.getString("insertDate"))
                    .ip(resultSet.getString("ip")).build();
            rippleDTOS.add(rippleDTO);
        }
        return rippleDTOS;
    }

}