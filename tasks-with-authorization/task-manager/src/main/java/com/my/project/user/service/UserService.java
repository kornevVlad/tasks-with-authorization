package com.my.project.user.service;

import com.my.project.user.dto.NewUserDto;
import com.my.project.user.dto.UserDto;
import com.my.project.user.dto.UserDtoJwt;

import java.util.List;

public interface UserService {

    UserDtoJwt createUser(NewUserDto newUserDto);

    UserDto updateUserStatusOnlyAdmin(String role, String token, Long userId);

    List<UserDto> getAllUsersOnlyAdmin(String token);
}
