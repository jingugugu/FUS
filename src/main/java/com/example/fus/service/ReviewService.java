package com.example.fus.service;

import com.example.fus.dao.ReviewDAO;
import com.example.fus.dto.ReviewDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.List;

@Log4j2
public class ReviewService {
    private final ReviewDAO reviewDAO;

    public ReviewService() {reviewDAO = new ReviewDAO();}

    //리뷰 등록
    public String getFileName(Part part) {
        String fileName = null;

        String header = part.getHeader("content-disposition");
        log.info("File Header : " + header);

        int start = header.indexOf("filename=");
        fileName = header.substring(start + 10, header.length()-1);
        log.info("파일명 : " + fileName);
        return fileName;
    }
    public void addReview(HttpServletRequest request) {
        /*상품을 등록하기 위한 요청을 처리하는 메서드*/
        ReviewDTO reviewDTO = new ReviewDTO();
        try{
            Part part = request.getPart("file");
            String fileName = this.getFileName(part);
            if (fileName != null && !fileName.isEmpty()) {
                part.write(fileName);
            }
            BeanUtils.populate(reviewDTO, request.getParameterMap());
            reviewDTO.setFileName(fileName);
            reviewDAO.insertReview(reviewDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("Review Insert error!");
            request.setAttribute("error", "리뷰가 정상적으로 등록되지 않았습니다.");
        }
    }
    //상품에 대한 리뷰를 보혀주기위한 요청을 하는 메서드
    public List<ReviewDTO> listReview(int productId) {
        List<ReviewDTO> reviewList = null;
        try{
            reviewList = reviewDAO.AllReview(productId);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("ReviewService listReview fail!@!");
        }
        return reviewList;
    }
}
