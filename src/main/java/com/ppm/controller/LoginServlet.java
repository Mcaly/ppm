package com.ppm.controller;

import com.ppm.entity.User;
import com.ppm.service.UserService;
import com.ppm.service.Impl.UserServiceImpl;
import com.ppm.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Constants.WEB_PREFIX + "login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = userService.login(username, password);
            if (user!=null) {
                HttpSession session = req.getSession();
                session.setAttribute("loginUser", user);

                resp.sendRedirect(req.getContextPath() + "/index");
            }else{
                resp.sendRedirect(req.getContextPath() + "/login?error=invalid"); //防止表单重复提交
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
