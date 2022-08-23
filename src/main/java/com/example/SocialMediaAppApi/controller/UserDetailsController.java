package com.example.SocialMediaAppApi.controller;

import com.example.SocialMediaAppApi.request.UserDetailsRequest;
import com.example.SocialMediaAppApi.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserDetailsController {

    private final  UserDetailsService userDetailsService;

    @PostMapping("/addDetails")
    public String addUserDetails(@RequestBody UserDetailsRequest request){
        return userDetailsService.addDetails(request);
    }
}