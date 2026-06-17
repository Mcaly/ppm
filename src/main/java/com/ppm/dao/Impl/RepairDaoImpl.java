package com.ppm.dao.Impl;

import com.ppm.dao.RepairDao;
import com.ppm.entity.Repair;
import com.ppm.utils.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RepairDaoImpl implements RepairDao {

    private Repair mapToEntity(Map<String, Object> map) {
        Repair r = new Repair();
        r.setId(((Number) map.get("id")).longValue());
        r.setHouseId(((Number) map.get("house_id")).longValue());
        r.setOwnerName((String) map.get("owner_name"));
        r.setPhone((String) map.get("phone"));
        r.setRepairType((String) map.get("repair_type"));
        r.setContent((String) map.get("content"));
        Object workerId = map.get("worker_id");
        r.setWorkerId(workerId != null ? ((Number) workerId).longValue() : null);
        r.setRepairStatus((Integer) map.get("repair_status"));
        r.setFinishTime(map.get("finish_time") != null ? map.get("finish_time").toString() : null);
        r.setIsDelete((Integer) map.get("is_delete"));
        r.setCreateTime(map.get("create_time") != null ? map.get("create_time").toString() : null);
        if (map.containsKey("house_no")) {
            r.setHouseNo((String) map.get("house_no"));
        }
        if (map.containsKey("build_name")) {
            r.setBuildName((String) map.get("build_name"));
        }
        if (map.containsKey("worker_name")) {
            r.setWorkerName((String) map.get("worker_name"));
        }
        return r;
    }

    @Override
    public List<Repair> findAll() throws SQLException {
        String sql = "SELECT r.*, h.house_no, b.build_name, u.real_name AS worker_name FROM repair r " +
                "LEFT JOIN house h ON r.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "LEFT JOIN user u ON r.worker_id = u.id " +
                "WHERE r.is_delete = 0 ORDER BY r.id DESC";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
        List<Repair> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public List<Repair> findByStatus(Integer status) throws SQLException {
        String sql = "SELECT r.*, h.house_no, b.build_name, u.real_name AS worker_name FROM repair r " +
                "LEFT JOIN house h ON r.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "LEFT JOIN user u ON r.worker_id = u.id " +
                "WHERE r.repair_status = ? AND r.is_delete = 0 ORDER BY r.id DESC";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, status);
        List<Repair> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public List<Repair> findByHouseId(Long houseId) throws SQLException {
        String sql = "SELECT r.*, h.house_no, b.build_name, u.real_name AS worker_name FROM repair r " +
                "LEFT JOIN house h ON r.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "LEFT JOIN user u ON r.worker_id = u.id " +
                "WHERE r.house_id = ? AND r.is_delete = 0 ORDER BY r.id DESC";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, houseId);
        List<Repair> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public Repair findById(Long id) throws SQLException {
        String sql = "SELECT r.*, h.house_no, b.build_name, u.real_name AS worker_name FROM repair r " +
                "LEFT JOIN house h ON r.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "LEFT JOIN user u ON r.worker_id = u.id " +
                "WHERE r.id = ? AND r.is_delete = 0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, id);
        return maps.isEmpty() ? null : mapToEntity(maps.get(0));
    }

    @Override
    public int insert(Repair r) throws SQLException {
        String sql = "INSERT INTO repair (house_id, owner_name, phone, repair_type, content) VALUES (?, ?, ?, ?, ?)";
        return DBUtil.executeUpdate(sql, r.getHouseId(), r.getOwnerName(), r.getPhone(), r.getRepairType(), r.getContent());
    }

    @Override
    public int assignWorker(Long id, Long workerId) throws SQLException {
        String sql = "UPDATE repair SET worker_id = ?, repair_status = 1 WHERE id = ?";
        return DBUtil.executeUpdate(sql, workerId, id);
    }

    @Override
    public int completeRepair(Long id) throws SQLException {
        String sql = "UPDATE repair SET repair_status = 2, finish_time = NOW() WHERE id = ?";
        return DBUtil.executeUpdate(sql, id);
    }

    @Override
    public int deleteById(Long id) throws SQLException {
        String sql = "UPDATE repair SET is_delete = 1 WHERE id = ?";
        return DBUtil.executeUpdate(sql, id);
    }
}
