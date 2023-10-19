package com.example.fus.service;


import com.example.fus.dao.RippleDAO;
import com.example.fus.dto.BoardDTO;
import com.example.fus.dto.RippleDTO;
import com.example.fus.dto.UserDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class RippleService {
    private  RippleDAO rippleDAO;

    public RippleService(){rippleDAO = new RippleDAO();}

    //댓글 추가 메서드
    public int addRipple(HttpServletRequest request) throws Exception{
        log.info("addRipple()...");
        rippleDAO = new RippleDAO();
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("loginInfo");

        RippleDTO rippleDTO = RippleDTO.builder()
                .boardNum(Integer.valueOf(request.getParameter("boardNum")))
                .memberId(userDTO.getMemberId())
                .name(request.getParameter("name"))
                .content(request.getParameter("content"))
                .ip(request.getRemoteAddr())
                .build();
        log.info(rippleDTO);
        return rippleDAO.insertRipple(rippleDTO);

    }

    //댓글 리스트 블러오기
    public List<RippleDTO> getRipples(HttpServletRequest request) throws Exception{
         rippleDAO = new RippleDAO();
        int boardNum = Integer.parseInt(request.getParameter("boardNum"));
        List<RippleDTO> rippleDTOS = rippleDAO.selectRipples(boardNum);

        //댓글 중 로그인한 사용자가 작성한 댓글이면 isLogin 값을 true로 변경
        for (RippleDTO rippleDTO : rippleDTOS){
            log.info(rippleDTO.getMemberId());
            log.info(request.getSession().getAttribute("loginInfo"));
            UserDTO userDTO = (UserDTO) request.getSession().getAttribute("loginInfo");

            if(rippleDTO.getMemberId().equals(userDTO.getMemberId())){
                //댓글 사용자와 로그인한 사용자가 같은 경우
                rippleDTO.setLogin(true);
            }
        }
        return rippleDTOS;
    }

    public boolean removeRipple(HttpServletRequest request) throws Exception{
        RippleDAO rippleDAO = new RippleDAO();
        int rippleId = Integer.parseInt(request.getParameter("rippleId"));
        return rippleDAO.deleteRipple(rippleId);
    }

    public List<RippleDTO> selectMemberRipples(HttpServletRequest req) {
        List<RippleDTO> rippleDTOList = null;
        try {
            UserDTO userDTO = (UserDTO) req.getSession().getAttribute("loginInfo");
            rippleDTOList = rippleDAO.selectMemberRipples(userDTO.getMemberId());
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("BoardService : selectMemberBoards5 에러");
        }
        return rippleDTOList;
    }
}