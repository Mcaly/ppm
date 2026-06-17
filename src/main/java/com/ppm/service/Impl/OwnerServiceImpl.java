package com.ppm.service.Impl;

import com.ppm.dao.OwnerDao;
import com.ppm.dao.Impl.OwnerDaoImpl;
import com.ppm.entity.Owner;
import com.ppm.service.OwnerService;

import java.sql.SQLException;
import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao = new OwnerDaoImpl();

    @Override
    public List<Owner> findAll() throws SQLException { return ownerDao.findAll(); }
    @Override
    public Owner findById(Long id) throws SQLException { return ownerDao.findById(id); }
    @Override
    public Owner findByPhoneAndIdCard(String phone, String idCard) throws SQLException { return ownerDao.findByPhoneAndIdCard(phone, idCard); }
    @Override
    public int insert(Owner owner) throws SQLException { return ownerDao.insert(owner); }
    @Override
    public int update(Owner owner) throws SQLException { return ownerDao.update(owner); }
    @Override
    public int deleteById(Long id) throws SQLException { return ownerDao.deleteById(id); }
}
