package com.eshop.backend.user.dao.models;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedUserModel {
    private Long id;
    private String userLogin;
    private String userPassword;
    private String userRole;
    private String userStatus;
    private String userName;
    private String userSurname;
    private Date userRegistrationDate;
    private String userAddress;
    private String userNumber;


    public AuthorizedUserModel(String userLogin, String userPassword, String userRole, String userStatus) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userStatus = userStatus;
        this.userRegistrationDate = new Date(new java.util.Date().getTime());
    }
}
