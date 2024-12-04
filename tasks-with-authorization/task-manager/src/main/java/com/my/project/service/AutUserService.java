package com.my.project.service;

import com.my.project.dto.authentication.JwtRequest;
import org.springframework.http.ResponseEntity;

public interface AutUserService {

    ResponseEntity<?> verificationUser(JwtRequest authRequest);
}
