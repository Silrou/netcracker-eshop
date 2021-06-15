package com.eshop.backend.product.catalog.mapper;


import com.eshop.backend.dao.Models.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    public static String SELECT_SQL = "select id, authorname from author ";

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("authorname");
        return new Author(id, name);
    }
}
