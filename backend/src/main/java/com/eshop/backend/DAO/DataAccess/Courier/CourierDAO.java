package com.eshop.backend.DAO.DataAccess.Courier;

import com.eshop.backend.DAO.DataAccess.MainDao;
import com.eshop.backend.DAO.Models.AuthorizedUser;

public interface CourierDAO extends MainDao<AuthorizedUser> {
    AuthorizedUser getCourierDataByLogin(String login);
    AuthorizedUser updateCourierDataByLogin(String login);
    AuthorizedUser getCourierDataById(int id);
    AuthorizedUser updateCourierDataById(int id);
}
