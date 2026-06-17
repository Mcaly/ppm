package com.ppm.controller;

import com.ppm.service.*;
import com.ppm.service.Impl.*;
import com.ppm.utils.Constants;
import com.ppm.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            // 统计数据
            Object houseCount = DBUtil.executeScalar("SELECT COUNT(*) FROM house WHERE is_delete=0");
            Object unpaidCount = DBUtil.executeScalar("SELECT COUNT(*) FROM fee_bill WHERE pay_status=0 AND is_delete=0");
            Object pendingRepair = DBUtil.executeScalar("SELECT COUNT(*) FROM repair WHERE repair_status=0 AND is_delete=0");
            Object todayVisitor = DBUtil.executeScalar("SELECT COUNT(*) FROM car_visitor WHERE DATE(in_time)=CURDATE() AND is_delete=0");
            req.setAttribute("houseCount", houseCount != null ? houseCount : 0);
            req.setAttribute("unpaidCount", unpaidCount != null ? unpaidCount : 0);
            req.setAttribute("pendingRepair", pendingRepair != null ? pendingRepair : 0);
            req.setAttribute("todayVisitor", todayVisitor != null ? todayVisitor : 0);

            // 最新公告
            NoticeService noticeService = new NoticeServiceImpl();
            req.setAttribute("notices", noticeService.findAll());

            // 最新报修
            RepairService repairService = new RepairServiceImpl();
            req.setAttribute("repairs", repairService.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher(Constants.WEB_PREFIX + "index.jsp").forward(req, resp);
    }
}
