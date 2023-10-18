package com.example.fus.controller;

import com.example.fus.dto.*;
import com.example.fus.service.*;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet("/user/*")
public class UserController extends HttpServlet {

    private String path;
    private UserService userService = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        path = req.getPathInfo();

        if (path == null) {
            resp.sendRedirect("/main");
        }

        switch (path) {
            case "/login" :
                req.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(req, resp);
                break;
            case "/register" :
                req.getRequestDispatcher("/WEB-INF/user/register.jsp").forward(req, resp);
                break;
            case "/logout" :
                req.getSession().invalidate();
                resp.sendRedirect("/main");
                break;
            case "/myPage" :
                // 여기에 유저의 최근 구매내역, 리뷰, 게시글, 댓글 목록을 리퀘스트에 setAttribute 해줘야 함
                // 게시글 5개
                BoardService boardService = new BoardService();
                req.setAttribute("userBoards", boardService.selectMemberBoards5(req));
                // 리플 5개
                RippleService rippleService = new RippleService();
                req.setAttribute("userRipples", rippleService.selectMemberRipples5(req));
                // 리뷰 5개
                ReviewService reviewService = new ReviewService();
                req.setAttribute("userReviews", reviewService.selectMemberReviews5(req));
                // 구매내역 5개
                ShippingService shippingService = new ShippingService();
                req.setAttribute("userShippings", shippingService.selectMemberShippings5(req));
                req.getRequestDispatcher("/WEB-INF/user/myPage.jsp").forward(req, resp);
                break;
            case "/edit" :
                req.getRequestDispatcher("/WEB-INF/user/userEdit.jsp").forward(req, resp);
                break;
            case "/findPassword" :
                req.getRequestDispatcher("/WEB-INF/user/findPassword.jsp").forward(req, resp);
                break;
            case "/result" :
                req.getRequestDispatcher("/WEB-INF/user/result.jsp").forward(req, resp);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        path = req.getPathInfo();
        int resultNum = 0;

        if (path == null) {
            resp.sendRedirect("/main");
        }

        userService = new UserService();
        try {
            switch (path) {
                case "/login":
                    UserDTO userDTO = userService.login(req);
                    HttpSession session = req.getSession();
                    session.setAttribute("loginInfo", userDTO);
                    if(userDTO == null) { resp.sendRedirect("/user/login?error=1"); }
                    else { resp.sendRedirect("/main"); }
                    break;
                case "/register" :
                    resultNum = userService.register(req);
                    resp.sendRedirect("/user/result?resultNum="+resultNum);
                    break;
                case "/remove" :
                    resultNum = userService.removeUser(req); // 회원탈퇴하고
                    req.getSession().invalidate();               // 로그아웃 처리까지
                    resp.sendRedirect("/user/result?resultNum="+resultNum);
                    break;
                case "/edit" :
                    resultNum = userService.editUser(req);
                    resp.sendRedirect("/user/result?resultNum="+resultNum);
                    break;
                case "/findPassword" :
                    resultNum = userService.findPassword(req);
                    // 성공 시 resultNum = 2, 실패 시 resultNum = 0
                    resp.sendRedirect("/user/result?resultNum="+resultNum);
                    break;
                case "/resetPassword" :
                    resultNum = userService.resetPassword(req);
                    resp.sendRedirect("/user/result?resultNum="+resultNum);
                    break;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
