package com.eshop.backend.DAO.DataAccess.Services;

import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AuthorizeduserServiceImp implements AuthorizeduserService {
    AuthorizedUserDao authorizedUserDao;

    @Autowired
    public AuthorizeduserServiceImp(AuthorizedUserDao authorizedUserDao) {
        this.authorizedUserDao = authorizedUserDao;
    }

    @Override
    public Collection<AuthorizedUser> getAllUsers() {return authorizedUserDao.getAllUsers();    }

    @Override
    public Collection<AuthorizedUser> getAllManager() {return authorizedUserDao.getAllManager();}

    @Override
    public Collection<AuthorizedUser> getAllCourier() {return authorizedUserDao.getAllCourier();}

    @Override
    public List<AuthorizedUser> getFilteredByStatusOn() {
        return null;
    }

    @Override
    public List<AuthorizedUser> getFilteredByStatusOff() {
        return null;
    }

    @Override
    public List<AuthorizedUser> getByName(String name) {
        return null;
    }

    @Override
    public List<AuthorizedUser> getBySurname(String surname) {
        return null;
    }

    @Override
    public List<AuthorizedUser> getByid(long id) {
        return null;
    }
}
