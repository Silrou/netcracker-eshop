package com.eshop.backend.auth.exceptions;


public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User with this email already exist");
    }

}
