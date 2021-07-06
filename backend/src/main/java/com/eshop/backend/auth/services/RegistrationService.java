package com.eshop.backend.auth.services;

import com.eshop.backend.auth.dto.RegistationRequestDTO;

public interface RegistrationService {
    boolean registration(RegistationRequestDTO request);

    boolean confirmUserAccount(String confirmationToken);
}
