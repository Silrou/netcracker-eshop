package com.eshop.backend.product.catalog.mapper;

import com.eshop.backend.dao.Models.Language;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageMapper implements RowMapper<Language>{

    public static final String SELECT_SQL = "select id, languagename from language ";

    @Override
    public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("languagename");
        return new Language(id, name);
    }
}
