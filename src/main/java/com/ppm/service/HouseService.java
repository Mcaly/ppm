package com.ppm.service;

import com.ppm.entity.House;
import java.sql.SQLException;
import java.util.List;

public interface HouseService {
    List<House> findAll() throws SQLException;
    House findById(Long id) throws SQLException;
    List<House> findByBuildId(Long buildId) throws SQLException;
    int insert(House house) throws SQLException;
    int update(House house) throws SQLException;
    int deleteById(Long id) throws SQLException;
}
