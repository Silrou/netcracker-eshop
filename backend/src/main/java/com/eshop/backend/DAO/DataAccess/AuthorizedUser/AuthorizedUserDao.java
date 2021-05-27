package com.eshop.backend.DAO.DataAccess.AuthorizedUser;


import com.eshop.backend.DAO.DataAccess.MainDao;
import com.eshop.backend.DAO.Models.AuthorizedUser;

public interface AuthorizedUserDao extends MainDao<AuthorizedUser> {

    AuthorizedUser getByLogin(String login);
    AuthorizedUser getByStatus(String status);

}
