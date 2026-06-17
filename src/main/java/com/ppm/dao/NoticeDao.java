package com.ppm.dao;

import com.ppm.entity.Notice;
import java.sql.SQLException;
import java.util.List;

public interface NoticeDao {
    List<Notice> findAll() throws SQLException;
    Notice findById(Long id) throws SQLException;
    int insert(Notice notice) throws SQLException;
    int update(Notice notice) throws SQLException;
    int toggleTop(Long id, Integer isTop) throws SQLException;
    int deleteById(Long id) throws SQLException;
}
