package com.my.project.service;

import com.my.project.dto.user.NewUserDto;
import com.my.project.dto.user.UserDto;
import com.my.project.dto.user.UserDtoJwt;

import java.util.List;

public interface UserService {

    UserDtoJwt createUser(NewUserDto newUserDto);

    UserDto updateUserStatusOnlyAdmin(String role, String token, Long userId);

    List<UserDto> getAllUsersOnlyAdmin(String token);
}
