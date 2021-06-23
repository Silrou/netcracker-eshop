package com.eshop.backend.auth.dao.user;


import com.eshop.backend.utils.CrudDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;

import java.util.Collection;
import java.util.List;

public interface AuthorizedUserDao extends CrudDao<AuthorizedUserModel> {

    AuthorizedUserModel getByLogin(String login);
    AuthorizedUserModel getByStatus(String status);
    AuthorizedUserModel getRoleByLogin(String login);
    AuthorizedUserModel getByToken(String token);
    String getLoginById(Long id);
    void setStatus(AuthorizedUserModel user);
    void createVerificationToken(AuthorizedUserModel user, String token);

    List<AuthorizedUserModel> getAllUsers();

    List<AuthorizedUserModel> getAllManager();

    List<AuthorizedUserModel> getAllCourier();
}
