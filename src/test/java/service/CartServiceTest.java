package service;

import com.example.fus.service.CartService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;

@Log4j2
public class CartServiceTest {
    private CartService cartService;

    @BeforeEach
    public void ready() {
        cartService = new CartService();
    }
}
