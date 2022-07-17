package com.example.SocialMediaAppApi.controller;

import com.example.SocialMediaAppApi.request.RegisterRequest;
import com.example.SocialMediaAppApi.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/register")
@AllArgsConstructor
public class RegisterController {
    private RegisterService registerService;

    @PostMapping
    public String register(@RequestBody RegisterRequest request){

        return registerService.register(request);
    }

}