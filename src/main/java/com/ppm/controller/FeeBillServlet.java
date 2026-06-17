package com.ppm.controller;

import com.ppm.entity.FeeBill;
import com.ppm.entity.House;
import com.ppm.entity.User;
import com.ppm.service.FeeBillService;
import com.ppm.service.HouseService;
import com.ppm.service.Impl.FeeBillServiceImpl;
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

@WebServlet("/fee/*")
public class FeeBillServlet extends HttpServlet {
    private FeeBillService feeBillService;
    private HouseService houseService;

    @Override
    public void init() {
        feeBillService = new FeeBillServiceImpl();
        houseService = new HouseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) pathInfo = "/list";
        try {
            switch (pathInfo) {
                case "/list":
                    String houseIdStr = req.getParameter("houseId");
                    String billMonth = req.getParameter("billMonth");
                    String payStatusStr = req.getParameter("payStatus");
                    Long houseId = (houseIdStr != null && !houseIdStr.isEmpty()) ? Long.parseLong(houseIdStr) : null;
                    Integer payStatus = (payStatusStr != null && !payStatusStr.isEmpty()) ? Integer.parseInt(payStatusStr) : null;
                    List<FeeBill> list = feeBillService.findByCondition(houseId, billMonth, payStatus);
                    List<House> houses = houseService.findAll();
                    req.setAttribute("list", list);
                    req.setAttribute("houses", houses);
                    req.setAttribute("paramHouseId", houseIdStr);
                    req.setAttribute("paramBillMonth", billMonth);
                    req.setAttribute("paramPayStatus", payStatusStr);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "fee/list.jsp").forward(req, resp);
                    break;
                case "/generate":
                    List<House> allHouses = houseService.findAll();
                    req.setAttribute("houses", allHouses);
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "fee/generate.jsp").forward(req, resp);
                    break;
                case "/pay":
                    Long payId = Long.parseLong(req.getParameter("id"));
                    User loginUser = (User) req.getSession().getAttribute("loginUser");
                    feeBillService.pay(payId, loginUser.getId());
                    resp.sendRedirect(req.getContextPath() + "/fee/list");
                    break;
                case "/delete":
                    Long deleteId = Long.parseLong(req.getParameter("id"));
                    feeBillService.deleteById(deleteId);
                    resp.sendRedirect(req.getContextPath() + "/fee/list");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/fee/list");
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
            if ("/generate".equals(pathInfo)) {
                String billMonth = req.getParameter("billMonth");
                String[] houseIds = req.getParameterValues("houseIds");
                if (houseIds != null) {
                    for (String hid : houseIds) {
                        House house = houseService.findById(Long.parseLong(hid));
                        if (house != null) {
                            FeeBill bill = new FeeBill();
                            bill.setHouseId(house.getId());
                            bill.setBillMonth(billMonth);
                            bill.setTotalFee(house.getArea().multiply(house.getUnitPrice()));
                            feeBillService.insert(bill);
                        }
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/fee/list");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
