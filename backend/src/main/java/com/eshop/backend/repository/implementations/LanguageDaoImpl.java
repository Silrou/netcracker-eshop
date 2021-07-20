package com.eshop.backend.repository.implementations;

import com.eshop.backend.repository.mappers.LanguageMapper;
import com.eshop.backend.model.LanguageModel;
import com.eshop.backend.repository.interfaces.LanguageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageDaoImpl implements LanguageDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(LanguageModel model) {

    }

    @Override
    public LanguageModel getById(Long id) {
        String sql = LanguageMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new LanguageMapper(), new Object[]{Long.valueOf(id)});

    }

    @Override
    public List<LanguageModel> getAll() {
        String sql = LanguageMapper.SELECT_SQL;
        return template.query(sql, new LanguageMapper());
    }

    @Override
    public void update(LanguageModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}
