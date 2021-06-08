package com.eshop.backend.DAO.DataAccess.Admin;

import com.eshop.backend.DAO.DataAccess.Manager.ManagerController;
import com.eshop.backend.DAO.Models.AuthorizedUser;

public interface AdminDAO extends ManagerController {
    AuthorizedUser getManagerDataByLogin(String login);
    AuthorizedUser updateManagerDataByLogin(String login);
    AuthorizedUser getManagerDataById(int id);
    AuthorizedUser updateManagerDataByID(int id);
    AuthorizedUser getCourierDataByLogin(String login);
    AuthorizedUser updateCourierDataByLogin(String login);
    AuthorizedUser getCourierDataById(int id);
    AuthorizedUser updateCourierDataById(int id);


}
