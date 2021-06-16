package com.eshop.backend.auth;
import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dao.user.AuthorizedUserDaoImpl;
import com.eshop.backend.auth.dto.LoginRequstDTO;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    private final AuthorizedUserDao authorizedUserdao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginController(AuthorizedUserDaoImpl authorizedUserdao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authorizedUserdao = authorizedUserdao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequstDTO request) {
        try {
            AuthorizedUserModel user = authorizedUserdao.getByLogin(request.getEmail());

            if (user != null && bCryptPasswordEncoder.matches(request.getPassword(), user.getUserPassword())){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/user/role")
    public ResponseEntity<?> getSortedProduct(@RequestParam String login) {
        AuthorizedUserModel user = authorizedUserdao.getRoleByLogin(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @GetMapping("/user/me")
//    public ResponseEntity<?> getSortedProduct(@RequestParam String login) {
//        AuthorizedUser user = authorizedUserdao.getRoleByLogin(login);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }


}
