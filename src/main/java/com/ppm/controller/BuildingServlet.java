package com.ppm.controller;

import com.ppm.entity.Building;
import com.ppm.service.BuildingService;
import com.ppm.service.Impl.BuildingServiceImpl;
import com.ppm.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/building/*")
public class BuildingServlet extends HttpServlet {
    private BuildingService buildingService;

    @Override
    public void init() { buildingService = new BuildingServiceImpl(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/list";
        try {
            switch (pathInfo) {
                case "/list":
                    List<Building> list = buildingService.findAll();
                    req.setAttribute("list", list);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "building/list.jsp").forward(req, resp);
                    break;
                case "/add":
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "building/form.jsp").forward(req, resp);
                    break;
                case "/edit":
                    Long editId = Long.parseLong(req.getParameter("id"));
                    Building building = buildingService.findById(editId);
                    req.setAttribute("building", building);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "building/form.jsp").forward(req, resp);
                    break;
                case "/delete":
                    Long deleteId = Long.parseLong(req.getParameter("id"));
                    buildingService.deleteById(deleteId);
                    resp.sendRedirect(req.getContextPath() + "/building/list");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/building/list");
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
            Building building = new Building();
            building.setBuildName(req.getParameter("buildName"));
            building.setFloorCount(Integer.parseInt(req.getParameter("floorCount")));
            if ("/add".equals(pathInfo)) {
                buildingService.insert(building);
            } else if ("/edit".equals(pathInfo)) {
                building.setId(Long.parseLong(req.getParameter("id")));
                buildingService.update(building);
            }
            resp.sendRedirect(req.getContextPath() + "/building/list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
