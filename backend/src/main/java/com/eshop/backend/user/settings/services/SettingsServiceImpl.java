package com.eshop.backend.user.settings.services;


import com.eshop.backend.product.dao.ProductDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.user.dao.settings.SettingsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl implements SettingsService {

    SettingsDao settingsDao;

    @Autowired
    public SettingsServiceImpl(SettingsDao settingsDao) {
        this.settingsDao = settingsDao;
    }

    @Override
    public AuthorizedUserModel getByLogin(String login) {
        return settingsDao.getByLogin(login);
    }
}
