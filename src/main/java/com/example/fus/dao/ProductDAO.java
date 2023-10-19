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

    // 상품 전체 길이를 가져오는 메소드
    public String getProductSize() throws SQLException {
        String count = null;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "SELECT count(*) AS 'count' from product;";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            count = rs.getString("count");
        }
        return count;
    }

    // 카테고리별 상품 전체 길이를 가져오는 메소드
    public String getCategoryProductSize(String category) throws SQLException {
        String count = null;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "SELECT count(*) AS 'count' from product WHERE category = ?;";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, category);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            count = rs.getString("count");
        }
        return count;
    }

    // 검색결과별 상품 전체 길이를 가져오는 메소드
    public String getSearchProductsSize(String word) throws SQLException {
        String count = null;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "SELECT count(*) AS 'count' from product WHERE productName LIKE '%"+word+"%' OR category LIKE '%"+word+"%'";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, word);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            count = rs.getString("count");
        }
        return count;
    }

    //상품 등록
    public void insertProduct(ProductDTO product) throws Exception{

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "insert into product (`productName`, `category`, `description`, `price`, `unitsInStock`, `fileName`, `addDate`, `reviewCount`, `orderCount`) values (?,?,?,?,?,?,now(),?,?)";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setString(2, product.getCategory());
        preparedStatement.setString(3, product.getDescription());
        preparedStatement.setInt(4, product.getPrice());
        preparedStatement.setInt(5, product.getUnitsInStock());
        preparedStatement.setString(6, product.getFileName());
        preparedStatement.setInt(7, 0);
        preparedStatement.setInt(8, 0);
        log.info(preparedStatement.executeUpdate());
    }

    // 전체 출력 ( 페이징 )
    public List<ProductDTO> selectAll(String pageNum, String orderby) throws Exception{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<ProductDTO> list = new ArrayList<>();
        log.info("orderby =============" + orderby);
        int pageNumValue = Integer.parseInt(pageNum);
        int limit = 8;
        int start = (pageNumValue * 8) - 8; // 0 8 16 24

        String sql = "select * from product order by " + orderby + " desc LIMIT "+start+", "+limit;
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        log.info("sql~ ================" + sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

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
                    .reviewCount(resultSet.getInt("reviewCount"))
                    .orderCount(resultSet.getInt("orderCount"))
                    .build();
            list.add(product);
        }
        return list;
    }

    // 카테고리별 전체 출력 ( 페이징 )
    public List<ProductDTO> getCategoryProducts(String pageNum, String category, String orderby) throws Exception{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<ProductDTO> list = new ArrayList<>();
        int pageNumValue = Integer.parseInt(pageNum);
//        int limit = category.equals("ALL") ? 8 : 12; // ajax로 하려던거
        int limit = 4;
        int start = (pageNumValue * limit) - limit; // 0 12 24 36 48
        String sql = null;
        if(category.equals("ALL")){
            sql = "select * from product order by " + orderby + " desc  LIMIT "+start+", "+limit;
        } else {
            sql = "select * from product WHERE category = ? order by " + orderby + " desc  LIMIT " + start + ", " + limit;
        }
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, category);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

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
                    .reviewCount(resultSet.getInt("reviewCount"))
                    .orderCount(resultSet.getInt("orderCount"))
                    .build();
            list.add(product);
        }
        return list;
    }

    // 검색결과별 전체 출력 ( 페이징 )
    public List<ProductDTO> getSearchProducts(String pageNum, String word) throws Exception{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<ProductDTO> list = new ArrayList<>();
        int pageNumValue = Integer.parseInt(pageNum);
        int limit = 12;
        int start = (pageNumValue * limit) - limit; // 0 12 24 36 48
        String sql = "select * from product WHERE productName LIKE '%"+word+"%' OR category LIKE '%"+word+"%' order by `addDate` desc  LIMIT " + start + ", " + limit;
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, word);
        preparedStatement.setString(2, word);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

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
                    .reviewCount(resultSet.getInt("reviewCount"))
                    .orderCount(resultSet.getInt("orderCount"))
                    .build();
            list.add(product);
        }
        return list;
    }

    // 선택한 상품의 정보를 불러오는 메서드
    public ProductDTO selectProduct(int productId) throws SQLException{
        log.info("selectProduct ...");

        ProductDTO product = null;
        String sql = "select * from product where productId = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, productId);
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
                    .reviewCount(resultSet.getInt("reviewCount"))
                    .orderCount(resultSet.getInt("orderCount"))
                    .build();
        }
        return product;
    }

    //상품 삭제하는 메서드
    public void removeProduct(int productId) throws Exception{
        String sql = "delete from product where productId=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, productId);

        preparedStatement.executeUpdate();
    }

    //상품 편집하는 메서드
    public void modifyProduct(ProductDTO productDTO) throws Exception{
        String sql = "update product set productName=?, category=?, description=?, price=?, unitsInStock=?, fileName=? where productId=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, productDTO.getProductName());
        preparedStatement.setString(2, productDTO.getCategory());
        preparedStatement.setString(3, productDTO.getDescription());
        preparedStatement.setInt(4, productDTO.getPrice());
        preparedStatement.setInt(5, productDTO.getUnitsInStock());
        preparedStatement.setString(6, productDTO.getFileName());
        preparedStatement.setInt(7, productDTO.getProductId());

        preparedStatement.executeUpdate();;
    }

    // 상품 신상품 기준 8개 출력 ( 메인화면 )
    public List<ProductDTO> selectAllLimit8() throws Exception{
        String sql = "select * from product order by `addDate` desc limit 8";
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
                    .reviewCount(resultSet.getInt("reviewCount"))
                    .orderCount(resultSet.getInt("orderCount"))
                    .build();
            list.add(product);
        }
        return list;
    }
    // 리뷰 등록 시 리뷰 수 올리는 메소드
    public Integer reviewCountUp(int productId) throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "UPDATE product SET reviewCount = reviewCount + 1 WHERE productId = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, productId);

        return preparedStatement.executeUpdate();
    }
}
