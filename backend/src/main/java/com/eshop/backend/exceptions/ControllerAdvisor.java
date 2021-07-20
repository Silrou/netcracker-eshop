package com.eshop.backend.exceptions;

import com.eshop.backend.exceptions.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({UserAlreadyExistsException.class, NeedMailConfirmationException.class,
                        NoUserWithThisEmailException.class, NewPasswordSameAsOldException.class,
                        ChangeExistMailException.class, WrongEmailOrPasswordException.class,
                        OrderCartAmountException.class, CaptchaException.class})
    public ResponseEntity<ErrorMessageDTO> handleException(
            WebException ex, WebRequest request) {

        ErrorMessageDTO message = new ErrorMessageDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getProblemList());

        return new ResponseEntity<ErrorMessageDTO>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
