package com.eshop.backend.auth.exceptions;

public class WrongEmailOrPasswordException extends RuntimeException {
    public WrongEmailOrPasswordException() {
        super("<h4>Wrong email or password</h4>\n" +
                "<p>Please check your email and password.</p>");
    }

}
