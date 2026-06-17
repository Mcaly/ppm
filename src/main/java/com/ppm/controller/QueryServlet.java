package com.ppm.controller;

import com.ppm.entity.FeeBill;
import com.ppm.entity.Owner;
import com.ppm.entity.Repair;
import com.ppm.service.FeeBillService;
import com.ppm.service.Impl.FeeBillServiceImpl;
import com.ppm.service.Impl.OwnerServiceImpl;
import com.ppm.service.Impl.RepairServiceImpl;
import com.ppm.service.OwnerService;
import com.ppm.service.RepairService;
import com.ppm.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/query")
public class QueryServlet extends HttpServlet {
    private OwnerService ownerService;
    private FeeBillService feeBillService;
    private RepairService repairService;

    @Override
    public void init() {
        ownerService = new OwnerServiceImpl();
        feeBillService = new FeeBillServiceImpl();
        repairService = new RepairServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Constants.WEB_PREFIX + "query.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        try {
            if ("verify".equals(action)) {
                String phone = req.getParameter("phone");
                String idCard = req.getParameter("idCard");
                if (phone == null || phone.isEmpty() || idCard == null || idCard.isEmpty()) {
                    req.setAttribute("error", "请输入手机号和身份证号");
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "query.jsp").forward(req, resp);
                    return;
                }
                Owner owner = ownerService.findByPhoneAndIdCard(phone.trim(), idCard.trim());
                if (owner == null) {
                    req.setAttribute("error", "验证失败，手机号或身份证号不正确");
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "query.jsp").forward(req, resp);
                    return;
                }
                // 验证通过，查询该业主的账单和报修
                List<FeeBill> bills = feeBillService.findByHouseId(owner.getHouseId());
                List<Repair> repairs = repairService.findByHouseId(owner.getHouseId());
                req.setAttribute("owner", owner);
                req.setAttribute("bills", bills);
                req.setAttribute("repairs", repairs);
                req.setAttribute("verified", true);
                req.getRequestDispatcher(Constants.WEB_PREFIX + "query.jsp").forward(req, resp);

            } else if ("repair".equals(action)) {
                // 提交报修
                String phone = req.getParameter("phone");
                String idCard = req.getParameter("idCard");
                Owner owner = ownerService.findByPhoneAndIdCard(phone, idCard);
                if (owner == null) {
                    req.setAttribute("error", "身份验证已过期，请重新验证");
                    req.getRequestDispatcher(Constants.WEB_PREFIX + "query.jsp").forward(req, resp);
                    return;
                }
                Repair repair = new Repair();
                repair.setHouseId(owner.getHouseId());
                repair.setOwnerName(owner.getOwnerName());
                repair.setPhone(owner.getPhone());
                repair.setRepairType(req.getParameter("repairType"));
                repair.setContent(req.getParameter("content"));
                repairService.insert(repair);

                // 重新查询数据并显示成功提示
                List<FeeBill> bills = feeBillService.findByHouseId(owner.getHouseId());
                List<Repair> repairs = repairService.findByHouseId(owner.getHouseId());
                req.setAttribute("owner", owner);
                req.setAttribute("bills", bills);
                req.setAttribute("repairs", repairs);
                req.setAttribute("verified", true);
                req.setAttribute("success", "报修提交成功！");
                req.getRequestDispatcher(Constants.WEB_PREFIX + "query.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
