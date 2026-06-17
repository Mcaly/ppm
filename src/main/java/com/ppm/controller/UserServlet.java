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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() { userService = new UserServiceImpl(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/list";
        try {
            switch (pathInfo) {
                case "/list":
                    List<User> list = userService.findAll();
                    req.setAttribute("list", list);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "user/list.jsp").forward(req, resp);
                    break;
                case "/add":
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "user/form.jsp").forward(req, resp);
                    break;
                case "/edit":
                    Long editId = Long.parseLong(req.getParameter("id"));
                    User user = userService.findById(editId);
                    req.setAttribute("user", user);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "user/form.jsp").forward(req, resp);
                    break;
                case "/delete":
                    Long deleteId = Long.parseLong(req.getParameter("id"));
                    userService.deleteById(deleteId);
                    resp.sendRedirect(req.getContextPath() + "/user/list");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/user/list");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String pathInfo = req.getPathInfo();
        try {
            if ("/add".equals(pathInfo)) {
                User user = new User();
                user.setUserName(req.getParameter("userName"));
                user.setPassword(req.getParameter("password"));
                user.setRealName(req.getParameter("realName"));
                user.setPhone(req.getParameter("phone"));
                user.setJobType(Integer.parseInt(req.getParameter("jobType")));
                user.setStatus(Integer.parseInt(req.getParameter("status")));
                userService.insert(user);
            } else if ("/edit".equals(pathInfo)) {
                User user = new User();
                user.setId(Long.parseLong(req.getParameter("id")));
                user.setUserName(req.getParameter("userName"));
                user.setRealName(req.getParameter("realName"));
                user.setPhone(req.getParameter("phone"));
                user.setJobType(Integer.parseInt(req.getParameter("jobType")));
                user.setStatus(Integer.parseInt(req.getParameter("status")));
                String pwd = req.getParameter("password");
                if (pwd != null && !pwd.isEmpty()) {
                    user.setPassword(pwd);
                }
                userService.update(user);
            }
            resp.sendRedirect(req.getContextPath() + "/user/list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
