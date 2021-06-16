package com.eshop.backend.auth.dao.user;

import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorizedUserDaoImpl implements AuthorizedUserDao {

    private JdbcTemplate jdbcTemplate;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthorizedUserDaoImpl(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void create(AuthorizedUserModel user) {
        String SQL = "insert into users (user_login, user_password," +
                "user_role, user_status)\n" +
                "values (?,?,?,?)";
        try {
            jdbcTemplate.update(SQL, user.getUserLogin(), user.getUserPassword(),
                    user.getUserRole(), user.getUserStatus());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public AuthorizedUserModel getById(Long id) {
        return null;
    }

    @Override
    public AuthorizedUserModel getByLogin(String login) throws DataAccessException {
        try{
            String getUserSql = "SELECT id, user_login , user_password, user_role, user_status FROM users WHERE user_login = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), login);
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public AuthorizedUserModel getByStatus(String status) {
        return null;
    }

    @Override
    public AuthorizedUserModel getRoleByLogin(String login) {
        try{
            String getUserSql = "SELECT id, user_login , user_password, user_role, user_status FROM users WHERE user_login = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), login);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public AuthorizedUserModel getByToken(String token) {
        try{
            String getUserSql = "SELECT users.id, user_login , user_password, user_role, user_status from users\n" +
                    "left join email_token et on users.id = et.user_id\n" +
                    "WHERE et.token = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), token);
        } catch (DataAccessException e) {
            String str = e.toString();
            return null;
        }
    }

    @Override
    public void createVerificationToken(AuthorizedUserModel user, String token) {
        //save token
        String SQL = "update users set \"user_login\" = ?,\n" +
                "                   \"user_password\"= ?,\n" +
                "                   \"user_role\" = ?,\n" +
                "                   \"user_status\" = ?,\n" +
                "                   \"user_token\" = ?\n" +
                "                   where id = ?";
        try {
            jdbcTemplate.update(SQL, user.getUserLogin(), user.getUserPassword(),
                    user.getUserRole(), user.getUserStatus(), token, user.getId());
        } catch (Exception e) {
            String str = e.toString();
        }
    }


    @Override
    public List<AuthorizedUserModel> getAll() {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getAllUsers() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where userrole IN ('MANAGER','COURIER')";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("userrole"),
                rs.getString("username"),
                rs.getString("usersurname"),
                rs.getString("userregistrationdate"),
                rs.getString("userstatus"),
                rs.getString("useraddress"),
                rs.getString("usernumber"));
        return   jdbcTemplate.query(getAllAuthorizedUsersSQL,rowMapper);
    }

    @Override
    public List<AuthorizedUserModel> getAllManager() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where USERROLE='MANAGER'";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
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
    public List<AuthorizedUserModel> getAllCourier() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where USERROLE='COURIER'";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
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
    public List<AuthorizedUserModel> getFilteredByStatusOn() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where userstatus='ON' ";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
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
    public List<AuthorizedUserModel> getFilteredByStatusOff() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where userstatus='OFF' ";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
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

//    @Override
    public List<AuthorizedUserModel> getBySurname(String surname) {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where usersurname = "+ surname;

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
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

//    @Override
    public List<AuthorizedUserModel> getByid(long id) {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where id="+id;

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
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

//    @Override
    public List<AuthorizedUserModel> getByName(String name) {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where USERNAME="+name;

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
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
    public void update( AuthorizedUserModel user) {
        String SQL = "update users set \"user_status\" = ?\n" +
                "                   where id = ?";
        try {
            jdbcTemplate.update(SQL, user.getUserStatus(), user.getId());
        } catch (Exception e) {
            String str = e.toString();
        }
    }


    public void delete(Long id) {
        return ;
    }

}
