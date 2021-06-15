package com.eshop.backend.dao.DataAccess.controllers;

import com.eshop.backend.dao.DataAccess.MainDao;
import com.eshop.backend.dao.Models.AuthorizedUser;

public interface ManagerController extends MainDao<AuthorizedUser> {
    AuthorizedUser getManagerDataByLogin(String login);
    AuthorizedUser updateManagerDataByLogin(String login);
    AuthorizedUser getManagerDataById(Long id);
    AuthorizedUser updateManagerDataByID(Long id);

}
