package dao;

import com.example.fus.dao.BoardDAO;
import com.example.fus.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@Log4j2
public class BoardDAOTests {
    private BoardDAO boardDAO;

    @BeforeEach
    public void ready(){boardDAO = new BoardDAO();}

//    @Test
//    public void testConnection() throws Exception{
//        BoardDAO boardDAO = new BoardDAO();
//        Connection connection = Con
//        log.info(connection);
//    }

    /*게시물 추가 테스트 코드*/
    @Test
    public void testInsetBoard() throws Exception{
        for(int i = 1; i <= 30; i++){
            BoardDTO boardDTO = BoardDTO.builder()
                    .title("FUS Project...")
                    .memberId("testman")
                    .name("user00" + i)
                    .content("이번 축구 경기는...")
                    .fileName("soccer.jsp")
                    .build();
            boardDAO.boardAdd(boardDTO);
        }
    }

    /*게시판 게시글 목록 전체를 가져오는지 확인하는 테스트 코드*/
//    @Test
//    public void testSelectAll() throws Exception{
////        List<BoardDTO> list = boardDAO.selectAll();
//
//        for(BoardDTO boardDTO : list){
//            System.out.println(boardDTO);
//        }
//    }

    @Test
    public void testSelectOne() throws Exception{
        int boardNum = 31;
        BoardDTO boardDTO = boardDAO.selectOne(boardNum);
        System.out.println(boardDTO);
    }

//    @Test
//    public void testDeleteBoard() throws Exception{
//        List<BoardDTO> list = boardDAO.selectAll();
//        list.forEach(boardDTO -> System.out.println(boardDAO));
//
//        int boardNum = 31;
//        boardDAO.deleteBoard(boardNum);
//
//        list = boardDAO.selectAll();
//        list.forEach(boardDTO -> System.out.println(boardDTO));
//    }

    @Test
    public void testAddBoard() throws Exception{
        BoardDTO boardDTO = BoardDTO.builder()
                .title("").build();
    }
}
