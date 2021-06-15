package com.eshop.backend.dao.DataAccess.Categories.CoverType;

import com.eshop.backend.dao.Models.CoverType;
import com.eshop.backend.product.catalog.mapper.CoverTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoverTypeDaoImpl implements CoverTypeDao{

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(CoverType model) {

    }

    @Override
    public CoverType getById(Long id) {
        String sql = CoverTypeMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new CoverTypeMapper(), new Object[]{Long.valueOf(id)});
    }

    @Override
    public List<CoverType> getAll() {
        String sql = CoverTypeMapper.SELECT_SQL;
        return template.query(sql, new CoverTypeMapper());
    }

    @Override
    public void update(CoverType model) {

    }

    @Override
    public void delete(Long id) {

    }
}
