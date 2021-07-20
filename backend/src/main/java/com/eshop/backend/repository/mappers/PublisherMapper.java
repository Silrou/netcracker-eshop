package com.eshop.backend.repository.mappers;

import com.eshop.backend.model.PublisherModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherMapper implements RowMapper<PublisherModel> {

    public static final String SELECT_SQL = "select id, publishername from publisher ";

    @Override
    public PublisherModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("publishername");
        return new PublisherModel(id, name);
    }
}
