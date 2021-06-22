package com.eshop.backend.user.dao.settings;


import com.eshop.backend.auth.dao.user.CustomerRowMapper;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SettingsDaoImpl implements SettingsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SettingsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public AuthorizedUserModel getByLogin(String login) {
        try{
            String getUserSql = "SELECT userlogin, username, " +
                    "usersurname, useraddress, usernumber FROM authorizeduser WHERE userlogin = ?";

            RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> AuthorizedUserModel.builder()
                    .userLogin(rs.getString("userlogin"))
                    .userName(rs.getString("username"))
                    .userSurname(rs.getString("usersurname"))
                    .userNumber(rs.getString("usernumber"))
                    .userAddress(rs.getString("useraddress"))
                    .build();
            AuthorizedUserModel user = jdbcTemplate.queryForObject(getUserSql, rowMapper, login);
            return user;
        } catch (DataAccessException e) {
            String str = e.toString();
            return null;
        }
    }

    @Override
    public void create(AuthorizedUserModel model) {

    }

    @Override
    public AuthorizedUserModel getById(Long id) {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getAll() {
        return null;
    }

    @Override
    public void update(AuthorizedUserModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}
