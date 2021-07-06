package com.eshop.backend.auth.services;

import com.eshop.backend.auth.dto.LoginRequestDTO;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;

public interface LoginService {
    AuthorizedUserModel login(LoginRequestDTO request);
    AuthorizedUserModel getUserRole(String login);
}
