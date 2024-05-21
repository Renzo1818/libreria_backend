package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dto.AuthResponse;
import com.project.libreria_backend.models.dto.CredentialsDTO;

public interface IAuth {
    AuthResponse login(CredentialsDTO credentialsDTO);
}
