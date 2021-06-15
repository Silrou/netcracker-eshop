package com.eshop.backend.dao.DataAccess.Categories.Language;

import com.eshop.backend.dao.Models.Language;
import com.eshop.backend.product.catalog.mapper.LanguageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageDaoImpl implements LanguageDao{

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(Language model) {

    }

    @Override
    public Language getById(Long id) {
        String sql = LanguageMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new LanguageMapper(), new Object[]{Long.valueOf(id)});

    }

    @Override
    public List<Language> getAll() {
        String sql = LanguageMapper.SELECT_SQL;
        return template.query(sql, new LanguageMapper());
    }

    @Override
    public void update(Language model) {

    }

    @Override
    public void delete(Long id) {

    }
}
