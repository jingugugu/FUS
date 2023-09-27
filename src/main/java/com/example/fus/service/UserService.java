package com.example.fus.service;

import com.example.fus.dao.UserDAO;
import com.example.fus.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public UserDTO login(HttpServletRequest request) throws SQLException {
        String memberId = request.getParameter("memberId");
        String passwd = request.getParameter("passwd");
        return userDAO.login(memberId, passwd);
    }
}
