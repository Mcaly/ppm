package com.ppm.dao.Impl;

import com.ppm.dao.OwnerDao;
import com.ppm.entity.Owner;
import com.ppm.utils.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OwnerDaoImpl implements OwnerDao {

    private Owner mapToEntity(Map<String, Object> map) {
        Owner o = new Owner();
        o.setId(((Number) map.get("id")).longValue());
        o.setHouseId(((Number) map.get("house_id")).longValue());
        o.setOwnerName((String) map.get("owner_name"));
        o.setPhone((String) map.get("phone"));
        o.setIdCard((String) map.get("id_card"));
        o.setLiveStatus((Integer) map.get("live_status"));
        o.setIsDelete((Integer) map.get("is_delete"));
        o.setCreateTime(map.get("create_time") != null ? map.get("create_time").toString() : null);
        if (map.containsKey("house_no")) {
            o.setHouseNo((String) map.get("house_no"));
        }
        if (map.containsKey("build_name")) {
            o.setBuildName((String) map.get("build_name"));
        }
        return o;
    }

    @Override
    public List<Owner> findAll() throws SQLException {
        String sql = "SELECT o.*, h.house_no, b.build_name FROM owner o " +
                "LEFT JOIN house h ON o.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "WHERE o.is_delete = 0 ORDER BY o.id";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
        List<Owner> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public Owner findById(Long id) throws SQLException {
        String sql = "SELECT o.*, h.house_no, b.build_name FROM owner o " +
                "LEFT JOIN house h ON o.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "WHERE o.id = ? AND o.is_delete = 0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, id);
        return maps.isEmpty() ? null : mapToEntity(maps.get(0));
    }

    @Override
    public Owner findByPhoneAndIdCard(String phone, String idCard) throws SQLException {
        String sql = "SELECT o.*, h.house_no, b.build_name FROM owner o " +
                "LEFT JOIN house h ON o.house_id = h.id " +
                "LEFT JOIN building b ON h.build_id = b.id " +
                "WHERE o.phone = ? AND o.id_card = ? AND o.is_delete = 0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, phone, idCard);
        return maps.isEmpty() ? null : mapToEntity(maps.get(0));
    }

    @Override
    public int insert(Owner o) throws SQLException {
        String sql = "INSERT INTO owner (house_id, owner_name, phone, id_card, live_status) VALUES (?, ?, ?, ?, ?)";
        return DBUtil.executeUpdate(sql, o.getHouseId(), o.getOwnerName(), o.getPhone(), o.getIdCard(), o.getLiveStatus());
    }

    @Override
    public int update(Owner o) throws SQLException {
        String sql = "UPDATE owner SET house_id = ?, owner_name = ?, phone = ?, id_card = ?, live_status = ? WHERE id = ?";
        return DBUtil.executeUpdate(sql, o.getHouseId(), o.getOwnerName(), o.getPhone(), o.getIdCard(), o.getLiveStatus(), o.getId());
    }

    @Override
    public int deleteById(Long id) throws SQLException {
        String sql = "UPDATE owner SET is_delete = 1 WHERE id = ?";
        return DBUtil.executeUpdate(sql, id);
    }
}
