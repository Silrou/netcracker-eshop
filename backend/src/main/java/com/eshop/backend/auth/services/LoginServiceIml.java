package com.eshop.backend.auth.services;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dao.user.AuthorizedUserDaoImpl;
import com.eshop.backend.auth.dto.LoginRequestDTO;
import com.eshop.backend.auth.exceptions.NeedMailConfirmationException;
import com.eshop.backend.auth.exceptions.NoUserWithThisEmailException;
import com.eshop.backend.auth.exceptions.WrongEmailOrPasswordException;
import com.eshop.backend.auth.utils.Role;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceIml implements LoginService {

    private final AuthorizedUserDao authorizedUserDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CaptchaService captchaService;

    @Autowired
    public LoginServiceIml(AuthorizedUserDaoImpl authorizedUserDao, BCryptPasswordEncoder bCryptPasswordEncoder, CaptchaService captchaService) {
        this.authorizedUserDao = authorizedUserDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.captchaService = captchaService;
    }

    @Override
    public AuthorizedUserModel login(LoginRequestDTO request) {
        boolean captchaVerified = captchaService.verify(request.getRecaptchaResponse());

        AuthorizedUserModel user = authorizedUserDao.getByLogin(request.getUserLogin());

        if (user == null) throw new NoUserWithThisEmailException();

        if (user.getUserStatus().equals(Role.INACTIVE.name())){
            throw new NeedMailConfirmationException();
        }
        if (captchaVerified && bCryptPasswordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
            return user;
        }
        else {
            throw new WrongEmailOrPasswordException();
        }
    }

}
