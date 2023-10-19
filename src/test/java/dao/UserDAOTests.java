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
        log.info("결과 : " + userDTO.getUserName());
    }

    @Test
    public void testRegister() {
        UserDTO userDTO = UserDTO.builder()
                .memberId("testman3")
                .passwd("test123")
                .name("test222")
                .birthDate("2000-01-01")
                .zipCode("22284")
                .address01("주소111")
                .address02("주소222")
                .phone("010-1111-1122")
                .findQuestion("treasure")
                .findAnswer("가족")
                .addDate("2023-10-01").build();
        userDAO.register(userDTO);
    }

    @Test
    public void testModify() {
        UserDTO userDTO = UserDTO.builder()
                .memberId("testman2")
                .passwd("test12")
                .name("test22")
                .birthDate("2000-01-01")
                .zipCode("22284")
                .address01("주소111")
                .phone("010-1111-1122")
                .findQuestion("teacher")
                .findAnswer("김호열")
                .build();
        userDAO.editUser(userDTO);
    }

    @Test
    public void testFindPassword() {
        String memberId = "testman3";
        String findQuestion = "sports";
        String findAnswer = "축구";
        userDAO.findPassword(memberId, findQuestion, findAnswer);
    }
}
