package com.eshop.backend.dao.DataAccess.Categories.Genre;

import com.eshop.backend.dao.Models.Genre;
import com.eshop.backend.product.catalog.mapper.GenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(Genre model) {

    }

    @Override
    public Genre getById(Long id) {
        String sql = GenreMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new GenreMapper(), new Object[]{Long.valueOf(id)});
    }

    @Override
    public List getAll() {
        String sql = GenreMapper.SELECT_SQL;
        return template.query(sql, new GenreMapper());
    }

    @Override
    public void update(Genre model) {

    }

    @Override
    public void delete(Long id) {

    }
}
