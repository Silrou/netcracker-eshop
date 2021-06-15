package com.eshop.backend.dao.DataAccess.Categories.Publisher;

import com.eshop.backend.dao.Models.Publisher;
import com.eshop.backend.product.catalog.mapper.PublisherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherDaoImpl implements PublisherDao{

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(Publisher model) {

    }

    @Override
    public Publisher getById(Long id) {
        String sql = PublisherMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new PublisherMapper(), new Object[]{Long.valueOf(id)});
    }

    @Override
    public List<Publisher> getAll() {
        String sql = PublisherMapper.SELECT_SQL;
        return template.query(sql, new PublisherMapper());
    }

    @Override
    public void update(Publisher model) {

    }

    @Override
    public void delete(Long id) {

    }
}
