package com.example.fus.service;

import com.example.fus.dao.CartDAO;
import com.example.fus.dto.CartDTO;
import com.example.fus.dto.ProductDTO;
import com.example.fus.dto.UserDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.List;

@Log4j2
public class CartService {
    private final CartDAO cartDAO;

    public CartService() {
        cartDAO = new CartDAO();
    }

    private String getFileName(Part part) {
        // Part 객체로 전달된 이미지 파일로 부터 파일 이름을 추출하기 위한 메서드
        String fileName = null;

        // 파일 이름이 들어있는 헤더 영역을 가지고 옴
        String header = part.getHeader("content-disposition");
        log.info("File Header : " + header);

        // 파일 이름이 들어있는 속성 부분의 시작위치를 가져와 쌍따옴표 사이의 값 부분만 가지고 옴
        int start = header.indexOf("filename="); // 해당되는 파일이름을 들고옴
        fileName = header.substring(start + 10, header.length() - 1); // filename= 뒤의 이름을 찾음
        log.info("파일명 : " + fileName);
        return fileName; // 리턴해주므로 fileName은 null값이 아닌 받아온 값을 가짐
    }

     //상품 목록 전체 가져오기
    public List<CartDTO> listCart(HttpServletRequest request) {
        List<CartDTO> cartDTOList = null;
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
        String memberId = userDTO.getMemberId();
        try{
            cartDTOList = cartDAO.selectAll(memberId);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("에러발생");
        }
        return cartDTOList;
    }

    public void insertCart(HttpServletRequest request, ProductDTO productDTO, String count) {
        CartDTO cartDTO = new CartDTO(); // 번호, 멤버 아이디, 상품이름, 수량, 가격, 파일네임, 등록날짜
        log.info("0");
        log.info(count);
        try {
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
            String memberId = userDTO.getMemberId();
            log.info("1");
            cartDTO.setProductId(productDTO.getProductId()); // 장바구니의 상품 번호
            cartDTO.setMemberId(memberId); // 장바구니의 멤버 아이디
            cartDTO.setProductName(productDTO.getProductName()); // 장바구니의 상품 이름
            cartDTO.setCount(Integer.parseInt(count)); // 장바구니의 상품 수량
            cartDTO.setPrice(productDTO.getPrice()); // 장바구니의 상품 가격
            cartDTO.setFileName(productDTO.getFileName()); // 장바구니의 파일 이름
            log.info("2");
            cartDAO.insertCart(cartDTO);
            log.info("3");
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("장바구니 담는과정에서 에러 발생");
        }
    }

    public void allRemoveCart(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
            String memberId = userDTO.getMemberId();
            cartDAO.allRemoveCart(memberId);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("장바구니 전체삭제 에러");
        }
    }
    public void checkedRemoveCart(HttpServletRequest request, String[] checkId) {
        try {
            log.info("service" + checkId);
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
            String memberId = userDTO.getMemberId();
            cartDAO.checkedRemoveCart(memberId, checkId);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("장바구니 체크 삭제 에러");
        }
    }

    // 주문완료 후 장바구니에서 완료 목록 삭제
    public void orderFinish(HttpServletRequest request, String[] productId) {
        try {
            log.info("service" + productId);
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
            String memberId = userDTO.getMemberId();
            cartDAO.orderFinish(memberId, productId);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("주문완료 삭제 에러");
        }
    }

    public void oneRemoveCart(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
            String memberId = userDTO.getMemberId();
            String productId = request.getParameter("productId");
            cartDAO.oneRemoveCart(memberId, productId);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("장바구니 체크 삭제 에러");
        }
    }

    public List<CartDTO> orderCart(HttpServletRequest request, String[] checkId) {
        List<CartDTO> cartDTOList = null;
        try {
            log.info("service" + checkId);
            for (String s : checkId) {
                log.info("service" + s);
            }
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
            String memberId = userDTO.getMemberId();
            cartDTOList = cartDAO.orderCart(memberId, checkId);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("장바구니 체크 불러오기 에러");
        }
        for (CartDTO cart : cartDTOList){
            log.info(cart);
        }
        return cartDTOList;
    }

    // 장바구니 전체 주문
    public List<CartDTO> allOrderCart(HttpServletRequest request) {
        List<CartDTO> cartDTOList = null;
        try {
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
            String memberId = userDTO.getMemberId();
            cartDTOList = cartDAO.allOrderCart(memberId);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("장바구니 전체불러오기 에러");
        }
        return cartDTOList;
    }
}
