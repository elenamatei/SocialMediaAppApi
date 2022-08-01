package com.example.SocialMediaAppApi.controller;

import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/allUsers")
    List<User> allUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/profile/{id}")
    User oneUser(@PathVariable Long id) {

        return userService.getUserById(id);
//                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/editProfile/{id}")
    User editUser(@RequestBody User updatedUser, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setGender(updatedUser.getGender());
                    user.setBirthDate(updatedUser.getBirthDate());

                    return userRepository.save(user);
                })
                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
                    return null;
                });
    }



}
