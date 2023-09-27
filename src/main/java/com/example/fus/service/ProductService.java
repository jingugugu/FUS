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
            productDTO.setFileName("/upload/fus/product" + fileName);
            productDAO.insertProduct(productDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("Product Insert error!");
            request.setAttribute("error", "상품이 정상적으로 등록되지 않았습니다.");
        }
    }
    
    // 상품 목록 전체 가져오기
    public List<ProductDTO> listProduct() {
        /*ProductList.jsp에서 상품 목록을 보여주기 위한 요청을 처리하는 메서드 */
        List<ProductDTO> productDTOList = null;
        try{
            productDTOList = productDAO.selectAll();
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("Product List error");
        }
        return productDTOList;
    }
    
    // 상품 1개 가져오기
    public ProductDTO getProduct(String productId) {
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
}
