package com.example.SocialMediaAppApi.controller;

import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.request.UserDetailsRequest;
import com.example.SocialMediaAppApi.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addDetails")
@AllArgsConstructor
public class UserDetailsController {

    private UserDetailsService userDetailsService;

    @PostMapping
    public String addUserDetails(@RequestBody UserDetailsRequest request){
        return userDetailsService.addDetails(request);

    }

    @GetMapping("/profile")
    List<User> allUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/profile/{id}")
    User oneUser(@PathVariable Long id) {

        return userRepository.getById(id);
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
