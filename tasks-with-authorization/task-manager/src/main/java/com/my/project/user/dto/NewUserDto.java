package com.my.project.user.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NewUserDto {
    private String userName;
    private String email;
    private String password;
}
