package com.example.fus.service;

import com.example.fus.dao.ProductDAO;
import com.example.fus.dto.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.List;

@Log4j2
public class ProductService {

    private final ProductDAO productDAO;

    public ProductService() { productDAO = new ProductDAO();}

    // 파일 이름 가져오기
    public String getFileName(Part part) {
        String fileName = null;

        String header = part.getHeader("content-disposition");
        log.info("File Header : " + header);

        int start = header.indexOf("filename=");
        fileName = header.substring(start + 10, header.length()-1);
        log.info("파일명 : " + fileName);
        return fileName;
    }

    // 상품 리스트 총 개수 구하는 메소드
    public String sizeProductList() {
        String count = null;
        try {
            count = productDAO.getProductSize();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ProductService : sizeBoardList() 에러");
        }
        return count;
    }

    // 카테고리 별 상품 리스트 총 개수 구하는 메소드
    public String sizeCategoryProductList(String category) {
        String count = null;
        try {
            count = productDAO.getCategoryProductSize(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ProductService : sizeCategoryProductList() 에러");
        }
        return count;
    }

    // 검색결과 별 상품 리스트 총 개수 구하는 메소드
    public String sizeSearchProductList(String word) {
        String count = null;
        try {
            count = productDAO.getSearchProductsSize(word);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ProductService : sizeCategoryProductList() 에러");
        }
        return count;
    }

    // 상품 하나 등록하기
    public void addProduct(HttpServletRequest request) {
        /*상품을 등록하기 위한 요청을 처리하는 메서드*/
        ProductDTO productDTO = new ProductDTO();
        try{
            Part part = request.getPart("file");
            String fileName = this.getFileName(part);
            if (fileName != null && !fileName.isEmpty()) {
                part.write(fileName);
            }
            BeanUtils.populate(productDTO, request.getParameterMap());
            productDTO.setFileName(fileName);
            productDAO.insertProduct(productDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("Product Insert error!");
            request.setAttribute("error", "상품이 정상적으로 등록되지 않았습니다.");
        }
    }
    
    // 상품 목록 전체 가져오기
    public List<ProductDTO> listProduct(String pageNum) {
        /*ProductList.jsp에서 상품 목록을 보여주기 위한 요청을 처리하는 메서드 */
        List<ProductDTO> productDTOList = null;
        try{
            productDTOList = productDAO.selectAll(pageNum);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("Product List error");
        }
        return productDTOList;
    }

    // 카테고리별 상품 목록 전체 가져오기
    public List<ProductDTO> categoryProducts(String pageNum, String category) {
        /*ProductList.jsp에서 상품 목록을 보여주기 위한 요청을 처리하는 메서드 */
        List<ProductDTO> productDTOList = null;
        try{
            productDTOList = productDAO.getCategoryProducts(pageNum, category);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("Product List error");
        }
        return productDTOList;
    }

    // 검색결과별 상품 목록 전체 가져오기
    public List<ProductDTO> searchProducts(String pageNum, String word) {
        /*ProductList.jsp에서 상품 목록을 보여주기 위한 요청을 처리하는 메서드 */
        List<ProductDTO> productDTOList = null;
        try{
            productDTOList = productDAO.getSearchProducts(pageNum, word);
            log.info("search service :");
            for(ProductDTO product1 : productDTOList) {
                log.info(product1);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("Product List error");
        }
        return productDTOList;
    }
    
    // 상품 1개 가져오기
    public ProductDTO getProduct(int productId) {
        /* 상품의 상세페이지를 호출하는 메서드 */
        ProductDTO productDTO = null;
        try{
            productDTO = productDAO.selectProduct(productId);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("selectProduct error");
        }
        return productDTO;
    }

    //상품 삭제하기
    public void removeProduct(int productId) throws Exception {
        try{
            productDAO.removeProduct(productId);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("deleteProduct error");
        }
    }

    //상품 편집하기
    public void modifyProduct(HttpServletRequest request, ProductDTO productDTO) throws Exception {
        try{
            Part part = request.getPart("file");
            String fileName = this.getFileName(part);
            if (fileName != null && !fileName.isEmpty()) {
                part.write(fileName);
            } else {
                fileName = request.getParameter("fileName");
            }
            productDTO.setFileName(fileName);
            productDAO.modifyProduct(productDTO);
        } catch (Exception e) {
                log.info(e.getMessage());
                log.info("Product Insert error!");
        }
    }

    // 상품 목록 신상품 기준 8개 가져오기 ( 메인화면 )
    public List<ProductDTO> listProductLimit8() {
        /*ProductList.jsp에서 상품 목록을 보여주기 위한 요청을 처리하는 메서드 */
        List<ProductDTO> productDTOList = null;
        try{
            productDTOList = productDAO.selectAllLimit8();
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("Product List error");
        }
        return productDTOList;
    }
}
