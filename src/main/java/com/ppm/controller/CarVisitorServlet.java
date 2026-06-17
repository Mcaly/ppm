package com.ppm.controller;

import com.ppm.entity.CarVisitor;
import com.ppm.entity.House;
import com.ppm.entity.User;
import com.ppm.service.CarVisitorService;
import com.ppm.service.HouseService;
import com.ppm.service.Impl.CarVisitorServiceImpl;
import com.ppm.service.Impl.HouseServiceImpl;
import com.ppm.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/car/*")
public class CarVisitorServlet extends HttpServlet {
    private CarVisitorService carVisitorService;
    private HouseService houseService;

    @Override
    public void init() {
        carVisitorService = new CarVisitorServiceImpl();
        houseService = new HouseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/list";
        try {
            switch (pathInfo) {
                case "/list":
                    List<CarVisitor> list = carVisitorService.findAll();
                    req.setAttribute("list", list);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "car/list.jsp").forward(req, resp);
                    break;
                case "/in":
                    List<House> houses = houseService.findAll();
                    req.setAttribute("houses", houses);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "car/in.jsp").forward(req, resp);
                    break;
                case "/out":
                    List<CarVisitor> notOut = carVisitorService.findNotOut();
                    req.setAttribute("list", notOut);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "car/out.jsp").forward(req, resp);
                    break;
                case "/doOut":
                    Long outId = Long.parseLong(req.getParameter("id"));
                    carVisitorService.updateOutTime(outId);
                    resp.sendRedirect(req.getContextPath() + "/car/list");
                    break;
                case "/delete":
                    Long deleteId = Long.parseLong(req.getParameter("id"));
                    carVisitorService.deleteById(deleteId);
                    resp.sendRedirect(req.getContextPath() + "/car/list");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/car/list");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            if ("/in".equals(req.getPathInfo())) {
                User loginUser = (User) req.getSession().getAttribute("loginUser");
                CarVisitor cv = new CarVisitor();
                String houseIdStr = req.getParameter("houseId");
                if (houseIdStr != null && !houseIdStr.isEmpty()) {
                    cv.setHouseId(Long.parseLong(houseIdStr));
                }
                cv.setVisitorName(req.getParameter("visitorName"));
                cv.setPlateNum(req.getParameter("plateNum"));
                cv.setOperUserId(loginUser.getId());
                carVisitorService.insert(cv);
                resp.sendRedirect(req.getContextPath() + "/car/list");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
