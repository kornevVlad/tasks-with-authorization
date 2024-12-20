package com.my.project.authentication.controller;

import com.my.project.authentication.dto.JwtRequest;
import com.my.project.authentication.service.AutUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AutUserController {

    private final AutUserService autUserService;

    @PostMapping()
    public ResponseEntity<?> createToken(@RequestBody JwtRequest jwtRequest) {
        return autUserService.verificationUser(jwtRequest);
    }
}