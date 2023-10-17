package dao;

import com.example.fus.dao.RippleDAO;
import com.example.fus.dto.RippleDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Log4j2
public class RippleDAOTests {
    private RippleDAO rippleDAO;

    @BeforeEach
    public void ready() {rippleDAO = new RippleDAO();}

    //테스트 댓글이 10번 게시글에 입력되는지 확인하는 테스트 코드(완)
    @Test
    public void testInsertRipple() throws Exception{
        RippleDTO rippleDTO = RippleDTO.builder()
                .boardNum(1)
                .name("테스트")
                .memberId("testman2")
                .content("ripple test")
                .ip("1.1.1.1.")
                .build();
        rippleDAO.insertRipple(rippleDTO);

    }

}
