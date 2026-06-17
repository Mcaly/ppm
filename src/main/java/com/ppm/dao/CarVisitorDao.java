package com.ppm.dao;

import com.ppm.entity.CarVisitor;
import java.sql.SQLException;
import java.util.List;

public interface CarVisitorDao {
    List<CarVisitor> findAll() throws SQLException;
    List<CarVisitor> findNotOut() throws SQLException;
    CarVisitor findById(Long id) throws SQLException;
    int insert(CarVisitor carVisitor) throws SQLException;
    int updateOutTime(Long id) throws SQLException;
    int deleteById(Long id) throws SQLException;
}
