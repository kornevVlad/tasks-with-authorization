package com.my.project.user.dto;

import com.my.project.user.type_access.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private Role role;
}