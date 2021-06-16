package com.eshop.backend.auth.dao.email;


import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.auth.dao.models.EmailTokenModel;
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
    public void createVerificationToken(AuthorizedUserModel user, EmailTokenModel token) {
        String SQL = "insert into email_token (token, expiry_date," +
                "user_id)\n" +
                "values (?,?,?)";
        try {
            jdbcTemplate.update(SQL, token.getToken(), token.getExpiryDate(), user.getId());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public EmailTokenModel getByToken(String token) {
        try{
            String getByTokenSql = "SELECT id, token , user_id, expiry_date FROM email_token WHERE token = ?";
            return jdbcTemplate.queryForObject(getByTokenSql, (rs, rowNum) ->
                    new EmailTokenModel(
                            rs.getLong("id"),
                            rs.getString("token"),
                            rs.getLong("user_id"),
                            rs.getDate("expiry_date")
                    ), token);
        } catch (DataAccessException e) {
            String str = e.toString();
            return null;
        }
    }

    @Override
    public void create(EmailTokenModel model) {

    }

    @Override
    public EmailTokenModel getById(Long id) {
        return null;
    }

    @Override
    public List<EmailTokenModel> getAll() {
        return null;
    }

    @Override
    public void update(EmailTokenModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}
