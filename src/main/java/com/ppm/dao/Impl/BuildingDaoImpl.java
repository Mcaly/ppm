package com.ppm.dao.Impl;

import com.ppm.dao.BuildingDao;
import com.ppm.entity.Building;
import com.ppm.utils.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildingDaoImpl implements BuildingDao {

    private Building mapToEntity(Map<String, Object> map) {
        Building b = new Building();
        b.setId(((Number) map.get("id")).longValue());
        b.setBuildName((String) map.get("build_name"));
        b.setFloorCount((Integer) map.get("floor_count"));
        b.setIsDelete((Integer) map.get("is_delete"));
        b.setCreateTime(map.get("create_time") != null ? map.get("create_time").toString() : null);
        return b;
    }

    @Override
    public List<Building> findAll() throws SQLException {
        String sql = "SELECT * FROM building WHERE is_delete = 0 ORDER BY id";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
        List<Building> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public Building findById(Long id) throws SQLException {
        String sql = "SELECT * FROM building WHERE id = ? AND is_delete = 0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, id);
        return maps.isEmpty() ? null : mapToEntity(maps.get(0));
    }

    @Override
    public int insert(Building b) throws SQLException {
        String sql = "INSERT INTO building (build_name, floor_count) VALUES (?, ?)";
        return DBUtil.executeUpdate(sql, b.getBuildName(), b.getFloorCount());
    }

    @Override
    public int update(Building b) throws SQLException {
        String sql = "UPDATE building SET build_name = ?, floor_count = ? WHERE id = ?";
        return DBUtil.executeUpdate(sql, b.getBuildName(), b.getFloorCount(), b.getId());
    }

    @Override
    public int deleteById(Long id) throws SQLException {
        String sql = "UPDATE building SET is_delete = 1 WHERE id = ?";
        return DBUtil.executeUpdate(sql, id);
    }
}
