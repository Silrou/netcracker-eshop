package com.eshop.backend.dao.DataAccess.Services;

import com.eshop.backend.dao.Models.AuthorizedUser;

import java.util.Collection;
import java.util.List;

public interface AuthorizeduserService {
    public List<AuthorizedUser> getAllUsers() ;
    public List<AuthorizedUser> getAllManager();
    public List<AuthorizedUser> getAllCourier();
    public List<AuthorizedUser> getFilteredByStatusOn();
    public  List<AuthorizedUser> getFilteredByStatusOff();
    public List<AuthorizedUser> getByName(String name);
    public  List<AuthorizedUser> getBySurname(String surname);
    public  List<AuthorizedUser> getByid(long id);

}
