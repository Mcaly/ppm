package com.ppm.controller;

import com.ppm.entity.Building;
import com.ppm.entity.House;
import com.ppm.service.BuildingService;
import com.ppm.service.HouseService;
import com.ppm.service.Impl.BuildingServiceImpl;
import com.ppm.service.Impl.HouseServiceImpl;
import com.ppm.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/house/*")
public class HouseServlet extends HttpServlet {
    private HouseService houseService;
    private BuildingService buildingService;

    @Override
    public void init() {
        houseService = new HouseServiceImpl();
        buildingService = new BuildingServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/list";
        try {
            switch (pathInfo) {
                case "/list":
                    List<House> list = houseService.findAll();
                    req.setAttribute("list", list);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "house/list.jsp").forward(req, resp);
                    break;
                case "/add":
                    List<Building> buildings = buildingService.findAll();
                    req.setAttribute("buildings", buildings);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "house/form.jsp").forward(req, resp);
                    break;
                case "/edit":
                    Long editId = Long.parseLong(req.getParameter("id"));
                    House house = houseService.findById(editId);
                    List<Building> allBuildings = buildingService.findAll();
                    req.setAttribute("house", house);
                    req.setAttribute("buildings", allBuildings);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "house/form.jsp").forward(req, resp);
                    break;
                case "/delete":
                    Long deleteId = Long.parseLong(req.getParameter("id"));
                    houseService.deleteById(deleteId);
                    resp.sendRedirect(req.getContextPath() + "/house/list");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/house/list");
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
            House house = new House();
            house.setBuildId(Long.parseLong(req.getParameter("buildId")));
            house.setHouseNo(req.getParameter("houseNo"));
            house.setArea(new BigDecimal(req.getParameter("area")));
            house.setUnitPrice(new BigDecimal(req.getParameter("unitPrice")));
            house.setStatus(Integer.parseInt(req.getParameter("status")));
            if ("/add".equals(pathInfo)) {
                houseService.insert(house);
            } else if ("/edit".equals(pathInfo)) {
                house.setId(Long.parseLong(req.getParameter("id")));
                houseService.update(house);
            }
            resp.sendRedirect(req.getContextPath() + "/house/list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
