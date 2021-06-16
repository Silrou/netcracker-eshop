package com.eshop.backend.dao.DataAccess.controllers;


import com.eshop.backend.dao.models.AuthorizedUser;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class AdminControllerImp implements AdminController {


    @Override
    public void create(AuthorizedUser model) {
        // create user
    }

    @Override
    public AuthorizedUser getById(Long id) {
        return null;
    }

    @Override
    public List<AuthorizedUser> getAll() {
        return Collections.emptyList();
    }

    @Override
    public void update(AuthorizedUser model) {
        // update user
    }

    @Override
    public void delete(Long id) {
        // delete user
    }


    @Override
    public AuthorizedUser getManagerDataByLogin(String login) {
        return null;
    }

    @Override
    public AuthorizedUser updateManagerDataByLogin(String login) {
        return null;
    }

    @Override
    public AuthorizedUser getManagerDataById(int id) {
        return null;
    }

    @Override
    public AuthorizedUser updateManagerDataByID(int id) {
        return null;
    }

    @Override
    public AuthorizedUser getCourierDataByLogin(String login) {
        return null;
    }

    @Override
    public AuthorizedUser updateCourierDataByLogin(String login) {
        return null;
    }

    @Override
    public AuthorizedUser getCourierDataById(int id) {
        return null;
    }

    @Override
    public AuthorizedUser updateCourierDataById(int id) {
        return null;
    }
}
