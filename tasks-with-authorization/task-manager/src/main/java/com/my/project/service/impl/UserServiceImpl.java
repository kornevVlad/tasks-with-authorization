package com.my.project.service.impl;

import com.my.project.utils.JwtTokenUtils;
import com.my.project.exception.ValidBedRequest;
import com.my.project.exception.ValidNotFound;
import com.my.project.dto.user.NewUserDto;
import com.my.project.dto.user.UserDto;
import com.my.project.dto.user.UserDtoJwt;
import com.my.project.mapper.UserMapper;
import com.my.project.model.User;
import com.my.project.repository.UserRepository;
import com.my.project.service.UserService;
import com.my.project.enums.role.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public UserDtoJwt createUser(NewUserDto newUserDto) {
        User user = userMapper.toUser(newUserDto);
        userRepository.save(user);
        String jwt = jwtTokenUtils.generateToken(user);
        return userMapper.toUserDtoJwt(jwt);
    }

    @Override
    public UserDto updateUserStatusOnlyAdmin(String role, String token, Long userId) {
        User user = validUser(userId);
        if (jwtTokenUtils.getRoles(token).equals(Role.ADMIN.toString())) {
            user.setRole(Role.ADMIN);
            log.info("Изменение доступа на ADMIN");
            userRepository.save(user);
        } else {
            throw new ValidBedRequest("Нет прав доступа для изменений");
        }
        return userMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsersOnlyAdmin(String token) {
        List<UserDto> usersDto = new ArrayList<>();
        if (jwtTokenUtils.getRoles(token).equals(Role.ADMIN.toString())) {
            List<User> users = userRepository.findAll();
            for (User user : users) {
                usersDto.add(userMapper.toUserDto(user));
            }
        }
        return usersDto;
    }


    private User validUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ValidNotFound("Изменяемый User не найден");
        }
        return user.get();
    }
}