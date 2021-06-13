package com.eshop.backend.dao.DataAccess.email_token;


import com.eshop.backend.dao.models.AuthorizedUser;
import com.eshop.backend.dao.models.EmailToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailTokenDaoImpl implements EmailTokenDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmailTokenDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createVerificationToken(AuthorizedUser user, EmailToken token) {
        String SQL = "insert into verificationtoken (tokenname, tokenvalue," +
                "tokenexpirydate, authorizeduserid)\n" +
                "values (?,?,?,?)";
        try {
            jdbcTemplate.update(SQL, token.getTokenName(), token.getTokenValue(),
                    token.getTokenExpiryDate(), token.getAuthorizedUserId());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public EmailToken getByToken(String token, String name) {
        try{
            String getByTokenSql = "SELECT id, tokenname , tokenvalue, tokenexpirydate, authorizeduserid FROM verificationtoken WHERE tokenvalue = ?" +
                    " AND tokenname = ?";
            return jdbcTemplate.queryForObject(getByTokenSql, (rs, rowNum) ->
                    new EmailToken(
                            rs.getLong("id"),
                            rs.getString("tokenname"),
                            rs.getString("tokenvalue"),
                            rs.getDate("tokenexpirydate"),
                            rs.getLong("authorizeduserid")
                    ), token, name);
        } catch (DataAccessException e) {
            String str = e.toString();
            return null;
        }
    }

    @Override
    public void deleteByValue(String token) {
        String SQL = "delete from verificationtoken where tokenvalue = ?";
        try {
            jdbcTemplate.update(SQL, token);
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public void create(EmailToken model) {

    }

    @Override
    public EmailToken getById(Long id) {
        return null;
    }

    @Override
    public List<EmailToken> getAll() {
        return null;
    }

    @Override
    public void update(EmailToken model) {

    }

    @Override
    public void delete(Long id) {

    }
}
