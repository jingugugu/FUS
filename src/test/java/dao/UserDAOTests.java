package dao;

import com.example.fus.dao.UserDAO;
import com.example.fus.dto.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Log4j2
public class UserDAOTests {
    private UserDAO userDAO;

    @BeforeEach
    public void ready() {
        userDAO = new UserDAO();
    }

    @Test
    public void testLogin() {
        UserDTO userDTO = userDAO.login("testman", "test123");
        log.info("결과 : " + userDTO.getName());
    }
}
