package com.my.project.dto.authentication;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}
