package com.ppm.service.Impl;

import com.ppm.dao.UserDao;
import com.ppm.entity.User;
import com.ppm.service.UserService;
import com.ppm.dao.Impl.UserDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) throws SQLException {
        return userDao.login(username, password);
    }

    @Override
    public List<User> findAll() throws SQLException { return userDao.findAll(); }

    @Override
    public User findById(Long id) throws SQLException { return userDao.findById(id); }

    @Override
    public List<User> findByJobType(Integer jobType) throws SQLException { return userDao.findByJobType(jobType); }

    @Override
    public int insert(User user) throws SQLException { return userDao.insert(user); }

    @Override
    public int update(User user) throws SQLException { return userDao.update(user); }

    @Override
    public int deleteById(Long id) throws SQLException { return userDao.deleteById(id); }
}
