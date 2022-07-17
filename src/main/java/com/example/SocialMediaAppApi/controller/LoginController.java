package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.request.LoginRequest;
import com.example.SocialMediaAppApi.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class LoginController {
    private LoginService loginService;

    @PostMapping
    public String login(@RequestBody LoginRequest request){

        return loginService.login(request);
    }

}
