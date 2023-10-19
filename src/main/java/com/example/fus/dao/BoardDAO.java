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


    // 상품 전체 길이를 가져오는 메소드
    public String getBoardsSize() throws SQLException {
        String count = null;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "SELECT count(*) AS 'count' from board;";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            count = rs.getString("count");
        }
        return count;
    }

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
    public List<BoardDTO> selectAll(String pageNum) throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        int pageNumValue = Integer.parseInt(pageNum);
        int limit = 20;
        int start = (pageNumValue * 20) - 20; // 0 20 40 60

        String sql = "SELECT * FROM board order by addDate desc LIMIT "+start+", "+limit;
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardNum(resultSet.getInt("boardNum"));
            boardDTO.setMemberId(resultSet.getString("memberId"));
            boardDTO.setTitle(resultSet.getString("title"));
            boardDTO.setName(resultSet.getString("name"));
            boardDTO.setContent(resultSet.getString("content"));
            boardDTO.setCount(resultSet.getInt("count"));
            boardDTO.setFileName(resultSet.getString("fileName"));
            boardDTO.setAddDate(resultSet.getString("addDate"));
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
            boardDTO.setMemberId(resultSet.getString("memberId"));
            boardDTO.setTitle(resultSet.getString("title"));
            boardDTO.setName(resultSet.getString("name"));
            boardDTO.setContent(resultSet.getString("content"));
            boardDTO.setCount(resultSet.getInt("count"));
            boardDTO.setFileName(resultSet.getString("fileName"));
            boardDTO.setAddDate(resultSet.getString("addDate"));
        }

        return boardDTO;
    }

    /*게시글 삭제*/
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

    /*글쓰기에서 게시글 추가하는 메서드*/
    public int boardAdd(BoardDTO boardDTO) throws SQLException {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        String sql = "INSERT INTO board(memberId, title, name, content, fileName ) VALUES(?, ?, ?, ?, ?)";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, boardDTO.getMemberId());
        preparedStatement.setString(2, boardDTO.getTitle());
        preparedStatement.setString(3, boardDTO.getName());
        preparedStatement.setString(4, boardDTO.getContent());
        preparedStatement.setString(5, boardDTO.getFileName());

        return preparedStatement.executeUpdate();
    }

    /*선택된 글 내용 수정하는 메서드*/
    public void updateBoard(BoardDTO boardDTO) throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        if(boardDTO.getFileName() != null) {
            String sql = "UPDATE board SET content = ?, fileName = ? WHERE boardNum = ?";
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, boardDTO.getContent());
            preparedStatement.setString(2, boardDTO.getFileName());
            preparedStatement.setInt(3, boardDTO.getBoardNum());

            preparedStatement.executeUpdate();

        } else {
            String sql = "UPDATE board SET content = ? WHERE boardNum = ?";
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, boardDTO.getContent());
            preparedStatement.setInt(2, boardDTO.getBoardNum());
            preparedStatement.executeUpdate();
        }
    }


    // 마이페이지 글 5개 가져오기
    public List<BoardDTO> selectMemberBoards5(String memberId) throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        String sql = "SELECT * FROM board WHERE memberId = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberId);
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


    // 상품 조회수 올리는 메소드
    public Integer boardCountUp(int boardNum) throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "UPDATE board SET count = count + 1 WHERE boardNum = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, boardNum);

        return preparedStatement.executeUpdate();
    }


}
