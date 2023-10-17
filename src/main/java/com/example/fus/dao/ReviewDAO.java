package com.example.fus.dao;

import com.example.fus.dto.ReviewDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//리뷰 저장
@Log4j2
public class ReviewDAO {

    public void insertReview(ReviewDTO reviewDTO) throws Exception{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "insert into review (productId, memberId, productName, rate, title, content, fileName, addDate) values(?,?,?,?,?,?,?,now())";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, reviewDTO.getProductId());
        preparedStatement.setString(2, reviewDTO.getMemberId());
        preparedStatement.setString(3, reviewDTO.getProductName());
        preparedStatement.setInt(4, reviewDTO.getRate());
        preparedStatement.setString(5, reviewDTO.getTitle());
        preparedStatement.setString(6, reviewDTO.getContent());
        preparedStatement.setString(7, reviewDTO.getFileName());
        preparedStatement.executeUpdate();
    }

    //상품에 따른 리뷰 불러오기
    public List<ReviewDTO> AllReview(int productId) throws SQLException{

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "select * from review where productId = ? order by addDate desc ";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, productId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<ReviewDTO> reviewList = new ArrayList<>();
        while(resultSet.next()){
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setProductName(resultSet.getString("productName"));
            reviewDTO.setMemberId(resultSet.getString("memberId"));
            reviewDTO.setRate(resultSet.getInt("rate"));
            reviewDTO.setTitle(resultSet.getString("title"));
            reviewDTO.setContent(resultSet.getString("content"));
            reviewDTO.setFileName(resultSet.getString("fileName"));
            reviewDTO.setAddDate(resultSet.getString("addDate"));
            reviewList.add(reviewDTO);
        }
        return reviewList;
    }
}
