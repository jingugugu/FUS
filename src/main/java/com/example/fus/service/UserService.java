package com.example.fus.service;

import com.example.fus.dao.UserDAO;
import com.example.fus.dto.UserDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Log4j2
public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    // 로그인 처리
    public UserDTO login(HttpServletRequest request) throws SQLException {
        String memberId = request.getParameter("memberId"); // 멤버아이디
        String passwd = request.getParameter("passwd");     // 패스워드를 받아서
        return userDAO.login(memberId, passwd);                   // 확인하는 메소드 호출
    }

    // 회원 가입
    public Integer register(HttpServletRequest request) throws SQLException {

        UserDTO userDTO = UserDTO.builder() // 폼에서 전달된 리퀘스트를 컨트롤러를 통해 전달받아서 userDTO에 삽입
                .memberId(request.getParameter("memberId"))
                .passwd(request.getParameter("passwd"))
                .name(request.getParameter("name"))
                .birthDate(request.getParameter("birthDate"))
                .zipCode(request.getParameter("zipCode"))
                .address01(request.getParameter("address01"))
                .address02(request.getParameter("address02"))
                .phone(request.getParameter("phone"))
                .findQuestion(request.getParameter("findQuestion"))
                .findAnswer(request.getParameter("findAnswer"))
                .addDate(request.getParameter("addDate"))
                .build();

        log.info("아이디 : " + userDTO);

        return userDAO.register(userDTO); // 완성된 userDTO를 이용해 유저 테이블에 인서트하는 메소드 호출
    }

    // 회원 탈퇴
    public Integer removeUser(HttpServletRequest request) throws SQLException {
        String memberId = request.getParameter("memberId");
        return userDAO.removeUser(memberId);
    }

    // 회원 수정
    public Integer editUser(HttpServletRequest request) throws SQLException {
        UserDTO userDTO = UserDTO.builder()
                .memberId(request.getParameter("memberId"))
                .passwd(request.getParameter("passwd"))
                .name(request.getParameter("name"))
                .zipCode(request.getParameter("zipCode"))
                .address01(request.getParameter("address01"))
                .address02(request.getParameter("address02"))
                .phone(request.getParameter("phone"))
                .findQuestion(request.getParameter("findQuestion"))
                .findAnswer(request.getParameter("findAnswer"))
                .build();
        Integer resultNum = userDAO.editUser(userDTO);
        log.info("확인 : "+resultNum);
        log.info(userDTO);

        if(resultNum == 1) { // 성공적으로 수행되면 로그인한 정보를 담고있는 userDTO도 바꿔줌
            HttpSession session = request.getSession();
//            session.removeAttribute("loginInfo"); // 기존 로그인 정보 제거 후
            UserDTO userDTOClone = (UserDTO) session.getAttribute("loginInfo");
            userDTO.setAddDate(userDTOClone.getAddDate());
            session.setAttribute("loginInfo", userDTO); // 새로 등록
        }

        return resultNum;
    }

    // 비밀번호 찾기
    public Integer findPassword(HttpServletRequest request) throws SQLException {
        String memberId = request.getParameter("memberId");
        String findQuestion = request.getParameter("findQuestion");
        String findAnswer = request.getParameter("findAnswer");

        Integer resultNum = userDAO.findPassword(memberId, findQuestion, findAnswer);

        if(resultNum == 2) {
            HttpSession session = request.getSession();
            session.setAttribute("memberId", memberId);
        }

        return resultNum;
    }

    // 비밀번호 재설정
    public Integer resetPassword(HttpServletRequest request) throws SQLException {
        String memberId = request.getParameter("memberId");
        String passwd = request.getParameter("passwd");
        Integer resultNum = userDAO.resetPassword(memberId, passwd);

        if(resultNum == 1){
            HttpSession session = request.getSession();
            session.removeAttribute("memberId");
        }

        return resultNum;
    }
}
