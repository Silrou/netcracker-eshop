package com.eshop.backend.DAO.DataAccess.AuthorizedUser;


import com.eshop.backend.DAO.DataAccess.MainDao;
import com.eshop.backend.DAO.Models.AuthorizedUser;

import java.util.List;

public interface AuthorizedUserDao extends MainDao<AuthorizedUser> {

    AuthorizedUser getByLogin(String login);
    AuthorizedUser getByStatus(String status);
    List<AuthorizedUser> getAllUsers();
    List<AuthorizedUser> getAllManager();
    List<AuthorizedUser> getAllCourier();
    List<AuthorizedUser> getFilteredByStatusOn();
    List<AuthorizedUser> getFilteredByStatusOff();
//    List<AuthorizedUser> getByName();
//    List<AuthorizedUser> getBySurname();
//    List<AuthorizedUser> getByid();
}
