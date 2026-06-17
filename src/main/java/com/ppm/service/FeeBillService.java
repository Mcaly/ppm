package com.ppm.service;

import com.ppm.entity.FeeBill;
import java.sql.SQLException;
import java.util.List;

public interface FeeBillService {
    List<FeeBill> findAll() throws SQLException;
    List<FeeBill> findByCondition(Long houseId, String billMonth, Integer payStatus) throws SQLException;
    List<FeeBill> findByHouseId(Long houseId) throws SQLException;
    FeeBill findById(Long id) throws SQLException;
    int insert(FeeBill feeBill) throws SQLException;
    int pay(Long id, Long operUserId) throws SQLException;
    int deleteById(Long id) throws SQLException;
}
