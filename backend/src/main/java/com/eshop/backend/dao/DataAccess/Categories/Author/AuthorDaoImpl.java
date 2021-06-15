package com.eshop.backend.dao.DataAccess.Categories.Author;

import com.eshop.backend.dao.Models.Author;
import com.eshop.backend.product.catalog.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao{

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(Author model) {

    }

    @Override
    public Author getById(Long id) {
        String sql = AuthorMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new AuthorMapper(), new Object[]{Long.valueOf(id)});
    }

    @Override
    public List<Author> getAll() {
        String sql = AuthorMapper.SELECT_SQL;
        return template.query(sql, new AuthorMapper());
    }

    @Override
    public void update(Author model) {

    }

    @Override
    public void delete(Long id) {

    }
}
