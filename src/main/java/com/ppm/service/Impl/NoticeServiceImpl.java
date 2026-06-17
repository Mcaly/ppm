package com.ppm.service.Impl;

import com.ppm.dao.NoticeDao;
import com.ppm.dao.Impl.NoticeDaoImpl;
import com.ppm.entity.Notice;
import com.ppm.service.NoticeService;

import java.sql.SQLException;
import java.util.List;

public class NoticeServiceImpl implements NoticeService {
    private final NoticeDao noticeDao = new NoticeDaoImpl();

    @Override
    public List<Notice> findAll() throws SQLException { return noticeDao.findAll(); }
    @Override
    public Notice findById(Long id) throws SQLException { return noticeDao.findById(id); }
    @Override
    public int insert(Notice notice) throws SQLException { return noticeDao.insert(notice); }
    @Override
    public int update(Notice notice) throws SQLException { return noticeDao.update(notice); }
    @Override
    public int toggleTop(Long id, Integer isTop) throws SQLException { return noticeDao.toggleTop(id, isTop); }
    @Override
    public int deleteById(Long id) throws SQLException { return noticeDao.deleteById(id); }
}
