package com.my.project.controller;

import com.my.project.dto.user.NewUserDto;
import com.my.project.dto.user.UserDto;
import com.my.project.dto.user.UserDtoJwt;
import com.my.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public UserDtoJwt createUser(@RequestBody NewUserDto newUserDto) {
        return userService.createUser(newUserDto);
    }

    @PatchMapping("/update/{role}")
    public UserDto updateUserStatusOnlyAdmin(@PathVariable String role,
                                    @RequestHeader("Task-User-Id") Long userId,
                                    @RequestHeader("Authorization") String token) {
        return userService.updateUserStatusOnlyAdmin(role, token, userId);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsersOnlyAdmin(@RequestHeader("Authorization") String token) {
        return userService.getAllUsersOnlyAdmin(token);
    }
}