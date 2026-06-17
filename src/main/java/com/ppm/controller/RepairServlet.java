package com.ppm.controller;

import com.ppm.entity.House;
import com.ppm.entity.Repair;
import com.ppm.entity.User;
import com.ppm.service.HouseService;
import com.ppm.service.Impl.HouseServiceImpl;
import com.ppm.service.Impl.RepairServiceImpl;
import com.ppm.service.Impl.UserServiceImpl;
import com.ppm.service.RepairService;
import com.ppm.service.UserService;
import com.ppm.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/repair/*")
public class RepairServlet extends HttpServlet {
    private RepairService repairService;
    private HouseService houseService;
    private UserService userService;

    @Override
    public void init() {
        repairService = new RepairServiceImpl();
        houseService = new HouseServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/list";
        try {
            switch (pathInfo) {
                case "/list":
                    List<Repair> list = repairService.findAll();
                    req.setAttribute("list", list);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "repair/list.jsp").forward(req, resp);
                    break;
                case "/add":
                    List<House> houses = houseService.findAll();
                    req.setAttribute("houses", houses);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "repair/form.jsp").forward(req, resp);
                    break;
                case "/assign":
                    Long assignId = Long.parseLong(req.getParameter("id"));
                    Repair repair = repairService.findById(assignId);
                    List<User> workers = userService.findByJobType(3);
                    req.setAttribute("repair", repair);
                    req.setAttribute("workers", workers);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "repair/assign.jsp").forward(req, resp);
                    break;
                case "/complete":
                    Long completeId = Long.parseLong(req.getParameter("id"));
                    repairService.completeRepair(completeId);
                    resp.sendRedirect(req.getContextPath() + "/repair/list");
                    break;
                case "/delete":
                    Long deleteId = Long.parseLong(req.getParameter("id"));
                    repairService.deleteById(deleteId);
                    resp.sendRedirect(req.getContextPath() + "/repair/list");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/repair/list");
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
                Repair repair = new Repair();
                repair.setHouseId(Long.parseLong(req.getParameter("houseId")));
                repair.setOwnerName(req.getParameter("ownerName"));
                repair.setPhone(req.getParameter("phone"));
                repair.setRepairType(req.getParameter("repairType"));
                repair.setContent(req.getParameter("content"));
                repairService.insert(repair);
                resp.sendRedirect(req.getContextPath() + "/repair/list");
            } else if ("/assign".equals(pathInfo)) {
                Long repairId = Long.parseLong(req.getParameter("id"));
                Long workerId = Long.parseLong(req.getParameter("workerId"));
                repairService.assignWorker(repairId, workerId);
                resp.sendRedirect(req.getContextPath() + "/repair/list");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
