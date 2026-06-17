package com.ppm.dao.Impl;

import com.ppm.dao.NoticeDao;
import com.ppm.entity.Notice;
import com.ppm.utils.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoticeDaoImpl implements NoticeDao {

    private Notice mapToEntity(Map<String, Object> map) {
        Notice n = new Notice();
        n.setId(((Number) map.get("id")).longValue());
        n.setTitle((String) map.get("title"));
        n.setContent((String) map.get("content"));
        Object pubUser = map.get("publish_user");
        n.setPublishUser(pubUser != null ? ((Number) pubUser).longValue() : null);
        n.setIsTop((Integer) map.get("is_top"));
        n.setIsDelete((Integer) map.get("is_delete"));
        n.setCreateTime(map.get("create_time") != null ? map.get("create_time").toString() : null);
        if (map.containsKey("publish_user_name")) {
            n.setPublishUserName((String) map.get("publish_user_name"));
        }
        return n;
    }

    @Override
    public List<Notice> findAll() throws SQLException {
        String sql = "SELECT n.*, u.real_name AS publish_user_name FROM notice n " +
                "LEFT JOIN user u ON n.publish_user = u.id " +
                "WHERE n.is_delete = 0 ORDER BY n.is_top DESC, n.id DESC";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
        List<Notice> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(mapToEntity(map));
        }
        return list;
    }

    @Override
    public Notice findById(Long id) throws SQLException {
        String sql = "SELECT n.*, u.real_name AS publish_user_name FROM notice n " +
                "LEFT JOIN user u ON n.publish_user = u.id " +
                "WHERE n.id = ? AND n.is_delete = 0";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, id);
        return maps.isEmpty() ? null : mapToEntity(maps.get(0));
    }

    @Override
    public int insert(Notice n) throws SQLException {
        String sql = "INSERT INTO notice (title, content, publish_user, is_top) VALUES (?, ?, ?, ?)";
        return DBUtil.executeUpdate(sql, n.getTitle(), n.getContent(), n.getPublishUser(), n.getIsTop());
    }

    @Override
    public int update(Notice n) throws SQLException {
        String sql = "UPDATE notice SET title = ?, content = ?, is_top = ? WHERE id = ?";
        return DBUtil.executeUpdate(sql, n.getTitle(), n.getContent(), n.getIsTop(), n.getId());
    }

    @Override
    public int toggleTop(Long id, Integer isTop) throws SQLException {
        String sql = "UPDATE notice SET is_top = ? WHERE id = ?";
        return DBUtil.executeUpdate(sql, isTop, id);
    }

    @Override
    public int deleteById(Long id) throws SQLException {
        String sql = "UPDATE notice SET is_delete = 1 WHERE id = ?";
        return DBUtil.executeUpdate(sql, id);
    }
}
