package com.eshop.backend.auth.controllers;

import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.DAO.Models.Role;
import com.eshop.backend.auth.dto.RegistationRequestDTO;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import com.eshop.backend.auth.jwt.JwtCreator;
import com.eshop.backend.auth.mail.EmailSenderService;
import com.eshop.backend.auth.validator.EmailValidator;
import com.eshop.backend.auth.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.eshop.backend.auth.jwt.SecurityConstants.HEADER_STRING;
import static com.eshop.backend.auth.jwt.SecurityConstants.TOKEN_PREFIX;

@Controller
public class RegistrationController {

    private final EmailValidator emailValidator;
    private final AuthorizedUserDao authorizedUsersDao;
    private final PasswordValidator passwordValidator;
    private final EmailSenderService emailSenderService;

    @Autowired
    public RegistrationController(EmailValidator emailValidator, AuthorizedUserDao authorizedUsersDao, PasswordValidator passwordValidator, EmailSenderService emailSenderService) {
        this.emailValidator = emailValidator;
        this.authorizedUsersDao = authorizedUsersDao;
        this.passwordValidator = passwordValidator;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/user/registration")
    public ResponseEntity<?> Registration(@RequestBody RegistationRequestDTO request) {

        AuthorizedUser user = authorizedUsersDao.getByLogin(request.getEmail());
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!"".equals(request.getEmail()) &&
                request.getEmail() != null &&
                emailValidator.isValid(request.getEmail()) &&
                passwordValidator.isValid(request.getPassword())) {

            user = new AuthorizedUser(request.getEmail(), request.getPassword(), Role.USER.name(), UUID.randomUUID().toString());
            authorizedUsersDao.create(user);

            emailSenderService.sendToEmail(user.getUserLogin());

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/user/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {

        if (confirmationToken != null) {
            AuthorizedUser user = authorizedUsersDao.getByStatus(confirmationToken);
            user.setUserStatus("verified");
            authorizedUsersDao.update(user);

            String token = JwtCreator.createJwt(user.getUserLogin());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HEADER_STRING, TOKEN_PREFIX + token);

            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
