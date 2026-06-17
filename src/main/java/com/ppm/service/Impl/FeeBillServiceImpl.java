package com.ppm.service.Impl;

import com.ppm.dao.FeeBillDao;
import com.ppm.dao.Impl.FeeBillDaoImpl;
import com.ppm.entity.FeeBill;
import com.ppm.service.FeeBillService;

import java.sql.SQLException;
import java.util.List;

public class FeeBillServiceImpl implements FeeBillService {
    private final FeeBillDao feeBillDao = new FeeBillDaoImpl();

    @Override
    public List<FeeBill> findAll() throws SQLException { return feeBillDao.findAll(); }
    @Override
    public List<FeeBill> findByCondition(Long houseId, String billMonth, Integer payStatus) throws SQLException { return feeBillDao.findByCondition(houseId, billMonth, payStatus); }
    @Override
    public List<FeeBill> findByHouseId(Long houseId) throws SQLException { return feeBillDao.findByHouseId(houseId); }
    @Override
    public FeeBill findById(Long id) throws SQLException { return feeBillDao.findById(id); }
    @Override
    public int insert(FeeBill feeBill) throws SQLException { return feeBillDao.insert(feeBill); }
    @Override
    public int pay(Long id, Long operUserId) throws SQLException { return feeBillDao.updatePayStatus(id, operUserId); }
    @Override
    public int deleteById(Long id) throws SQLException { return feeBillDao.deleteById(id); }
}
