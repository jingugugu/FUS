package dao;

import com.example.fus.dao.CartDAO;
import com.example.fus.dto.CartDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Log4j2
public class CartDAOTests {
    private CartDAO cartDAO;

    @BeforeEach
    public void ready() {
        cartDAO = new CartDAO();
    }

    @Test
    public void testInsertCart() throws Exception {
        for (int i = 1; i < 10; i++) {
            CartDTO cartDTO = CartDTO.builder()
                    .productId(i)
                    .memberId("testMemberId" + i)
                    .productName("testProductName" + i)
                    .count(1)
                    .price(5000)
                    .fileName("testFileName" + i)
                    .build();
            cartDAO.insertCart(cartDTO);
        }
    }
    @Test
    public void testRemoveCart() throws Exception {
        String memberId = "testman2";
        cartDAO.allRemoveCart(memberId);
    }
}
