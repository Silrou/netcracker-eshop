package com.eshop.backend.auth.DTO;

import lombok.Data;

@Data
public class LoginRequstDTO {
    private String userLogin;
    private String userPassword;
}
