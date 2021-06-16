package com.eshop.backend.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({UserAlreadyExistsException.class, NeedMailConfirmationException.class,
                        NoUserWithThisEmailException.class, NewPasswordSameAsOldException.class})
    public ResponseEntity<ErrorMessage> handleCityNotFoundException(
            Exception ex, WebRequest request) {

        ErrorMessage message = new ErrorMessage(
                LocalDateTime.now(),
                ex.getMessage());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }

}
