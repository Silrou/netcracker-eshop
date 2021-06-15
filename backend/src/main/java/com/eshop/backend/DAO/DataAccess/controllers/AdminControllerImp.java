package com.eshop.backend.dao.DataAccess.controllers;


import com.eshop.backend.dao.Models.AuthorizedUser;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminControllerImp implements AdminController {


    @Override
    public void create(AuthorizedUser model) {

    }

    @Override
    public AuthorizedUser getById(Long id) {
        return null;
    }

    @Override
    public List<AuthorizedUser> getAll() {
        return null;
    }

    @Override
    public void update(AuthorizedUser model) {

    }

    @Override
    public void delete(Long id) {

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
    public AuthorizedUser getManagerDataById(Long id) {
        return null;
    }

    @Override
    public AuthorizedUser updateManagerDataByID(Long id) {
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
    public AuthorizedUser getCourierDataById(Long id) {
        return null;
    }

    @Override
    public AuthorizedUser updateCourierDataById(Long id) {
        return null;
    }
}
