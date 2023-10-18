package service;

import com.example.fus.dao.ProductDAO;
import com.example.fus.dto.ProductDTO;
import com.example.fus.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
public class ProductServiceTests {

    @Test
    public void Test(){
        try {
            ProductService productService = new ProductService();
            ProductDAO productDAO = new ProductDAO();
//        log.info(productService.sizeCategoryProductList("ALL"));
            ProductDTO productDTO = ProductDTO.builder()
                    .productId(2000)
                    .productName("2000")
                    .category("NAT")
                    .description("123")
                    .price(500)
                    .unitsInStock(20)
                    .fileName("test1.jpg")
                    .build();
            productDAO.insertProduct(productDTO);
        } catch (Exception e){
            log.error(e.getMessage());
        }

    }
    //리뷰 등록시 리뷰수 추가
    @Test
    public void reviewCountTest(){
        int productId = 1;
        ProductService productService = new ProductService();
        productService.reviewCountUp(productId);
    }
}
