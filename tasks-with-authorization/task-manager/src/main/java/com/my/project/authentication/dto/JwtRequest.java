package com.my.project.authentication.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}
