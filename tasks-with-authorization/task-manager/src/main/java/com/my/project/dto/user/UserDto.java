package com.my.project.dto.user;

import com.my.project.enums.role.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private Role role;
}