package com.eshop.backend.courier.service;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;

@Service
public class CourierServiceImp implements CourierService {
    AuthorizedUserDao authorizedUserDao;

    public CourierServiceImp(AuthorizedUserDao authorizedUserDao) {this.authorizedUserDao = authorizedUserDao;}

    @Override
    public List<AuthorizedUserModel> getById(long id) {return null;}



}
