package com.my.project.service.impl;

import com.my.project.dto.authentication.JwtRequest;
import com.my.project.dto.authentication.JwtResponse;
import com.my.project.service.AutUserService;
import com.my.project.exception.AppException;
import com.my.project.model.User;
import com.my.project.repository.UserRepository;
import com.my.project.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutUserServiceImpl implements AutUserService {

    private final UserRepository userRepository;

    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public ResponseEntity<?> verificationUser(JwtRequest jwtRequest) {
        try {
            validationParameters(jwtRequest);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppException(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        User user = getUserByEmail(jwtRequest.getEmail());
        String token = jwtTokenUtils.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void validationParameters(JwtRequest jwtRequest) {
        User user = getUserByEmail(jwtRequest.getEmail());
        if(user.getPassword().equals(jwtRequest.getPassword())) {
            log.info("Password прошел валидацию");
        } else {
            throw new RuntimeException();
        }
    }

    private User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException();
        }
        return user.get();
    }
}