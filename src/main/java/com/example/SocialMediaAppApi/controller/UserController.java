package com.example.SocialMediaAppApi.controller;

import com.example.SocialMediaAppApi.request.UpdateUserRequest;
import com.example.SocialMediaAppApi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/allUsers")
    public String allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/profile/{id}")
    String oneUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/editProfile")
    String editUser(@RequestBody UpdateUserRequest request) {
        return userService.updateUser(request);
    }

}