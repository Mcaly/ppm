package com.ppm.service.Impl;

import com.ppm.dao.HouseDao;
import com.ppm.dao.Impl.HouseDaoImpl;
import com.ppm.entity.House;
import com.ppm.service.HouseService;

import java.sql.SQLException;
import java.util.List;

public class HouseServiceImpl implements HouseService {
    private final HouseDao houseDao = new HouseDaoImpl();

    @Override
    public List<House> findAll() throws SQLException { return houseDao.findAll(); }
    @Override
    public House findById(Long id) throws SQLException { return houseDao.findById(id); }
    @Override
    public List<House> findByBuildId(Long buildId) throws SQLException { return houseDao.findByBuildId(buildId); }
    @Override
    public int insert(House house) throws SQLException { return houseDao.insert(house); }
    @Override
    public int update(House house) throws SQLException { return houseDao.update(house); }
    @Override
    public int deleteById(Long id) throws SQLException { return houseDao.deleteById(id); }
}
