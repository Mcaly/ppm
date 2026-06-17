package com.ppm.dao.Impl;

import com.ppm.dao.FeeBillDao;
import com.ppm.entity.FeeBill;
import com.ppm.utils.DBUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeeBillDaoImpl implements FeeBillDao {

    private FeeBill mapToEntity(Map<String, Object> map) {
        FeeBill f = new FeeBill();
        f.setId(((Number) map.get("id")).longValue());
        f.setHouseId(((Number) map.get("house_id")).longValue());
        f.setBillMonth((String) map.get("bill_month"));
        f.setTotalFee((BigDecimal) map.get("total_fee"));
        f.setPayStatus((Integer) map.get("pay_status"));
        f.setPayTime(map.get("pay_time") != null ? map.get("pay_time").toString() : null);
        Object operId = map.get("oper_user_id");
        f.setOperUserId(operId != null ? ((Number) operId).longValue() : null);
        f.setIsDelete((Integer) map.get("is_delete"));
        f.setCreateTime(map.get("create_time") != null ? map.get("create_time").toString() : null);
        if (map.containsKey("house_no")) {
            f.setHouseNo((String) map.get("house_no"));
        }
        if (map.containsKey("build_name")) {
            f.setBuildName((String) map.get("build_name"));
        }
        return f;
    }

    @Override
    public List<FeeBill> findAll() throws SQLException {
        String sql = "SELECT f.*, h.house_no, b.build_name FROM fee_bill f " +
                "LEFT JOIN house h ON f.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "WHERE f.is_delete = 0 ORDER BY f.id DESC";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
        List<FeeBill> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public List<FeeBill> findByCondition(Long houseId, String billMonth, Integer payStatus) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT f.*, h.house_no, b.build_name FROM fee_bill f " +
                "LEFT JOIN house h ON f.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "WHERE f.is_delete = 0");
        List<Object> params = new ArrayList<>();
        if (houseId != null) {
            sql.append(" AND f.house_id = ?");
            params.add(houseId);
        }
        if (billMonth != null && !billMonth.isEmpty()) {
            sql.append(" AND f.bill_month = ?");
            params.add(billMonth);
        }
        if (payStatus != null) {
            sql.append(" AND f.pay_status = ?");
            params.add(payStatus);
        }
        sql.append(" ORDER BY f.id DESC");
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql.toString(), params.toArray());
        List<FeeBill> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public List<FeeBill> findByHouseId(Long houseId) throws SQLException {
        String sql = "SELECT f.*, h.house_no, b.build_name FROM fee_bill f " +
                "LEFT JOIN house h ON f.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "WHERE f.house_id = ? AND f.is_delete = 0 ORDER BY f.id DESC";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, houseId);
        List<FeeBill> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public FeeBill findById(Long id) throws SQLException {
        String sql = "SELECT f.*, h.house_no, b.build_name FROM fee_bill f " +
                "LEFT JOIN house h ON f.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "WHERE f.id = ? AND f.is_delete = 0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, id);
        return maps.isEmpty() ? null : mapToEntity(maps.get(0));
    }

    @Override
    public int insert(FeeBill f) throws SQLException {
        String sql = "INSERT INTO fee_bill (house_id, bill_month, total_fee) VALUES (?, ?, ?)";
        return DBUtil.executeUpdate(sql, f.getHouseId(), f.getBillMonth(), f.getTotalFee());
    }

    @Override
    public int updatePayStatus(Long id, Long operUserId) throws SQLException {
        String sql = "UPDATE fee_bill SET pay_status = 1, pay_time = NOW(), oper_user_id = ? WHERE id = ?";
        return DBUtil.executeUpdate(sql, operUserId, id);
    }

    @Override
    public int deleteById(Long id) throws SQLException {
        String sql = "UPDATE fee_bill SET is_delete = 1 WHERE id = ?";
        return DBUtil.executeUpdate(sql, id);
    }
}
