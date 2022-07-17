package com.example.SocialMediaAppApi.controller;

import com.example.SocialMediaAppApi.request.TestRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
@AllArgsConstructor
public class TestController {

    private TokenService tokenService;


    @PostMapping
    public String test(@RequestBody TestRequest request){

        Token newT = tokenService.verifyToken(request.getToken());
        if(newT!=null){
            return newT.toString();
        }
        else
            return "blabla";

    }

}
