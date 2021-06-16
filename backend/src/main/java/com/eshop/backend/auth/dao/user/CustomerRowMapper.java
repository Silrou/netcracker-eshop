package com.eshop.backend.auth.dao.user;

import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<AuthorizedUserModel> {

    @Override
    public AuthorizedUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        AuthorizedUserModel authorizedUsersModel = new AuthorizedUserModel();
        authorizedUsersModel.setId(rs.getLong("id"));
        authorizedUsersModel.setUserLogin(rs.getString("user_login"));
        authorizedUsersModel.setUserPassword(rs.getString("user_password"));
        authorizedUsersModel.setUserRole(rs.getString("user_role"));
        authorizedUsersModel.setUserStatus(rs.getString("user_status"));
        return authorizedUsersModel;

    }
}
