package com.eshop.backend.user.dao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedUserModel {
    private Long id;
    private String userLogin;
    private String userPassword;
    private String userRole;
    private String userName;
    private String userSurname;
    private String userRegistrationDate;
    private String userStatus;
    private String userAddress;
    private String userNumber;




    public AuthorizedUserModel(String userLogin, String userPassword, String userRole, String userStatus) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }
}
