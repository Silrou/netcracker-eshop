package com.eshop.backend.product.catalog.mapper;

import com.eshop.backend.dao.Models.CoverType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoverTypeMapper implements RowMapper<CoverType> {

    public static final String SELECT_SQL = "select id, covertypename from covertype ";

    @Override
    public CoverType mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("covertypename");
        return new CoverType(id, name);
    }
}
