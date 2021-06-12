package com.eshop.backend.auth.controllers;
import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDaoImpl;
import com.eshop.backend.auth.dto.LoginRequestDTO;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import com.eshop.backend.auth.exceptions.UserAlreadyExists;
import com.eshop.backend.auth.services.CaptchaService;
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

    @Autowired
    public LoginController(AuthorizedUserDaoImpl authorizedUserDao, BCryptPasswordEncoder bCryptPasswordEncoder, CaptchaService captchaService) {
        this.authorizedUserDao = authorizedUserDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.captchaService = captchaService;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequestDTO request) {
        try {
            //add to if statement
            boolean captchaVerified = captchaService.verify(request.getRecaptchaResponse());

            AuthorizedUser user = authorizedUserDao.getByLogin(request.getUserLogin());

            if (captchaVerified && user != null && bCryptPasswordEncoder.matches(request.getUserPassword(), user.getUserPassword())){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }


        } catch (Exception e) {
            throw new UserAlreadyExists();
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/user/role")
    public ResponseEntity<?> getSortedProduct(@RequestParam String login) {
        AuthorizedUser user = authorizedUserDao.getRoleByLogin(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//        @PostMapping("/user/login")
//    public ResponseEntity<?> authenticate(@RequestBody LoginRequestDTO request) {
//        try {
//            AuthorizedUser user = authorizedUserDao.getByLogin(request.getUserLogin());
//
//            throw new UserAlreadyExists();
//
////            if (user != null && bCryptPasswordEncoder.matches(request.getUserPassword(), user.getUserPassword())){
//////                return new ResponseEntity<>(user, HttpStatus.OK);
////            }
//
//        } catch (Exception e) {
//            throw new UserAlreadyExists();
////            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
////        return new ResponseEntity<>(HttpStatus.OK);
//
//    }


}
