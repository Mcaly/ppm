package com.ppm.controller;

import com.ppm.entity.Notice;
import com.ppm.entity.User;
import com.ppm.service.Impl.NoticeServiceImpl;
import com.ppm.service.NoticeService;
import com.ppm.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/notice/*")
public class NoticeServlet extends HttpServlet {
    private NoticeService noticeService;

    @Override
    public void init() { noticeService = new NoticeServiceImpl(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/list";
        try {
            switch (pathInfo) {
                case "/list":
                    List<Notice> list = noticeService.findAll();
                    req.setAttribute("list", list);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "notice/list.jsp").forward(req, resp);
                    break;
                case "/add":
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "notice/form.jsp").forward(req, resp);
                    break;
                case "/edit":
                    Long editId = Long.parseLong(req.getParameter("id"));
                    Notice notice = noticeService.findById(editId);
                    req.setAttribute("notice", notice);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "notice/form.jsp").forward(req, resp);
                    break;
                case "/toggleTop":
                    Long topId = Long.parseLong(req.getParameter("id"));
                    Integer isTop = Integer.parseInt(req.getParameter("isTop"));
                    noticeService.toggleTop(topId, isTop == 1 ? 0 : 1);
                    resp.sendRedirect(req.getContextPath() + "/notice/list");
                    break;
                case "/delete":
                    Long deleteId = Long.parseLong(req.getParameter("id"));
                    noticeService.deleteById(deleteId);
                    resp.sendRedirect(req.getContextPath() + "/notice/list");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/notice/list");
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
            User loginUser = (User) req.getSession().getAttribute("loginUser");
            Notice notice = new Notice();
            notice.setTitle(req.getParameter("title"));
            notice.setContent(req.getParameter("content"));
            notice.setIsTop(Integer.parseInt(req.getParameter("isTop")));
            if ("/add".equals(pathInfo)) {
                notice.setPublishUser(loginUser.getId());
                noticeService.insert(notice);
            } else if ("/edit".equals(pathInfo)) {
                notice.setId(Long.parseLong(req.getParameter("id")));
                noticeService.update(notice);
            }
            resp.sendRedirect(req.getContextPath() + "/notice/list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
