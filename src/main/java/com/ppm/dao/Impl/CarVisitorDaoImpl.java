package com.ppm.dao.Impl;

import com.ppm.dao.CarVisitorDao;
import com.ppm.entity.CarVisitor;
import com.ppm.utils.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarVisitorDaoImpl implements CarVisitorDao {

    private CarVisitor mapToEntity(Map<String, Object> map) {
        CarVisitor cv = new CarVisitor();
        cv.setId(((Number) map.get("id")).longValue());
        Object houseId = map.get("house_id");
        cv.setHouseId(houseId != null ? ((Number) houseId).longValue() : null);
        cv.setVisitorName((String) map.get("visitor_name"));
        cv.setPlateNum((String) map.get("plate_num"));
        cv.setInTime(map.get("in_time") != null ? map.get("in_time").toString() : null);
        cv.setOutTime(map.get("out_time") != null ? map.get("out_time").toString() : null);
        Object operId = map.get("oper_user_id");
        cv.setOperUserId(operId != null ? ((Number) operId).longValue() : null);
        cv.setIsDelete((Integer) map.get("is_delete"));
        cv.setCreateTime(map.get("create_time") != null ? map.get("create_time").toString() : null);
        if (map.containsKey("house_no")) {
            cv.setHouseNo((String) map.get("house_no"));
        }
        if (map.containsKey("build_name")) {
            cv.setBuildName((String) map.get("build_name"));
        }
        return cv;
    }

    @Override
    public List<CarVisitor> findAll() throws SQLException {
        String sql = "SELECT c.*, h.house_no, b.build_name FROM car_visitor c " +
                "LEFT JOIN house h ON c.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "WHERE c.is_delete = 0 ORDER BY c.id DESC";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
        List<CarVisitor> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public List<CarVisitor> findNotOut() throws SQLException {
        String sql = "SELECT c.*, h.house_no, b.build_name FROM car_visitor c " +
                "LEFT JOIN house h ON c.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "WHERE c.out_time IS NULL AND c.is_delete = 0 ORDER BY c.id DESC";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
        List<CarVisitor> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public CarVisitor findById(Long id) throws SQLException {
        String sql = "SELECT c.*, h.house_no, b.build_name FROM car_visitor c " +
                "LEFT JOIN house h ON c.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "WHERE c.id = ? AND c.is_delete = 0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, id);
        return maps.isEmpty() ? null : mapToEntity(maps.get(0));
    }

    @Override
    public int insert(CarVisitor cv) throws SQLException {
        String sql = "INSERT INTO car_visitor (house_id, visitor_name, plate_num, in_time, oper_user_id) VALUES (?, ?, ?, NOW(), ?)";
        return DBUtil.executeUpdate(sql, cv.getHouseId(), cv.getVisitorName(), cv.getPlateNum(), cv.getOperUserId());
    }

    @Override
    public int updateOutTime(Long id) throws SQLException {
        String sql = "UPDATE car_visitor SET out_time = NOW() WHERE id = ?";
        return DBUtil.executeUpdate(sql, id);
    }

    @Override
    public int deleteById(Long id) throws SQLException {
        String sql = "UPDATE car_visitor SET is_delete = 1 WHERE id = ?";
        return DBUtil.executeUpdate(sql, id);
    }
}
