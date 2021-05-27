package com.eshop.backend.DAO.DataAccess.AuthorizedUser;

import com.eshop.backend.DAO.Models.AuthorizedUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<AuthorizedUser> {

    @Override
    public AuthorizedUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        AuthorizedUser authorizedUsers = new AuthorizedUser();
        authorizedUsers.setUserLogin(rs.getString("user_login"));
        authorizedUsers.setUserPassword(rs.getString("user_password"));
        authorizedUsers.setUserRole(rs.getString("user_role"));
        return authorizedUsers;

    }
}
