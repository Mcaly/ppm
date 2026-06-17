package com.ppm.dao;

import com.ppm.entity.Repair;
import java.sql.SQLException;
import java.util.List;

public interface RepairDao {
    List<Repair> findAll() throws SQLException;
    List<Repair> findByStatus(Integer status) throws SQLException;
    List<Repair> findByHouseId(Long houseId) throws SQLException;
    Repair findById(Long id) throws SQLException;
    int insert(Repair repair) throws SQLException;
    int assignWorker(Long id, Long workerId) throws SQLException;
    int completeRepair(Long id) throws SQLException;
    int deleteById(Long id) throws SQLException;
}
