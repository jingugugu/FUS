package service;




import com.example.fus.dao.RippleDAO;
import com.example.fus.dto.RippleDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


@Log4j2
public class RippleTests {

    private RippleDAO rippleDAO;

    @BeforeEach
    public void ready(){rippleDAO = new RippleDAO();}

    @Test
    public void testSelectRipple() throws Exception{
        List<RippleDTO> list = rippleDAO.selectRipples(10);

        for (RippleDTO rippleDTO : list){
            System.out.println(rippleDTO);
        }
        //테이블에서 들고 와야 될 갯수를 확인 후에 결과값이 맞는지 확인하는 방법
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void  testDeleteRipple() throws Exception{
        int rippleId = 1; //데이버베이스에 존재하는 번호
        rippleDAO.deleteRipple(rippleId);
    }

}
