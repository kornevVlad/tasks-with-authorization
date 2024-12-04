package com.my.project.mapper;

import com.my.project.dto.user.NewUserDto;
import com.my.project.dto.user.UserDto;
import com.my.project.dto.user.UserDtoJwt;
import com.my.project.model.User;
import com.my.project.enums.role.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(NewUserDto newUserDto) {
        User user = new User();
        user.setUserName(newUserDto.getUserName());
        user.setEmail(newUserDto.getEmail());
        user.setPassword(newUserDto.getPassword());
        user.setRole(Role.USER);
        return user;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public UserDtoJwt toUserDtoJwt(String jwt) {
        UserDtoJwt userDtoJwt = new UserDtoJwt();
        userDtoJwt.setJwtToken(jwt);
        return userDtoJwt;
    }
}