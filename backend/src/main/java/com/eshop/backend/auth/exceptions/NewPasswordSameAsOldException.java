package com.eshop.backend.auth.exceptions;

public class NewPasswordSameAsOldException extends RuntimeException{
    public NewPasswordSameAsOldException() {
        super("<h4>Error: new password invalid</h4>\n" +
                "<p>New Password cannot be same as previously used password</p>");
    }
}
