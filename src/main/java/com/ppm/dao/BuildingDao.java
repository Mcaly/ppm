package com.ppm.dao;

import com.ppm.entity.Building;
import java.sql.SQLException;
import java.util.List;

public interface BuildingDao {
    List<Building> findAll() throws SQLException;
    Building findById(Long id) throws SQLException;
    int insert(Building building) throws SQLException;
    int update(Building building) throws SQLException;
    int deleteById(Long id) throws SQLException;
}
