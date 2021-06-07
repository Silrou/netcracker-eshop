package com.eshop.backend.auth.controllers;

import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.DAO.DataAccess.EmailToken.EmailTokenDao;
import com.eshop.backend.DAO.Models.EmailToken;
import com.eshop.backend.DAO.Models.Role;
import com.eshop.backend.auth.dto.RegistationRequestDTO;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import com.eshop.backend.auth.mail.EmailSenderService;
import com.eshop.backend.auth.validator.EmailValidator;
import com.eshop.backend.auth.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
public class RegistrationController {

    private final EmailValidator emailValidator;
    private final AuthorizedUserDao authorizedUsersDao;
    private final EmailTokenDao emailTokenDao;
    private final PasswordValidator passwordValidator;
    private final EmailSenderService emailSenderService;

    @Autowired
    public RegistrationController(EmailValidator emailValidator, AuthorizedUserDao authorizedUsersDao,
                                  EmailTokenDao emailTokenDao, PasswordValidator passwordValidator, EmailSenderService emailSenderService) {
        this.emailValidator = emailValidator;
        this.authorizedUsersDao = authorizedUsersDao;
        this.emailTokenDao = emailTokenDao;
        this.passwordValidator = passwordValidator;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> Registration(@RequestBody RegistationRequestDTO request) {

        AuthorizedUser user = authorizedUsersDao.getByLogin(request.getEmail());

        if (user != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (emailValidator.isValid(request.getEmail()) &&
                passwordValidator.isValid(request.getPassword())) {

            user = new AuthorizedUser(request.getEmail(), request.getPassword(), Role.USER.name(), Role.ANONYMOUS.name());
            authorizedUsersDao.create(user);
            user = authorizedUsersDao.getByLogin(user.getEmail());

            emailSenderService.sendEmail(user);

        } else new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/user/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {

        if (confirmationToken != null) {
            EmailToken emailToken = emailTokenDao.getByToken(confirmationToken);
            AuthorizedUser user = authorizedUsersDao.getByToken(confirmationToken);
//            user.setStatus(Role.AUTHORIZED.name());
//            authorizedUsersDao.update(user);

            Calendar cal = Calendar.getInstance();

            if ((emailToken.getExpiryDate().getTime() - cal.getTime().getTime()) >= 0) {
                user.setStatus(Role.AUTHORIZED.name());
                authorizedUsersDao.update(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }
//            String token = JwtCreator.createJwt(user.getEmail());
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HEADER_STRING, TOKEN_PREFIX + token);

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
