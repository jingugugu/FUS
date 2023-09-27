package dao;

import com.example.fus.dao.ProductDAO;
import com.example.fus.dto.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@Log4j2
public class ProductDAOTests {
    private ProductDAO productDAO;

    @BeforeEach
    public void ready() {productDAO = new ProductDAO();}

    //product 추가 (완)
    @Test
    public void insertTest() throws Exception{
        ProductDTO productDTO = ProductDTO.builder()
                .productId(125)
                .productName("123")
                .category("123")
                .description("123")
                .price(123)
                .unitsInStock(123)
                .fileName("https://imgnews.pstatic.net/image/005/2023/09/26/2023092613594878000_1695704388_0018709360_20230926164406785.jpg?type=w647")
                .build();
        productDAO.insertProduct(productDTO);
    }

    //전체 출력 (완)
    @Test
    public void testAddList() throws Exception{
        List<ProductDTO> list = productDAO.selectAll();
        //1)람다와 스트림 이요해서 출력.
        list.forEach(product -> System.out.println(product));
        //2)foreach사용
        for(ProductDTO productDTO : list) {
            System.out.println(productDTO);
        }
    }

    @Test
    public void testGetProduct() throws Exception{
        String productId = "123";
        ProductDTO productDTO = productDAO.selectProduct(productId);
        log.info(productDTO);

    }
}
