package com.eshop.backend.auth;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dao.email.EmailTokenDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.auth.dao.models.EmailTokenModel;
import com.eshop.backend.auth.security.Role;
import com.eshop.backend.auth.dto.RegistationRequestDTO;
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
    private final AuthorizedUserDao authorizedUsersdao;
    private final EmailTokenDao emailTokendao;
    private final PasswordValidator passwordValidator;
    private final EmailSenderService emailSenderService;

    @Autowired
    public RegistrationController(EmailValidator emailValidator, AuthorizedUserDao authorizedUsersdao,
                                  EmailTokenDao emailTokendao, PasswordValidator passwordValidator, EmailSenderService emailSenderService) {
        this.emailValidator = emailValidator;
        this.authorizedUsersdao = authorizedUsersdao;
        this.emailTokendao = emailTokendao;
        this.passwordValidator = passwordValidator;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> Registration(@RequestBody RegistationRequestDTO request) {

        AuthorizedUserModel user = authorizedUsersdao.getByLogin(request.getEmail());

        if (user != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (emailValidator.isValid(request.getEmail()) &&
                passwordValidator.isValid(request.getPassword())) {

            user = new AuthorizedUserModel(request.getEmail(), request.getPassword(), Role.USER.name(), Role.ANONYMOUS.name());
            authorizedUsersdao.create(user);
            user = authorizedUsersdao.getByLogin(user.getUserLogin());

            emailSenderService.sendEmail(user);

        } else new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/user/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {

        if (confirmationToken != null) {
            EmailTokenModel emailTokenModel = emailTokendao.getByToken(confirmationToken);
            AuthorizedUserModel user = authorizedUsersdao.getByToken(confirmationToken);
//            user.setStatus(Role.AUTHORIZED.name());
//            authorizedUsersdao.update(user);

            Calendar cal = Calendar.getInstance();

            if ((emailTokenModel.getExpiryDate().getTime() - cal.getTime().getTime()) >= 0) {
                user.setUserStatus(Role.AUTHORIZED.name());
                authorizedUsersdao.update(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }
//            String token = JwtCreator.createJwt(user.getEmail());
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HEADER_STRING, TOKEN_PREFIX + token);

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
