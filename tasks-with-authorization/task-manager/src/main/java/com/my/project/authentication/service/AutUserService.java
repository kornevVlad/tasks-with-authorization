package com.my.project.authentication.service;

import com.my.project.authentication.dto.JwtRequest;
import org.springframework.http.ResponseEntity;

public interface AutUserService {

    ResponseEntity<?> verificationUser(JwtRequest authRequest);
}
