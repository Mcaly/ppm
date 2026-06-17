package com.ppm.service.Impl;

import com.ppm.dao.RepairDao;
import com.ppm.dao.Impl.RepairDaoImpl;
import com.ppm.entity.Repair;
import com.ppm.service.RepairService;

import java.sql.SQLException;
import java.util.List;

public class RepairServiceImpl implements RepairService {
    private final RepairDao repairDao = new RepairDaoImpl();

    @Override
    public List<Repair> findAll() throws SQLException { return repairDao.findAll(); }
    @Override
    public List<Repair> findByStatus(Integer status) throws SQLException { return repairDao.findByStatus(status); }
    @Override
    public List<Repair> findByHouseId(Long houseId) throws SQLException { return repairDao.findByHouseId(houseId); }
    @Override
    public Repair findById(Long id) throws SQLException { return repairDao.findById(id); }
    @Override
    public int insert(Repair repair) throws SQLException { return repairDao.insert(repair); }
    @Override
    public int assignWorker(Long id, Long workerId) throws SQLException { return repairDao.assignWorker(id, workerId); }
    @Override
    public int completeRepair(Long id) throws SQLException { return repairDao.completeRepair(id); }
    @Override
    public int deleteById(Long id) throws SQLException { return repairDao.deleteById(id); }
}
