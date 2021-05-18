package com.eshop.backend.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistationRequestDTO {

    private String firstName;
    private String lastName;
    private String email;

    private String password;

}
