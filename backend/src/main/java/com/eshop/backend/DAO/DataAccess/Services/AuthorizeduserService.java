package com.eshop.backend.DAO.DataAccess.Services;

import com.eshop.backend.DAO.Models.AuthorizedUser;

import java.util.Collection;

public interface AuthorizeduserService {
    public Collection<AuthorizedUser> getAllUsers() ;
    public Collection<AuthorizedUser> getAllManager();
    public Collection<AuthorizedUser> getAllCourier();
}
