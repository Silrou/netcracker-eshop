package com.eshop.backend.auth.controllers;

import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.auth.DTO.RegistationRequestDTO;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import com.eshop.backend.auth.validator.EmailValidator;
import com.eshop.backend.auth.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrarionController {

    private final EmailValidator emailValidator;
    private final AuthorizedUserDao authorizedUsersDao;
    private final PasswordValidator passwordValidator;

    @Autowired
    public RegistrarionController(EmailValidator emailValidator, AuthorizedUserDao authorizedUsersDao, PasswordValidator passwordValidator) {
        this.emailValidator = emailValidator;
        this.authorizedUsersDao = authorizedUsersDao;
        this.passwordValidator = passwordValidator;
    }

    @PostMapping("/user/registration")
    public ResponseEntity<?> Registration(@RequestBody RegistationRequestDTO request) {

        if (!"".equals(request.getEmail()) &&
                request.getEmail() != null &&
                emailValidator.isValid(request.getEmail()) &&
                passwordValidator.isValid(request.getPassword())) {

            AuthorizedUser user = authorizedUsersDao.readByLogin(request.getEmail());
            if (user != null) {
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }

            user = new AuthorizedUser(request.getEmail(), request.getPassword(), "ROLE_USER", "STATUS");
            authorizedUsersDao.create(user);

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
