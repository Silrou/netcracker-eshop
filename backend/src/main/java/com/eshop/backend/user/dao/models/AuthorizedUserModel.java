package com.eshop.backend.user.dao.models;

<<<<<<< HEAD
import lombok.*;

import java.sql.Date;

@Data
@Builder
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
>>>>>>> develop
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedUserModel {
    private Long id;
    private String userLogin;
    private String userPassword;
    private String userRole;
<<<<<<< HEAD
    private String userStatus;
    private String userName;
    private String userSurname;
    private Date userRegistrationDate;
=======
    private String userName;
    private String userSurname;
    private String userRegistrationDate;
    private String userStatus;
>>>>>>> develop
    private String userAddress;
    private String userNumber;


<<<<<<< HEAD
=======


>>>>>>> develop
    public AuthorizedUserModel(String userLogin, String userPassword, String userRole, String userStatus) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userStatus = userStatus;
<<<<<<< HEAD
        this.userRegistrationDate = new Date(new java.util.Date().getTime());
=======
>>>>>>> develop
    }
}
