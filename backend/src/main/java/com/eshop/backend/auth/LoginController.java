package com.eshop.backend.auth;

import com.eshop.backend.auth.exceptions.NeedMailConfirmationException;
import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dao.user.AuthorizedUserDaoImpl;
import com.eshop.backend.auth.dto.LoginRequestDTO;
import com.eshop.backend.auth.exceptions.NoUserWithThisEmailException;
import com.eshop.backend.auth.exceptions.WrongEmailOrPasswordException;
import com.eshop.backend.auth.services.LoginServiceIml;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.auth.services.CaptchaService;
import com.eshop.backend.auth.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    private final AuthorizedUserDao authorizedUserDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CaptchaService captchaService;
    private final LoginServiceIml loginServiceIml;

    @Autowired
    public LoginController(AuthorizedUserDaoImpl authorizedUserDao, BCryptPasswordEncoder bCryptPasswordEncoder, CaptchaService captchaService, LoginServiceIml loginServiceIml) {
        this.authorizedUserDao = authorizedUserDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.captchaService = captchaService;
        this.loginServiceIml = loginServiceIml;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequestDTO request) {
        AuthorizedUserModel user = loginServiceIml.login(request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/role")
    public ResponseEntity<?> getSortedProduct(@RequestParam String login) {
        AuthorizedUserModel user = authorizedUserDao.getRoleByLogin(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @GetMapping("/user/me")
//    public ResponseEntity<?> getSortedProduct(@RequestParam String login) {
//        AuthorizedUser user = authorizedUserdao.getRoleByLogin(login);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }


}
