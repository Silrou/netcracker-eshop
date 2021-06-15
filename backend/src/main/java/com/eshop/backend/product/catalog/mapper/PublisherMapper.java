package com.eshop.backend.product.catalog.mapper;
import com.eshop.backend.dao.Models.Publisher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherMapper implements RowMapper<Publisher>{

    public static final String SELECT_SQL = "select id, publishername from publisher ";

    @Override
    public Publisher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("publishername");
        return new Publisher(id, name);
    }
}
