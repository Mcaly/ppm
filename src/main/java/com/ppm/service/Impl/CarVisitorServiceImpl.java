package com.ppm.service.Impl;

import com.ppm.dao.CarVisitorDao;
import com.ppm.dao.Impl.CarVisitorDaoImpl;
import com.ppm.entity.CarVisitor;
import com.ppm.service.CarVisitorService;

import java.sql.SQLException;
import java.util.List;

public class CarVisitorServiceImpl implements CarVisitorService {
    private final CarVisitorDao carVisitorDao = new CarVisitorDaoImpl();

    @Override
    public List<CarVisitor> findAll() throws SQLException { return carVisitorDao.findAll(); }
    @Override
    public List<CarVisitor> findNotOut() throws SQLException { return carVisitorDao.findNotOut(); }
    @Override
    public CarVisitor findById(Long id) throws SQLException { return carVisitorDao.findById(id); }
    @Override
    public int insert(CarVisitor carVisitor) throws SQLException { return carVisitorDao.insert(carVisitor); }
    @Override
    public int updateOutTime(Long id) throws SQLException { return carVisitorDao.updateOutTime(id); }
    @Override
    public int deleteById(Long id) throws SQLException { return carVisitorDao.deleteById(id); }
}
