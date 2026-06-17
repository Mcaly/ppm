package com.ppm.controller;

import com.ppm.entity.House;
import com.ppm.entity.Owner;
import com.ppm.service.HouseService;
import com.ppm.service.Impl.HouseServiceImpl;
import com.ppm.service.Impl.OwnerServiceImpl;
import com.ppm.service.OwnerService;
import com.ppm.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/owner/*")
public class OwnerServlet extends HttpServlet {
    private OwnerService ownerService;
    private HouseService houseService;

    @Override
    public void init() {
        ownerService = new OwnerServiceImpl();
        houseService = new HouseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/list";
        try {
            switch (pathInfo) {
                case "/list":
                    List<Owner> list = ownerService.findAll();
                    req.setAttribute("list", list);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "owner/list.jsp").forward(req, resp);
                    break;
                case "/add":
                    List<House> houses = houseService.findAll();
                    req.setAttribute("houses", houses);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "owner/form.jsp").forward(req, resp);
                    break;
                case "/edit":
                    Long editId = Long.parseLong(req.getParameter("id"));
                    Owner owner = ownerService.findById(editId);
                    List<House> allHouses = houseService.findAll();
                    req.setAttribute("owner", owner);
                    req.setAttribute("houses", allHouses);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "owner/form.jsp").forward(req, resp);
                    break;
                case "/delete":
                    Long deleteId = Long.parseLong(req.getParameter("id"));
                    ownerService.deleteById(deleteId);
                    resp.sendRedirect(req.getContextPath() + "/owner/list");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/owner/list");
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
            Owner owner = new Owner();
            owner.setHouseId(Long.parseLong(req.getParameter("houseId")));
            owner.setOwnerName(req.getParameter("ownerName"));
            owner.setPhone(req.getParameter("phone"));
            owner.setIdCard(req.getParameter("idCard"));
            owner.setLiveStatus(Integer.parseInt(req.getParameter("liveStatus")));
            if ("/add".equals(pathInfo)) {
                ownerService.insert(owner);
            } else if ("/edit".equals(pathInfo)) {
                owner.setId(Long.parseLong(req.getParameter("id")));
                ownerService.update(owner);
            }
            resp.sendRedirect(req.getContextPath() + "/owner/list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
