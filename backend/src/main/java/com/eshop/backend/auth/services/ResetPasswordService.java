package com.eshop.backend.auth.services;

import com.eshop.backend.auth.dto.ForgotPasswordDTO;
import com.eshop.backend.auth.dto.ResetPasswordDTO;
import com.eshop.backend.auth.dto.ValidateResetTokenDTO;

public interface ResetPasswordService {
    void forgotPassword(ForgotPasswordDTO forgotPasswordDTO);

    boolean validateResetToken(ValidateResetTokenDTO validateResetTokenDTO);

    boolean resetPassword(ResetPasswordDTO resetPasswordDTO);
}
