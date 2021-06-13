package com.eshop.backend.dao.DataAccess.email_token;

import com.eshop.backend.dao.DataAccess.MainDao;
import com.eshop.backend.dao.models.AuthorizedUser;
import com.eshop.backend.dao.models.EmailToken;

public interface EmailTokenDao extends MainDao<EmailToken> {
    void createVerificationToken(AuthorizedUser user, EmailToken token);
    EmailToken getByToken(String token, String name);
    void deleteByValue(String token);
}
