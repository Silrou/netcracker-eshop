package com.eshop.backend.auth.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private LocalDateTime timestamp;
    private String message;

}
