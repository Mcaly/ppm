package com.ppm.service.Impl;

import com.ppm.dao.BuildingDao;
import com.ppm.dao.Impl.BuildingDaoImpl;
import com.ppm.entity.Building;
import com.ppm.service.BuildingService;

import java.sql.SQLException;
import java.util.List;

public class BuildingServiceImpl implements BuildingService {
    private final BuildingDao buildingDao = new BuildingDaoImpl();

    @Override
    public List<Building> findAll() throws SQLException { return buildingDao.findAll(); }
    @Override
    public Building findById(Long id) throws SQLException { return buildingDao.findById(id); }
    @Override
    public int insert(Building building) throws SQLException { return buildingDao.insert(building); }
    @Override
    public int update(Building building) throws SQLException { return buildingDao.update(building); }
    @Override
    public int deleteById(Long id) throws SQLException { return buildingDao.deleteById(id); }
}
