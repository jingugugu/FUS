package com.example.fus.dao;

import com.example.fus.dto.BoardDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class BoardDAO {


    /*게시물 추가하는 메서드*/
    public void insertBoard(BoardDTO boardDTO) throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "INSERT INTO board(title, name, content, fileName, addDate) VALUES (?, ?, ?, ?, now())";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, boardDTO.getTitle());
        preparedStatement.setString(2, boardDTO.getName());
        preparedStatement.setString(3, boardDTO.getContent());
        preparedStatement.setString(4, boardDTO.getFileName());
        preparedStatement.executeUpdate();

    }

    /*게시판 게시글 목록 전체를 가져오는 메소드*/
    public List<BoardDTO> selectAll() throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        String sql = "SELECT * FROM board";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardNum(resultSet.getInt("boardNum"));
            boardDTO.setContent(resultSet.getString("content"));
            boardDTO.setTitle(resultSet.getString("title"));
            boardDTO.setName(resultSet.getString("name"));
            boardDTO.setAddDate(resultSet.getString("addDate"));
            boardDTO.setFileName(resultSet.getString("fileName"));
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }

    /* 게시물을 선택했을 때 세부 내용을 보여주는 메서드*/
    public BoardDTO selectOne(int boardNum) throws SQLException{
        log.info("selectOne(int title)...");

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        BoardDTO boardDTO = null;

        String sql = "SELECT * FROM board WHERE boardNum = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, boardNum);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            boardDTO = new BoardDTO();
            boardDTO.setBoardNum(resultSet.getInt("boardNum"));
            boardDTO.setTitle(resultSet.getString("title"));
            boardDTO.setName(resultSet.getString("name"));
            boardDTO.setContent(resultSet.getString("content"));
            boardDTO.setFileName(resultSet.getString("fileName"));
            boardDTO.setAddDate(resultSet.getString("addDate"));
        }

        return boardDTO;
    }

    /*뉴스 삭제*/
    public BoardDTO deleteBoard(int boardNum) throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        String sql = "DELETE FROM board WHERE boardNUM = ? ";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, boardNum);
        if(preparedStatement.executeUpdate() == 0){
            throw  new SQLException("DB error");
        }
        return null;
    }






}
