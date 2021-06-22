package com.eshop.backend.user.dao.settings;

import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.utils.CrudDao;

public interface SettingsDao extends CrudDao<AuthorizedUserModel> {
    AuthorizedUserModel getByLogin(String login);
}
