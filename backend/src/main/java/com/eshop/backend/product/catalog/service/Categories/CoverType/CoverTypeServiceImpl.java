package com.eshop.backend.product.catalog.service.Categories.CoverType;

import com.eshop.backend.dao.DataAccess.Categories.CoverType.CoverTypeDao;
import com.eshop.backend.dao.Models.CoverType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverTypeServiceImpl implements CoverTypeService{

    CoverTypeDao coverTypeDao;

    @Autowired
    public CoverTypeServiceImpl (CoverTypeDao coverTypeDao) {this.coverTypeDao = coverTypeDao;}

    @Override
    public void create(CoverType model) {

    }

    @Override
    public CoverType getById(Long id) {
        return coverTypeDao.getById(id);
    }

    @Override
    public List<CoverType> getAll() {
        return coverTypeDao.getAll();
    }

    @Override
    public void update(CoverType model) {

    }

    @Override
    public void delete(Long id) {

    }
}
