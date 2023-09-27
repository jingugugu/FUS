package com.example.fus.controller;

import com.example.fus.dto.UserDTO;
import com.example.fus.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        path = req.getPathInfo();

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
                    resp.sendRedirect("/main");
                    break;
//            case "register" :
//                newsService.addNews(req);
//                resp.sendRedirect("list.news?action=list");
//                break;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
