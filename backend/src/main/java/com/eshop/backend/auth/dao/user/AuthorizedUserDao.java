package com.eshop.backend.auth.dao.user;


import com.eshop.backend.utils.CrudDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;

public interface AuthorizedUserDao extends CrudDao<AuthorizedUserModel> {

    AuthorizedUserModel getByLogin(String login);
    AuthorizedUserModel getByStatus(String status);
    AuthorizedUserModel getRoleByLogin(String login);
    AuthorizedUserModel getByToken(String token);
    void createVerificationToken(AuthorizedUserModel user, String token);

}
