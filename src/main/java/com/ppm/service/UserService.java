package com.ppm.service;

import com.ppm.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User login(String username, String password) throws SQLException;
    List<User> findAll() throws SQLException;
    User findById(Long id) throws SQLException;
    List<User> findByJobType(Integer jobType) throws SQLException;
    int insert(User user) throws SQLException;
    int update(User user) throws SQLException;
    int deleteById(Long id) throws SQLException;
}
