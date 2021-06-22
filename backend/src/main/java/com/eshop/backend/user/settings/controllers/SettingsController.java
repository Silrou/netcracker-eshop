package com.eshop.backend.user.settings.controllers;

import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.user.settings.services.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    SettingsService settingsService;

    @Autowired
    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping("/info/{login}")
    public ResponseEntity<AuthorizedUserModel> getById(@PathVariable("login")String login) {
        AuthorizedUserModel userInformation = settingsService.getByLogin(login);
        return new ResponseEntity<>(userInformation, HttpStatus.OK);
    }

}
