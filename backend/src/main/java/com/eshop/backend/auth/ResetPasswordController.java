package com.eshop.backend.auth;

import com.eshop.backend.auth.services.ResetPasswordService;
import com.eshop.backend.auth.dto.ForgotPasswordDTO;
import com.eshop.backend.auth.dto.ResetPasswordDTO;
import com.eshop.backend.auth.dto.ValidateResetTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    @Autowired
    public ResetPasswordController(ResetPasswordService resetPasswordService) {
        this.resetPasswordService = resetPasswordService;
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        resetPasswordService.forgotPassword(forgotPasswordDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate-reset-token")
    public ResponseEntity<?> validateResetToken(@RequestBody ValidateResetTokenDTO validateResetTokenDTO) {
        if (resetPasswordService.validateResetToken(validateResetTokenDTO))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        if (resetPasswordService.resetPassword(resetPasswordDTO))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
