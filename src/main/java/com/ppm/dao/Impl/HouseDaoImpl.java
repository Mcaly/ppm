package com.ppm.dao.Impl;

import com.ppm.dao.HouseDao;
import com.ppm.entity.House;
import com.ppm.utils.DBUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HouseDaoImpl implements HouseDao {

    private House mapToEntity(Map<String, Object> map) {
        House h = new House();
        h.setId(((Number) map.get("id")).longValue());
        h.setBuildId(((Number) map.get("build_id")).longValue());
        h.setHouseNo((String) map.get("house_no"));
        h.setArea((BigDecimal) map.get("area"));
        h.setUnitPrice((BigDecimal) map.get("unit_price"));
        h.setStatus((Integer) map.get("status"));
        h.setIsDelete((Integer) map.get("is_delete"));
        h.setCreateTime(map.get("create_time") != null ? map.get("create_time").toString() : null);
        if (map.containsKey("build_name")) {
            h.setBuildName((String) map.get("build_name"));
        }
        return h;
    }

    @Override
    public List<House> findAll() throws SQLException {
        String sql = "SELECT h.*, b.build_name FROM house h LEFT JOIN building b ON h.build_id = b.id WHERE h.is_delete = 0 ORDER BY h.id";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
        List<House> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public House findById(Long id) throws SQLException {
        String sql = "SELECT h.*, b.build_name FROM house h LEFT JOIN building b ON h.build_id = b.id WHERE h.id = ? AND h.is_delete = 0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, id);
        return maps.isEmpty() ? null : mapToEntity(maps.get(0));
    }

    @Override
    public List<House> findByBuildId(Long buildId) throws SQLException {
        String sql = "SELECT h.*, b.build_name FROM house h LEFT JOIN building b ON h.build_id = b.id WHERE h.build_id = ? AND h.is_delete = 0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, buildId);
        List<House> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public int insert(House h) throws SQLException {
        String sql = "INSERT INTO house (build_id, house_no, area, unit_price, status) VALUES (?, ?, ?, ?, ?)";
        return DBUtil.executeUpdate(sql, h.getBuildId(), h.getHouseNo(), h.getArea(), h.getUnitPrice(), h.getStatus());
    }

    @Override
    public int update(House h) throws SQLException {
        String sql = "UPDATE house SET build_id = ?, house_no = ?, area = ?, unit_price = ?, status = ? WHERE id = ?";
        return DBUtil.executeUpdate(sql, h.getBuildId(), h.getHouseNo(), h.getArea(), h.getUnitPrice(), h.getStatus(), h.getId());
    }

    @Override
    public int deleteById(Long id) throws SQLException {
        String sql = "UPDATE house SET is_delete = 1 WHERE id = ?";
        return DBUtil.executeUpdate(sql, id);
    }
}
