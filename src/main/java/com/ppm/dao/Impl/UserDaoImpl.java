package com.ppm.dao.Impl;

import com.ppm.dao.UserDao;
import com.ppm.entity.User;
import com.ppm.utils.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private User mapToEntity(Map<String, Object> map) {
        User user = new User();
        user.setId(((Number) map.get("id")).longValue());
        user.setUserName((String) map.get("username"));
        user.setPassword((String) map.get("password"));
        user.setRealName((String) map.get("real_name"));
        user.setPhone((String) map.get("phone"));
        user.setJobType((Integer) map.get("job_type"));
        user.setStatus((Integer) map.get("status"));
        user.setIsDelete((Integer) map.get("is_delete"));
        user.setCreateTime(map.get("create_time") != null ? map.get("create_time").toString() : null);
        user.setUpdateTime(map.get("update_time") != null ? map.get("update_time").toString() : null);
        return user;
    }

    @Override
    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE username=? AND password=? AND is_delete=0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, username, password);
        return maps.isEmpty() ? null : mapToEntity(maps.get(0));
    }

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM user WHERE is_delete = 0 ORDER BY id";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
        List<User> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public User findById(Long id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id = ? AND is_delete = 0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, id);
        return maps.isEmpty() ? null : mapToEntity(maps.get(0));
    }

    @Override
    public List<User> findByJobType(Integer jobType) throws SQLException {
        String sql = "SELECT * FROM user WHERE job_type = ? AND is_delete = 0 AND status = 1 ORDER BY id";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, jobType);
        List<User> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO user (username, password, real_name, phone, job_type, status) VALUES (?, ?, ?, ?, ?, ?)";
        return DBUtil.executeUpdate(sql, user.getUserName(), user.getPassword(), user.getRealName(),
                user.getPhone(), user.getJobType(), user.getStatus());
    }

    @Override
    public int update(User user) throws SQLException {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String sql = "UPDATE user SET username=?, password=?, real_name=?, phone=?, job_type=?, status=? WHERE id=?";
            return DBUtil.executeUpdate(sql, user.getUserName(), user.getPassword(), user.getRealName(),
                    user.getPhone(), user.getJobType(), user.getStatus(), user.getId());
        } else {
            String sql = "UPDATE user SET username=?, real_name=?, phone=?, job_type=?, status=? WHERE id=?";
            return DBUtil.executeUpdate(sql, user.getUserName(), user.getRealName(),
                    user.getPhone(), user.getJobType(), user.getStatus(), user.getId());
        }
    }

    @Override
    public int deleteById(Long id) throws SQLException {
        String sql = "UPDATE user SET is_delete = 1 WHERE id = ?";
        return DBUtil.executeUpdate(sql, id);
    }
}
