package com.eshop.backend.DAO.DataAccess.AuthorizedUser;

import com.eshop.backend.DAO.Models.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorizedUserDaoImpl implements AuthorizedUserDao {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthorizedUserDaoImpl(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void create(AuthorizedUser user) {
    }

    @Override
    public AuthorizedUser getByLogin(String login) throws DataAccessException {
        return null;
    }

    @Override
    public AuthorizedUser getByStatus(String status) {
        return null;
    }


    @Override
    public AuthorizedUser getById(int id) {
        return null;
    }

    @Override
    public List<AuthorizedUser> getAll() {
        return null;
    }

    @Override
    public List<AuthorizedUser> getAllUsers() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER";

        RowMapper<AuthorizedUser> rowMapper = (rs,rowNum) -> new AuthorizedUser(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("getRole"),
                rs.getString("getName"),
                rs.getString("getSurname"),
                rs.getString("UserRegistrationDate"),
                rs.getString("UserStatus"),
                rs.getString("UserAddres"),
                rs.getString("UserNumber"));
        return   jdbcTemplate.query(getAllAuthorizedUsersSQL,rowMapper);
    }

    @Override
    public List<AuthorizedUser> getAllManager() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where USERROLE='ADMIN'";

        RowMapper<AuthorizedUser> rowMapper = (rs,rowNum) -> new AuthorizedUser(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("getRole"),
                rs.getString("getName"),
                rs.getString("getSurname"),
                rs.getString("UserRegistrationDate"),
                rs.getString("UserStatus"),
                rs.getString("UserAddres"),
                rs.getString("UserNumber"));
        return   jdbcTemplate.query(getAllAuthorizedUsersSQL,rowMapper);
    }

    @Override
    public List<AuthorizedUser> getAllCourier() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where USERROLE='ADMIN'";

        RowMapper<AuthorizedUser> rowMapper = (rs,rowNum) -> new AuthorizedUser(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("getRole"),
                rs.getString("getName"),
                rs.getString("getSurname"),
                rs.getString("UserRegistrationDate"),
                rs.getString("UserStatus"),
                rs.getString("UserAddres"),
                rs.getString("UserNumber"));
        return   jdbcTemplate.query(getAllAuthorizedUsersSQL,rowMapper);
    }

    ;

    @Override
    public void update(AuthorizedUser model) {

    }

    @Override
    public void delete(int id) {

    }
}
