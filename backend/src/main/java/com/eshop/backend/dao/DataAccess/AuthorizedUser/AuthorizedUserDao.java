package com.eshop.backend.dao.DataAccess.AuthorizedUser;


import com.eshop.backend.dao.DataAccess.MainDao;
import com.eshop.backend.dao.Models.AuthorizedUser;

import java.util.List;

public interface AuthorizedUserDao extends MainDao<AuthorizedUser> {

    AuthorizedUser getByLogin(String login);
    AuthorizedUser getByStatus(String status);
    List<AuthorizedUser> getAllUsers();
    List<AuthorizedUser> getAllManager();
    List<AuthorizedUser> getAllCourier();
    List<AuthorizedUser> getFilteredByStatusOn();
    List<AuthorizedUser> getFilteredByStatusOff();
    List<AuthorizedUser> getByName(String name);
//    List<AuthorizedUser> getBySurname();
//    List<AuthorizedUser> getByid();
    AuthorizedUser getRoleByLogin(String login);
    AuthorizedUser getByToken(String token);
    void createVerificationToken(AuthorizedUser user, String token);
}
