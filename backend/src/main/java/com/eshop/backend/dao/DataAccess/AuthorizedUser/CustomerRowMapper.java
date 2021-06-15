package com.eshop.backend.dao.DataAccess.AuthorizedUser;

import com.eshop.backend.dao.Models.AuthorizedUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<AuthorizedUser> {

    @Override
    public AuthorizedUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        AuthorizedUser authorizedUsers = new AuthorizedUser();
        authorizedUsers.setId(rs.getLong("id"));
        authorizedUsers.setUserLogin(rs.getString("user_login"));
        authorizedUsers.setUserPassword(rs.getString("user_password"));
        authorizedUsers.setUserRole(rs.getString("user_role"));
        authorizedUsers.setUserStatus(rs.getString("user_status"));
        return authorizedUsers;

    }
}
