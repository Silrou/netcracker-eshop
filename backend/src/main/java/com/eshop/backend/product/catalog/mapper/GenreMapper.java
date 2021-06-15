package com.eshop.backend.product.catalog.mapper;

import com.eshop.backend.dao.Models.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre>{

    public static final String SELECT_SQL = "select id, genrename from genre ";

    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("genrename");
        return new Genre(id, name);
    }
}
