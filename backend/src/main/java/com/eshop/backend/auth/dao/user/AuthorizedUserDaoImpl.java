package com.eshop.backend.auth.dao.user;

import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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

        String SQL = "insert into authorizeduser (userlogin, userpassword," +
                "userrole, username, usersurname, userregistrationdate, userstatus," +
                " useraddress, usernumber)\n" +
                "values (?,?,?,?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(SQL, user.getUserLogin(), bCryptPasswordEncoder.encode(user.getUserPassword()),
                    user.getUserRole(), user.getUserName(), user.getUserSurname(), user.getUserRegistrationDate(),
                    user.getUserStatus(), " ", " ");
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public AuthorizedUserModel getByLogin(String login) throws DataAccessException {
        try{
            String getUserSql = "SELECT * FROM authorizeduser WHERE userlogin = ?";
            AuthorizedUserModel user = jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), login);
            return user;
        } catch (DataAccessException e) {
            String str = e.toString();
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
            String getUserSql = "SELECT * FROM authorizeduser WHERE userlogin = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), login);
        } catch (DataAccessException e) {
            String srt = e.toString();
            return null;
        }
    }

    @Override
    public AuthorizedUserModel getByToken(String token) {
        try{
            String getUserSql = "SELECT a.id, a.userlogin , a.userpassword, a.userrole, a.username, " +
                    "a.userregistrationdate, a.userstatus, a.useraddress, a.usernumber from authorizeduser a\n" +
                    "left join verificationtoken t on a.id = t.authorizeduserid\n" +
                    "WHERE t.tokenvalue = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), token);
        } catch (DataAccessException e) {
            String str = e.toString();
            return null;
        }
    }

    @Override
    public void createVerificationToken(AuthorizedUserModel user, String token) {
        //save token
//        String SQL = "update users set \"user_login\" = ?,\n" +
//                "                   \"user_password\"= ?,\n" +
//                "                   \"user_role\" = ?,\n" +
//                "                   \"user_status\" = ?,\n" +
//                "                   \"user_token\" = ?\n" +
//                "                   where id = ?";
//        try {
//            jdbcTemplate.update(SQL, user.getEmail(), user.getPassword(), user.getRole(),
//                    user.getStatus(), token, user.getId());
//        } catch (Exception e) {
//            String str = e.toString();
//        }
    }


    @Override
    public AuthorizedUserModel getById(Long id) {
        try{
            String getUserSql = "SELECT * FROM authorizeduser WHERE id = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), id);
        } catch (DataAccessException e) {
            String srt = e.toString();
            return null;
        }
    }

    @Override
    public List<AuthorizedUserModel> getAll() {
        return null;
    }

    @Override
    public void update(AuthorizedUserModel user) {
        String SQL = "update authorizeduser set userlogin = ?, " +
                "userpassword = ?, userrole = ?, username = ?, userregistrationdate = ?, "+
                "userstatus = ?, useraddress = ?, usernumber = ? " +
                "where id = ?";

        try {
            jdbcTemplate.update(SQL, user.getUserLogin(), bCryptPasswordEncoder.encode(user.getUserPassword()),
                    user.getUserRole(), user.getUserName(), user.getUserRegistrationDate(),
                    user.getUserStatus(), user.getUserAddress(), user.getUserNumber(), user.getId());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public void delete(Long id) {

    }
}
