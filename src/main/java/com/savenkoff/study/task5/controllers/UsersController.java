package com.savenkoff.study.task5.controllers;

import com.savenkoff.study.task4.UserService;
import com.savenkoff.study.task5.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userService.getAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }
}
