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
        for (int i = 181; i < 211; i++) {
            ProductDTO productDTO = ProductDTO.builder()
                    .productId(i)
                    .productName("유니폼" + i)
                    .category("NAT")
                    .description("123")
                    .price(29000)
                    .unitsInStock(20)
                    .fileName("test3.jpg")
                    .build();
            productDAO.insertProduct(productDTO);
        }
    }

    // 카테고리별 전체 출력 (완)
    @Test
    public void testCategoryProductList() throws Exception{
        List<ProductDTO> list = productDAO.getCategoryProducts("1", "EPL");
        //1)람다와 스트림 이요해서 출력.
        list.forEach(product -> System.out.println(product));
        //2)foreach사용
        for(ProductDTO productDTO : list) {
            System.out.println(productDTO);
        }
    }

    //전체 출력 (완)
    @Test
    public void testAddList() throws Exception{
        List<ProductDTO> list = productDAO.selectAll("1");
        //1)람다와 스트림 이요해서 출력.
        list.forEach(product -> System.out.println(product));
        //2)foreach사용
        for(ProductDTO productDTO : list) {
            System.out.println(productDTO);
        }
    }

    @Test
    public void testGetProduct() throws Exception{
        Integer productId = 123;
        ProductDTO productDTO = productDAO.selectProduct(productId);
        log.info(productDTO);

    }
}
