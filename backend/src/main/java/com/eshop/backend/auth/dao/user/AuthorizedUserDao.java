package com.eshop.backend.auth.dao.user;


import com.eshop.backend.utils.CrudDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;

import java.util.List;

public interface AuthorizedUserDao extends CrudDao<AuthorizedUserModel> {

    AuthorizedUserModel getByLogin(String login);
    AuthorizedUserModel getByStatus(String status);
    List<AuthorizedUserModel> getAllUsers();
    List<AuthorizedUserModel> getAllManager();
    List<AuthorizedUserModel> getAllCourier();
    List<AuthorizedUserModel> getFilteredByStatusOn();
    List<AuthorizedUserModel> getFilteredByStatusOff();
//    List<AuthorizedUser> getByName();
//    List<AuthorizedUser> getBySurname();
//    List<AuthorizedUser> getByid();
    AuthorizedUserModel getRoleByLogin(String login);
    AuthorizedUserModel getByToken(String token);
    void createVerificationToken(AuthorizedUserModel user, String token);
}
