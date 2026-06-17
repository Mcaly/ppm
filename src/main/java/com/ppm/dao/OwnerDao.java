package com.ppm.dao;

import com.ppm.entity.Owner;
import java.sql.SQLException;
import java.util.List;

public interface OwnerDao {
    List<Owner> findAll() throws SQLException;
    Owner findById(Long id) throws SQLException;
    Owner findByPhoneAndIdCard(String phone, String idCard) throws SQLException;
    int insert(Owner owner) throws SQLException;
    int update(Owner owner) throws SQLException;
    int deleteById(Long id) throws SQLException;
}
