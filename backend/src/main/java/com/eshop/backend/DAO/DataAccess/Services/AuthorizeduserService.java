package com.eshop.backend.DAO.DataAccess.Services;

import com.eshop.backend.DAO.Models.AuthorizedUser;

import java.util.Collection;
import java.util.List;

public interface AuthorizeduserService {
    public Collection<AuthorizedUser> getAllUsers() ;
    public Collection<AuthorizedUser> getAllManager();
    public Collection<AuthorizedUser> getAllCourier();
    public List<AuthorizedUser> getFilteredByStatusOn();
    public  List<AuthorizedUser> getFilteredByStatusOff();
    public List<AuthorizedUser> getByName(String name);
    public  List<AuthorizedUser> getBySurname(String surname);
    public  List<AuthorizedUser> getByid(long id);

}
